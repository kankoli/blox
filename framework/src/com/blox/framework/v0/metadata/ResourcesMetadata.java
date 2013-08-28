package com.blox.framework.v0.metadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Node;

import com.blox.framework.v0.util.Utils;

public final class ResourcesMetadata extends Metadata {
	private Map<String, ResourceMetadata> resources = new HashMap<String, ResourceMetadata>();

	ResourcesMetadata() {

	}

	@Override
	protected void loadNode(Node node) {
		loadResources(node, "textures", "texture");
		loadResources(node, "sounds", "sound");
		loadResources(node, "musics", "music");
		loadResources(node, "fonts", "font");
	}

	@Override
	protected void setAttribute(String key, String value) {

	}

	private <T extends ResourceMetadata> void loadResources(Node node,
			String nodeName, String childNodeName) {
		Node nodes = Utils.getChildNode(node, nodeName);
		List<Node> resourceNodes = Utils.getChildNodes(nodes, childNodeName);
		for (Node resourceNode : resourceNodes) {
			T resource = Metadata.fromNode(resourceNode);
			resources
					.put(resource.getType() + ":" + resource.getId(), resource);
		}
	}

	public Map<String, ResourceMetadata> getAll() {
		return resources;
	}

	public ResourceMetadata getTexture(String id) {
		return getResource("texture", id);
	}

	public ResourceMetadata getSound(String id) {
		return getResource("sound", id);
	}

	public ResourceMetadata getMusic(String id) {
		return getResource("music", id);
	}

	public ResourceMetadata getFont(String id) {
		return getResource("font", id);
	}

	private ResourceMetadata getResource(String type, String id) {
		return resources.get(type + ":" + id);
	}
}
