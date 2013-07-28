package com.blox.framework.v0.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.blox.framework.v0.IAnimationEndListener;

public class Animator {
	private Animation currentAnimation;
	private Map<String, Animation> animations;
	private List<IAnimationEndListener> endListeners;

	public Animator() {
		animations = new HashMap<String, Animation>();
		endListeners = new ArrayList<IAnimationEndListener>();
	}

	public void addAnimation(Animation animation) {
		animation.setManager(this);
		animations.put(animation.getName(), animation);
	}

	public void removeAnimation(String name) {
		Animation animation = animations.remove(name);
		animation.setManager(null);
	}

	public void stop() {
		if (currentAnimation != null)
			currentAnimation.stop();
		currentAnimation = null;
	}

	public Animation start() {
		return start(false);
	}

	public Animation start(boolean forceRestart) {
		if (currentAnimation != null)
			currentAnimation.start(forceRestart);
		return currentAnimation;
	}

	public Animation start(String name) {
		if (currentAnimation != null)
			currentAnimation.stop();
		currentAnimation = animations.get(name);
		currentAnimation.start(true);
		return currentAnimation;
	}

	public void pause() {
		if (currentAnimation != null)
			currentAnimation.pause();
	}

	public Animation getAnimation() {
		return currentAnimation;
	}

	public void registerEndListener(IAnimationEndListener listener) {
		endListeners.add(listener);
	}

	public void unregisterEndListener(IAnimationEndListener listener) {
		endListeners.remove(listener);
	}

	public void notifyEndListeners(Animation animation) {
		ListIterator<IAnimationEndListener> itr = endListeners.listIterator();
		while (itr.hasNext()) {
			IAnimationEndListener l = itr.next();
			l.onAnimationEnd(animation);
		}
	}
}
