package com.blox.set.util;

import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Vector;
import com.blox.set.model.Card;

public final class R {
	private R() {
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
	
	public static final class textures {
		public static final String buttonLightBlue = "btn-lightblue.png";
		public static final String buttonPink = "btn-pink.png";		
	}
}