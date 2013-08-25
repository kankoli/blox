package com.blox.framework.v0.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class GameMetadata {
	private static final GameMetadata instance = new GameMetadata();

	private String gameName;
	private String gameClass;

	private Map<String, GameParam> params = new HashMap<String, GameParam>();
	private Map<String, ScreenMetadata> screens = new HashMap<String, ScreenMetadata>();
	private Map<String, FormMetadata> forms = new HashMap<String, FormMetadata>();
	private Map<String, FontMetadata> fonts = new HashMap<String, FontMetadata>();
	private Map<String, SkinMetadata> skins = new HashMap<String, SkinMetadata>();

	private GameMetadata() {

	}

	public static String getGameName() {
		return instance.gameName;
	}

	public static String getGameClass() {
		return instance.gameClass;
	}

	public static String getParam(String key) {
		GameParam param = instance.params.get(key);
		return param == null ? null : param.getValue();
	}

	public static ScreenMetadata getScreen(String id) {
		return instance.screens.get(id);
	}

	public static FormMetadata getForm(String id) {
		return instance.forms.get(id);
	}

	public static FontMetadata getFont(String id) {
		return instance.fonts.get(id);
	}
	
	public static Set<String> getFontNames() {
		return instance.fonts.keySet();
	}
	
	public static SkinMetadata getSkin(String id) {
		return instance.skins.get(id);
	}
	
	public static void load(String path) {
		instance.init(path);
	}

	private void init(String path) {
		params.clear();
		screens.clear();
		forms.clear();
		fonts.clear();
		skins.clear();

		Document game = Utils.loadXml(path);
		Node gameNode = game.getDocumentElement();

		gameName = Utils.getAttributeValue(gameNode, "id");
		gameClass = Utils.getAttributeValue(gameNode, "class");

		loadParams(Utils.getChildNode(gameNode, "params"));
		loadScreens(Utils.getChildNode(gameNode, "screens"));
		loadForms(Utils.getChildNode(gameNode, "forms"));
		loadFonts(Utils.getChildNode(gameNode, "fonts"));
		loadSkins(Utils.getChildNode(gameNode, "skins"));
	}

	private void loadParams(Node paramsNode) {
		List<Node> paramNodes = Utils.getChildNodes(paramsNode, "param");

		for (Node paramNode : paramNodes) {
			GameParam param = GameParam.fromNode(paramNode);
			params.put(param.getKey(), param);
		}
	}

	private void loadScreens(Node screensNode) {
		List<Node> screenNodes = Utils.getChildNodes(screensNode, "screen");

		for (Node screenNode : screenNodes) {
			ScreenMetadata screen = ScreenMetadata.fromNode(screenNode);
			screens.put(screen.getId(), screen);
		}
	}

	private void loadForms(Node formsNode) {
		List<Node> formNodes = Utils.getChildNodes(formsNode, "form");

		for (Node formNode : formNodes) {
			FormMetadata form = FormMetadata.fromNode(formNode);
			forms.put(form.getId(), form);
		}
	}

	private void loadFonts(Node fontsNode) {
		List<Node> fontNodes = Utils.getChildNodes(fontsNode, "font");

		for (Node fontNode : fontNodes) {
			FontMetadata font = FontMetadata.fromNode(fontNode);
			fonts.put(font.getId(), font);
		}
	}

	private void loadSkins(Node skinsNode) {
		List<Node> skinNodes = Utils.getChildNodes(skinsNode, "skin");

		for (Node skinNode : skinNodes) {
			SkinMetadata skin = SkinMetadata.fromNode(skinNode);
			skins.put(skin.getId(), skin);
		}
	}
}
