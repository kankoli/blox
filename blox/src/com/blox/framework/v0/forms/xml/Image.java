package com.blox.framework.v0.forms.xml;

import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.util.Game;

public class Image extends Control {
	private ITexture texture;

	@Override
	protected ITexture getTexture() {
		return texture;
	}

	@Override
	protected String getNodeName() {
		return "image";
	}
	
	@Override
	protected void setAttribute(String attribute, String value) {
		if ("texture".equals(attribute))
			texture = Game.getResourceManager().loadTexture(value);
		super.setAttribute(attribute, value);
	}
}
