package com.blox.set.view;

import com.blox.framework.v0.impl.BaseGame;
import com.blox.framework.v0.impl.FadingScreenSwitcher;

public class SetGame extends BaseGame {
	private SplashScreen splashScreen;
	private MainMenuScreen mainMenuScreen;
	private LoginScreen loginScreen;
	private ScoreBoardModeSelectionMenuScreen scoreBoardModeSelectionMenuScreen;
	private SettingsMenuScreen settingsMenuScreen;
	private ModeSelectionMenuScreen modeSelectionMenuScreen;
	
	void showMainMenu() {
		setScreen(mainMenuScreen);
	}

	void showLogin() {
		setScreen(loginScreen);
	}
	
	void showScoreBoardModeSelectionMenu() {
		setScreen(scoreBoardModeSelectionMenuScreen);
	}
	
	void showSettingsMenu() {
		setScreen(settingsMenuScreen);
	}
	
	void showModeSeletionMenu() {
		setScreen(modeSelectionMenuScreen);
	}
	
	@Override
	public void init() {
		super.init();
		
		splashScreen = new SplashScreen(this);
		mainMenuScreen = new MainMenuScreen(this);
		loginScreen = new LoginScreen(this);
		modeSelectionMenuScreen = new ModeSelectionMenuScreen(this);
		scoreBoardModeSelectionMenuScreen = new ScoreBoardModeSelectionMenuScreen(this);
		settingsMenuScreen = new SettingsMenuScreen(this);

		mainMenuScreen.init();
		
		setScreenSwitcher(new FadingScreenSwitcher(0.25f));
		setScreen(mainMenuScreen);
	}
	
	@Override
	public float getVirtualHeight() {
		return 800;
	}

	@Override
	public float getVirtualWidth() {
		return 550;
	}
}
