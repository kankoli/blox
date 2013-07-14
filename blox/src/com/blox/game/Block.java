package com.blox.game;

import com.blox.framework.v0.Animation;
import com.blox.framework.v0.DrawOptions;
import com.blox.framework.v0.GameObject;
import com.blox.framework.v0.ITexture;

public class Block extends GameObject {
	private final class Animations {
		private static final String Walk = "MummyWalkAnimation";
		private static final String WalkImagePath = "mummyImages/mummyWalk.png";
		private static final int WalkFrameWidth = 41;
		private static final int WalkFrameHeight = 48;
		private static final float WalkFrameDuration = 0.1f;

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

	private DrawOptions drawOptions;

	public Block() {
		drawOptions = new DrawOptions();

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
		
		width = 41;
		height = 48;		
	}

	@Override
	protected Animation startAnimation(String name) {
		Animation animation = super.startAnimation(name);
		drawOptions.width = animation.getWidth();
		drawOptions.height = animation.getHeight();
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
			drawOptions.flipX = !drawOptions.flipX;
			if (drawOptions.flipX)
				walk();
			else
				stand();
		}
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		if (x > this.location.getX() && x < this.location.getX() + this.width
				&& y > this.location.getY()
				&& y < this.location.getY() + this.height)
			jump();
		else
			turn();
		return super.touchDown(x, y, pointer, button);
	}

	public void draw() {
		Animation animation = getAnimation();
		ITexture frame = animation.getFrame();
		frame.draw(drawOptions);
	}
}