package com.blox.framework.v0.metadata;

public class ResourceMetadata extends Metadata {	
	public String getPath() {
		return attributes.get("path");
	}

	public boolean isPrimary() {
		return "primary".equals(get("resource-load"));
	}
	
	public boolean skip() {
		return "never".equals(get("resource-load"));
	}
}
