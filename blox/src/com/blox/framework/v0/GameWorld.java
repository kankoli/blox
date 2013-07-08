package com.blox.framework.v0;

public final class GameWorld {
	public static float scale;
	public static Vector gravity;
	public static float width;
	public static float height;
	public static float depth;

	private static IDeltaTime deltaTime;
	private static IResourceManager resourceManager;
	private static IAnimationDataFactory animationDataFactory;

	public static void initialize(IGameFactory factory) {
		deltaTime = factory.createDeltaTime();
		resourceManager = factory.createResourceManager();
	}

	public static float getDeltaTime() {
		return deltaTime.getDeltaTime();
	}

	public static IResourceManager getResourceManager() {
		return resourceManager;
	}
	
	public static IAnimationDataFactory getAnimationDataFactory() {
		return animationDataFactory;
	}
}
