package com.blox.framework.v0.impl;

import com.blox.framework.v0.forms.xml.Form;
import com.blox.framework.v0.forms.xml.UIManager;

public class FormScreen extends Screen {
	private static FormScreen currentScreen;

	private Form currentForm;

	public FormScreen() {

	}

	protected void setForm(String formId) {
		if (currentForm != null)
			currentForm.hide();
		currentForm = UIManager.getForm(formId);
		currentForm.show();
	}

	@Override
	public void render() {
		super.render();
		currentForm.render();
	}

	@Override
	public void activated() {
		super.activated();
		currentScreen = this;
		currentForm.show();
	}
	
	@Override
	public void deactivated() {
		super.deactivated();
		currentScreen = null;
		currentForm.hide();
	}
	
	public static void switchTo(String formId) {
		currentScreen.setForm(formId);
	}
}