package com.blox.framework.v0.util;

import com.blox.framework.v0.ITexture;

final class AnimationBuilder {
	private Animation animation;

	private String mainTexturePath;

	private AnimationBuilder() {
		animation = new Animation();
	}

	public static AnimationBuilder createAnimation(String name) {
		AnimationBuilder builder = new AnimationBuilder();
		builder.animation.setName(name);
		return builder;
	}

	public AnimationBuilder from(String imagePath) {
		mainTexturePath = imagePath;
		return this;
	}

	public AnimationBuilder withFrameDuration(float frameDuration) {
		animation.setFrameDuration(frameDuration);
		return this;
	}

	public AnimationBuilder withFrameSize(int width, int height) {
		animation.setWidth(width);
		animation.setHeight(height);
		return this;
	}

	public AnimationBuilder setLooping(boolean isLooping) {
		animation.setLooping(isLooping);
		return this;
	}

	public Animation build() {
		buildAnimation();
		return animation;
	}

	private void buildAnimation() {
		ITexture mainTexture = Game.getResourceManager().getTexture(mainTexturePath);
		ITexture[] frames = mainTexture.split(animation.getWidth(), animation.getHeight());
		animation.setFrames(frames);
	}
}