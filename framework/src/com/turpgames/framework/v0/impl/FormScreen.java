package com.turpgames.framework.v0.impl;

import java.util.Stack;

import com.badlogic.gdx.Input.Keys;
import com.turpgames.framework.v0.IView;
import com.turpgames.framework.v0.IViewFinder;
import com.turpgames.framework.v0.IViewSwitcher;
import com.turpgames.framework.v0.forms.xml.Control;
import com.turpgames.framework.v0.forms.xml.Form;
import com.turpgames.framework.v0.forms.xml.UIManager;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.Utils;

public class FormScreen extends Screen implements IViewFinder {
	private static FormScreen currentScreen;

	protected Stack<String> formHistory;
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
		onFormDeactivated(currentForm);
		currentForm = UIManager.getForm(formId);
		onFormActivated(currentForm);
	}

	protected Form getCurrentForm() {
		return currentForm;
	}
	
	protected void onFormActivated(Form activatedForm) {
		
	}

	protected void onFormDeactivated(Form deactivatedForm) {
		
	}

	protected <T extends Control> T getControl(String id) {
		return currentForm.getControl(id);
	}

	@Override
	public void init() {
		super.init();
		initFormSwitcher();
		registerInputListener(this);
		registerDrawable(switcher, Utils.LAYER_SCREEN);
	}

	@Override
	protected void onAfterActivate() {
		currentScreen = this;
		currentForm.show();
	}

	@Override
	protected void onAfterDeactivate() {
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

	public void back() {
		if (formHistory.size() > 0)
			setForm(formHistory.peek(), true);
	}
}