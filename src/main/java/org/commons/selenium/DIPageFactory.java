package org.commons.selenium;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.commons.logger.DILogger;
import org.commons.models.DIMapWebElements;
import org.commons.models.DIWebElements;

public class DIPageFactory  {

	private static final Logger logger = LogManager.getLogger(DILogger.class);

	private HashMap<String, Object> pageObjects;
	
	@SuppressWarnings({ "unchecked" })
	public <T> T getPage(Class<T> clazz) {
		if (clazz == null) {
			logger.fatal("Did not expect null for the parameter clazz " + this.getClass().toString());
		}
		T pageObject = null;
		
		if(pageObjects == null) {
			pageObjects = new HashMap<String, Object>();
		}
		
		if (pageObjects.containsKey(clazz.getSimpleName())) {
			pageObject = (T) pageObjects.get(clazz.getSimpleName());
		}else{
			pageObject = instantiatePage(clazz);
			getSelectors(clazz);
			pageObjects.put(clazz.getSimpleName(), pageObject);
		}
		
		return pageObject;		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private <T> T instantiatePage(Class<T> clazz) {
		T pageObject = null;
		try {
			Constructor constructor = clazz.getConstructor();
			pageObject = (T) constructor.newInstance();
		} catch (Exception e) {
			logger.catching(e);
		}
		return pageObject;
	}

	public <T> T getSelectors(Class<T> clazz) {
		Map<String, DIWebElements> guiElementsMap = new HashMap<String, DIWebElements>();
		File file = new File(selectorToRead(clazz.getSimpleName())); //get path for each page to do
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(DIMapWebElements.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			DIMapWebElements empMap = (DIMapWebElements) jaxbUnmarshaller.unmarshal(file);
			guiElementsMap = empMap.getwebElements();

		} catch (JAXBException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return initializeSelectors(clazz, guiElementsMap);
	}

	public <T>  T initializeSelectors(Class<T> clazz, Map<String, DIWebElements> guiElementsMap) {

		for (Field field : clazz.getFields()) {
			if (isWebElement(field)) {
				if (containsKey(field.getName(), guiElementsMap)) {
					DIWebElements elementFromMap = guiElementsMap.get(field.getName());
					try {
						field.set(this, elementFromMap);
					} catch (Exception e) {
						logger.error(e.toString());
						e.printStackTrace();
					}

				}
			}
		}
		return null;

	}

	public static boolean containsKey(String key, Map<String, DIWebElements> guiElementsMap) {
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
	
	private String selectorToRead(String fileToRead) {
		return new File(DISetUp.getSelectorsPath(),"project1/"+ fileToRead).toString().concat(".xml");
	}

}
