package com.turpgames.framework.v0.forms.xml;


public interface IControlActionHandler {
	public static final IControlActionHandler NULL = new IControlActionHandler() {
		@Override
		public void handle(Control control) {
			
		}
	};
	
	void handle(Control control);
}
