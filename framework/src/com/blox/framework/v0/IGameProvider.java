package com.blox.framework.v0;

public interface IGameProvider {
	IResourceManager createResourceManager();

	IDeltaTime createDeltaTime();

	ITextureDrawer createTextureDrawer();

	IInputManager createInputManager();
	
	ISettings createSettings();
	
	IVibrator createVibrator();
	
	void exit();
}