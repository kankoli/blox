package com.blox.maze.model;

import com.blox.framework.v0.util.AnimationInfo;
import com.blox.framework.v0.util.Game;

public class Background extends MazeGameObject {	
	public Background(AnimationInfo info) {
		addAnimation(info);
		startAnimation(info);
		width = Game.getScreenWidth();
		height = Game.getScreenHeight();
	}
	
	@Override
	public boolean ignoreViewportOffset() {
		return true;
	}
	
	@Override
	public boolean ignoreViewportScaling() {
		return true;
	}
}
