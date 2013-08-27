package com.blox.framework.v0.metadata;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public abstract class Metadata {
	private String id;

	public String getId() {
		return id;
	}

	Metadata() {

	}

	protected void loadNode(Node node) {
		NamedNodeMap attrs = node.getAttributes();
		for (int i = 0; i < attrs.getLength(); i++) {
			Node attr = attrs.item(i);
			setAttribute(attr.getNodeName(), attr.getNodeValue());
		}
	}

	protected void setAttribute(String key, String value) {
		if ("id".equals(key))
			id = value;
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
		if ("font".equals(nodeName))
			return new FontMetadata();
		if ("form".equals(nodeName))
			return new FormMetadata();
		if ("music".equals(nodeName))
			return new MusicMetadata();
		if ("screen".equals(nodeName))
			return new ScreenMetadata();
		if ("skin".equals(nodeName))
			return new SkinMetadata();
		if ("sound".equals(nodeName))
			return new SoundMetadata();
		if ("texture".equals(nodeName))
			return new TextureMetadata();
		return null;
	}
}