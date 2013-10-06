package com.blox.framework.v0.util;

public class Timer {
	public static interface ITimerTickListener {
		void timerTick(Timer timer);
	}
	
	private final static int State_Stopped = 0;
	private final static int State_Started = 1;
	private final static int State_Paused = 2;

	private int state;
	private float totalElapsed;
	private float elapsed;
	private float interval;
	private ITimerTickListener tickListener;
	
	public Timer() {
		state = State_Stopped;
	}
	
	protected void update() {
		float dt = Game.getDeltaTime();
		elapsed += dt;
		totalElapsed += dt; 
		if (elapsed >= interval) {			
			elapsed -= interval;
			if (tickListener != null)
				tickListener.timerTick(this);
		}
	}
	
	public boolean isStopped() {
		return state == State_Stopped;
	}
	
	public boolean isRunning() {
		return state == State_Started;
	}
	
	public boolean isPaused() {
		return state == State_Paused;
	}

	public float getInterval() {
		return interval;
	}
	
	/**
	 * Causes timer to stop, if already running restarts
	 * @param interval
	 */
	public synchronized void setInterval(float interval) {
		this.interval = interval;
		
		int s = state;
		stop();
		if (s == State_Started)
			start();
	}

	public ITimerTickListener getTickListener() {
		return tickListener;
	}

	public void setTickListener(ITimerTickListener tickListener) {
		this.tickListener = tickListener;
	}

	public float getTotalElapsedTime() {
		return totalElapsed;
	}

	public float getElapsedTime() {
		return elapsed;
	}

	public synchronized void restart() {
		stop();
		start();
	}
	
	public synchronized void start() {
		if (state != State_Started)
			TimerManager.instance.register(this);
		state = State_Started;
	}

	public synchronized void stop() {
		if (state == State_Stopped)
			return;
		elapsed = 0;
		totalElapsed = 0;
		TimerManager.instance.unregister(this);
		state = State_Stopped;
	}

	public synchronized void pause() {
		if (state != State_Started)
			return;
		TimerManager.instance.unregister(this);
		state = State_Paused;
	}
}
