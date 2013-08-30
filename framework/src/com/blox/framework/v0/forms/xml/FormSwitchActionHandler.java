package com.blox.framework.v0.forms.xml;

import com.blox.framework.v0.impl.FormScreen;

public class FormSwitchActionHandler implements IControlActionHandler {
	private String formId;
	private boolean back;
	
	public FormSwitchActionHandler(String formId, boolean back) {
		this.formId = formId;
		this.back = back;
	}
	
	@Override
	public void handle(Control control) {
		FormScreen.switchTo(formId, back);
	}
}
