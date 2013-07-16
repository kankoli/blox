package com.blox.game;

import com.blox.framework.v0.util.Animation;
import com.blox.framework.v0.util.DefaultMover;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.GameObject;

public class Block extends GameObject {
	private final class Animations {
		private static final String Walk = "MummyWalkAnimation";
		private static final String WalkImagePath = "mummyImages/mummyWalk.png";
		private static final int WalkFrameWidth = 41;
		private static final int WalkFrameHeight = 48;
		private static final float WalkFrameDuration = 0.033f;

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

		width = Game.scale(41);
		height = Game.scale(48);
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
				velocity.x = Game.scale(63);
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
		if (x > this.location.x && y > this.location.y
				&& x < this.location.x + this.width
				&& y < this.location.y + this.height) {
			acceleration.y = Game.scale(-800);
			velocity.y = Game.scale(300);
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

	public void draw() {
		if (location.y < 0) {
			velocity.x = 0;
			velocity.y = 0;
			acceleration.x = 0;
			acceleration.y = 0;
			location.y = 0;
		}
		if (location.x + (width / 2) > Game.scale(Game.width)) {
			location.x = -width / 2;
		}
		move();
		getAnimation().getFrame().draw(this);
	}
}