package com.blox.framework.v0;

public interface IGameFactory {
	IResourceManager createResourceManager();
	IDeltaTime createDeltaTime();
	IVector createVector();
	ITextureSplitter createTextureSplitter();
	IInputManager createInputManager();
}
