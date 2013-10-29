package com.turpgames.framework.v0.metadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.turpgames.framework.v0.util.Utils;

public class GameMetadata {
	private static final GameMetadata instance = new GameMetadata();

	private String gameName;
	private String gameClass;

	private ResourcesMetadata resources;
	private Map<String, String> params = new HashMap<String, String>();
	private Map<String, ScreenMetadata> screens = new HashMap<String, ScreenMetadata>();
	private Map<String, FormMetadata> forms = new HashMap<String, FormMetadata>();
	private Map<String, SkinMetadata> skins = new HashMap<String, SkinMetadata>();
	private Map<String, AnimationMetadata> animations = new HashMap<String, AnimationMetadata>();
	private Map<String, LanguageMetadata> languages = new HashMap<String, LanguageMetadata>();

	private GameMetadata() {

	}

	public static String getGameName() {
		return instance.gameName;
	}

	public static String getGameClass() {
		return instance.gameClass;
	}

	public static String getParam(String key) {
		return instance.params.get(key);
	}

	public static ScreenMetadata getScreen(String id) {
		return instance.screens.get(id);
	}

	public static FormMetadata getForm(String id) {
		return instance.forms.get(id);
	}

	public static ResourcesMetadata getResources() {
		return instance.resources;
	}

	public static SkinMetadata getSkin(String id) {
		return instance.skins.get(id);
	}

	public static AnimationMetadata getAnimation(String id) {
		return instance.animations.get(id);
	}
	
	public static LanguageMetadata getLanguage(String id) {
		return instance.languages.get(id);
	}
	
	public static Set<String> getLanguages() {
		return instance.languages.keySet();
	}
	
	public static void load(Document gameXml) {
		instance.init(gameXml);
	}

	private void init(Document gameXml) {
		params.clear();
		screens.clear();
		forms.clear();
		skins.clear();

		Node gameNode = gameXml.getDocumentElement();

		gameName = Utils.getAttributeValue(gameNode, "id");
		gameClass = Utils.getAttributeValue(gameNode, "class");

		loadParams(Utils.getChildNode(gameNode, "params"));
		load(gameNode, screens, "screens", "screen");
		load(gameNode, forms, "forms", "form");
		load(gameNode, skins, "skins", "skin");
		load(gameNode, animations, "animations", "animation");
		load(gameNode, languages, "languages", "language");
		
		resources = new ResourcesMetadata();
		resources.loadNode(Utils.getChildNode(gameNode, "resources"));
	}

	private void loadParams(Node paramsNode) {
		List<Node> paramNodes = Utils.getChildNodes(paramsNode, "param");
		for (Node paramNode : paramNodes)
			params.put(Utils.getAttributeValue(paramNode, "key"), Utils.getAttributeValue(paramNode, "value"));
	}

	private static <T extends Metadata> void load(Node node, Map<String, T> resources, String nodeName, String childNodeName) {
		Node nodes = Utils.getChildNode(node, nodeName);
		List<Node> resourceNodes = Utils.getChildNodes(nodes, childNodeName);
		for (Node resourceNode : resourceNodes) {
			T resource = Metadata.fromNode(resourceNode);
			resources.put(resource.getId(), resource);
		}
	}
}
