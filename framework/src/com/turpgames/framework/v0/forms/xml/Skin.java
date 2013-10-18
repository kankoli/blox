package com.turpgames.framework.v0.forms.xml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.turpgames.framework.v0.metadata.ControlMetadata;
import com.turpgames.framework.v0.metadata.GameMetadata;
import com.turpgames.framework.v0.metadata.SkinMetadata;

public class Skin {
	private Map<String, Map<String, String>> values;
	private String id;
	
	public Skin() {
		values = new HashMap<String, Map<String,String>>();
	}
	
	public void put(String control, String key, String value) {
		Map<String, String> ctrlMap = values.get(control);
		if (ctrlMap == null) {
			ctrlMap = new HashMap<String, String>();
			values.put(control, ctrlMap);
		}
		ctrlMap.put(key, value);
	}
	
	public Map<String, String> get(String control) {
		return values.get(control);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	static Skin load(String skinId) {
		SkinMetadata metadata = GameMetadata.getSkin(skinId);
		if (metadata == null)
			return null;
		
		Skin skin = new Skin();
		skin.id = metadata.getId();
		
		List<ControlMetadata> controlsMetadata = metadata.getControls();
		for (ControlMetadata controlMetadata : controlsMetadata) {
			for (String key : controlMetadata.getAttributes().keySet()) {
				skin.put(controlMetadata.getType(), key, controlMetadata.getAttributes().get(key));
			}
		}
		
		return skin;
	}
}
