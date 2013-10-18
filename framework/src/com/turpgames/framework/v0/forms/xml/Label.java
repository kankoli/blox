package com.turpgames.framework.v0.forms.xml;

import com.turpgames.framework.v0.ITexture;
import com.turpgames.framework.v0.impl.AttachedText;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Utils;

public class Label extends DrawableControl {
	private Text text;

	public Label() {
		text = new AttachedText(drawable);
	}
	
	public String getText() {
		return text.getText();
	}
	
	public void setText(String text) {
		this.text.setText(text);
	}

	public Color getColor() {
		return text.getColor();
	}

	public void setColor(Color color) {
		getColor().set(color);
	}
	
	public void setFontScale(float f) {
		text.setFontScale(f);
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
		text.draw();
	}
	
	@Override
	protected void setAttribute(String attribute, String value) {
		if ("text".equals(attribute))
			setText(value);
		
		else if ("color".equals(attribute))
			setColor(Color.fromHex(value));
		
		else if ("font-scale".equals(attribute))
			setFontScale(Utils.parseFloat(value));
		
		else if ("halign".equals(attribute))
			text.setHorizontalAlignment(Text.getHAlign(value));

		else if ("valign".equals(attribute))
			text.setVerticalAlignment(Text.getVAlign(value));
		
		else 
			super.setAttribute(attribute, value);
	}
}
