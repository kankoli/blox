package com.blox.framework.v0.util;

import org.w3c.dom.Node;

public class GameParam {
	private String key;
	private String value;

	private GameParam() {

	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	static GameParam fromNode(Node paramNode) {
		GameParam param = new GameParam();

		param.key = Utils.getAttributeValue(paramNode, "key");
		param.value = Utils.getAttributeValue(paramNode, "value");

		return param;
	}
}
