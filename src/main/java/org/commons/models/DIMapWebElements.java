package org.commons.models;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.commons.selenium.DISelectorAdapter;

@XmlRootElement(name = "page")
public class DIMapWebElements {

	// private Map<String, DIWebElements> guielement;
	//
	// @XmlElement
	// @XmlJavaTypeAdapter(DISelectorAdapter.class)
	// public Map<String, DIWebElements> getwebElements() {
	// return guielement;
	// }
	//
	//
	//
	// public void setwebElements(Map<String, DIWebElements> guielement) {
	// this.guielement = guielement;
	// }

	@XmlElement
	@XmlJavaTypeAdapter(DISelectorAdapter.class)
	private HashMap<String, DIWebElements> pageMap;

	public DIMapWebElements() {
	}

	public DIMapWebElements(HashMap<String, DIWebElements> b) {
		pageMap = b;
	}

	public Map<String, DIWebElements> getwebElements() {
		return pageMap;
	}

}
