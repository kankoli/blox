package com.blox.framework.v0.util;

import com.blox.framework.v0.ICollisionDetectorFactory;
import com.blox.framework.v0.IDeltaTime;
import com.blox.framework.v0.IDisposeManager;
import com.blox.framework.v0.IGameProvider;
import com.blox.framework.v0.IInputManager;
import com.blox.framework.v0.IResourceManager;
import com.blox.framework.v0.IScreenFader;
import com.blox.framework.v0.ITextDrawer;
import com.blox.framework.v0.ITextureSplitter;

public final class Game {
	private static Vector gravity = new Vector();
	private static Viewport viewport;

	private static IGameProvider provider;

	private static IDeltaTime deltaTime;
	private static IResourceManager resourceManager;
	private static ITextureSplitter textureSplitter;
	private static ICollisionDetectorFactory collisionDetectorFactory;
	private static IScreenFader screenFader;
	private static ITextDrawer textDrawer;
	private static IDisposeManager disposeManager;
	
	private static IInputManager inputManager;

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
		inputManager = provider.createInputManager();
		gravity.y = -9.8f;
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

	public static IInputManager getInputManager() {
		return inputManager;
	}
	
	public static IDisposeManager getDisposeManager() {
		return disposeManager;
	}

	public static void exit() {
		provider.exit();
	}

	// region viewport

	public static float scale(float f) {
		return f * getScale();
	}

	public static float descale(float f) {
		return f / getScale();
	}
	
	public static void initViewport(float virtualWidth, float virtualHeight, float screenWidth, float screenHeight) {
		viewport = Viewport.create(virtualWidth, virtualHeight, screenWidth, screenHeight);
		textDrawer.getFont().setScale(1);
	}
	
	public static void updateViewport(float screenWidth, float screenHeight) {
		viewport.update(screenWidth, screenHeight);
	}
	
	public static float getViewportWidth() {
		return viewport.getWidth();
	}

	public static float getViewportHeight() {
		return viewport.getHeight();
	}

	public static float getScale() {
		return viewport.getScale();
	}

	public static float getViewportOffsetX() {
		return viewport.getOffsetX();
	}

	public static float getViewportOffsetY() {
		return viewport.getOffsetY();
	}

	public static float getVirtualWidth() {
		return viewport.getVirtualWidth();
	}

	public static float getVirtualHeight() {
		return viewport.getVirtualHeight();
	}

	public static float getScreenWidth() {
		return viewport.getScreenWidth();
	}

	public static float getScreenHeight() {
		return viewport.getScreenHeight();
	}

	// endregion viewport
}
