package com.blox.framework.v0.forms.xml;

import com.blox.framework.v0.IFont;
import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.FontManager;
import com.blox.framework.v0.util.TextDrawer;
import com.blox.framework.v0.util.Utils;

public class Label extends DrawableControl {
	private String text;
	private boolean centered;
	private int alignment;
	private IFont font;
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public Color getColor() {
		return font.getColor();
	}

	public void setColor(Color color) {
		getColor().set(color);
	}

	public boolean isCentered() {
		return centered;
	}

	public void setCentered(boolean centered) {
		this.centered = centered;
	}
	
	public void setFontScale(float f) {
		font.setScale(f);
	}
	
	public Label() {
		font = FontManager.createDefaultFontInstance();
	}
	
	@Override
	protected ITexture getTexture() {
		return null;
	}

	@Override
	protected String getNodeName() {
		return "label";
	}
	
	@Override
	public void draw() {
		TextDrawer.draw(font, text, drawable, alignment);
	}
	
	@Override
	protected void setAttribute(String attribute, String value) {
		if ("text".equals(attribute))
			text = value;
		
		else if ("color".equals(attribute))
			setColor(Color.fromHex(value));
		
		else if ("font-scale".equals(attribute))
			setFontScale(Utils.parseFloat(value));
		
		else if ("align".equals(attribute))
			alignment = TextDrawer.getAlignment(value);
		
		else 
			super.setAttribute(attribute, value);
	}
}
