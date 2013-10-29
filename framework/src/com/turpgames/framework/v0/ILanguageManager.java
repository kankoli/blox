package com.turpgames.framework.v0;

public interface ILanguageManager {
	void register(ILanguageListener listener);

	void unregister(ILanguageListener listener);
	
	String getString(String id);
	
	void changeLanguage(String id, String country);
}
