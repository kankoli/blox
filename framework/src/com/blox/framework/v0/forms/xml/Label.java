package com.blox.framework.v0.forms.xml;

import com.blox.framework.v0.IFont;
import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.FontManager;
import com.blox.framework.v0.util.TextDrawer;

public class Label extends DrawableControl {
	private String text;
	private Color color;
	private boolean centered;
	private IFont font;
	private int alignment;
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color.r = color.r;
		this.color.g = color.g;
		this.color.b = color.b;
		this.color.a = color.a;
	}

	public boolean isCentered() {
		return centered;
	}

	public void setCentered(boolean centered) {
		this.centered = centered;
	}
	
	public Label() {
		font = FontManager.defaultFont;
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
	protected void draw() {
		TextDrawer.draw(font, text, drawable, alignment);
	}
	
	@Override
	protected void setAttribute(String attribute, String value) {
		if ("text".equals(attribute))
			text = value;
		
		else if ("color".equals(attribute))
			color = Color.fromHex(value);
		
		else if ("align".equals(attribute))
			alignment = TextDrawer.getAlignment(value);
		
		else if ("font".equals(attribute))
			font = FontManager.get(value);
		
		else 
			super.setAttribute(attribute, value);
	}
}
