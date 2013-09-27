package com.blox.setgame.view;

import com.blox.framework.v0.IResourceManager;
import com.blox.framework.v0.impl.Screen;
import com.blox.framework.v0.impl.ScreenManager;
import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.ShapeRenderer;
import com.blox.setgame.model.Logo;
import com.blox.setgame.utils.R;

public class SplashScreen extends Screen {

	private IResourceManager resourceManager;
	private Color progressColor;

	@Override
	public void init() {
		super.init();
		registerDrawable(new Logo(), 1);
		progressColor = new Color(R.colors.setRed);
		resourceManager = Game.getResourceManager();
	}

	@Override
	public void render() {
		super.render();

		float width = 500 * resourceManager.getProgress();
		float x = (Game.getVirtualWidth() - width) / 2;

		ShapeRenderer.drawRect(x, 100, width, 10, progressColor, true, false);
	}

	@Override
	public void deactivated() {
		super.deactivated();
		SetGame.activateToolbar();
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
			progressColor.r = (1 - 2 * progress) * R.colors.setRed.r + 2 * progress * R.colors.setGreen.r;
			progressColor.g = (1 - 2 * progress) * R.colors.setRed.g + 2 * progress * R.colors.setGreen.g;
			progressColor.b = (1 - 2 * progress) * R.colors.setRed.b + 2 * progress * R.colors.setGreen.b;
		}
		else {
			progressColor.r = 2 * (1 - progress) * R.colors.setGreen.r + 2 * (progress - 0.5f) * R.colors.setBlue.r;
			progressColor.g = 2 * (1 - progress) * R.colors.setGreen.g + 2 * (progress - 0.5f) * R.colors.setBlue.g;
			progressColor.b = 2 * (1 - progress) * R.colors.setGreen.b + 2 * (progress - 0.5f) * R.colors.setBlue.b;
		}
	}

	private void switchToMenu() {
		ScreenManager.instance.switchTo(R.game.screens.menu, false);
	}
}