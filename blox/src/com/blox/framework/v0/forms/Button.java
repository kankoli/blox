package com.blox.framework.v0.forms;

public class Button extends ControlBase {
	public Button() {
		addAnimation("default", "btn-lightblue.png", 1, 250, 80);
		addAnimation("focused", "btn-pink.png", 1, 250, 80);
		startAnimation("default");
	}
	
	public void setImage(String animName, String path, float frameDuration, int frameWidth, int frameHeight) {
		removeAnimation(animName);
		addAnimation(animName, path, frameDuration, frameWidth, frameHeight);
	}
	
	@Override
	protected void onTouchDown() {
		startAnimation("focused");
		super.onTouchDown();
	}
	
	@Override
	protected void onTouchUp() {
		startAnimation("default");
		super.onTouchUp();
	}
}
