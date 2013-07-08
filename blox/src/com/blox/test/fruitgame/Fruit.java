package com.blox.test.fruitgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.blox.World;
import com.blox.framework.BloxSprite;
import com.blox.framework.AnimationBuilder;
import com.blox.framework.BloxAnimation;

public class Fruit extends BloxSprite {
	private Vector2 pullStart;
	private Vector2 pullEnd;
	
	public enum FruitState { WAITING, FLYING, CRASHING };
	
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
	
	public Fruit(SpriteBatch spriteBatch) {
		this(spriteBatch,70f,70f);
	}
	
	public Fruit(SpriteBatch spriteBatch, float posX, float posY) {
		super(spriteBatch, new Texture(Gdx.files.internal("watermelon.png")), 30, 30);
		BloxAnimation watermelonAnimation = AnimationBuilder
				.createAnimation(Animations.Watermelon)
				.fromImageFile(Animations.WatermelonPath)
				.withFrameSize(Animations.WatermelonFrameWidth,
						Animations.WatermelonFrameHeight)
				.withFrameDuration(Animations.WatermelonFrameDuration).setAsLooping().build();
		
		BloxAnimation crashAnimation = AnimationBuilder
				.createAnimation(Animations.Crash)
				.fromImageFile(Animations.CrashPath)
				.withFrameSize(Animations.CrashFrameWidth,
						Animations.CrashFrameHeight)
				.withFrameDuration(Animations.CrashFrameDuration).withFinishListeners(this).build();
		
		addAnimation(watermelonAnimation);
		addAnimation(crashAnimation);
		
		
		this.position.x = posX;
		this.position.y = posY;
		this.currentState = FruitState.WAITING;
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
		this.velocity.mul(3);
		this.acceleration.x = 0;
		this.acceleration.y = World.gravity;
		startAnimation(Animations.Watermelon);
		this.currentState = FruitState.FLYING;
		return false;
	}

	@Override
	public void update(float delta) {
		super.move(Gdx.graphics.getDeltaTime());
		if (this.currentState == FruitState.FLYING) {
			if (position.y <= 0) { // crashed
				velocity.mul(0);
				acceleration.mul(0);
				startAnimation(Animations.Crash);
				this.currentState = FruitState.CRASHING;
			}
		}
		draw(spriteBatch);
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch) {
		super.draw(spriteBatch);
	}

	@Override
	public void onAnimationFinished(BloxAnimation animation) {
		if (Animations.Crash == animation.getName()) {
			stopAnimation();
			position.x = 70f;
			position.y = 70f;
		}
	}
}
