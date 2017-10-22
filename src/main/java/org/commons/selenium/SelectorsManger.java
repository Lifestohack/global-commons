package org.commons.selenium;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.commons.models.DIWebElements;

public class SelectorsManger extends DIWebPageActions{

	Map<String, DIWebElements> guiElementsMap;
	
	public SelectorsManger(Class clazz){
		getXML();
		getSelectors(clazz);
	}
	
	public void getXML() {
		guiElementsMap = new HashMap<String, DIWebElements>();
		
		DIWebElements ds1 = new DIWebElements();
		DIWebElements ds2 = new DIWebElements();
		
		
		ds1.setElementName("diwas");
		ds1.setLocateType("xpath");
		ds1.setElementValue("/html/body/nav/div/div/ul/li[1]/a");
		
		
		ds2.setElementName("bhattarai");
		ds2.setLocateType("xpath");
		ds2.setElementValue("/html/body/nav/div/div/ul/li[2]/a");
		
		
		guiElementsMap.put("diwas", ds1);
		guiElementsMap.put("bhattarais", ds2);
	}
	
	
	public void getSelectors(Class clazz) {
		
	
		
		for (Field field : clazz.getFields()) {
			if(containsKey(field.getName()) ) {
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
	
	
	
//	tryd d = new tryd();
//	
//	
//	for (Field field : d.getClass().getFields()) {
//		System.out.println(field.getName());
//	}
//	
	
	
}
