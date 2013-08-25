package com.blox.framework.v0;

public interface IActionHandler {
	public static final IActionHandler NULL = new IActionHandler() {
		@Override
		public void handle() {
			
		}
	};
	
	void handle();
}
