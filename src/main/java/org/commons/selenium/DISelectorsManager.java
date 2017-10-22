package org.commons.selenium;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.commons.models.DIMapWebElements;
import org.commons.models.DIWebElements;

public class DISelectorsManager extends DIWebPageActions {

	Map<String, DIWebElements> guiElementsMap;

	public DISelectorsManager(Class clazz) {
		getXML();
		getSelectors(clazz);
	}

	public void getXML() {
		guiElementsMap = new HashMap<String, DIWebElements>();

		File file = new File("src/main/resources/Selectors/NewTestPage.xml");

		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(DIMapWebElements.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			DIMapWebElements empMap = (DIMapWebElements) jaxbUnmarshaller.unmarshal(file);

//			for (String empId : empMap.getwebElements().keySet()) {
//				System.out.println(empMap.getwebElements().get(empId).getElementName());
//				System.out.println(empMap.getwebElements().get(empId).getElementValue());
//				
//				guiElementsMap.put(empMap.getwebElements().get(empId).getElementName(), (DIWebElements) empMap.getwebElements());
//			}
			
			guiElementsMap = empMap.getwebElements();
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		// guiElementsMap.put("diwas", ds1);
		// guiElementsMap.put("bhattarais", ds2);
	}

	public void getSelectors(Class clazz) {

		for (Field field : clazz.getFields()) {
			if (containsKey(field.getName())) {
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

	public boolean containsKey(String key) {
		boolean keyexists = false;
		DIWebElements value = guiElementsMap.get(key);
		if (value != null) {
			keyexists = true;
		}

		return keyexists;

	}

	// tryd d = new tryd();
	//
	//
	// for (Field field : d.getClass().getFields()) {
	// System.out.println(field.getName());
	// }
	//

}
