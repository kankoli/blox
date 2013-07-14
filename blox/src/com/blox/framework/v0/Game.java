package com.blox.framework.v0;

public final class Game {
	public static float width = 480;
	public static float height = 800;
	public static float scale = 1.0f;
	public static IVector gravity;

	private static IGameFactory factory;
	
	private static IDeltaTime deltaTime;
	private static IResourceManager resourceManager;
	private static ITextureSplitter textureSplitter;
	private static IInputManager inputManager;

	private Game() {

	}

	public static void initialize(IGameFactory factory) {
		Game.factory = factory;
		deltaTime = factory.createDeltaTime();
		resourceManager = factory.createResourceManager();
		textureSplitter = factory.createTextureSplitter();
		inputManager = factory.createInputManager();
		gravity = factory.createVector();
		gravity.setY(-9.8f);
	}

	public static float getDeltaTime() {
		return deltaTime.getDeltaTime();
	}

	public static IResourceManager getResourceManager() {
		return resourceManager;
	}

	public static IVector createVector() {
		return factory.createVector();
	}

	public static ITextureSplitter getTextureSplitter() {
		return textureSplitter;
	}
	
	public static IInputManager getInputManager() {
		return inputManager;
	}
}
