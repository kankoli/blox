package com.blox.framework.v0.forms.xml;

public class XmlControlFactory {
	public static Control create(String name) {
		if ("button".equals(name))
			return new Button();

		return null;
	}
}
