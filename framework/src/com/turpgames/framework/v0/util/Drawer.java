package com.turpgames.framework.v0.util;

import java.util.ArrayList;
import java.util.List;

import com.turpgames.framework.v0.IDrawable;

public class Drawer {
	private static Drawer current;
	private List<Layer<IDrawable>> layers;

	public static Drawer getCurrent() {
		return current;
	}

	public Drawer() {
		this.layers = new ArrayList<Layer<IDrawable>>();
	}

	public void activate() {
		current = this;
	}

	public void deactivate() {
		current = null;
	}

	public void register(IDrawable obj, int layerIndex) {

		unregister(obj);

		int i = 0;
		for (; i < layers.size(); i++) {
			Layer<IDrawable> layer = layers.get(i);
			if (layerIndex == layer.getIndex()) {
				layer.register(obj);
				return;
			}
			if (layerIndex < layer.getIndex()) {
				break;
			}
		}
		Layer<IDrawable> newLayer = new Layer<IDrawable>(layerIndex);
		newLayer.register(obj);
		layers.add(i, newLayer);
	}

	public void unregister(IDrawable obj) {
		for (Layer<IDrawable> layer : layers) {
			if (layer.unregister(obj))
				return;
		}
	}

	public void draw() {
		for (int i = 0; i < layers.size(); i++) {
			Layer<IDrawable> layer = layers.get(i);
			List<IDrawable> objects = layer.getObjects();
			for (int j = 0; j < objects.size(); j++) {
				objects.get(j).draw();
			}
		}
	}
}
