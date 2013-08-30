package com.blox.framework.v0.impl;

import com.blox.framework.v0.forms.xml.CheckBox;
import com.blox.framework.v0.forms.xml.Control;
import com.blox.framework.v0.forms.xml.IControlActionHandler;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Utils;

public final class BindingActionHandler {
	private BindingActionHandler() {

	}

	public static IControlActionHandler create(Control control, String binding) {
		if (binding.startsWith("settings."))
			return new SettingsActionHandler(control, binding.substring(9));
		return IControlActionHandler.NULL;
	}

	private static class SettingsActionHandler implements IControlActionHandler {
		private final String key;
		private final String defaultValue;

		private SettingsActionHandler(Control control, String bindingValue) {
			String[] ss = bindingValue.split(",");
			this.key = ss[0];
			this.defaultValue = ss[1];
			if (control instanceof CheckBox)
				((CheckBox) control).setChecked(Game.getSettings().getBoolean(key, Utils.parseBoolean(defaultValue)));
		}

		@Override
		public void handle(Control control) {
			if (control instanceof CheckBox) {
				boolean on = ((CheckBox) control).isChecked();
				Game.getSettings().putBoolean(key, on);
			}
		}
	}
}
