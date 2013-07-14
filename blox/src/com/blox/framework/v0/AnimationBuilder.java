package com.blox.framework.v0;

public class AnimationBuilder {
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
		
	public AnimationBuilder setAsLooping() {
		animation.setLooping(true);
		return this;
	}
	
	public Animation build() {
		buildAnimation();
		return animation;
	}
	
	private void buildAnimation() {
		ITexture mainTexture = Game.getResourceManager().loadTexture(mainTexturePath);
		ITextureSplitter textureSplitter = Game.getTextureSplitter();
		ITexture[] frames = textureSplitter.split(mainTexture, animation.getWidth(), animation.getHeight());
		animation.setFrames(frames);
	}
}
