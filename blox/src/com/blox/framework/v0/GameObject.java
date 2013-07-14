package com.blox.framework.v0;

public abstract class GameObject implements IInputListener {
	// http://en.wikipedia.org/wiki/Physical_property#List_of_properties
	protected float mass;
	protected float width;
	protected float height;
	protected IVector location;
	protected IVector velocity;
	protected IVector acceleration;
	protected IVector rotation;

	private AnimationManager animationManager;

	protected GameObject() {
		location = Game.createVector();
		velocity = Game.createVector();
		acceleration = Game.createVector();
		rotation = Game.createVector();
		animationManager = new AnimationManager();
		animationManager.registerEndListener(new AnimationEndListener(this));
		
		Game.getInputManager().register(this);
	}

	private class AnimationEndListener implements IAnimationEndListener {
		private GameObject gameObj;

		AnimationEndListener(GameObject gameObj) {
			this.gameObj = gameObj;
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			gameObj.onAnimationEnd(animation);
		}
	}

	protected Animation addAnimation(String name, String resourcePath,
			float frameDuration, int frameWidth, int frameHeight) {
		Animation animation = AnimationBuilder.createAnimation(name)
				.from(resourcePath).withFrameDuration(frameDuration)
				.withFrameSize(frameWidth, frameHeight).build();

		animationManager.addAnimation(animation);

		return animation;
	}

	protected void removeAnimation(String name) {
		animationManager.removeAnimation(name);
	}

	protected void onAnimationEnd(Animation animation) {

	}

	protected void stopAnimation() {
		animationManager.stop();
	}

	protected Animation startAnimation() {
		return startAnimation(false);
	}

	protected Animation startAnimation(boolean forceRestart) {
		return animationManager.start(forceRestart);
	}

	protected Animation startAnimation(String name) {
		return animationManager.start(name);
	}

	protected void pauseAnimation() {
		animationManager.pause();
	}

	protected Animation getAnimation() {
		return animationManager.getAnimation();
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(float x, float y, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float vx, float vy, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float dx, float xy) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(IVector p1Start, IVector p2Start, IVector p1End,
			IVector p2End) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(float amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
