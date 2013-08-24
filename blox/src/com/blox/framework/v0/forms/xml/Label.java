package com.blox.framework.v0.forms.xml;

import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.TextDrawer;
import com.blox.set.utils.SetFonts;

public class Label extends DrawableControl {
	private String text;
	private Color color;
	private boolean centered;
	private float fontScale;
	
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

	public float getFontScale() {
		return fontScale;
	}

	public void setFontScale(float fontScale) {
		this.fontScale = fontScale;
	}
	
	public Label() {
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
		TextDrawer.draw(SetFonts.font24, text, drawable, TextDrawer.AlignW);
	}
	
	@Override
	protected void setAttribute(String attribute, String value) {
		if ("text".equals(attribute))
			text = value;
		if ("color".equals(attribute))
			color = Color.fromHex(value);
		if ("centered".equals(attribute))
			setCentered("true".equals(value));
		if ("font-scale".equals(attribute))
			setFontScale(Float.parseFloat(value));
		super.setAttribute(attribute, value);
	}
}
