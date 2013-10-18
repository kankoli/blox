package com.turpgames.framework.v0.metadata;

import com.turpgames.framework.v0.util.Utils;

public final class AnimationMetadata extends Metadata {
	private String textureId;
	private float duration;
	private int width;
	private int height;
	private boolean looping;

	AnimationMetadata() {
	}

	public String getTextureId() {
		return textureId;
	}

	public float getDuration() {
		return duration;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isLooping() {
		return looping;
	}

	@Override
	protected void setAttribute(String key, String value) {
		if ("texture".equals(key))
			textureId = value;
		if ("duration".equals(key))
			duration = Utils.parseFloat(value);
		if ("width".equals(key))
			width = Utils.parseInt(value);
		if ("height".equals(key))
			height = Utils.parseInt(value);
		if ("loop".equals(key))
			looping = "true".equals(value);
		else
			super.setAttribute(key, value);
	}
}