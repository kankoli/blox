package com.blox.framework.v0.forms.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.blox.framework.v0.IInputListener;
import com.blox.framework.v0.IUpdatable;
import com.blox.framework.v0.util.Game;

public class Panel implements IUpdatable  {
	protected int rows;
	protected int cols;
	private Map<String, Control> controls;
	private ControlInputListener inputListener;

	private Panel() {
		controls = new HashMap<String, Control>();
		inputListener = new ControlInputListener();
	}

	public void addControl(Control control) {
		if (control.getPanel() != null)
			control.getPanel().removeControl(control.id);
		controls.put(control.id, control);
		control.panel = this;
		registerInputListener(control);
	}

	public void removeControl(String id) {
		Control control = controls.remove(id);
		if (control != null && control.panel != null) {
			unregisterInputListener(control);
			control.panel = null;
		}
	}

	public Collection<Control> getControls() {
		return controls.values();
	}

	@SuppressWarnings("unchecked")
	public <T extends Control> T getControl(String id) {
		return (T)controls.get(id);
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public void draw() {
		for (Control control : getControls())
			control.draw();
	}

	public IInputListener getInputListener() {
		return inputListener;
	}
	
	private void registerInputListener(Control control) {
		inputListener.controls.add(control);
	}
	
	private void unregisterInputListener(Control control) {
		inputListener.controls.remove(control);
	}
	
	@Override
	public void update() {
		for (Control control : getControls())
			control.update();
	}

	private void load(Element node) {
		NodeList children = node.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			Control control = XmlControlFactory.create(child.getNodeName());
			if (control == null)
				continue;
			control.load(child);
			addControl(control);
		}
	}

	public static Panel load(String path) {
		InputStream is = null;
		try {
			is = Game.getResourceManager().readFile(path);

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xmlDocument = builder.parse(is);

			Element root = xmlDocument.getDocumentElement();

			Panel panel = new Panel();

			panel.cols = Integer.parseInt(root.getAttribute("cols"));
			panel.rows = Integer.parseInt(root.getAttribute("rows"));
			panel.load(root);
			
			return panel;
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
}