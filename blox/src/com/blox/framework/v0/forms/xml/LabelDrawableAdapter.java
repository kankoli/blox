package com.blox.framework.v0.forms.xml;

import com.blox.framework.v0.IFont;
import com.blox.framework.v0.ITextDrawer;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Vector;

public class LabelDrawableAdapter extends ControlDrawableAdapter {
	private final Label label;

	protected LabelDrawableAdapter(Control control) {
		super(control);
		label = (Label) control;
	}

	@Override
	public void draw() {
		Vector loc = getLocation();
		float width = getWidth();
		float height = getHeight();

		ITextDrawer txtDrawer = Game.getTextDrawer();
		IFont font = txtDrawer.getFont();
		font.setColor(label.getColor());
		font.setScale(label.getFontScale());

		Vector size = font.calculateSize(label.getText());
		float lineHeight = font.getLineHeight();
		
		float x = loc.x;
		float y = loc.y + (height + lineHeight) / 2;
		
		if (label.isCentered())
			x += (width - size.x) / 2;

		txtDrawer.draw(label.getText(), x, y);
	}
}
