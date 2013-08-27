package com.blox.framework.v0.metadata;

import com.blox.framework.v0.util.Utils;

public final class FontMetadata extends ResourceMetadata {

	private int[] sizes;

	public int[] getSizes() {
		return sizes;
	}

	FontMetadata() {

	}

	@Override
	protected void setAttribute(String key, String value) {
		if ("sizes".equals(key))
			setSizes(value);
		else
			super.setAttribute(key, value);
	}

	private void setSizes(String sizes) {
		String[] sizeVals = sizes.split(",");

		this.sizes = new int[sizeVals.length];
		for (int i = 0; i < sizeVals.length; i++)
			this.sizes[i] = Utils.parseInt(sizeVals[i]);
	}
}