package com.blox.framework.v0.impl;

import com.blox.framework.v0.forms.xml.Control;
import com.blox.framework.v0.forms.xml.IControlActionHandler;
import com.blox.framework.v0.util.Game;

public class ExitActionHandler implements IControlActionHandler {
	@Override
	public void handle(Control control) {
		Game.exit();		
	}
}
