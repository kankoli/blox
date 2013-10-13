package com.blox.framework.v0.impl;

import com.blox.framework.v0.IDrawingInfo;
import com.blox.framework.v0.util.FontManager;

public class AttachedText extends Text {
	private final IDrawingInfo drawingInfo;

	public AttachedText(IDrawingInfo drawingInfo) {
		super(FontManager.createDefaultFontInstance(), drawingInfo.ignoreViewport(), drawingInfo.ignoreShifting());
		this.drawingInfo = drawingInfo;
	}

	public AttachedText(IDrawingInfo drawingInfo, String fontId) {
		super(fontId, drawingInfo.ignoreViewport(), drawingInfo.ignoreShifting());
		this.drawingInfo = drawingInfo;
	}

	@Override
	protected void updateArea() {
		setX(drawingInfo.getLocation().x);
		setY(drawingInfo.getLocation().y);
		
		setWidth(drawingInfo.getWidth());
		setHeight(drawingInfo.getHeight());
		
		super.updateArea();
	}
}
