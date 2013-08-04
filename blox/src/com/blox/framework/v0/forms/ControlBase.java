package com.blox.framework.v0.forms;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.IAnimationEndListener;
import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.IDrawer;
import com.blox.framework.v0.util.Animation;
import com.blox.framework.v0.util.AnimationBuilder;
import com.blox.framework.v0.util.Animator;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Rotation;
import com.blox.framework.v0.util.Vector;

public abstract class ControlBase implements IDrawable {
	protected float width;
	protected float height;
	protected Vector location;
	protected Vector scale;
	protected Rotation rotation;
	protected boolean flipX;
	protected boolean flipY;

	protected Animator animator;
	protected IDrawer drawer;

	protected boolean isVisible;
	protected boolean isEnabled;
	
	private String text;
	private boolean isTouched;
	private List<IClickListener> clickListeners;

	protected ControlBase() {
		location = new Vector();
		scale = new Vector(1, 1, 1);
		rotation = new Rotation();

		animator = new Animator();
		animator.registerEndListener(new AnimationEndListener(this));

		drawer = IDrawer.NULL;
		clickListeners = new ArrayList<IClickListener>();
		isVisible = true;
		isEnabled = true;
	}

	// region animations

	private class AnimationEndListener implements IAnimationEndListener {
		private ControlBase control;

		AnimationEndListener(ControlBase control) {
			this.control = control;
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			control.onAnimationEnd(animation);
		}
	}

	protected Animation addAnimation(String name, String resourcePath, float frameDuration, int frameWidth, int frameHeight) {
		return addAnimation(name, resourcePath, frameDuration, frameWidth, frameHeight, false);
	}

	protected Animation addAnimation(String name, String resourcePath, float frameDuration, int frameWidth, int frameHeight, boolean isLooping) {
		Animation animation = AnimationBuilder.createAnimation(name).from(resourcePath).withFrameDuration(frameDuration).withFrameSize(frameWidth, frameHeight).setLooping(isLooping).build();

		animator.addAnimation(animation);

		return animation;
	}

	protected void removeAnimation(String name) {
		animator.removeAnimation(name);
	}

	protected void onAnimationEnd(Animation animation) {

	}

	protected void stopAnimation() {
		animator.stop();
	}

	protected Animation startAnimation() {
		return startAnimation(false);
	}

	protected Animation startAnimation(boolean forceRestart) {
		return animator.start(forceRestart);
	}

	protected Animation startAnimation(String name) {
		return animator.start(name);
	}

	protected void pauseAnimation() {
		animator.pause();
	}

	protected Animation getAnimation() {
		return animator.getAnimation();
	}

	// endregion

	// region IDrawable

	@Override
	public float getWidth() {
		return width;
	}

	@Override
	public float getHeight() {
		return height;
	}

	@Override
	public Vector getScale() {
		return scale;
	}

	@Override
	public boolean isFlipX() {
		return flipX;
	}

	@Override
	public boolean isFlipY() {
		return flipY;
	}

	@Override
	public void draw() {
		getAnimation().getFrame().draw(this);
		Game.getTextDrawer().draw(text, location.x + 25, location.y + 25);
	}

	@Override
	public void setDrawer(IDrawer drawer) {
		this.drawer = drawer;
	}

	@Override
	public boolean ignoreViewportOffset() {
		return true;
	}

	@Override
	public boolean ignoreViewportScaling() {
		return true;
	}

	protected void flipX() {
		flipX = !flipX;
	}

	protected void flipY() {
		flipY = !flipY;
	}

	@Override
	public Vector getLocation() {
		return location;
	}

	@Override
	public Rotation getRotation() {
		return rotation;
	}

	// endregion

	public void setSize(float w, float h) {
		width = w;
		height = h;
	}

	public void setLocation(float x, float y) {
		location.x = x;
		location.y = y;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean visible) {
		isVisible = visible;
	}

	public void show() {
		setVisible(true);
	}

	public void hide() {
		setVisible(false);
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean enabled) {
		isEnabled = enabled;
	}

	public void enable() {
		setEnabled(true);
	}

	public void disable() {
		setEnabled(false);
	}
	
	public void setText(String text) {
		this.text = text;				
	}
	
	public String getText() {
		return text;
	}

	protected void onTouchDown() {
		isTouched = true;
	}

	protected void onTouchUp() {
		if (isTouched) {
			onClick();
		}
		isTouched = false;
	}

	public void registerClickListener(IClickListener listener) {
		if (!clickListeners.contains(listener))
			clickListeners.add(listener);
	}

	public void unregisterClickListener(IClickListener listener) {
		clickListeners.remove(listener);
	}

	protected void onClick() {
		for (IClickListener listener : clickListeners)
			listener.clicked(this);
	}
}
