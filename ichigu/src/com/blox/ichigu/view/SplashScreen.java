package com.blox.ichigu.view;

import com.blox.framework.v0.IResourceManager;
import com.blox.framework.v0.impl.Screen;
import com.blox.framework.v0.impl.ScreenManager;
import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.ShapeDrawer;
import com.blox.ichigu.model.Logo;
import com.blox.ichigu.utils.R;

public class SplashScreen extends Screen {

	private IResourceManager resourceManager;
	private Color progressColor;

	@Override
	public void init() {
		super.init();
		registerDrawable(new Logo(), 1);
		progressColor = new Color(R.colors.ichiguRed);
		resourceManager = Game.getResourceManager();
	}

	@Override
	public void draw() {
		super.draw();

		float width = 500 * resourceManager.getProgress();
		float x = (Game.getVirtualWidth() - width) / 2;

		ShapeDrawer.drawRect(x, 100, width, 10, progressColor, true, false);
	}

	@Override
	public void update() {
		if (!resourceManager.isLoading())
			switchToMenu();
		else
			setProgressColor(resourceManager.getProgress());
	}

	private void setProgressColor(float progress) {
		if (progress < 0.5f) {
			progressColor.r = (1 - 2 * progress) * R.colors.ichiguRed.r + 2 * progress * R.colors.ichiguGreen.r;
			progressColor.g = (1 - 2 * progress) * R.colors.ichiguRed.g + 2 * progress * R.colors.ichiguGreen.g;
			progressColor.b = (1 - 2 * progress) * R.colors.ichiguRed.b + 2 * progress * R.colors.ichiguGreen.b;
		}
		else {
			progressColor.r = 2 * (1 - progress) * R.colors.ichiguGreen.r + 2 * (progress - 0.5f) * R.colors.ichiguBlue.r;
			progressColor.g = 2 * (1 - progress) * R.colors.ichiguGreen.g + 2 * (progress - 0.5f) * R.colors.ichiguBlue.g;
			progressColor.b = 2 * (1 - progress) * R.colors.ichiguGreen.b + 2 * (progress - 0.5f) * R.colors.ichiguBlue.b;
		}
	}

	private void switchToMenu() {
		ScreenManager.instance.switchTo(R.game.screens.menu, false);
	}
}