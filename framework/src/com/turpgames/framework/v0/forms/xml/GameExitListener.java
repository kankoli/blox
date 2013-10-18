package com.turpgames.framework.v0.forms.xml;

import com.turpgames.framework.v0.util.Game;

public class GameExitListener implements IClickListener {
	@Override
	public void onClick(Control control) {
		Game.exit();
	}
}
