package com.blox.framework.v0.impl;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.IDrawer;
import com.blox.framework.v0.IDrawerManager;

public class DrawerManager implements IDrawerManager {

	private class Layer {
		private int index;
		private List<IDrawer> objects = new ArrayList<IDrawer>();

		private void register(IDrawer obj) {
			objects.add(obj);
		}

		private boolean unregister(IDrawer obj) {
			return objects.remove(obj);
		}
	}

	private List<Layer> layers;

	public DrawerManager() {
		layers = new ArrayList<Layer>();
	}

	@Override
	public void register(IDrawer obj, int layerIndex) {

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

	@Override
	public void unregister(IDrawer obj) {
		for (Layer layer : layers) {
			if (layer.unregister(obj))
				return;
		}
	}

	@Override
	public void draw() {
		for (Layer layer : layers) {
			for (IDrawer drawer : layer.objects) {
				drawer.draw();
			}
		}
	}
}
