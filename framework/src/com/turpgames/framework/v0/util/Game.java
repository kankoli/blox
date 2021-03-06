package com.turpgames.framework.v0.util;

import java.util.Stack;

import org.w3c.dom.Document;

import com.badlogic.gdx.Gdx;
import com.turpgames.framework.v0.ICollisionDetectorFactory;
import com.turpgames.framework.v0.IDeltaTime;
import com.turpgames.framework.v0.IDisposable;
import com.turpgames.framework.v0.IEnvironmentProvider;
import com.turpgames.framework.v0.IGameExitListener;
import com.turpgames.framework.v0.IGameProvider;
import com.turpgames.framework.v0.IHttpClient;
import com.turpgames.framework.v0.IInputManager;
import com.turpgames.framework.v0.IResourceManager;
import com.turpgames.framework.v0.ISettings;
import com.turpgames.framework.v0.IShapeRenderer;
import com.turpgames.framework.v0.ITextureDrawer;
import com.turpgames.framework.v0.IVibrator;
import com.turpgames.framework.v0.forms.xml.ControlActionHandlerFactory;
import com.turpgames.framework.v0.forms.xml.IControlActionHandlerFactory;
import com.turpgames.framework.v0.impl.CollisionDetectorFactory;
import com.turpgames.framework.v0.impl.DisposeManager;
import com.turpgames.framework.v0.impl.LanguageManager;
import com.turpgames.framework.v0.metadata.GameMetadata;

public final class Game {
	private static Viewport viewport;

	private static IGameProvider provider;

	private static final Stack<Float> renderingShifts = new Stack<Float>();

	public static IGameExitListener exitListener;

	private static Version osVersion;
	private static Version gameVersion;
	
	public static IEnvironmentProvider environmentProvider;

	private static DisposeManager disposeManager;
	private static IDeltaTime deltaTime;
	private static IResourceManager resourceManager;
	private static LanguageManager languageManager;
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

	public static void pushRenderingShift(float x, float y, boolean ignoreViewport) {
		float shiftX = ignoreViewport ? x : Game.scale(x);
		float shiftY = ignoreViewport ? y : Game.scale(y);

		renderingShiftX += shiftX;
		renderingShiftY += shiftY;

		renderingShifts.push(shiftX);
		renderingShifts.push(shiftY);
	}

	public static void popRenderingShift() {
		if (renderingShifts.size() > 0) {
			renderingShiftY -= renderingShifts.pop();
			renderingShiftX -= renderingShifts.pop();
		}
		else {
			renderingShiftX = 0;
			renderingShiftY = 0;
		}
	}

	/**
	 * @return renderingShiftX value in screen coordinates
	 */
	public static float getRenderingShiftX() {
		return renderingShiftX;
	}

	/**
	 * @return renderingShiftY value in screen coordinates
	 */
	public static float getRenderingShiftY() {
		return renderingShiftY;
	}

	private Game() {

	}

	public static void initialize(Document gameXml) {
		GameMetadata.load(gameXml);

		provider = (IGameProvider) Utils.createInstance(GameMetadata.getParam("provider"));

		disposeManager = new DisposeManager();

		deltaTime = provider.createDeltaTime();
		settings = provider.createSettings();
		resourceManager = provider.createResourceManager();

		languageManager = new LanguageManager();

		textureDrawer = provider.createTextureDrawer();
		shapeRenderer = provider.createShapeRenderer();
		inputManager = provider.createInputManager();
		vibrator = provider.createVibrator();

		actionHandlerFactory = new ControlActionHandlerFactory();
		collisionDetectorFactory = new CollisionDetectorFactory();

		osVersion = new Version(System.getProperty("os.version"));
		
		if (environmentProvider == null)
			gameVersion = new Version(GameMetadata.getGameVersion());
		else
			gameVersion = environmentProvider.getVersion();

		initViewport();

		resourceManager.beginLoading();
	}

	public static float getDeltaTime() {
		return deltaTime.getDeltaTime();
	}

	public static IResourceManager getResourceManager() {
		return resourceManager;
	}

	public static LanguageManager getLanguageManager() {
		return languageManager;
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

	public static IHttpClient createHttpClient() {
		return provider.createHttpClient();
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

	public static void openUrl(String url) {
		provider.openUrl(url);
	}

	public static boolean isIOS() {
		return IGameProvider.AppTypeIOS == provider.getAppType();
	}

	public static boolean isAndroid() {
		return IGameProvider.AppTypeAndroid == provider.getAppType();
	}

	public static boolean isDesktop() {
		return IGameProvider.AppTypeDesktop == provider.getAppType();
	}

	public static void exit() {
		if (exitListener == null || exitListener.onGameExit())
			provider.exit();
	}

	public static String getParam(String key) {
		return GameMetadata.getParam(key);
	}

	public static Version getOSVersion() {
		return osVersion;
	}

	public static Version getVersion() {
		return gameVersion;
	}

	// region viewport

	/**
	 * viewport to screen
	 * 
	 * @param f
	 *            width, height etc...
	 * @return
	 */
	public static float scale(float f) {
		return f * getScale();
	}

	/**
	 * screen to viewport
	 * 
	 * @param f
	 *            width, height etc...
	 * @return
	 */
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
