package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Array;

public class TtfFontLoader extends BitmapFontLoader {
	public TtfFontLoader() {
		super(new InternalFileHandleResolver());
	}

	@Override
	public BitmapFont loadSync(AssetManager manager, String fileName, BitmapFontParameter parameter) { 
		return super.loadSync(manager, fileName, parameter);
	}
	
	@Override
	public void loadAsync(AssetManager manager, String fileName, BitmapFontParameter parameter) {
		super.loadAsync(manager, fileName, parameter);
	}
	
	@Override
	public Array<AssetDescriptor> getDependencies(String fileName,
			BitmapFontParameter parameter) {
		return null;
	}
}
