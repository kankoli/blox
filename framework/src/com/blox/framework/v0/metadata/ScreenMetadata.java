package com.blox.framework.v0.metadata;

public final class ScreenMetadata extends Metadata {
	private String screenClass;

	ScreenMetadata() {

	}

	public String getScreenClass() {
		return screenClass;
	}

	@Override
	protected void setAttribute(String key, String value) {
		if ("class".equals(key))
			screenClass = value;
		else 
			super.setAttribute(key, value);
	}
}
