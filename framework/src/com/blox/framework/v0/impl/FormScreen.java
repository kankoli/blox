package com.blox.framework.v0.impl;

import java.util.Stack;

import com.badlogic.gdx.Input.Keys;
import com.blox.framework.v0.IView;
import com.blox.framework.v0.IViewFinder;
import com.blox.framework.v0.IViewSwitcher;
import com.blox.framework.v0.forms.xml.Control;
import com.blox.framework.v0.forms.xml.Form;
import com.blox.framework.v0.forms.xml.UIManager;
import com.blox.framework.v0.util.Game;

public class FormScreen extends Screen implements IViewFinder {
	private static FormScreen currentScreen;

	private Stack<String> formHistory;
	private Form currentForm;
	private IViewSwitcher switcher;

	public FormScreen() {
		formHistory = new Stack<String>();
	}

	private void initFormSwitcher() {
		String screenSwitcher = Game.getParam("form-switcher");
		switcher = ViewSwitcher.createInstance(screenSwitcher);
		switcher.setViewFinder(this);
	}

	protected void setForm(String formId, boolean back) {
		if (back)
			formHistory.pop();
		else if (currentForm != null)
			formHistory.push(currentForm.getId());

		switcher.switchTo(formId, back);
		currentForm = UIManager.getForm(formId);
	}

	protected <T extends Control> T getControl(String id) {
		return currentForm.getControl(id);
	}

	@Override
	public void init() {
		super.init();
		initFormSwitcher();
		registerInputListener(this);
	}

	@Override
	public void update() {
		if (!switcher.isSwitching())
			super.update();
	}

	@Override
	public void render() {
		super.render();
		if (switcher.isSwitching())
			switcher.render();
		else
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

	@Override
	public IView findView(String id) {
		return UIManager.getForm(id);
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.BACK || keycode == Keys.ESCAPE) {
			if (formHistory.size() == 0) {
				if ("true".equals(Game.getParam("allow-exit-on-back"))) {
					Game.exit();
				}
			}
			else {
				setForm(formHistory.peek(), true);
			}
		}
		return super.keyDown(keycode);
	}

	public static void switchTo(String formId, boolean back) {
		currentScreen.setForm(formId, back);
	}
}