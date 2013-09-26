package com.blox.setgame.model;

import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.TextureDrawer;
import com.blox.setgame.utils.R;
import com.blox.setgame.utils.SetGameResources;

class ToolbarButton extends SetGameObject {
	private static final int n = 1 << 0;
	private static final int e = 1 << 1;
	private static final int s = 1 << 2;
	private static final int w = 1 << 3;

	public static final int AlignN = n;
	public static final int AlignNE = n | e;
	public static final int AlignE = e;
	public static final int AlignSE = s | e;
	public static final int AlignS = s;
	public static final int AlignSW = s | w;
	public static final int AlignW = w;
	public static final int AlignNW = n | w;
	public static final int AlignCentered = 0;

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
	public boolean ignoreShifting() {
		return true;
	}

	@Override
	public void draw() {
		onDraw(texture);
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

	protected void onDraw(ITexture texture) {
		if (Game.getInputManager().isTouched() && isIn(Game.getInputManager().getX(), Game.getInputManager().getY()))
			getColor().set(R.colors.setBlue);
		else
			getColor().set(1);
		TextureDrawer.draw(texture, this);
	}

	void setLocation(int align, float marginX, float marginY) {
		float x = 0;
		float y = 0;
		float width = getWidth();
		float height = getHeight();

		if ((align & e) == e)
			x = Game.getScreenWidth() - width - marginX;
		else if ((align & w) == w)
			x = marginX;
		else
			x = (Game.getScreenWidth() - width) / 2 + marginX;

		if ((align & n) == n)
			y = Game.getScreenHeight() - height - marginY;
		else if ((align & s) == s)
			y = marginY;
		else
			y = (Game.getScreenHeight() - height) / 2 + marginY;

		getLocation().set(x, y);
	}
}
