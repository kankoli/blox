package com.blox.framework.v0;


public interface IGameProvider {
	IResourceManager createResourceManager();

	IDeltaTime createDeltaTime();

	ITextureSplitter createTextureSplitter();

	IInputManager createInputManager();

	ICollisionDetectorFactory createCollisionDetectorFactory();

	IDrawer createDrawer();

	IDrawerManager createDrawerManager();

	IMoveManager createMoveManager();

	ICollisionManager createCollisionManager();

	IFontFactory createFontFactory();

	IDisposeManager createDisposeManager();
	
	IActionHandlerFactory createActionHandlerFactory();
	
	String getMetadataFile();
	
	void exit();
}