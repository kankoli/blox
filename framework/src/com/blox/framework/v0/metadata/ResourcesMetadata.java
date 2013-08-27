package com.blox.framework.v0.metadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Node;

import com.blox.framework.v0.util.Utils;

public final class ResourcesMetadata extends Metadata {
	private Map<String, TextureMetadata> textures = new HashMap<String, TextureMetadata>();
	private Map<String, SoundMetadata> sounds = new HashMap<String, SoundMetadata>();
	private Map<String, MusicMetadata> musics = new HashMap<String, MusicMetadata>();
	private Map<String, FontMetadata> fonts = new HashMap<String, FontMetadata>();

	ResourcesMetadata() {
		
	}
	
	@Override
	protected void loadNode(Node node) {
		loadResources(node, textures, "textures", "texture");
		loadResources(node, sounds, "sounds", "sound");
		loadResources(node, musics, "musics", "music");
		loadResources(node, fonts, "fonts", "font");
	}
	
	@Override
	protected void setAttribute(String key, String value) {
		
	}
	
	private static <T extends ResourceMetadata> void loadResources(Node node, Map<String, T> resources, String nodeName, String childNodeName) {
		Node nodes = Utils.getChildNode(node, nodeName);
		List<Node> resourceNodes = Utils.getChildNodes(nodes, childNodeName);		
		for (Node resourceNode : resourceNodes) {
			T resource = Metadata.fromNode(resourceNode);
			resources.put(resource.getId(), resource);
		}		
	}

	public TextureMetadata getTexture(String id) {
		return textures.get(id);
	}

	public SoundMetadata getSound(String id) {
		return sounds.get(id);
	}

	public MusicMetadata getMusic(String id) {
		return musics.get(id);
	}

	public Set<String> getFontNames() {
		return fonts.keySet();
	}

	public FontMetadata getFont(String id) {
		return fonts.get(id);
	}
}
