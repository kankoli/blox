package com.turpgames.framework.v0.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.turpgames.framework.v0.IDrawingInfo;

public class Utils {
	public static final int LAYER_BACKGROUND = 0;
	public static final int LAYER_SCREEN = 1;
	public static final int LAYER_GAME = 2;
	public static final int LAYER_INFO = 3;
	public static final int LAYER_DIALOG = 4;
		
	private static final Random rnd = new Random();
	
	public static String getOSVersion() {
		return System.getProperty("os.version");
	}

	public static String readUtf8String(InputStream is) throws IOException {
		StringBuffer strBuffer = new StringBuffer();
		byte[] buffer = new byte[128];
		int bytesRead;
		while ((bytesRead = is.read(buffer, 0, buffer.length)) > 0)
			strBuffer.append(new String(buffer, 0, bytesRead, "UTF-8"));
		return strBuffer.toString();
	}
	
	public static boolean isIn(float x, float y, IDrawingInfo drawingInfo) {
		return isIn(x, y, 
				drawingInfo.getLocation().x, drawingInfo.getLocation().y, 
				drawingInfo.getWidth(), drawingInfo.getHeight(), 
				drawingInfo.ignoreViewport());
	}

	private static boolean isIn(float x, float y, float lx, float ly, float width, float height, boolean ignoreViewport) {
		if (!ignoreViewport) {
			x = Game.screenToViewportX(x);
			y = Game.screenToViewportY(y);
		}
		return x > lx && x < lx + width && y > ly && y < ly + height;
	}

	public static boolean isNullOrWhitespace(String str) {
		return str == null || str.trim().length() == 0;
	}

	public static List<Node> getChildElements(Node node) {
		NodeList childNodes = node.getChildNodes();

		List<Node> list = new ArrayList<Node>();

		for (int i = 0; i < childNodes.getLength(); i++) {
			if (childNodes.item(i) instanceof Element)
				list.add(childNodes.item(i));
		}

		return list;
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

	public static Document loadXml(InputStream is) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			return builder.parse(is);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		finally {
			close(is);
		}
	}

	public static Object createInstance(String className) {
		try {
			return Class.forName(className).newInstance();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static float parseFloat(String f) {
		return Float.parseFloat(f);
	}

	public static int parseInt(String i) {
		return Integer.parseInt(i);
	}

	public static boolean parseBoolean(String b) {
		return Boolean.parseBoolean(b);
	}

	public static int randInt() {
		return rnd.nextInt();
	}
	
	public static int randInt(int maxValue) {
		return rnd.nextInt(maxValue);
	}

	public static <T> void shuffle(T[] array) {
		int loopSize = array.length * array.length;
		for (int i = 0; i < loopSize; i++) {
			int x = randInt(array.length);
			int y = randInt(array.length);

			T tmp = array[x];
			array[x] = array[y];
			array[y] = tmp;
		}
	}
	
	public static <T> T random(T[] array) {
		return array[randInt(array.length)];
	}
	
	public static String getTimeString(int time) {
		int min = time / 60;
		int sec = time % 60;
		return (min < 10 ? ("0" + min) : ("" + min)) + ":" + (sec < 10 ? ("0" + sec) : ("" + sec));
	}

	public static void close(Closeable closable) {
		if (closable == null)
			return;
		try {
			closable.close();
		} catch (IOException e) {
			// ignore
			e.printStackTrace();
		}
	}
}