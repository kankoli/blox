package com.blox.framework.v0.util;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;

public class SkinMetadata {

	private String id;
	private List<ControlMetadata> controls;
	
	private SkinMetadata() {
		controls = new ArrayList<ControlMetadata>();
	}

	public String getId() {
		return id;
	}

	public List<ControlMetadata> getControls() {
		return controls;
	}

	static SkinMetadata fromNode(Node skinNode) {
		SkinMetadata skin = new SkinMetadata();		
		skin.id = Utils.getAttributeValue(skinNode, "id");
		
		List<Node> controlNodes = Utils.getChildElements(skinNode);
		
		for (Node controlNode : controlNodes)
			skin.controls.add(ControlMetadata.fromNode(controlNode));
		
		return skin;
	}


}
