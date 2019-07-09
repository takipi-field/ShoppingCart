package com.empire.shoppingcart.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.empire.shoppingcart.dao.SkuDAO;
import com.empire.shoppingcart.dao.impl.SkuDAOImpl;
import com.empire.shoppingcart.domain.SKU;
import com.empire.shoppingcart.thirdpartyutils.AmazonUtils;

public class SkuManager {

	private static final Logger log = LoggerFactory.getLogger(SkuManager.class);
	SkuDAO skuDAO = new SkuDAOImpl();

	public SKU get(String skuNumber) {
		log.info("Getting the Sku for SkuNumber: {}", skuNumber);
		return skuDAO.get(skuNumber);
	}

	public SKU generate() {
		log.info("Generating a Sku.");
		return skuDAO.generate();
	}

	public void update(SKU sku) {
		log.info("Updating a Sku.");
		skuDAO.update(sku);
	}
	
	public void updateModel(SKU sku) {
		log.info("Updating a Model.");
		String skuType = AmazonUtils.getSkuType(sku);
		log.info("Getting SkuType from Amazon");
		sku.setSkuType(skuType);
		log.info("Updating SkuType");
		skuDAO.update(sku);
		log.info("Update Sku Completed.");
	}
	
	public void delete(SKU sku) {
		log.info("Deleting the Sku");
		skuDAO.delete(sku);
		log.info("Successfully deleted the Sku");
	}

	public SKU updateSkuType(SKU sku) {
		log.info("Updating the Sku");
		try {
			if (sku.getSkuType().equalsIgnoreCase("COLOR")) {
				log.info("Sku Type is COLOR. Need to update it.");
				sku.setSkuType("COLOR_INDICATOR");
				sku.setId(new Integer(sku.getSkuNumber()));
				log.info("New Sku Type is: {}", sku.getSkuType());
			}
		} catch (Exception e) {
			//NeedToDo: Handle exception when we get an incorrect SKU type
			// For now, Swallow it ... continue.
		}
		log.info("Sucessfully updated the Sku");
		return sku;
	}

	public void generateSkuAndChangeSkuTypeColor() {
		try {
			log.info("Inside generateSkuAndChangeSkuTypeColor");
			SKU sku = generate();
			log.info("generateSkuAndChangeSkuTypeColor: New Sku Created. Sku Type is: {}", sku.getSkuType());
			updateSkuType(sku);
			update(sku);
			log.info("Completed Updating sku.");
		} catch (Exception e) {
			//NeedToDo: Handle exception when we get an incorrect SKU type
			// For now, Swallow it ... continue.
		}
	}

	public void deleteSkuManufacturer() {
		log.info("First, lets generate a new Sku");
		SKU sku = generate();
		sku.setSkuType("MANUFACTURER");
		log.info("deleteSkuManufacturer: Sku Created. Type is: {}", sku.getSkuType());

		log.info("Now, lets delete the sku ...");
		delete(sku);
		log.info("Completed deleting sku.");
	}
	
	public void updateSkuModel() {
		log.info("Generating new Sku");
		SKU sku = generate();
		sku.setSkuType("MODEL");
		log.info("New Sku Created. Sku Type is: {}", sku.getSkuType());

		log.info("Deleting the sku ...");
		updateModel(sku);
		log.info("Completed deleting sku.");
	}
}