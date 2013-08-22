package com.blox.set.view;

import com.blox.framework.v0.forms.xml.Button;
import com.blox.framework.v0.forms.xml.Control;
import com.blox.framework.v0.forms.xml.IClickListener;
import com.blox.framework.v0.forms.xml.Layout;
import com.blox.framework.v0.forms.xml.UIManager;
import com.blox.set.utils.R;

public class SettingsMenuScreen extends SetGameScreen {
	private Layout menu;
	
	public SettingsMenuScreen(SetGame game) {
		super(game);
	}

	@Override
	protected void onInit() {
		menu = UIManager.loadLayout(R.menus.settings.xmlPath);
		
		Button btn = menu.getControl(R.menus.settings.btnBack);
		btn.addClickListener(new IClickListener() {
			@Override
			public void onClick(Control control) {
				game.showMainMenu();				
			}
		});
	}
	
	@Override
	public void activated() {
		super.activated();
		setLayout(menu);
	}
}