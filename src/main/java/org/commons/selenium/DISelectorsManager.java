package org.commons.selenium;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.commons.logger.DILogger;
import org.commons.models.DIMapWebElements;
import org.commons.models.DIWebElements;

public class DISelectorsManager extends DIWebPageActions {

	private static final Logger logger = LogManager.getLogger(DILogger.class);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DISelectorsManager(Class clazz) {
		if (clazz == null) {
			logger.fatal("Did not expect null for the parameter clazz " + this.getClass().toString()
					+ ". Throwing the Exception.");
		}
		getSelectors(clazz);
	}

	public void getSelectors(Class<T> clazz) {
		Map<String, DIWebElements> guiElementsMap = new HashMap<String, DIWebElements>();
		File file = new File("src/test/resources/Selectors/NewTestPage.xml");
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(DIMapWebElements.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			DIMapWebElements empMap = (DIMapWebElements) jaxbUnmarshaller.unmarshal(file);
			guiElementsMap = empMap.getwebElements();

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		initializeSelectors(clazz, guiElementsMap);

	}

	public void initializeSelectors(Class<T> clazz, Map<String, DIWebElements> guiElementsMap) {

		for (Field field : clazz.getFields()) {
			if (isWebElement(field)) {
				if (containsKey(field.getName(), guiElementsMap)) {
					DIWebElements elementFromMap = guiElementsMap.get(field.getName());
					try {
						field.set(this, elementFromMap);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}

	}

	public boolean containsKey(String key, Map<String, DIWebElements> guiElementsMap) {
		boolean keyexists = false;
		DIWebElements value = guiElementsMap.get(key);
		if (value != null) {
			keyexists = true;
		}

		return keyexists;

	}

	private boolean isWebElement(Field field) {
		return field.getType().equals(DIWebElements.class);
	}

}
