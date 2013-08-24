package com.blox.framework.v0.impl;

import com.blox.framework.v0.IActionHandler;

public class FormSwitchActionHandler implements IActionHandler {
	private String formId;
	
	public FormSwitchActionHandler(String formId) {
		this.formId = formId;
	}
	
	@Override
	public void handle() {
		FormScreen.switchTo(formId);
	}
}
