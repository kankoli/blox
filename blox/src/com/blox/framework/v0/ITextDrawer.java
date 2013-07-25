package com.blox.framework.v0;

public interface ITextDrawer {
	void draw(String text, float x, float y);

	void setFont(IFont font);

	IFont getFont();
}