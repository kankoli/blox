package com.blox.framework.v0;


public interface IGameProvider {
	IResourceManager createResourceManager();

	IDeltaTime createDeltaTime();

	ITextureSplitter createTextureSplitter();

	IInputManager createInputManager();

	IFontFactory createFontFactory();

	ICollisionDetectorFactory createCollisionDetectorFactory();
	
	IActionHandlerFactory createActionHandlerFactory();
	
	void exit();
}