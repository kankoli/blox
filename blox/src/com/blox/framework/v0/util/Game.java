package com.blox.framework.v0.util;

import com.blox.framework.v0.ICollisionDetectorFactory;
import com.blox.framework.v0.IDeltaTime;
import com.blox.framework.v0.IDisposeManager;
import com.blox.framework.v0.IGameProvider;
import com.blox.framework.v0.IResourceManager;
import com.blox.framework.v0.IScreenFader;
import com.blox.framework.v0.ITextDrawer;
import com.blox.framework.v0.ITextureSplitter;

public final class Game {
	public static final GameWorld world = new GameWorld();

	private static IGameProvider provider;

	private static IDeltaTime deltaTime;
	private static IResourceManager resourceManager;
	private static ITextureSplitter textureSplitter;
	private static ICollisionDetectorFactory collisionDetectorFactory;
	private static IScreenFader screenFader;
	private static ITextDrawer textDrawer;
	private static IDisposeManager disposeManager;

	private Game() {

	}

	public static void initialize(IGameProvider provider) {
		Game.provider = provider;
		disposeManager = provider.createDisposeManager();
		deltaTime = provider.createDeltaTime();
		resourceManager = provider.createResourceManager();
		textureSplitter = provider.createTextureSplitter();
		collisionDetectorFactory = provider.createCollisionDetectorFactory();
		screenFader = provider.createScreenFader();
		textDrawer = provider.createTextDrawer();

		world.gravity.y = -9.8f;
	}

	public static IGameProvider getProvider() {
		return provider;
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

	public static IScreenFader getScreenFader() {
		return screenFader;
	}

	public static ITextDrawer getTextDrawer() {
		return textDrawer;
	}

	public static IDisposeManager getDisposeManager() {
		return disposeManager;
	}

	public static void exit() {
		provider.exit();
	}
}
