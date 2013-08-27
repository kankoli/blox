package com.blox.framework.v0.impl;

import com.blox.framework.v0.IActionHandler;

public class FormSwitchActionHandler implements IActionHandler {
	private String formId;
	private boolean back;
	
	public FormSwitchActionHandler(String formId, boolean back) {
		this.formId = formId;
		this.back = back;
	}
	
	@Override
	public void handle() {
		FormScreen.switchTo(formId, back);
	}
}
