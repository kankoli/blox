package com.blox.framework.v0.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class FormMetadata {
	
	private List<ControlMetadata> controls;
	private Map<String, String> attributes;
	
	public String getId() {
		return attributes.get("id");
	}
	
	private FormMetadata() {
		controls = new ArrayList<ControlMetadata>();
		attributes = new HashMap<String, String>();
	}

	public List<ControlMetadata> getControls() {
		return controls;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}
	
	static FormMetadata fromNode(Node formNode) {
		FormMetadata form = new FormMetadata();
		
		List<Node> controlNodes = Utils.getChildElements(formNode);
		
		for (Node controlNode : controlNodes)
			form.controls.add(ControlMetadata.fromNode(controlNode));
		
		NamedNodeMap attrs = formNode.getAttributes();		
		for (int i = 0; i < attrs.getLength(); i++) {
			Node attr = attrs.item(i);
			form.attributes.put(attr.getNodeName(), attr.getNodeValue());
		}
		
		return form;
	}
}
