package com.blox.ichigu.view;

import com.blox.framework.v0.IGameExitListener;
import com.blox.framework.v0.forms.xml.Dialog;
import com.blox.framework.v0.impl.FormScreen;
import com.blox.framework.v0.util.Game;
import com.blox.ichigu.model.Logo;
import com.blox.ichigu.model.Toolbar;
import com.blox.ichigu.utils.R;

public class MenuScreen extends FormScreen implements IGameExitListener {
	private Dialog exitConfirm;
	
	@Override
	public void init() {
		super.init();

		Dialog.clickSound = Game.getResourceManager().getSound(R.game.sounds.flip);
		
		Game.exitListener = this;
		
		Logo logo = new Logo();
		logo.getColor().a = 0.25f;
		registerDrawable(logo, 1);
		registerDrawable(IchiguGame.getToolbar(), 1);
		
		exitConfirm = new Dialog();
		exitConfirm.setListener(new Dialog.IDialogListener() {			
			@Override
			public void onDialogButtonClicked(String id) {
				if ("Yes".equals(id)) {
					Game.exitListener = null;
					Game.exit();		
				}
			}
		});

		setForm(R.game.forms.mainMenu, false);
	}
	
	@Override
	protected void onAfterActivate() {
		super.onAfterActivate();
		Toolbar.getInstance().deactivateBackButton();
	}
	
	@Override
	public boolean onGameExit() {
		exitConfirm.open("Are you sure you want to exit?");
		return false;
	}
}