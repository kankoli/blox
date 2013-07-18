package com.blox.test.screen;

import com.blox.framework.v0.impl.GameObject;
import com.blox.framework.v0.impl.RectangleBound;
import com.blox.framework.v0.util.Rotation;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Vector;

class Block extends GameObject {
	static final int blockWidth = 40;
	static final int blockHeight = 40;

	private final class Animations {
		private static final String Block = "Block";
		private static final String BlockImagePath = "turnmaze/body_full40-2.png";
		private static final int BlockFrameWidth = blockWidth;
		private static final int BlockFrameHeight = blockHeight;
		private static final float BlockFrameDuration = 1f;
	}

	Block(float x, float y) {
		this.location.x = x;
		this.location.y = y;
		addAnimation(Animations.Block, Animations.BlockImagePath,
				Animations.BlockFrameDuration, Animations.BlockFrameWidth,
				Animations.BlockFrameHeight);
		width = Game.world.scale(blockWidth);
		height = Game.world.scale(blockHeight);
		bounds.add(new RectangleBound(this, new Vector(0, 0), blockWidth,
				blockHeight));
		startAnimation(Animations.Block);
	}

	void setRotation(Rotation rotation) {
		super.rotation = rotation;
	}
}