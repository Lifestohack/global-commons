package org.commons.selenium;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.apache.logging.log4j.message.Message;
import org.commons.models.DIWebElements;

public class DISelectorAdapter extends XmlAdapter<DISelectorAdapter.GUIElements, Map<String, DIWebElements>>{

	public static class GUIElements {
	    @XmlElement(name="guielement")
	    public DIWebElements[] pageMap;
	}

	@Override
	public Map<String, DIWebElements> unmarshal(GUIElements value) throws Exception {
		   Map<String, DIWebElements> map = new HashMap<String, DIWebElements>();
	        for( DIWebElements msg : value.pageMap )
	            map.put( msg.getElementName(), msg );
	        return map;
	}

	@Override
	public GUIElements marshal(Map<String, DIWebElements> v) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	

}
