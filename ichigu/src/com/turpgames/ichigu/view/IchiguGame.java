package com.turpgames.ichigu.view;

import com.turpgames.framework.v0.component.Toolbar;
import com.turpgames.framework.v0.forms.xml.Dialog;
import com.turpgames.framework.v0.impl.BaseGame;
import com.turpgames.framework.v0.impl.FormScreen;
import com.turpgames.framework.v0.impl.Screen;
import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.ichigu.model.display.IchiguToolbar;
import com.turpgames.ichigu.utils.R;

public class IchiguGame extends BaseGame {
	public static Toolbar toolbar;

	@Override
	public void init() {		
		super.init();
		
		Dialog.activeButtonColor.set(R.colors.ichiguYellow);
		Dialog.closeButtonFocusColor.set(R.colors.ichiguRed);
	}

	public static Toolbar getToolbar() {
		if (toolbar == null) {
			toolbar = IchiguToolbar.getInstance();
			toolbar.setListener(new Toolbar.IToolbarListener() {
				@Override
				public void onToolbarBack() {
					Screen screen = ScreenManager.instance.getCurrentScreen();
					if (screen instanceof IchiguScreen)
						((IchiguScreen) screen).back();
					else if (screen instanceof FormScreen)
						((FormScreen) screen).back();
				}
			});
		}
		return toolbar;
	}

	@Override
	public void draw() {
		super.draw();
	}
}