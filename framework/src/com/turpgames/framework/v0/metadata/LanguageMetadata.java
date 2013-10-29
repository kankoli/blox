package com.turpgames.framework.v0.metadata;

public class LanguageMetadata extends Metadata {
	public String getCountry() {
		return get("country");
	}
	
	public String getFlagTextureId() {
		return get("flag-texture-id");
	}
}
