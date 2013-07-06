package com.blox.framework;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BloxAnimation {
	private Animation animation;
	private String name;
	private String imagePath;
	private boolean looping;
	private float frameDuration;
	private int frameWidth;
	private int frameHeight;

	private List<AnimationFinishListener> finishListeners;

	private boolean flipped;
	private boolean animating;
	private float stateTime;

	BloxAnimation() {
		finishListeners = new ArrayList<AnimationFinishListener>();
		animating = false;
		stateTime = 0;
	}

	public Animation getAnimation() {
		return animation;
	}

	protected void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public String getImagePath() {
		return imagePath;
	}

	protected void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public boolean isLooping() {
		return looping;
	}

	protected void setLooping(boolean looping) {
		this.looping = looping;
	}

	public float getFrameDuration() {
		return frameDuration;
	}

	protected void setFrameDuration(float frameDuration) {
		this.frameDuration = frameDuration;
	}

	public int getFrameWidth() {
		return frameWidth;
	}

	protected void setFrameWidth(int frameWidth) {
		this.frameWidth = frameWidth;
	}

	public int getFrameHeight() {
		return frameHeight;
	}

	protected void setFrameHeight(int frameHeight) {
		this.frameHeight = frameHeight;
	}

	public boolean isFlipped() {
		return flipped;
	}

	public void setFlipped(boolean flipped) {
		this.flipped = flipped;
	}

	public void flip() {
		flipped = !flipped;
	}

	public boolean isAnimating() {
		return animating;
	}

	public float getStateTime() {
		return stateTime;
	}

	public void startAnimation() {
		if (animating)
			return;
		stateTime = 0;
		animating = true;
	}

	public void stopAnimation() {
		stateTime = 0;
		animating = false;
	}

	public void registerFinishListener(AnimationFinishListener listener) {
		finishListeners.add(listener);
	}

	public void unregisterFinishListener(AnimationFinishListener listener) {
		finishListeners.remove(listener);
	}

	public void notifyFinishListeners() {
		for (AnimationFinishListener listener : finishListeners)
			listener.onAnimationFinished(this);
	}

	public TextureRegion getFrame() {
		stateTime += Gdx.graphics.getDeltaTime();

		if (animation.isAnimationFinished(stateTime))
			notifyFinishListeners();

		TextureRegion frameTexture = animation.getKeyFrame(stateTime, looping);

		if (flipped != frameTexture.isFlipX()) {
			frameTexture.flip(true, false);
		}
		return frameTexture;
	}
}