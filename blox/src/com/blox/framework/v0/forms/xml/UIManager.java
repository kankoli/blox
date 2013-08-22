package com.blox.framework.v0.forms.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.blox.framework.v0.IDrawManager;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Utils;

public final class UIManager {
	private static Layout currentLayout;
	private static Map<String, Layout> cache = new HashMap<String, Layout>();
	private static Map<String, Skin> skins = new HashMap<String, Skin>();

	static IDrawManager drawManager;

	public static Skin getSkin(String skinId) {
		if (skins.containsKey(skinId))
			return skins.get(skinId);

		Skin skin = loadSkin(skinId);

		if (skin == null)
			return null;

		skins.put(skin.getId(), skin);
		return skin;
	}

	public static Layout loadLayout(String path) {
		return getOrCreateLayout(path);
	}

	public static Layout getLayout() {
		return currentLayout;
	}

	public static void setLayout(Layout layout, IDrawManager manager) {
		if (drawManager != null && currentLayout != null) {
			for (Control c : currentLayout.getControls()) {
				drawManager.unregister(c.getDrawable());
				ControlInputListener.instance.unregister(c);
			}
		}

		drawManager = manager;
		currentLayout = layout;

		if (drawManager != null && currentLayout != null) {
			for (Control c : currentLayout.getControls()) {
				drawManager.register(c.getDrawable(), 1000);
				ControlInputListener.instance.register(c);
			}
		}
	}

	public static void unloadLayout() {
		setLayout(null, null);
	}

	static Control createControl(String name) {
		if ("button".equals(name))
			return new Button();

		if ("checkbox".equals(name))
			return new CheckBox();

		if ("image".equals(name))
			return new Image();

		if ("label".equals(name))
			return new Label();

		return null;
	}

	private static Layout getOrCreateLayout(String path) {
		if (cache.containsKey(path))
			return cache.get(path);

		InputStream is = null;
		try {
			is = Game.getResourceManager().readFile(path);

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xmlDocument = builder.parse(is);

			Element root = xmlDocument.getDocumentElement();

			Layout layout = new Layout();

			layout.load(root);

			return layout;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (is != null) {
				try {
					is.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	private static Skin loadSkin(String skinId) {
		InputStream is = null;
		try {
			is = Game.getResourceManager().readFile("setui/skins.xml");

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xmlDocument = builder.parse(is);

			Element root = xmlDocument.getDocumentElement();

			Node skinNode = Utils.findChildNodeByAttributeValue(root, "skin", "id", skinId);
			if (skinNode == null)
				return null;

			NodeList ctrlNodes = skinNode.getChildNodes();
			Skin skin = new Skin();
			skin.setId(skinId);

			for (int i = 0; i < ctrlNodes.getLength(); i++) {
				Node ctrlNode = ctrlNodes.item(i);
				NamedNodeMap attrs = ctrlNode.getAttributes();
				if (attrs == null)
					continue;
				for (int j = 0; j < attrs.getLength(); j++) {
					Node attr = attrs.item(j);
					skin.put(ctrlNode.getNodeName(), attr.getNodeName(), attr.getNodeValue());
				}
			}
			return skin;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (is != null) {
				try {
					is.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	private UIManager() {

	}
}
