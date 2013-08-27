package com.blox.framework.v0.impl;

import com.blox.framework.v0.IView;
import com.blox.framework.v0.IViewFinder;
import com.blox.framework.v0.IViewSwitcher;
import com.blox.framework.v0.forms.xml.Form;
import com.blox.framework.v0.forms.xml.UIManager;
import com.blox.framework.v0.util.Game;

public class FormScreen extends Screen implements IViewFinder {
	private static FormScreen currentScreen;

	private Form currentForm;
	private IViewSwitcher switcher;

	public FormScreen() {
		
	}

	private void initFormSwitcher() {
		String screenSwitcher = Game.getParam("form-switcher");
		switcher = ViewSwitcher.createInstance(screenSwitcher);
		switcher.setViewFinder(this);
	}

	protected void setForm(String formId, boolean back) {
		switcher.switchTo(formId, back);
		currentForm = UIManager.getForm(formId);
	}
	
	@Override
	public void init() {
		super.init();
		initFormSwitcher();
	}

	@Override
	public void update() {
		if (!switcher.isSwitching())
			super.update();
	}

	@Override
	public void render() {
		if (switcher.isSwitching()) {
			switcher.render();
		}
		else {
			super.render();
			currentForm.render();
		}
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

	@Override
	public IView findView(String id) {
		return UIManager.getForm(id);
	}

	public static void switchTo(String formId, boolean back) {
		currentScreen.setForm(formId, back);
	}
}