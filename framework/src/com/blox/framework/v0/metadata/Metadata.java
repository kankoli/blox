package com.blox.framework.v0.metadata;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public abstract class Metadata {
	private String id;
	private String type;
	protected final Map<String, String> attributes;

	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}
	
	Metadata() {
		attributes = new HashMap<String, String>();
	}

	protected void loadNode(Node node) {
		type = node.getNodeName();
		NamedNodeMap attrs = node.getAttributes();
		for (int i = 0; i < attrs.getLength(); i++) {
			Node attr = attrs.item(i);
			setAttribute(attr.getNodeName(), attr.getNodeValue());
		}
	}

	protected void setAttribute(String key, String value) {
		if ("id".equals(key))
			id = value;
		else
			attributes.put(key, value);
	}

	@SuppressWarnings("unchecked")
	static <T extends Metadata> T fromNode(Node node) {
		Metadata metadata = createMetadata(node.getNodeName());
		metadata.loadNode(node);
		return (T) metadata;
	}

	private static Metadata createMetadata(String nodeName) {
		if ("animation".equals(nodeName))
			return new AnimationMetadata();
		if ("button".equals(nodeName) || "checkbox".equals(nodeName) || "label".equals(nodeName))
			return new ControlMetadata();
		if ("font".equals(nodeName) || "music".equals(nodeName) ||"sound".equals(nodeName)||"texture".equals(nodeName))
			return new ResourceMetadata();
		if ("form".equals(nodeName))
			return new FormMetadata();
		if ("screen".equals(nodeName))
			return new ScreenMetadata();
		if ("skin".equals(nodeName))
			return new SkinMetadata();
		return null;
	}
}