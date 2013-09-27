package com.blox.framework.v0.util;

import org.w3c.dom.Document;

import com.badlogic.gdx.Gdx;
import com.blox.framework.v0.ICollisionDetectorFactory;
import com.blox.framework.v0.IDeltaTime;
import com.blox.framework.v0.IDisposable;
import com.blox.framework.v0.IGameProvider;
import com.blox.framework.v0.IInputManager;
import com.blox.framework.v0.IResourceManager;
import com.blox.framework.v0.ISettings;
import com.blox.framework.v0.IShapeRenderer;
import com.blox.framework.v0.ITextureDrawer;
import com.blox.framework.v0.IVibrator;
import com.blox.framework.v0.forms.xml.ControlActionHandlerFactory;
import com.blox.framework.v0.forms.xml.IControlActionHandlerFactory;
import com.blox.framework.v0.impl.CollisionDetectorFactory;
import com.blox.framework.v0.impl.DisposeManager;
import com.blox.framework.v0.metadata.GameMetadata;

public final class Game {
	private static Viewport viewport;

	private static IGameProvider provider;
	
	private static DisposeManager disposeManager;
	private static IDeltaTime deltaTime;
	private static IResourceManager resourceManager;
	private static ITextureDrawer textureDrawer;
	private static IShapeRenderer shapeRenderer;
	private static ICollisionDetectorFactory collisionDetectorFactory;
	private static IInputManager inputManager;
	private static ISettings settings;
	private static IVibrator vibrator;
	private static IControlActionHandlerFactory actionHandlerFactory;

	public static float renderingAlpha = 1;
	
	private static float renderingShiftX = 0;
	private static float renderingShiftY = 0;
	
	public static void setRenderingShift(float x, float y, boolean ignoreViewport) {
		renderingShiftX = ignoreViewport ? x : Game.scale(x);
		renderingShiftY = ignoreViewport ? y : Game.scale(y);
	}
	
	public static void resetRenderingShift() {
		renderingShiftX = 0;
		renderingShiftY = 0;
	}
	
	public static float getRenderingShiftX() {
		return renderingShiftX;
	}
	
	public static float getRenderingShiftY() {
		return renderingShiftY;
	}

	private Game() {

	}

	public static void initialize(Document gameXml) {
		GameMetadata.load(gameXml);
		
		provider = (IGameProvider)Utils.createInstance(GameMetadata.getParam("provider"));
		
		disposeManager = new DisposeManager();
		
		deltaTime = provider.createDeltaTime();
		resourceManager = provider.createResourceManager();
		textureDrawer = provider.createTextureDrawer();
		shapeRenderer = provider.createShapeRenderer();
		inputManager = provider.createInputManager();
		settings = provider.createSettings();
		vibrator = provider.createVibrator();
		
		actionHandlerFactory = new ControlActionHandlerFactory();
		collisionDetectorFactory = new CollisionDetectorFactory();
				
		initViewport();
		
		resourceManager.beginLoading();
	}

	public static float getDeltaTime() {
		return deltaTime.getDeltaTime();
	}

	public static IResourceManager getResourceManager() {
		return resourceManager;
	}

	public static ITextureDrawer getTextureDrawer() {
		return textureDrawer;
	}
	
	public static IShapeRenderer getShapeRenderer() {
		return shapeRenderer;
	}
	
	public static ICollisionDetectorFactory getCollisionDetectorFactory() {
		return collisionDetectorFactory;
	}

	public static IInputManager getInputManager() {
		return inputManager;
	}

	public static ISettings getSettings() {
		return settings;
	}

	public static IControlActionHandlerFactory getActionHandlerFactory() {
		return actionHandlerFactory;
	}

	public static void vibrate(long... pattern) {
		vibrator.vibrate(pattern);
	}
	
	public static void stopVibrating() {
		vibrator.stop();
	}
	
	public static void dispose() {
		disposeManager.execute();
	}
	
	public static void registerDisposable(IDisposable disposable) {
		disposeManager.register(disposable);
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
