package com.blox.test.mummy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationBuilder {
	private BloxAnimation animation;
	
	private AnimationBuilder() {
		animation = new BloxAnimation();
	}
	
	public static AnimationBuilder createAnimation(String name) {
		AnimationBuilder builder = new AnimationBuilder();
		builder.animation.setName(name);
		return builder;
	}
	
	public AnimationBuilder fromImageFile(String imagePath) {
		animation.setImagePath(imagePath);
		return this;
	}
	
	public AnimationBuilder withFrameDuration(float duration) {
		animation.setFrameDuration(duration);
		return this;
	}
	
	public AnimationBuilder withFrameSize(int width, int height) {
		animation.setFrameWidth(width);
		animation.setFrameHeight(height);
		return this;
	}
	
	public AnimationBuilder withFinishListeners(AnimationFinishListener... listeners) {
		for (AnimationFinishListener listener : listeners)
			animation.registerFinishListener(listener);
		return this;
	}
	
	public AnimationBuilder setAsLooping() {
		animation.setLooping(true);
		return this;
	}
	
	public BloxAnimation build() {
		buildAnimation();
		return animation;
	}
	
	private void buildAnimation() {
		Texture mainTexture = new Texture(Gdx.files.internal(animation.getImagePath()));
		int cols = mainTexture.getWidth() / animation.getFrameWidth();
		int rows = mainTexture.getHeight() / animation.getFrameHeight();
		TextureRegion[][] tmp = TextureRegion.split(mainTexture, animation.getFrameWidth(), animation.getFrameHeight()); // #10
		TextureRegion[] frames = new TextureRegion[cols * rows];
		int index = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				frames[index++] = tmp[i][j];
			}
		}
		
		Animation libgdxAnimation = new Animation(animation.getFrameDuration(), frames);		
		animation.setAnimation(libgdxAnimation);
	}
}
