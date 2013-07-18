package com.blox.framework.v0.util;

import com.blox.framework.v0.ICollisionDetectorFactory;
import com.blox.framework.v0.IDeltaTime;
import com.blox.framework.v0.IGameFactory;
import com.blox.framework.v0.IResourceManager;
import com.blox.framework.v0.ITextureSplitter;

public final class ToolBox {
	public static float screenWidth = 480;
	public static float screenHeight = 800;
	public static float scale = 1.0f;
	public static Vector gravity;
	
	private static IGameFactory factory;

	private static IDeltaTime deltaTime;
	private static IResourceManager resourceManager;
	private static ITextureSplitter textureSplitter;
	private static ICollisionDetectorFactory collisionDetectorFactory;
	
	private ToolBox() {

	}

	public static void initialize(IGameFactory factory) {
		ToolBox.factory = factory;
		deltaTime = factory.createDeltaTime();
		resourceManager = factory.createResourceManager();
		textureSplitter = factory.createTextureSplitter();
//		inputManager = factory.createInputManager();
//		moveManager = factory.createMoveManager();
//		drawManager = factory.createDrawManager();
//		collisionManager = factory.createCollisionManager();
		collisionDetectorFactory = factory.createCollisionDetectorFactory();

		gravity = new Vector();
		gravity.y = -9.8f;
	}
	
	public static IGameFactory getFactory() {
		return factory;
	}

	public static float scale(float f) {
		return f * scale;
	}

	public static float descale(float f) {
		return f / scale;
	}

	public static float getDeltaTime() {
		return deltaTime.getDeltaTime();
	}

	public static IResourceManager getResourceManager() {
		return resourceManager;
	}

	public static ITextureSplitter getTextureSplitter() {
		return textureSplitter;
	}

	public static ICollisionDetectorFactory getCollisionDetectorFactory() {
		return collisionDetectorFactory;
	}

//	private static IInputManager inputManager;
//	private static IMoveManager moveManager;
//	private static ICollisionManager collisionManager;
//	private static IDrawManager drawManager;
	
//	public static IInputManager getInputManager() {
//		return inputManager;
//	}
//	
//	public static IDrawManager getDrawManager() {
//		return drawManager;
//	}
//	
//	public static IMoveManager getMoveManager() {
//		return moveManager;
//	}
//	
//	public static ICollisionManager getCollisionManager() {
//		return collisionManager;
//	}
}
