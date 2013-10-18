package com.turpgames.framework.v0.impl;

import com.turpgames.framework.v0.IAnimationEndListener;
import com.turpgames.framework.v0.metadata.AnimationMetadata;
import com.turpgames.framework.v0.metadata.GameMetadata;
import com.turpgames.framework.v0.util.Animation;
import com.turpgames.framework.v0.util.Animator;
import com.turpgames.framework.v0.util.TextureDrawer;

public abstract class AnimatedGameObject extends GameObject implements IAnimationEndListener {
	protected Animator animator;

	protected AnimatedGameObject() {
		animator = new Animator();
		animator.registerEndListener(this);
	}

	protected Animation addAnimation(String animationId) {
		AnimationMetadata metadata = GameMetadata.getAnimation(animationId);
		Animation animation = Animation.fromMetadata(metadata);

		animator.addAnimation(animation);

		return animation;
	}

	protected void removeAnimation(String name) {
		animator.removeAnimation(name);
	}

	@Override
	public void onAnimationEnd(Animation animation) {

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

	protected Animation startAnimation(String animationId) {
		return animator.start(animationId);
	}

	protected void pauseAnimation() {
		animator.pause();
	}

	protected Animation getAnimation() {
		return animator.getAnimation();
	}

	@Override
	public void draw() {
		Animation curr = getAnimation();
		if (curr != null)
			TextureDrawer.draw(curr.getFrame(), this);
	}
}
