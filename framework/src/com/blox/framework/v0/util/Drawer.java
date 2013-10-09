package com.blox.framework.v0.util;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.IDrawable;

public class Drawer {
	private static Drawer current;	
	public static Drawer getCurrent() {
		return current;
	}
	
	private class Layer {
		private int index;
		private List<IDrawable> objects = new ArrayList<IDrawable>();

		private void register(IDrawable obj) {
			objects.add(obj);
		}

		private boolean unregister(IDrawable obj) {
			return objects.remove(obj);
		}
	}

	private List<Layer> layers;

	public Drawer() {
		this.layers = new ArrayList<Layer>();
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
			Layer layer = layers.get(i);
			if (layerIndex == layer.index) {
				layer.register(obj);
				return;
			}
			if (layerIndex < layer.index) {
				break;
			}
		}
		Layer newLayer = new Layer();
		newLayer.index = layerIndex;
		newLayer.register(obj);
		layers.add(i, newLayer);
	}

	public void unregister(IDrawable obj) {
		for (Layer layer : layers) {
			if (layer.unregister(obj))
				return;
		}
	}

	public void draw() {
		for (Layer layer : layers) {
			for (IDrawable drawable : layer.objects) {
				drawable.draw();
			}
		}
	}
}
