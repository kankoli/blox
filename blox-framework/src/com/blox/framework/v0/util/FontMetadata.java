package com.blox.framework.v0.util;

import org.w3c.dom.Node;

public class FontMetadata {

	private String id;
	private String path;
	private int[] sizes;

	public String getId() {
		return id;
	}

	public String getPath() {
		return path;
	}

	public int[] getSizes() {
		return sizes;
	}

	private FontMetadata() {

	}

	static FontMetadata fromNode(Node fontNode) {
		FontMetadata font = new FontMetadata();

		font.id = Utils.getAttributeValue(fontNode, "id");
		font.path = Utils.getAttributeValue(fontNode, "path");

		String sizesAttr = Utils.getAttributeValue(fontNode, "sizes");
		String[] sizeVals = sizesAttr.split(",");
		
		font.sizes = new int[sizeVals.length];
		for (int i = 0; i < sizeVals.length; i++)
			font.sizes[i] = Utils.parseInt(sizeVals[i]);

		return font;
	}

}
