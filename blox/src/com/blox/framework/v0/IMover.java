package com.blox.framework.v0;

public interface IMover {
	public static final IMover NULL = new IMover() {
		@Override
		public void move(IMovable movable) {
			
		}
	};
	
	void move(IMovable movable);
}
