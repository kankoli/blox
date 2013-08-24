package com.blox.framework.v0.util;

import com.badlogic.gdx.Gdx;
import com.blox.framework.v0.IActionHandlerFactory;
import com.blox.framework.v0.ICollisionDetectorFactory;
import com.blox.framework.v0.ICollisionManager;
import com.blox.framework.v0.IDeltaTime;
import com.blox.framework.v0.IDisposeManager;
import com.blox.framework.v0.IDrawer;
import com.blox.framework.v0.IDrawerManager;
import com.blox.framework.v0.IFontFactory;
import com.blox.framework.v0.IGameProvider;
import com.blox.framework.v0.IInputManager;
import com.blox.framework.v0.IMoveManager;
import com.blox.framework.v0.IResourceManager;
import com.blox.framework.v0.ITextureSplitter;

public final class Game {
	private static Vector gravity = new Vector();
	private static Viewport viewport;

	private static IGameProvider provider;

	private static IDeltaTime deltaTime;
	private static IResourceManager resourceManager;
	private static ITextureSplitter textureSplitter;
	private static ICollisionDetectorFactory collisionDetectorFactory;
	private static IDisposeManager disposeManager;
	private static IFontFactory fontFactory;
	private static IInputManager inputManager;
	private static IDrawerManager drawerManager;
	private static IActionHandlerFactory actionHandlerFactory;
	
	public static float renderingAlpha = 1;

	private Game() {

	}

	public static void initialize(IGameProvider provider) {
		Game.provider = provider;
		disposeManager = provider.createDisposeManager();
		deltaTime = provider.createDeltaTime();
		resourceManager = provider.createResourceManager();
		textureSplitter = provider.createTextureSplitter();
		collisionDetectorFactory = provider.createCollisionDetectorFactory();
		inputManager = provider.createInputManager();
		drawerManager = provider.createDrawerManager();
		fontFactory = provider.createFontFactory();
		actionHandlerFactory = provider.createActionHandlerFactory();
		gravity.y = -9.8f;

		GameMetadata.load(provider.getMetadataFile());

		initViewport();
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

	public static IFontFactory getFontFactory() {
		return fontFactory;
	}

	public static IInputManager getInputManager() {
		return inputManager;
	}

	public static IDrawerManager getDrawerManager() {
		return drawerManager;
	}

	public static IDisposeManager getDisposeManager() {
		return disposeManager;
	}

	public static IActionHandlerFactory getActionHandlerFactory() {
		return actionHandlerFactory;
	}
	
	public static IDrawer createDrawer() {
		return provider.createDrawer();
	}
	
	public static IMoveManager createMoveManager() {
		return provider.createMoveManager();
	}
	
	public static ICollisionManager createCollisionManager() {
		return provider.createCollisionManager();
	}

	public static void exit() {
		provider.exit();
	}

	public static String getParam(String key) {
		return GameMetadata.getParam(key);
	}

	// region viewport

	public static float scale(float f) {
		return f * getScale();
	}

	public static float descale(float f) {
		return f / getScale();
	}

	private static void initViewport() {
		float virtualWidth = Utils.parseFloat(getParam("virtual-width"));
		float virtualHeight = Utils.parseFloat(getParam("virtual-height"));
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();

		viewport = Viewport.create(virtualWidth, virtualHeight, screenWidth, screenHeight);
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

	public static float screenToViewportX(float x) {
		return descale(x - getViewportOffsetX());
	}

	public static float screenToViewportY(float y) {
		return descale(y - getViewportOffsetY());
	}

	public static Vector screenToViewport(Vector v) {
		v.x = screenToViewportX(v.x);
		v.y = screenToViewportY(v.y);
		return v;
	}

	public static float viewportToScreenX(float x) {
		return scale(x) + getViewportOffsetX();
	}

	public static float viewportToScreenY(float y) {
		return scale(y) + getViewportOffsetY();
	}

	public static Vector viewportToScreen(Vector v) {
		v.x = viewportToScreenX(v.x);
		v.y = viewportToScreenY(v.y);
		return v;
	}

	// endregion viewport
}
