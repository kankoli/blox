package com.blox.framework.v0.util;

import org.w3c.dom.Node;

public class ScreenMetadata {
	private String id;
	private String screenClass;

	private ScreenMetadata() {

	}

	public String getId() {
		return id;
	}

	public String getScreenClass() {
		return screenClass;
	}

	static ScreenMetadata fromNode(Node screenNode) {
		ScreenMetadata screen = new ScreenMetadata();

		screen.id = Utils.getAttributeValue(screenNode, "id");
		screen.screenClass = Utils.getAttributeValue(screenNode, "class");

		return screen;
	}
}
