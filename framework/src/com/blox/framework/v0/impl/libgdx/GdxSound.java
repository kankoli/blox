package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.audio.Sound;
import com.blox.framework.v0.ISound;
import com.blox.framework.v0.util.Game;

final class GdxSound implements ISound {
	private final Sound sound;
	
	GdxSound(Sound sound) {
		this.sound = sound;
		Game.registerDisposable(this);
	}
	
	@Override
	public void play() {
		if (Game.isSoundOn())
			sound.play(1f);
	}

	@Override
	public void dispose() {
		sound.dispose();
	}
}
