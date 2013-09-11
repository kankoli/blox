package com.blox.setgame.utils;

import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Vector;
import com.blox.setgame.model.Card;

public final class R {
	/**
	 * game.xml yapýsýndaki id ve key'ler
	 */
	public static final class game {
		public static final class controls {
			public static final class mainMenu {
				public static final String btnSound = "btnSound";
			}
		}

		public static final class forms {
			public static final String mainMenu = "mainMenu";
		}

		public static final class screens {
			public static final String menu = "menu";
		}

		public static final class sounds {
			public static final String error = "error";
			public static final String success = "success";
			public static final String timeUp = "time-up";
			public static final String wait = "wait";
			public static final String flip = "flip";
		}

		public static final class textures {
			public static final String cardBorder = "card-border";
			public static final String cardClosed = "card-closed";
			public static final String cardEmpty = "card-empty";
			public static final String logo = "logo";
		}
	}

	public static final class learningModeScreen {
		public static final class layout {
			public static final Vector cardOnTable1Pos = new Vector(Game.getVirtualWidth() / 2 - Card.Width - 20, 4 * Game.getVirtualHeight() / 7);
			public static final Vector cardOnTable2Pos = new Vector(Game.getVirtualWidth() / 2 + 20, 4 * Game.getVirtualHeight() / 7);

			public static final Vector cardToSelect1Pos = new Vector(Game.getVirtualWidth() / 2 - Card.Width * 1.5f - 40, 2 * Game.getVirtualHeight() / 7);
			public static final Vector cardToSelect2Pos = new Vector(Game.getVirtualWidth() / 2 - Card.Width * 0.5f, 2 * Game.getVirtualHeight() / 7);
			public static final Vector cardToSelect3Pos = new Vector(Game.getVirtualWidth() / 2 + Card.Width * 0.5f + 40, 2 * Game.getVirtualHeight() / 7);

			public static final Vector[] positions = new Vector[] { cardOnTable1Pos, cardOnTable2Pos, cardToSelect1Pos, cardToSelect2Pos, cardToSelect3Pos };
		}
	}

	public static final class symbolpositions {
		public static final Vector firstOfOne = new Vector((Card.Width - Card.SymbolWidth) / 2, (Card.Height - Card.SymbolHeight) / 2);

		public static final Vector firstOfTwo = new Vector((Card.Width - Card.SymbolWidth) / 2, Card.Height / 2 - Card.SymbolHeight * 1.2f);
		public static final Vector secondOfTwo = new Vector((Card.Width - Card.SymbolWidth) / 2, Card.Height / 2 + Card.SymbolHeight * 0.2f);

		public static final Vector firstOfThree = new Vector((Card.Width - Card.SymbolWidth) / 2, Card.Height / 2 - Card.SymbolHeight * 1.8f);
		public static final Vector secondOfThree = new Vector((Card.Width - Card.SymbolWidth) / 2, Card.Height / 2 - Card.SymbolHeight * 0.5f);
		public static final Vector thirdOfThree = new Vector((Card.Width - Card.SymbolWidth) / 2, Card.Height / 2 + Card.SymbolHeight * 0.8f);
	}

	public static final class colors {
		public static final Color setRed = Color.fromHex("#d0583bff");
		public static final Color setGreen = Color.fromHex("#56ad89ff");
		public static final Color setBlue = Color.fromHex("#3974b1ff");
	}

	private R() {
	}
}