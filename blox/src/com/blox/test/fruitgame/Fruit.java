package com.blox.test.fruitgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.blox.World;
import com.blox.framework.AnimatedSprite;
import com.blox.framework.AnimationBuilder;
import com.blox.framework.AnimationFinishListener;
import com.blox.framework.BloxAnimation;
import com.blox.test.mummy.Mummy.Animations;

public class Fruit extends AnimatedSprite implements AnimationFinishListener {
	private Vector2 pullStart;
	private Vector2 pullEnd;
	
	public final class Animations {
		public static final String Watermelon = "FruitWatermelonAnimation";
		private static final String WatermelonPath = "watermelon.png";
		private static final int WatermelonFrameWidth = 30;
		private static final int WatermelonFrameHeight = 30;
		private static final float WatermelonFrameDuration = 0.1f;
		
		public static final String Crash = "CrashAnimation";
		private static final String CrashPath = "crash.png";
		private static final int CrashFrameWidth = 30;
		private static final int CrashFrameHeight = 30;
		private static final float CrashFrameDuration = 0.1f;
	}
	
	public Fruit(float frameDur, float posX, float posY) {
		BloxAnimation watermelonAnimation = AnimationBuilder
				.createAnimation(Animations.Watermelon)
				.fromImageFile(Animations.WatermelonPath)
				.withFrameSize(Animations.WatermelonFrameWidth,
						Animations.WatermelonFrameHeight)
				.withFrameDuration(frameDur).setAsLooping().build();
		
		BloxAnimation crashAnimation = AnimationBuilder
				.createAnimation(Animations.Crash)
				.fromImageFile(Animations.CrashPath)
				.withFrameSize(Animations.CrashFrameWidth,
						Animations.CrashFrameHeight)
				.withFrameDuration(Animations.CrashFrameDuration).withFinishListeners(this).build();
		
		addAnimation(watermelonAnimation);
		addAnimation(crashAnimation);
		
		setDefaultAnimation(Animations.Watermelon);
		startAnimation(Animations.Watermelon);
		
		this.position.x = posX;
		this.position.y = posY;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		pullStart = new Vector2(screenX, screenY);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		pullEnd = new Vector2(screenX, screenY);
		this.velocity = new Vector2(pullStart.x - pullEnd.x, -(pullStart.y - pullEnd.y));
		this.velocity.mul(4);
		this.acceleration.x = 0;
		this.acceleration.y = World.gravity;
		return false;
	}

	@Override
	public void draw(SpriteBatch spriteBatch) {
		super.draw(spriteBatch);
		move();
	}
	
	public void move() {
		float dt = Gdx.graphics.getDeltaTime();
		position.x += velocity.x * dt;
		position.y += velocity.y * dt;

		velocity.add(acceleration.tmp().mul(dt));
		
		if (getCurrentAnimation() != Animations.Crash && position.y <= 0) { //crashed
			velocity.mul(0);
			acceleration.mul(0);
			startAnimation(Animations.Crash);
		}
	}

	@Override
	public void onAnimationFinished(BloxAnimation animation) {
		if (Animations.Crash == animation.getName()) {
			animation.stopAnimation();
			startAnimation(Animations.Watermelon);
			position.x = 70f;
			position.y = 70f;
		}
	}
}
