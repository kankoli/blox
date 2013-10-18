package com.turpgames.framework.v0.forms.xml;

import com.turpgames.framework.v0.util.Game;

public class ExitActionHandler implements IControlActionHandler {
	@Override
	public void handle(Control control) {
		Game.exit();		
	}
}
