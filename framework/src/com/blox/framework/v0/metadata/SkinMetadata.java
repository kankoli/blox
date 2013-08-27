package com.blox.framework.v0.metadata;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;

import com.blox.framework.v0.util.Utils;

public final class SkinMetadata extends Metadata {
	private List<ControlMetadata> controls;
	
	SkinMetadata() {
		controls = new ArrayList<ControlMetadata>();
	}

	public List<ControlMetadata> getControls() {
		return controls;
	}

	@Override
	protected void loadNode(Node node) {
		super.loadNode(node);
		
		List<Node> controlNodes = Utils.getChildElements(node);		
		for (Node controlNode : controlNodes)
			controls.add((ControlMetadata)Metadata.fromNode(controlNode));
	}
}