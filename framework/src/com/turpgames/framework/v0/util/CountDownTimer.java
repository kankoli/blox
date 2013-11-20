package com.turpgames.framework.v0.util;

public class CountDownTimer extends Timer {
	public static interface ICountDownListener {
		void onCountDownEnd(CountDownTimer timer);
	}

	private float timeout;
	private ICountDownListener listener;

	public CountDownTimer(float timeout) {
		this.timeout = timeout;
	}

	public void setCountDownListener(ICountDownListener listener) {
		this.listener = listener;
	}

	public float getTimeout() {
		return timeout;
	}

	@Override
	protected void update() {
		super.update();

		if (getRemaining() <= 0) {
			stop();
			if (listener != null)
				listener.onCountDownEnd(this);
		}
	}

	public float getRemaining() {
		return Math.max(0, timeout - getTotalElapsedTime());
	}

	@Override
	public String getText() {
		return Utils.getTimeString((int)getRemaining());
	}
}