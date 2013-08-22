package com.blox.set.view;

import com.blox.framework.v0.forms.xml.Button;
import com.blox.framework.v0.forms.xml.Control;
import com.blox.framework.v0.forms.xml.IClickListener;
import com.blox.framework.v0.forms.xml.Layout;
import com.blox.framework.v0.forms.xml.UIManager;
import com.blox.framework.v0.util.Game;
import com.blox.set.utils.R;

public class MainMenuScreen extends SetGameScreen {
	private Layout menu;
	
	public MainMenuScreen(SetGame game) {
		super(game);
	}

	@Override
	protected void onInit() {
		menu = UIManager.loadLayout(R.menus.main.xmlPath);
		
		Button btn = menu.getControl(R.menus.main.btnExitGame);
		btn.addClickListener(new IClickListener() {
			@Override
			public void onClick(Control control) {
				Game.exit();				
			}
		});
		
		btn = menu.getControl(R.menus.main.btnLogin);
		btn.addClickListener(new IClickListener() {
			@Override
			public void onClick(Control control) {
				game.showLogin();		
			}
		});
		
		btn = menu.getControl(R.menus.main.btnPlay);
		btn.addClickListener(new IClickListener() {
			@Override
			public void onClick(Control control) {
				game.showModeSeletionMenu();
			}
		});
		
		btn = menu.getControl(R.menus.main.btnScoreBoard);
		btn.addClickListener(new IClickListener() {
			@Override
			public void onClick(Control control) {
				game.showScoreBoardModeSelectionMenu();			
			}
		});
		
		btn = menu.getControl(R.menus.main.btnSetting);
		btn.addClickListener(new IClickListener() {
			@Override
			public void onClick(Control control) {
				game.showSettingsMenu();		
			}
		});
	}
	
	@Override
	public void activated() {
		super.activated();
		setLayout(menu);
	}
}