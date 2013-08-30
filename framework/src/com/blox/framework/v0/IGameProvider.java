package com.blox.framework.v0;

import com.blox.framework.v0.forms.xml.IControlActionHandlerFactory;


public interface IGameProvider {
	IResourceManager createResourceManager();

	IDeltaTime createDeltaTime();

	ITextureDrawer createTextureDrawer();

	IInputManager createInputManager();

	ICollisionDetectorFactory createCollisionDetectorFactory();
	
	IControlActionHandlerFactory createActionHandlerFactory();
	
	ISettings createSettings();
	
	IVibrator createVibrator();
	
	void exit();
}