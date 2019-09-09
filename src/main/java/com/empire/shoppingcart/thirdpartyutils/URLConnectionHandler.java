package com.empire.shoppingcart.thirdpartyutils;

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
import com.empire.shoppingcart.manager.ThirdPartyManager;
 
public class URLConnectionHandler {
 
	private static final Logger log = LoggerFactory.getLogger(URLConnectionHandler.class);
	private static final DateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMdd");
	private static final String TEMP_DIRECTORY = "temp";
	private static final String METHOD_NAME = "connect";
	
	private static long connectionsStoredDate = 0;
		
	public synchronized void handle(String customerNumber, String orderNumber, String connectString) {
		String noOfConnections = getNumberOfConnections(orderNumber);
		if (Integer.parseInt(noOfConnections) != 0) {
			for (int connectionNumber = 1; connectionNumber <= Integer.parseInt(noOfConnections); connectionNumber++) {
				ThirdPartyManager thirdPartyManager = new ThirdPartyManager();
				String connectionDetails = thirdPartyManager.getConnectionDetails();
				log.debug("Connection details: {}", connectionDetails);

				handleConnections(customerNumber, orderNumber, connectionDetails, connectString);
			}
		}
	}

	private String getNumberOfConnections(String orderNumber) {
		Date nowDate = Calendar.getInstance().getTime();
		long now = Long.parseLong(simpleDateFormat.format(nowDate));
		log.debug("Stored Date is {}: ", connectionsStoredDate);
		if (connectionsStoredDate == 0 || connectionsStoredDate != now) {
			connectionsStoredDate = now;
			log.info("Connection Stored Date set to: {}", connectionsStoredDate);
			log.info("Lets get the number of Connection ...");
			int noOfConnections = RandomUtil.getRandomNumberInRange(5, 20);
			log.info("Number of Connections for Order {} is {}", orderNumber, noOfConnections);
			return noOfConnections + "";
		} else {
			log.info("Already created connections for today.");
			return "0";
		}
	}

    private void handleConnections(String customerNumber, String orderNumber, String connectionDetails, String connectString) {
    	File sourceFile = null;
    	File classFile = null;
    	try {
    		File dir = new File(TEMP_DIRECTORY);
    		if (!dir.exists()) {
    			dir.mkdir();
    		}
 
			sourceFile = createFile(TEMP_DIRECTORY + "/" + connectionDetails + ".java");
			classFile = createFile(TEMP_DIRECTORY + "/" + connectionDetails + ".class");
	        requestConnection(sourceFile, customerNumber, orderNumber, connectionDetails, connectString);
    	} catch (Exception e) {
    		log.error("Error in processing connections", e);
    	} finally {
    		if (sourceFile != null && sourceFile.exists()) {
    			sourceFile.delete();
    		}
    		if (classFile != null && classFile.exists()) {
    			classFile.delete();
    		}
    	}
    }

    private String getSourceCode(String classname, String connectionDetails, String connectString) {
    	String sourceCode = 
			"import java.io.BufferedReader;\n" + 
			"import java.io.IOException;\n" + 
			"import java.io.InputStreamReader;\n" + 
			"import java.net.URL;\n" + 
			"import java.net.URLConnection;\n" + 
			"\n" + 
			"import org.slf4j.Logger;\n" + 
			"import org.slf4j.LoggerFactory;\n" + 
			"\n" + 
			"import com.empire.mockdata.generate.utils.RandomUtil;\n" + 
			"\n" + 
			"public class " + classname + " {\n" + 
			"\n" + 
			"	private final Logger log = LoggerFactory.getLogger(this.getClass());\n" + 
			"\n" + 
			"	public void " + METHOD_NAME + "(String customerNumber, String orderNumber, String connectionDetails, String connectString) {	\n" + 
			"		//Fail 1 in 10 attempts - take 10 seconds to connect and fail.\n" + 
			"		int randomNumber = RandomUtil.getRandomNumberInRange(2, 12);\n" + 
			"		int randomNumber2 = RandomUtil.getRandomNumberInRange(1, 20);\n" + 
			"		log.warn(\"Trying to connect to RestAPI {}. Random Number1 is {}, RandomNumber2 is {} \", connectString, randomNumber, randomNumber2);\n" + 
			"		if (randomNumber == 10) {\n" + 
			"			connect(connectString, 25 + randomNumber2);\n" + 
			"		} else {\n" + 
			"			connect(connectString, randomNumber);			\n" + 
			"		}\n" + 
			"	}\n" + 
			"	\n" + 
			"    private void connect(String connectString, int noOfErrors) {\n" + 
			"    	BufferedReader in = null;\n" + 
			"    	try {\n" + 
			"	        URL url = new URL(connectString + \"?noOfErrors=\" + noOfErrors);\n" + 
			"	        URLConnection yc = url.openConnection();\n" + 
			"	        in = new BufferedReader(new InputStreamReader(yc.getInputStream()));\n" + 
			"	        String inputLine;\n" + 
			"	        while ((inputLine = in.readLine()) != null) {\n" + 
			"	            log.info(inputLine);\n" + 
			"	        }\n" + 
			"    	} catch (Exception e) {\n" + 
			"    		log.error(\"An exception occured in calling an external Rest URL\", e);\n" + 
			"    	} finally {\n" + 
			"    		if (in != null) {\n" + 
			"    	        try {\n" + 
			"					in.close();\n" + 
			"				} catch (IOException e) {\n" + 
			"		    		log.error(\"IOException\", e);				}\n" + 
			"    		}\n" + 
			"    	}\n" + 
			"    }\n" + 
			"}";
    	return sourceCode;
    }

	private File createFile(String sourceFileName) throws IOException {
	    FileWriter fw = new FileWriter(sourceFileName);
	    fw.close();
	    File file = new File(sourceFileName);
	    return file;
    }
    
    private void requestConnection(File sourceFile, String customerNumber, 
    		String orderNumber, String connectionDetails, String connectString) throws Exception {
        String classname = sourceFile.getName().split("\\.")[0];
        
        String sourceCode = getSourceCode(classname, connectionDetails, connectString);

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
				method.invoke(Klass.newInstance(), customerNumber, orderNumber, connectionDetails, connectString);
			}
		}
    }
}