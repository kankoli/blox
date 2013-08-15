package com.blox.framework.v0.forms.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.blox.framework.v0.IDrawManager;
import com.blox.framework.v0.util.Game;

public final class UIManager {
	private static Layout currentLayout;	
	private static Map<String, Layout> cache = new HashMap<String, Layout>();

	static IDrawManager drawManager; 
	
	public static Layout loadLayout(String path) {		
		return getOrCreateLayout(path);
	}

	public static Layout getLayout() {
		return currentLayout;
	}
	
	public static void setLayout(Layout layout, IDrawManager manager) {		
		if (drawManager != null && currentLayout != null)
			for (Control c : currentLayout.getControls())				
				drawManager.unregister(c.getDrawable());

		drawManager = manager;
		currentLayout = layout;
		
		if (drawManager != null && currentLayout != null)
			for (Control c : currentLayout.getControls())
				drawManager.register(c.getDrawable(), 1000);
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
			layout.cols = Integer.parseInt(root.getAttribute("cols"));
			layout.rows = Integer.parseInt(root.getAttribute("rows"));

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
	
	private UIManager() {

	}
}
