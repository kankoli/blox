package com.blox.framework.v0.metadata;

public final class ScreenMetadata extends Metadata {
	public String getScreenClass() {
		return get("class");
	}

	public String getBackgroundTextureId() {
		return get("background-texture");
	}
	
	public String getBackgroundMusicId() {
		return get("background-music");
	}
}
