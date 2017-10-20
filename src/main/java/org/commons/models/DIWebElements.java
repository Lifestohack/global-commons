package org.commons.models;

public class DIWebElements {

	private String elementName = null;
	private WebElementSelectType selectType = null;
	private String locateType = null;
	private String elementValue = null;

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String getElementName() {
		return elementName;
	}

	public void setLocateType(String locateType) {
		this.locateType = locateType;
	}

	public String getLocateType() {
		return locateType;
	}

	public void setElementValue(String elementValue) {
		this.elementValue = elementValue;
	}

	public String getElementValue() {
		return elementValue;
	}

	public void setSelectType(WebElementSelectType selectType) {
		this.selectType = selectType;
	}

	public WebElementSelectType getSelectType() {
		return selectType;
	}
}
