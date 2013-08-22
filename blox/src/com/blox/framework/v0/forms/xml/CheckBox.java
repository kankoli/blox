package com.blox.framework.v0.forms.xml;

import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.util.Game;

public class CheckBox extends Control {
	private boolean isChecked;
	private Style style;
	
	public CheckBox() {
		style = new Style(); 
	}
	
	@Override
	protected void setAttribute(String attribute, String value) {
		if ("texture-checked".equals(attribute))
			style.textureChecked = Game.getResourceManager().loadTexture(value);

		else if ("texture-unchecked".equals(attribute))
			style.textureUnchecked = Game.getResourceManager().loadTexture(value);
		
		else
			super.setAttribute(attribute, value);
	}
	
	@Override
	protected ITexture getTexture() {
		return isChecked ? style.textureChecked : style.textureUnchecked;
	}

	@Override
	protected String getNodeName() {
		return "checkbox";
	}

	@Override
	protected void onTap() {
		isChecked = !isChecked;
		super.onTap();
	}
	
	public boolean isChecked() {
		return isChecked;
	}
	
	public void setChecked(boolean checked) {
		isChecked = checked;
	}

	protected class Style {
		public ITexture textureChecked;
		public ITexture textureUnchecked;

		public Style() {

		}
	}
}
