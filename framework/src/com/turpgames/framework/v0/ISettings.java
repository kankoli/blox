package com.turpgames.framework.v0;

public interface ISettings {
	void putBoolean(String key, boolean val);

	void putFloat(String key, float val);

	void putInteger(String key, int val);

	void putLong(String key, long val);

	void putString(String key, String val);

	boolean getBoolean(String key, boolean defValue);

	float getFloat(String key, float defValue);

	int getInteger(String key, int defValue);

	long getLong(String key, long defValue);

	String getString(String key, String defValue);
}
