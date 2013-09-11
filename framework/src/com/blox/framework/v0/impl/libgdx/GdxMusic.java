package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.blox.framework.v0.IMusic;
import com.blox.framework.v0.util.Game;

final class GdxMusic implements IMusic {
	private final Music music;
	private final AssetManager manager;
	private final String path;

	GdxMusic(Music music, AssetManager manager, String path) {
		this.music = music;
		this.manager = manager;
		this.path = path;
		Game.registerDisposable(this);
	}

	@Override
	public void play() {
		music.setLooping(true);
		music.play();
	}

	@Override
	public void pause() {
		music.pause();
	}

	@Override
	public void stop() {
		music.stop();
	}

	@Override
	public void dispose() {
		music.dispose();
		if (manager.isLoaded(path))
			manager.unload(path);
	}
}