package com.blox.framework.v0.util;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Utils {
	public static boolean isIn(float x, float y, Vector location, float width, float height) {
		return isIn(x, y, location.x, location.y, width, height);
	}

	public static boolean isIn(float x, float y, float lx, float ly, float width, float height) {
		return x > lx && x < lx + width && y > ly && y < ly + height;
	}

	public static List<Node> getChildNodes(Node node, String childNodeName) {
		NodeList childNodes = node.getChildNodes();

		List<Node> list = new ArrayList<Node>();

		for (int i = 0; i < childNodes.getLength(); i++) {
			if (childNodeName.equals(childNodes.item(i).getNodeName()))
				list.add(childNodes.item(i));
		}

		return list;
	}

	public static Node getChildNode(Node node, String childNodeName) {
		NodeList childNodes = node.getChildNodes();

		for (int i = 0; i < childNodes.getLength(); i++) {
			if (childNodeName.equals(childNodes.item(i).getNodeName()))
				return childNodes.item(i);
		}

		return null;
	}

	public static List<Node> findChildNodesByAttributeValue(Node node, String childNodeName, String attributeName, String attributeValue) {
		NodeList childNodes = node.getChildNodes();

		List<Node> list = new ArrayList<Node>();

		for (int i = 0; i < childNodes.getLength(); i++) {
			Node child = childNodes.item(i);
			if (childNodeName.equals(child.getNodeName()) && attributeValue.equals(getAttributeValue(child, attributeName)))
				list.add(child);
		}

		return list;
	}

	public static Node findChildNodeByAttributeValue(Node node, String childNodeName, String attributeName, String attributeValue) {
		NodeList childNodes = node.getChildNodes();

		for (int i = 0; i < childNodes.getLength(); i++) {
			Node child = childNodes.item(i);
			if (childNodeName.equals(child.getNodeName()) && attributeValue.equals(getAttributeValue(child, attributeName)))
				return child;
		}

		return null;
	}

	public static String getAttributeValue(Node node, String attributeName) {
		if (node instanceof Element)
			return ((Element) node).getAttribute(attributeName);
		return null;
	}
}
