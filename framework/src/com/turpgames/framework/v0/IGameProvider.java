package com.turpgames.framework.v0;

public interface IGameProvider {
	IResourceManager createResourceManager();

	IDeltaTime createDeltaTime();

	ITextureDrawer createTextureDrawer();

	IShapeRenderer createShapeRenderer();

	IInputManager createInputManager();
	
	ISettings createSettings();
	
	IVibrator createVibrator();
	
	IHttpClient createHttpClient();
	
	void exit();
}