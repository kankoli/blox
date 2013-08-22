package com.blox.framework.v0.forms.xml;

import java.util.HashMap;
import java.util.Map;

public class Skin {
	private Map<String, Map<String, String>> values;
	private String id;
	
	public Skin() {
		values = new HashMap<String, Map<String,String>>();
	}
	
	public void put(String control, String key, String value) {
		Map<String, String> ctrlMap = values.get(control);
		if (ctrlMap == null) {
			ctrlMap = new HashMap<String, String>();
			values.put(control, ctrlMap);
		}
		ctrlMap.put(key, value);
	}
	
	public Map<String, String> get(String control) {
		return values.get(control);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
