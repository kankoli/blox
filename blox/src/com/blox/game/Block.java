package com.blox.game;

import com.blox.framework.v0.IBound;
import com.blox.framework.v0.ICollidable;
import com.blox.framework.v0.impl.DefaultMover;
import com.blox.framework.v0.impl.GameObject;
import com.blox.framework.v0.impl.RectangleBound;
import com.blox.framework.v0.util.Animation;
import com.blox.framework.v0.util.ToolBox;
import com.blox.framework.v0.util.Vector;

public class Block extends GameObject {
	private final class Animations {
		private static final String Walk = "MummyWalkAnimation";
		private static final String WalkImagePath = "mummyImages/mummyWalk.png";
		private static final int WalkFrameWidth = 41;
		private static final int WalkFrameHeight = 48;
		private static final float WalkFrameDuration = 0.2f;

		private static final String Stand = "MummyStandAnimation";
		private static final String StandImagePath = "mummyImages/mummyStand.png";
		private static final int StandFrameWidth = 41;
		private static final int StandFrameHeight = 48;
		private static final float StandFrameDuration = 1 / 6f;

		private static final String Turn = "MummyTurnAnimation";
		private static final String TurnImagePath = "mummyImages/mummyTurn.png";
		private static final int TurnFrameWidth = 41;
		private static final int TurnFrameHeight = 48;
		private static final float TurnFrameDuration = 0.1f;

		private static final String Jump = "MummyJumpAnimation";
		private static final String JumpImagePath = "mummyImages/mummyStand.png";
		private static final int JumpFrameWidth = 41;
		private static final int JumpFrameHeight = 48;
		private static final float JumpFrameDuration = 1 / 6f;
	}

	private boolean isStatic;

	public Block() {
		addAnimation(Animations.Walk, Animations.WalkImagePath,
				Animations.WalkFrameDuration, Animations.WalkFrameWidth,
				Animations.WalkFrameHeight).setLooping(true);
		addAnimation(Animations.Stand, Animations.StandImagePath,
				Animations.StandFrameDuration, Animations.StandFrameWidth,
				Animations.StandFrameHeight).setLooping(true);
		addAnimation(Animations.Jump, Animations.JumpImagePath,
				Animations.JumpFrameDuration, Animations.JumpFrameWidth,
				Animations.JumpFrameHeight);
		addAnimation(Animations.Turn, Animations.TurnImagePath,
				Animations.TurnFrameDuration, Animations.TurnFrameWidth,
				Animations.TurnFrameHeight);

		stand();

		mover = new DefaultMover();

		width = ToolBox.scale(41);
		height = ToolBox.scale(48);
		
		isStatic = false;
		bounds.add(new RectangleBound(this, new Vector(15,0), 20, 46));
	}

	@Override
	protected Animation startAnimation(String name) {
		Animation animation = super.startAnimation(name);
		return animation;
	}

	private void walk() {
		startAnimation(Animations.Walk);
	}

	private void stand() {
		startAnimation(Animations.Stand);
	}

	private void jump() {
		startAnimation(Animations.Jump);
	}

	private void turn() {
		startAnimation(Animations.Turn);
	}

	@Override
	protected void onAnimationEnd(Animation animation) {
		if (animation.getName() == Animations.Turn) {
			flipX = !flipX;
			if (flipX) {
				walk();
				velocity.x = ToolBox.scale(13);
			} else {
				velocity.y = 0;
				velocity.y = 0;
				acceleration.x = 0;
				acceleration.y = 0;
				stand();
			}
		}
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		if (isStatic) return false;
		
		if (x > this.location.x && y > this.location.y
				&& x < this.location.x + this.width
				&& y < this.location.y + this.height) {
			acceleration.y = ToolBox.scale(-800);
			velocity.y = ToolBox.scale(300);
			jump();
		} else {
			velocity.x = 0;
			velocity.y = 0;
			acceleration.x = 0;
			acceleration.y = 0;
			turn();
		}
		return super.touchDown(x, y, pointer, button);
	}

	public void setLocation(float x, float y) {
		this.location.x = x;
		this.location.y = y;
	}
	
	public void draw() {
		if (location.y < 0) {
			velocity.x = 0;
			velocity.y = 0;
			acceleration.x = 0;
			acceleration.y = 0;
			location.y = 0;
		}
		if (location.x + (width / 2) > ToolBox.scale(ToolBox.width)) {
			location.x = -width / 2;
		}
		if (!isStatic) move();
		getAnimation().getFrame().draw(this);
	}

	public void setStatic(boolean b) {
		isStatic = b;
	}
	
	@Override
	public boolean onCollide(IBound thisBound, IBound thatBound, ICollidable obj) {
		if (!isStatic) {
			turn();
			velocity.x *= -1;
		}
		return super.onCollide(thisBound, thatBound, obj);
	}
}