package com.turpgames.framework.v0;

public interface IGameProvider {
	IResourceManager createResourceManager();
	
	ILanguageManager createLanguageManager();

	IDeltaTime createDeltaTime();

	ITextureDrawer createTextureDrawer();

	IShapeRenderer createShapeRenderer();

	IInputManager createInputManager();
	
	ISettings createSettings();
	
	IVibrator createVibrator();
	
	void exit();
}