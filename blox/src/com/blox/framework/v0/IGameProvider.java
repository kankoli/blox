package com.blox.framework.v0;

public interface IGameProvider {
	IResourceManager createResourceManager();
	IDeltaTime createDeltaTime();
	ITextureSplitter createTextureSplitter();
	IInputManager createInputManager();
	ICollisionDetectorFactory createCollisionDetectorFactory();
	IDrawManager createDrawManager();
	IMoveManager createMoveManager();
	ICollisionManager createCollisionManager();
	IScreenFader createScreenFader();
	void exit();
}