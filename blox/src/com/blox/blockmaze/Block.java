package com.blox.blockmaze;

import com.blox.framework.v0.impl.GameObject;
import com.blox.framework.v0.impl.RectangleBound;
import com.blox.framework.v0.util.Rotation;
import com.blox.framework.v0.util.ToolBox;
import com.blox.framework.v0.util.Vector;

class Block extends GameObject {

	public static final int blockWidth = 20;
	public static final int blockHeight = 20;
	
	private final class Animations {
		private static final String Block = "Block";
		private static final String BlockImagePath = "turnmaze/body_full.png";
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
		width = ToolBox.scale(blockWidth);
		height = ToolBox.scale(blockHeight);
		bounds.add(new RectangleBound(this, new Vector(0,0), blockWidth, blockHeight));
		startAnimation(Animations.Block);
	}
	
	void setRotation(Rotation rotation) {
		super.rotation = rotation;
	}
}