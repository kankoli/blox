package com.blox.framework.v0.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blox.framework.v0.IAnimationEndListener;

class Animator {
	private Animation currentAnimation;
	private Map<String, Animation> animations;
	private List<IAnimationEndListener> endListeners;

	Animator() {
		animations = new HashMap<String, Animation>();
		endListeners = new ArrayList<IAnimationEndListener>();
	}

	void addAnimation(Animation animation) {
		animation.setManager(this);
		animations.put(animation.getName(), animation);
	}

	void removeAnimation(String name) {
		Animation animation = animations.remove(name);
		animation.setManager(null);
	}

	void stop() {
		if (currentAnimation != null)
			currentAnimation.stop();
		currentAnimation = null;
	}

	Animation start() {
		return start(false);
	}

	Animation start(boolean forceRestart) {
		if (currentAnimation != null)
			currentAnimation.start(forceRestart);
		return currentAnimation;
	}

	Animation start(String name) {
		if (currentAnimation != null)
			currentAnimation.stop();
		currentAnimation = animations.get(name);
		currentAnimation.start(true);
		return currentAnimation;
	}

	void pause() {
		if (currentAnimation != null)
			currentAnimation.pause();
	}

	Animation getAnimation() {
		return currentAnimation;
	}

	void registerEndListener(IAnimationEndListener listener) {
		endListeners.add(listener);
	}

	void unregisterEndListener(IAnimationEndListener listener) {
		endListeners.remove(listener);
	}

	void notifyEndListeners(Animation animation) {
		for (IAnimationEndListener listener : endListeners)
			listener.onAnimationEnd(animation);
	}
}
