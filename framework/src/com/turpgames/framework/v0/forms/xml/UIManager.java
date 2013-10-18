package com.turpgames.framework.v0.forms.xml;

import java.util.HashMap;
import java.util.Map;

import com.turpgames.framework.v0.metadata.ControlMetadata;

public final class UIManager {
	private static Map<String, Form> forms = new HashMap<String, Form>();
	private static Map<String, Skin> skins = new HashMap<String, Skin>();

	public static Form getForm(String formId) {
		if (forms.containsKey(formId))
			return forms.get(formId);

		Form form = Form.load(formId);
		forms.put(formId, form);
		return form;
	}

	public static Skin getSkin(String skinId) {
		if (skins.containsKey(skinId))
			return skins.get(skinId);

		Skin skin = Skin.load(skinId);
		skins.put(skinId, skin);
		return skin;
	}

	private static Control createControl(String name) {
		if ("button".equals(name))
			return new Button();

		if ("checkbox".equals(name))
			return new CheckBox();

		if ("image".equals(name))
			return new Image();

		if ("label".equals(name))
			return new Label();

		return null;
	}
	
	static Control createControl(ControlMetadata metadata) {
		Control control = createControl(metadata.getType());
		control.initAttributes(metadata.getAttributes());
		return control;
	}

	private UIManager() {

	}
}