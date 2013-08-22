package com.blox.test.movers;

import com.blox.framework.v0.impl.GameObject;
import com.blox.framework.v0.impl.Mover2;
import com.blox.framework.v0.util.Vector;
import com.blox.maze.util.R;

public class MovableObj extends GameObject {
	private Mover2 mover;
	public MovableObj() {
		addAnimation(R.animations.Block.def);
		startAnimation(R.animations.Block.def);
		mover = new Mover2(new Vector(10, 10), new Vector(400, 750), 40f, 0.75f, 0.25f);
		mover.setLooping(true);
		setMover(mover);
		width = 40;
		height = 40;
	}
	
	@Override
	public void draw() {
		super.draw();
		mover.drawInfo();
	}
}
