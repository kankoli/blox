package com.blox.framework.v0.impl;

import com.blox.framework.v0.IActionHandler;
import com.blox.framework.v0.util.Game;

public class ExitActionHandler implements IActionHandler {
	@Override
	public void handle() {
		Game.exit();		
	}
}
