package com.blox.framework.v0.impl;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.IDrawManager;
import com.blox.framework.v0.IDrawable;

public class DrawManager implements IDrawManager {

	private class Layer {
		int index;
		List<IDrawable> objects = new ArrayList<IDrawable>();

		void register(IDrawable obj) {
			objects.add(obj);
		}

		boolean unregister(IDrawable obj) {
			return objects.remove(obj);
		}
	}

	List<Layer> layers;

	public DrawManager() {
		layers = new ArrayList<Layer>();
	}

	@Override
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

	@Override
	public void unregister(IDrawable obj) {
		for (int i = 0; i < layers.size(); i++) {
			if (layers.get(i).unregister(obj))
				return;
		}
	}

	@Override
	public void draw() {
		for (int i = 0; i < layers.size(); i++) {
			for (int j = 0; j < layers.get(i).objects.size(); j++) {
				layers.get(i).objects.get(j).draw();
			}
		}
	}
}
