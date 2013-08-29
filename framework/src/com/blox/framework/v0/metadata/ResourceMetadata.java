package com.blox.framework.v0.metadata;

public class ResourceMetadata extends Metadata {
	public String getPath() {
		return attributes.get("path");
	}
	
	public boolean isPrimary() {
		return "true".equals(get("primary-resource"));
	}
}
