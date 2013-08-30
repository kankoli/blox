package com.blox.framework.v0.impl;

import com.blox.framework.v0.IGameObject;
import com.blox.framework.v0.IState;
import com.blox.framework.v0.util.Animation;
import com.blox.framework.v0.util.CollisionEvent;
import com.blox.framework.v0.util.Vector;

public abstract class State implements IState {

	public State() {
	}

	@Override
	public void work() {

	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(float x, float y, int pointer) {
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		return false;
	}

	@Override
	public boolean fling(float vx, float vy, int button) {
		return false;
	}

	@Override
	public boolean pan(float x, float y, float dx, float xy) {
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return false;
	}

	@Override
	public boolean pinch(Vector p1Start, Vector p2Start, Vector p1End, Vector p2End) {
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean mouseMoved(float x, float y) {
		return false;
	}

	@Override
	public boolean scrolled(float amount) {
		return false;
	}
	
	@Override
	public void onTouchDown(IGameObject obj, float x, float y, int pointer, int button) {
		
	}

	@Override
	public void onTouchUp(IGameObject obj, float x, float y, int pointer, int button) {

	}

	@Override
	public void onTouchDragged(IGameObject obj, float x, float y, int pointer) {

	}

	@Override
	public void onTap(IGameObject obj, float x, float y, int count, int button) {

	}

	@Override
	public void onLongPress(IGameObject obj, float x, float y) {
		
	}

	@Override
	public void onFling(IGameObject obj, float vx, float vy, int button) {
		
	}

	@Override
	public void onPan(IGameObject obj, float x, float y, float dx, float xy) {
		
	}

	@Override
	public void onZoom(IGameObject obj, float initialDistance, float distance) {
		
	}

	@Override
	public void onPinch(IGameObject obj, Vector p1Start, Vector p2Start, Vector p1End, Vector p2End) {
		
	}

	@Override
	public void onKeyDown(IGameObject obj, int keycode) {
		
	}

	@Override
	public void onKeyUp(IGameObject obj, int keycode) {
		
	}

	@Override
	public void onKeyTyped(IGameObject obj, char character) {
		
	}

	@Override
	public void onMouseMoved(IGameObject obj, float x, float y) {
		
	}

	@Override
	public void onScrolled(IGameObject obj, float amount) {
		
	}
	
	@Override
	public void onAnimationEnd(Animation animation) {

	}

	@Override
	public void onCollide(CollisionEvent event) {

	}

	@Override
	public void onNotCollide(CollisionEvent event) {

	}
}
