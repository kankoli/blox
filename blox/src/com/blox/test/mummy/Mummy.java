package com.blox.test.mummy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.blox.World;
import com.blox.framework.*;

public class Mummy extends BloxSprite {
	public final class Animations {
		public static final String Walk = "MummyWalkAnimation";
		private static final String WalkImagePath = "mummyImages/mummyWalk.png";
		private static final int WalkFrameWidth = 41;
		private static final int WalkFrameHeight = 48;
		private static final float WalkFrameDuration = 0.033f;

		public static final String Stand = "MummyStandAnimation";
		private static final String StandImagePath = "mummyImages/mummyStand.png";
		private static final int StandFrameWidth = 41;
		private static final int StandFrameHeight = 48;
		private static final float StandFrameDuration = 0.1f;

		public static final String Turn = "MummyTurnAnimation";
		private static final String TurnImagePath = "mummyImages/mummyTurn.png";
		private static final int TurnFrameWidth = 41;
		private static final int TurnFrameHeight = 48;
		private static final float TurnFrameDuration = 0.1f;

		public static final String Jump = "MummyJumpAnimation";
		private static final String JumpImagePath = "mummyImages/mummyStand.png";
		private static final int JumpFrameWidth = 41;
		private static final int JumpFrameHeight = 48;
		private static final float JumpFrameDuration = 1 / 6f;
	}
	public Mummy(SpriteBatch spriteBatch) {
		this(spriteBatch, 20f, 20f);
	}
	
	public Mummy(SpriteBatch spriteBatch, float posX, float posY) {
		super(spriteBatch);
		
		BloxAnimation walkAnimation = AnimationBuilder
				.createAnimation(Animations.Walk)
				.fromImageFile(Animations.WalkImagePath)
				.withFrameDuration(Animations.WalkFrameDuration)
				.withFrameSize(Animations.WalkFrameWidth,
						Animations.WalkFrameHeight).withFinishListeners(this)
				.setAsLooping().build();

		BloxAnimation standAnimation = AnimationBuilder
				.createAnimation(Animations.Stand)
				.fromImageFile(Animations.StandImagePath)
				.withFrameDuration(Animations.StandFrameDuration)
				.withFrameSize(Animations.StandFrameWidth,
						Animations.StandFrameHeight).withFinishListeners(this)
				.setAsLooping().build();

		BloxAnimation turnAnimation = AnimationBuilder
				.createAnimation(Animations.Turn)
				.fromImageFile(Animations.TurnImagePath)
				.withFrameDuration(Animations.TurnFrameDuration)
				.withFrameSize(Animations.TurnFrameWidth,
						Animations.TurnFrameHeight).withFinishListeners(this)
				.build();

		BloxAnimation jumpAnimation = AnimationBuilder
				.createAnimation(Animations.Jump)
				.fromImageFile(Animations.JumpImagePath)
				.withFrameDuration(Animations.JumpFrameDuration)
				.withFrameSize(Animations.JumpFrameWidth,
						Animations.JumpFrameHeight).withFinishListeners(this)
				.setAsLooping().build();

		addAnimation(walkAnimation);
		addAnimation(standAnimation);
		addAnimation(turnAnimation);
		addAnimation(jumpAnimation);

		setDefaultAnimation(Animations.Stand);
		startAnimation(Animations.Stand);

		this.flipped = true;
		this.position.x = 1f;
		this.position.y = 1f;
	}

	private boolean isJumping() {
		return Animations.Jump == currentAnimation.getName();
	}

	private boolean isStanding() {
		return Animations.Stand == currentAnimation.getName();
	}

	private boolean isWalking() {
		return Animations.Walk == currentAnimation.getName();
	}

	private boolean isTurning() {
		return Animations.Turn == currentAnimation.getName();
	}

	private boolean flipped;

	@Override
	public Rectangle getBoundingRectangle() {
		Rectangle bounds = super.getBoundingRectangle();
		bounds.width = currentAnimation.getFrameWidth();
		bounds.height = currentAnimation.getFrameHeight();
		return bounds;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (isStanding() || isWalking()) {
			if (screenX < World.width * 0.333f) {
				if (flipped) {
					startAnimation(Animations.Turn);
					velocity.x = 0;
				} else {
					startAnimation(Animations.Walk);
					velocity.x = -3;
				}
			} else if (screenX > World.width * 0.666f) {
				if (!flipped) {
					startAnimation(Animations.Turn);
					velocity.x = 0;
				} else {
					startAnimation(Animations.Walk);
					velocity.x = 3;
				}
			}
		}
		return super.touchDown(screenX, screenY, pointer, button);
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (!isJumping() && !isTurning()) {
			if (getBoundingRectangle()
					.contains(screenX, World.height - screenY)) {
				velocity.y = 10;
				acceleration.y = World.gravity;
				startAnimation(Animations.Jump);
			}
		} else if (isWalking()) {
			velocity.x = 0;
			startAnimation(Animations.Stand);
		}
		return super.touchUp(screenX, screenY, pointer, button);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void onAnimationFinished(BloxAnimation animation) {
		if (Animations.Turn == animation.getName()) {
			animation.stopAnimation();
			startAnimation(Animations.Stand);
			flipped = !flipped;
		}
	}

	@Override
	public void update(float delta) {
		if (currentAnimation != null)
			currentAnimation.setFlipped(flipped);
		move(delta);
		if (isJumping() && position.y < 1) {
			position.y = 1;
			velocity.mul(0);
			acceleration.mul(0);
			currentAnimation.stopAnimation();
			startAnimation(Animations.Stand);
		}
		draw(spriteBatch);
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch) {
		super.draw(spriteBatch);
	}

	@Override
	public void move(float delta) {
		float dt = delta;
		float dt2 = dt * dt;
		position.x += velocity.x * dt + 0.5f * acceleration.x * dt2;
		position.y += velocity.y * dt + 0.5f * acceleration.y * dt2;

		velocity.add(acceleration.tmp().mul(dt));
	}

}
