package com.blox.framework.v0;

import java.util.Map;

import com.blox.framework.v0.metadata.FontMetadata;

public interface IFontFactory {
	Map<Integer, IFont> create(FontMetadata metadata);
}
