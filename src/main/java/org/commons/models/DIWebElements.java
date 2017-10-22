package org.commons.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "guielement")
public class DIWebElements {

	private String name;
	private String locate;
	private String select;
	private String value;

	public void setElementName(String name) {
		this.name = name;
	}

	@XmlElement(name = "name")
	public String getElementName() {
		return name;
	}

	public void setLocateType(String locate) {
		this.locate = locate;
	}

	@XmlElement(name = "locate")
	public String getLocateType() {
		return locate;
	}

	public void setSelectType(String select) {
		this.select = select;
	}

	@XmlElement(name = "select")
	public String getSelectType() {
		return select;
	}

	public void setElementValue(String value) {
		this.value = value;
	}

	@XmlElement(name = "value")
	public String getElementValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return "Element [name=" + name + ", locateType=" + locate
				+ ", selectType=" + select + ", elementValue=" + value + "]";
	}

}
