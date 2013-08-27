package com.blox.framework.v0.metadata;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Node;

public class ControlMetadata extends Metadata {
	
	private String tag;
	private Map<String, String> attributes;
	
	ControlMetadata() {
		attributes = new HashMap<String, String>();
	}

	public String getTag() {
		return tag;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}
	
	@Override
	protected void loadNode(Node node) {
		tag = node.getNodeName();
		super.loadNode(node);
	}

	@Override
	protected void setAttribute(String key, String value) {
		attributes.put(key, value);
		super.setAttribute(key, value);
	}
}
