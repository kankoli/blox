package com.blox.framework.v0.util;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class ControlMetadata {
	
	private String tag;
	private Map<String, String> attributes;
	
	private ControlMetadata() {
		attributes = new HashMap<String, String>();
	}

	public String getTag() {
		return tag;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	static ControlMetadata fromNode(Node controlNode) {
		ControlMetadata control = new ControlMetadata();
		control.tag = controlNode.getNodeName();
		
		NamedNodeMap attrs = controlNode.getAttributes();		
		for (int i = 0; i < attrs.getLength(); i++) {
			Node attr = attrs.item(i);
			control.attributes.put(attr.getNodeName(), attr.getNodeValue());
		}
		
		return control;
	}
}
