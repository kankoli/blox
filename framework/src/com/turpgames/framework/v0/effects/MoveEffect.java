package com.turpgames.framework.v0.effects;

import com.turpgames.framework.v0.IDrawingInfo;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.Vector;

public class MoveEffect extends DrawingEffect {
	private final Vector destination;
	private final Vector speed;

	public MoveEffect(IDrawingInfo obj) {
		super(obj);
		destination = new Vector();
		speed = new Vector();
	}

	public void setDestination(Vector destination) {
		setDestination(destination.x, destination.y);
	}

	public void setDestination(float x, float y) {
		this.destination.set(x, y);
		updateSpeed();
	}

	@Override
	protected void onStart() {
		updateSpeed();
	}

	protected void updateSpeed() {
		speed.set(
			(destination.x - obj.getLocation().x) / duration,
			(destination.y - obj.getLocation().y) / duration
		);
	}
	
	@Override
	protected void onStop() {
		obj.getLocation().set(destination);
	}	

	@Override
	protected void onUpdate() {
		obj.getLocation().add(speed.tmp().mul(Game.getDeltaTime()));
	}
}