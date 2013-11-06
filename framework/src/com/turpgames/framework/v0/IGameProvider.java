package com.turpgames.framework.v0;

public interface IGameProvider {	
	public static final int AppTypeUnknown = 0;
	public static final int AppTypeDesktop = 1;
	public static final int AppTypeAndroid = 2;
	public static final int AppTypeIOS = 3;
	
	IResourceManager createResourceManager();

	IDeltaTime createDeltaTime();

	ITextureDrawer createTextureDrawer();

	IShapeRenderer createShapeRenderer();

	IInputManager createInputManager();
	
	ISettings createSettings();
	
	IVibrator createVibrator();
	
	void openUrl(String url);
	
	int getAppType(); 
	
	void exit();
}