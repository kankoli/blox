package com.blox.framework.v0.impl;

import com.blox.framework.v0.ISettings;
import com.blox.framework.v0.ISettingsChangeListener;
import com.blox.framework.v0.util.Game;

public final class Settings extends Manager<ISettingsChangeListener> {

	private final static ISettings settings = Game.getSettings();
	private final static Settings instance = new Settings();

	private String key;
	private Object value;

	private Settings() {

	}

	@Override
	protected void execute(ISettingsChangeListener item) {
		if (key == null)
			return;
		item.settingChanged(key, value);
		key = null;
		value = null;
	}
	
	public static void registerListener(ISettingsChangeListener listener) {
		instance.register(listener);
	}
	
	public static void unregisterListener(ISettingsChangeListener listener) {
		instance.unregister(listener);
	}

	public static void putBoolean(String key, boolean val) {
		settings.putBoolean(key, val);
		instance.key = key;
		instance.value = val;
		instance.execute();
	}

	public static void putFloat(String key, float val) {
		settings.putFloat(key, val);
		instance.key = key;
		instance.value = val;
		instance.execute();
	}

	public static void putInteger(String key, int val) {
		settings.putInteger(key, val);
		instance.key = key;
		instance.value = val;
		instance.execute();
	}

	public static void putLong(String key, long val) {
		settings.putLong(key, val);
		instance.key = key;
		instance.value = val;
		instance.execute();
	}

	public static void putString(String key, String val) {
		settings.putString(key, val);
		instance.key = key;
		instance.value = val;
		instance.execute();
	}

	public static boolean getBoolean(String key, boolean defValue) {
		return settings.getBoolean(key, defValue);
	}

	public static float getFloat(String key, float defValue) {
		return settings.getFloat(key, defValue);
	}

	public static int getInteger(String key, int defValue) {
		return settings.getInteger(key, defValue);
	}

	public static long getLong(String key, long defValue) {
		return settings.getLong(key, defValue);
	}

	public static String getString(String key, String defValue) {
		return settings.getString(key, defValue);
	}	

	public static boolean isSoundOn() {
		return getBoolean("sound", true);
	}

	public static boolean isVibrationOn() {
		return getBoolean("vibration", true);
	}

	public static boolean isMusicOn() {
		return getBoolean("music", true);
	}
}