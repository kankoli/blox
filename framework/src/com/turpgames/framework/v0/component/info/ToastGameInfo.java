package com.turpgames.framework.v0.component.info;

import com.turpgames.framework.v0.forms.xml.Toast;
import com.turpgames.framework.v0.util.Color;

public abstract class ToastGameInfo {
	protected Toast toast;
	
	public ToastGameInfo() {
		toast = new Toast();
	}

	/***
	 * 
	 * @param message
	 * @param showDuration in seconds
	 * @param slideDuration in seconds
	 */
	public void show(String message, float showDuration, float slideDuration) {
		toast.setSlideDuration(slideDuration);
		toast.show(message, showDuration);
	}

	public void hide() {
		toast.hide();
	}
	
	public void setToastListener(Toast.IToastListener listener) {
		toast.setListener(listener);
	}

	public void setTextColor(Color color) {
		toast.setTextColor(color);
	}

	public void setToastColor(Color color) {
		toast.setToastColor(color);
	}
	
	public void setText(String message) {
		toast.setText(message);
	}

	public void setAlpha(float f) {
		toast.setAlpha(f);
	}
}