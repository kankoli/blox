package com.blox.framework.v0;

public interface IView extends IDrawable {
	String getId();

	void activate();

	/**
	 * 
	 * @return false if View refuses to be deactivated. ie: view opens a confirm dialog and returns false
	 */
	boolean deactivate();
}
