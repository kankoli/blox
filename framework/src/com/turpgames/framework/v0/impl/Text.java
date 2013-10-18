package com.turpgames.framework.v0.impl;

import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.IFont;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.FontManager;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.Rectangle;

public class Text implements IDrawable {
	public static final int VAlignCenter = 0;
	public static final int VAlignBottom = 1;
	public static final int VAlignTop = 2;
	public static final int HAlignCenter = 0;
	public static final int HAlignLeft = 1;
	public static final int HAlignRight = 2;

	private static final Color textColor = Color.white();
	private static final Rectangle textRect = new Rectangle();

	public Text() {
		this(FontManager.createDefaultFontInstance(), false, false);
	}

	public Text(boolean ignoreViewport, boolean ignoreShifting) {
		this(FontManager.createDefaultFontInstance(), ignoreViewport, ignoreShifting);
	}
	
	public Text(String fontId) {
		this(Game.getResourceManager().getFont(fontId), false, false);
	}

	public Text(String fontId, boolean ignoreViewport, boolean ignoreShifting) {
		this(Game.getResourceManager().getFont(fontId), ignoreViewport, ignoreShifting);
	}

	protected Text(IFont font, boolean ignoreViewport, boolean ignoreShifting) {
		this.font = font;
		this.textArea = new Rectangle();
		this.ignoreViewport = ignoreViewport;
		this.ignoreShifting = ignoreShifting;

		this.text = "";
		this.setX(0);
		this.setY(0);
		this.setVerticalAlignment(VAlignBottom);
		this.setHorizontalAlignment(HAlignLeft);
		this.setWrapped(true);
	}

	// region members:

	protected final Rectangle textArea;
	private final IFont font;

	protected boolean isDirty;

	private String text;
	private float x;
	private float y;
	private float width;
	private float height;
	private float padX;
	private float padY;
	private int horizontalAlignment;
	private int verticalAlignment;
	private boolean isWrapped;
	private boolean ignoreViewport;
	private boolean ignoreShifting;

	// x, y, width vs screen'e göre deðerleri tutmakta.

	// ignoreViewport deðilse, dýþarýya viewporta göre olan deðerler üzerinden
	// çalýþmakta
	// bu yüzden get/set'lerde descale/scale yapýlýyor

	public void setFontScale(float scale) {
		font.setScale(scale);
		isDirty = true;
	}

	public Color getColor() {
		return font.getColor();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		if (text == null)
			text = "";
		if (requiresUpdate(!this.text.equals(text)))
			this.text = text;
	}

	public float getX() {
		return ignoreViewport ? x : Game.screenToViewportX(x);
	}

	public void setX(float x) {
		x = ignoreViewport ? x : Game.viewportToScreenX(x);
		if (requiresUpdate(this.x != x))
			this.x = x;
	}

	public float getY() {
		return ignoreViewport ? y : Game.screenToViewportY(y);
	}

	public void setY(float y) {
		y = ignoreViewport ? y : Game.viewportToScreenY(y);
		if (requiresUpdate(this.y != y))
			this.y = y;
	}

	public float getWidth() {
		return descale(width);
	}

	public void setWidth(float width) {
		width = scale(width);
		if (requiresUpdate(this.width != width))
			this.width = width;
	}

	public float getHeight() {
		return descale(height);
	}

	public void setHeight(float height) {
		height = scale(height);
		if (requiresUpdate(this.height != height))
			this.height = height;
	}

	public float getPadX() {
		return descale(padX);
	}

	public void setPadX(float padX) {
		padX = scale(padX);
		if (requiresUpdate(this.padX != padX))
			this.padX = padX;
	}

	public float getPadY() {
		return descale(padY);
	}

	public void setPadY(float padY) {
		padY = scale(padY);
		if (requiresUpdate(this.padY != padY))
			this.padY = padY;
	}

	public int getHorizontalAlignment() {
		return horizontalAlignment;
	}

	public void setHorizontalAlignment(int horizontalAlignment) {
		if (requiresUpdate(this.horizontalAlignment != horizontalAlignment))
			this.horizontalAlignment = horizontalAlignment;
	}

	public int getVerticalAlignment() {
		return verticalAlignment;
	}

	public void setVerticalAlignment(int verticalAlignment) {
		if (requiresUpdate(this.verticalAlignment != verticalAlignment))
			this.verticalAlignment = verticalAlignment;
	}

	public boolean isWrapped() {
		return isWrapped;
	}

	public void setWrapped(boolean isWrapped) {
		if (requiresUpdate(this.isWrapped != isWrapped))
			this.isWrapped = isWrapped;
	}

	public boolean ignoreViewport() {
		return ignoreViewport;
	}

	public boolean ignoreShifting() {
		return ignoreShifting;
	}

	public float getTextAreaHeight() {
		updateArea();
		return descale(textArea.height);
	}

	public float getTextAreaWidth() {
		updateArea();
		return descale(textArea.width);
	}

	// endregion

	private boolean requiresUpdate(boolean hasChange) {
		isDirty = isDirty || hasChange;
		return hasChange;
	}

	protected void updateArea() {
		if (!isDirty)
			return;

		textArea.setSize(font.measureText(text, isWrapped ? width - 2 * padX : 100000000f));

		switch (horizontalAlignment) {
		case HAlignRight:
			textArea.x = x + width - textArea.width - padX;
			break;
		case HAlignCenter:
			textArea.x = x + (width - textArea.width) / 2;
			break;
		default:
			textArea.x = x + padX;
			break;
		}

		switch (verticalAlignment) {
		case VAlignTop:
			textArea.y = y + height - textArea.height - padY;
			break;
		case VAlignCenter:
			textArea.y = y + (height - textArea.height) / 2;
			break;
		default:
			textArea.y = y + padY;
			break;
		}

		isDirty = false;
	}

	private float descale(float f) {
		return ignoreViewport ? f : Game.descale(f);
	}

	private float scale(float f) {
		return ignoreViewport ? f : Game.scale(f);
	}

	@Override
	public void draw() {
		if (text == null || text.length() == 0)
			return;

		updateArea();

		textColor.set(font.getColor());
		textRect.set(textArea);

		if (!ignoreShifting) {
			textRect.x += Game.getRenderingShiftX();
			textRect.y += Game.getRenderingShiftY();
		}

		font.draw(text, textRect, horizontalAlignment);
	}

	public static int getHAlign(String value) {
		if ("center".equals(value))
			return HAlignCenter;
		if ("left".equals(value))
			return HAlignLeft;
		if ("right".equals(value))
			return HAlignRight;
		return HAlignCenter;
	}

	public static int getVAlign(String value) {
		if ("center".equals(value))
			return VAlignCenter;
		if ("top".equals(value))
			return VAlignTop;
		if ("bottom".equals(value))
			return VAlignBottom;
		return VAlignCenter;
	}
}
