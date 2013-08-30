package com.blox.set.view;

import com.blox.framework.v0.impl.BaseGame;
import com.blox.framework.v0.util.Game;
import com.blox.set.utils.SetGameActionHandlerFactory;

public class SetGame extends BaseGame {
	@Override
	public void init() {
		super.init();
		Game.getActionHandlerFactory().setSuccessor(new SetGameActionHandlerFactory());
	}
}
