package com.blox.test;

import com.blox.framework.v0.ISound;
import com.blox.framework.v0.forms.xml.Button;
import com.blox.framework.v0.forms.xml.Control;
import com.blox.framework.v0.forms.xml.IClickListener;
import com.blox.framework.v0.impl.FormScreen;
import com.blox.framework.v0.util.Game;

public class MenuScreen extends FormScreen {
	@Override
	public void init() {
		super.init();
		setForm("mainMenu", false);

		final ISound sound = Game.getResourceManager().getSound("success");

		Button btn = getControl("btnSoundTest");
		btn.addClickListener(new IClickListener() {
			@Override
			public void onClick(Control control) {
				sound.play();
			}
		});
	}
}
