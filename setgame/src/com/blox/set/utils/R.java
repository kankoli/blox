package com.blox.set.utils;

import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Vector;
import com.blox.set.model.Card;

public final class R {
	private R() {
	}
	public static final class menus {
		public static final class main {
			public static final String xmlPath = "setui/menus/mainMenu.xml";
			public static final String btnPlay = "btnPlay";
			public static final String btnSetting = "btnSetting";
			public static final String btnLogin = "btnLogin";
			public static final String btnScoreBoard = "btnScoreBoard";
			public static final String btnExitGame = "btnExitGame";
		}
		public static final class modeSelection {
			public static final String xmlPath = "setui/menus/modeSelectionMenu.xml";
			public static final String btnBack = "btnBack";
		}
		public static final class login {
			public static final String xmlPath = "setui/menus/loginMenu.xml";
			public static final String btnBack = "btnBack";
		}
		public static final class scoreBoardModeSelection {
			public static final String xmlPath = "setui/menus/scoreBoardModeSelectionMenu.xml";
			public static final String btnBack = "btnBack";
		}
		public static final class settings {
			public static final String xmlPath = "setui/menus/settingsMenu.xml";
			public static final String btnBack = "btnBack";
		}
	}
	
	public static final class learningModeScreen {
		public static final class layout {
			public static final Vector cardOnTable1Pos = new Vector(Game.getVirtualWidth()/2 - Card.Width - 20, 490);
			public static final Vector cardOnTable2Pos = new Vector(Game.getVirtualWidth()/2 + 20, 490);
			
			public static final Vector cardToSelect1Pos = new Vector(Game.getVirtualWidth()/2 - Card.Width*1.5f - 10, 260);
			public static final Vector cardToSelect2Pos = new Vector(Game.getVirtualWidth()/2 - Card.Width*0.5f, 260);
			public static final Vector cardToSelect3Pos = new Vector(Game.getVirtualWidth()/2 + Card.Width*0.5f + 10, 260);
			
			public static final Vector[] positions = new Vector[] { cardOnTable1Pos, cardOnTable2Pos, cardToSelect1Pos,
				cardToSelect2Pos, cardToSelect3Pos};
		}
	}
}