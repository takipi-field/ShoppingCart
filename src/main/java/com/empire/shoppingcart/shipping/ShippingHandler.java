package com.empire.shoppingcart.shipping;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.empire.mockdata.generate.utils.RandomUtil;
import com.empire.shoppingcart.manager.ShippingManager;
 
public class ShippingHandler {
 
	private static final Logger log = LoggerFactory.getLogger(ShippingHandler.class);
	private static final DateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
	private static final String TEMP_DIRECTORY = "temp";
	private static final String METHOD_NAME = "processShipment";
	
	private static long shippingStoredDate = 0;
		
	public synchronized void handle(String customerNumber, String orderNumber) {
		String noOfShipments = getNumberOfShipments(orderNumber);
		if (Integer.parseInt(noOfShipments) != 0) {
			for (int shipNumber = 1; shipNumber <= Integer.parseInt(noOfShipments); shipNumber++) {
				ShippingManager shipManager = new ShippingManager();
				String shipmentDetails = shipManager.getShipmentDetails();
				log.debug("Shippment details: {}", shipmentDetails);

				handleShipment(customerNumber, orderNumber, shipmentDetails, shipNumber + "");
			}
		}
	}

	private String getNumberOfShipments(String orderNumber) {
		Date nowDate = Calendar.getInstance().getTime();
		long now = Long.parseLong(simpleDateFormat.format(nowDate));
		log.debug("Stored Date is {}: ", shippingStoredDate);
		if (shippingStoredDate == 0 || shippingStoredDate != now) {
			shippingStoredDate = now;
			log.info("Shipping Stored Date set to: {}", shippingStoredDate);
			log.info("Lets get the number of Shipments ...");
			int noOfShipments = RandomUtil.getRandomNumberInRange(5, 20);
			log.info("Number of Shipments for Order {} is {}", orderNumber, noOfShipments);
			return noOfShipments + "";
		} else {
			log.info("Already created shipments for today.");
			return "0";
		}
	}

    private void handleShipment(String customerNumber, String orderNumber, String shipmentDetails, String shipNumber) {
    	File sourceFile = null;
    	File classFile = null;
    	try {
    		File dir = new File(TEMP_DIRECTORY);
    		if (!dir.exists()) {
    			dir.mkdir();
    		}
 
			sourceFile = createFile(TEMP_DIRECTORY + "/" + shipmentDetails + ".java");
			classFile = createFile(TEMP_DIRECTORY + "/" + shipmentDetails + ".class");
	        requestShipment(sourceFile, customerNumber, orderNumber, shipmentDetails, shipNumber);
    	} catch (Exception e) {
    		log.error("Error in processing shipment", e);
    	} finally {
    		if (sourceFile != null && sourceFile.exists()) {
    			sourceFile.delete();
    		}
    		if (classFile != null && classFile.exists()) {
    			classFile.delete();
    		}
    	}
    }

    private String getSourceCode(String classname, String shipmentDetails, String shipNumber) {
    	String sourceCode = 
			"import org.slf4j.Logger;\n" + 
			"import org.slf4j.LoggerFactory;\n" + 
			"\n" + 
			"import com.empire.mockdata.generate.utils.RandomUtil;\n" + 
			"import com.empire.shoppingcart.domain.Customer;\n" + 
			"import com.empire.shoppingcart.domain.Order;\n" + 
			"import com.empire.shoppingcart.manager.CustomerManager;\n" + 
			"import com.empire.shoppingcart.manager.OrderManager;\n" + 
			"import com.empire.shoppingcart.util.ExceptionListReader;\n" + 
			"\n" + 
        	"public class " + classname + " {\n" + 
			"\n" + 
			"	private final Logger log = LoggerFactory.getLogger(this.getClass());\n" + 
			"	\n" + 
			"	@SuppressWarnings(\"unchecked\")\n" + 
			"	public void " + METHOD_NAME + "(String customerNumber, String orderNumber, String shipmentDetails, String shipNumber) {	\n" + 
			"		CustomerManager custManager = new CustomerManager();\n" + 
			"		Customer customer = custManager.getCustomer(customerNumber);		\n" + 
			"		log.info(\"Processing Shipment for {} ...\", customer.getAccountNumber());\n" + 
			"		\n" + 
			"		OrderManager orderManager = new OrderManager();\n" + 
			"		Order order = orderManager.getOrder(orderNumber, customerNumber);\n" + 
			"		\n" + 
			"		try {			\n" + 
			"			int i = RandomUtil.getRandomNumberInRange(1, ExceptionListReader.getInstance().size() + 1);\n" + 
			"			String className = (String) ExceptionListReader.getInstance().get(i + \"\");\n" + 
			"			Class<Throwable> c = (Class<Throwable>) Class.forName(className);\n" + 
			"			process(customer, order, shipmentDetails, shipNumber, c);\n" + 
			"		}\n" + 
			"		catch (Throwable e) {\n" + 
			"			log.error(\"Error occured processing order\", e);\n" + 
			"		}\n" + 
			"		log.info(\"Completed processing shipment {}\", order.getOrderNumber());\n" + 
			"	}\n" + 
			"	\n" + 
			"	private <T extends Throwable> void process(Customer customer, Order order, \n" + 
			"			String shipmentDetails, String shipNumber, Class<T> exceptionType) throws Exception, T {\n" + 
			"		\n" + 
			"	    String shippingMessage = \"Exception while processing Order \" + order.getOrderNumber();\n" + 
			"	    log.debug(\"Customer is: {} ShipNumber is {}\", customer.getAccountNumber(), shipNumber);\n" + 
			"	    throw exceptionType.getConstructor(String.class).newInstance(shippingMessage);\n" + 
			"	}\n" + 
			"}";
    	return sourceCode;
    }

	private File createFile(String sourceFileName) throws IOException {
	    FileWriter fw = new FileWriter(sourceFileName);
	    fw.close();
	    File file = new File(sourceFileName);
	    return file;
    }
    
    private void requestShipment(File sourceFile, String customerNumber, 
    		String orderNumber, String shipmentDetails, String shipNumber) throws Exception {
        String classname = sourceFile.getName().split("\\.")[0];
        
        String sourceCode = getSourceCode(classname, shipmentDetails, shipNumber);

        // write the source code into the source file
        FileWriter writer = new FileWriter(sourceFile);
        writer.write(sourceCode);
        writer.close();
        
        // compile the source file
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        File parentDirectory = sourceFile.getParentFile();
        fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(parentDirectory));
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(sourceFile));
        compiler.getTask(null, fileManager, null, Collections.singleton("-g"), null, compilationUnits).call();
        fileManager.close();

        // load the compiled class
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { parentDirectory.toURI().toURL() });
        Class<?> Klass = classLoader.loadClass(classname);
        
        // For some reason I am unable to use ... Method method = Klass.getDeclaredMethod(methodname);
        //However the below loop works.
        Method[] methods = Klass.getMethods();
        for (Method method : methods) {
			if (method.getName().equalsIgnoreCase(METHOD_NAME)) {
				method.invoke(Klass.newInstance(), customerNumber, orderNumber, shipmentDetails, shipNumber);
			}
		}
    }
}
