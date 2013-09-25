package com.blox.setgame.model;

import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.TextureDrawer;
import com.blox.setgame.utils.SetGameResources;

class ToolbarButton extends SetGameObject {
	public static interface IToolbarButtonListener {
		void onToolbarButtonTapped(ToolbarButton button);
	}
	
	private ITexture texture;
	private IToolbarButtonListener listener;
	
	void setListener(IToolbarButtonListener listener) {
		this.listener = listener;
	}
		
	void setTexture(String textureId) {
		texture = Game.getResourceManager().getTexture(textureId);
	}
	
	@Override
	public boolean ignoreViewport() {		
		return true;
	}
	
	@Override
	public void draw() {
		TextureDrawer.draw(texture, this);
	}
	
	@Override
	protected boolean isIn(float x, float y) {
		return super.isIn(Game.viewportToScreenX(x), Game.viewportToScreenY(y));
	}
	
	@Override
	protected boolean onTap() {
		SetGameResources.playSoundFlip();
		if (listener != null)
			listener.onToolbarButtonTapped(this);
		return true;
	}
	
	@Override
	public boolean ignoreShifting() {
		return true;
	}
}
