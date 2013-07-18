package com.blox.framework.v0.impl;

import com.blox.framework.v0.ICollidable;
import com.blox.framework.v0.ICollisionManager;
import com.blox.framework.v0.IDrawManager;
import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.IInputListener;
import com.blox.framework.v0.IInputManager;
import com.blox.framework.v0.IMovable;
import com.blox.framework.v0.IMoveManager;
import com.blox.framework.v0.IScreen;
import com.blox.framework.v0.util.ToolBox;

public abstract class Screen extends GameObject implements IScreen {
	private IMoveManager moveManager;
	private IDrawManager drawManager;
	private ICollisionManager collisionManager;
	private IInputManager inputManager;
	
	protected Screen() {
	}

	@Override
	public void init() {
		moveManager = ToolBox.getFactory().createMoveManager();
		drawManager = ToolBox.getFactory().createDrawManager();
		collisionManager = ToolBox.getFactory().createCollisionManager();
		inputManager = ToolBox.getFactory().createInputManager();		
	}

	@Override
	public void move() {
		moveManager.move();
	}

	@Override
	public void draw() {
		drawManager.draw();
	}

	@Override
	public void update() {
		move();
		collide();
	}

	@Override
	public void render() {
		draw();
	}

	protected final void registerMovable(IMovable obj) {
		moveManager.register(obj);
	}

	protected final void unregisterMovable(IMovable obj) {
		moveManager.unregister(obj);
	}

	protected final void registerDrawable(IDrawable obj, int layer) {
		drawManager.register(obj, layer);
	}

	protected final void unregisterDrawable(IDrawable obj) {
		drawManager.unregister(obj);
	}

	protected final void registerCollidable(ICollidable obj) {
		collisionManager.register(obj);
	}

	protected final void unregisterCollidable(ICollidable obj) {
		collisionManager.unregister(obj);
	}

	protected final void registerInputListener(IInputListener obj) {
		inputManager.register(obj);
	}

	protected final void unregisterInputListener(IInputListener obj) {
		inputManager.unregister(obj);
	}
	
	public void collide() {
		collisionManager.collide();
	}
}
