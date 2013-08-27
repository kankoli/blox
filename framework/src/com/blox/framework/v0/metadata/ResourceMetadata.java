package com.blox.framework.v0.metadata;

public abstract class ResourceMetadata extends Metadata {
	private String path;

	public String getPath() {
		return path;
	}

	ResourceMetadata() {

	}

	@Override
	protected void setAttribute(String key, String value) {
		if ("path".equals(key))
			path = value;
		else
			super.setAttribute(key, value);
	}
}
