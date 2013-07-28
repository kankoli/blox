package com.blox.maze.model;

import com.blox.framework.v0.impl.RectangleBound;
import com.blox.framework.v0.util.Vector;

public class Block extends MazeGameObject {

	private final class Animations {
		private static final String Block = "Block";
		private static final String BlockImagePath = "turnmaze/body_full40-2.png";
		private static final int BlockFrameWidth = Maze.blockWidth;
		private static final int BlockFrameHeight = Maze.blockHeight;
		private static final float BlockFrameDuration = 1f;
	}

	Block(float x, float y) {
		this.location.x = x;
		this.location.y = y;
		addAnimation(Animations.Block, Animations.BlockImagePath, Animations.BlockFrameDuration, Animations.BlockFrameWidth, Animations.BlockFrameHeight);
		width = Maze.blockWidth;
		height = Maze.blockHeight;
		bounds.add(new RectangleBound(this, new Vector(0, 0), Maze.blockWidth, Maze.blockHeight));
		startAnimation(Animations.Block);
	}
}