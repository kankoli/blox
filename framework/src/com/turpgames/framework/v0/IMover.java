package com.turpgames.framework.v0;

public interface IMover {
	public static final IMover NULL = new IMover() {
		@Override
		public void move(IMovable movable) {

		}
		
		@Override
		public void setEndListener(IMoveEndListener endListener) {
			
		}
	};

	void move(IMovable movable);
	
	void setEndListener(IMoveEndListener endListener);
}
