package com.blox.framework.v0.util;

import com.blox.framework.v0.ICollisionDetectorFactory;
import com.blox.framework.v0.ICollisionManager;
import com.blox.framework.v0.IDeltaTime;
import com.blox.framework.v0.IDrawManager;
import com.blox.framework.v0.IGameFactory;
import com.blox.framework.v0.IInputManager;
import com.blox.framework.v0.IMoveManager;
import com.blox.framework.v0.IResourceManager;
import com.blox.framework.v0.ITextureSplitter;

public final class ToolBox {
	public static float screenWidth = 480;
	public static float screenHeight = 800;
	public static float scale = 1.0f;
	public static Vector gravity;

	private static IDeltaTime deltaTime;
	private static IResourceManager resourceManager;
	private static ITextureSplitter textureSplitter;
	private static IInputManager inputManager;
	private static ICollisionDetectorFactory collisionDetectorFactory;
	private static IDrawManager drawManager;
	
	private ToolBox() {

	}

	public static void initialize(IGameFactory factory) {
		deltaTime = factory.createDeltaTime();
		resourceManager = factory.createResourceManager();
		textureSplitter = factory.createTextureSplitter();
		inputManager = factory.createInputManager();
		collisionDetectorFactory = factory.createCollisionDetectorFactory();
		drawManager = factory.createDrawManager();
		
		gravity = new Vector();
		gravity.y = -9.8f;
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

	public static IInputManager getInputManager() {
		return inputManager;
	}

	public static ICollisionDetectorFactory getCollisionDetectorFactory() {
		return collisionDetectorFactory;
	}
	
	public static IDrawManager getDrawManager() {
		return drawManager;
	}
}
