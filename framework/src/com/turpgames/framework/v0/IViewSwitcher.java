package com.turpgames.framework.v0;

public interface IViewSwitcher extends IDrawable {
	/**
	 * 
	 * @param id id of new view
	 * @param back true if switching back to new view 
	 * @return true if switching succeeded
	 */
	boolean switchTo(String id, boolean back);
	
	void setViewFinder(IViewFinder finder);
}