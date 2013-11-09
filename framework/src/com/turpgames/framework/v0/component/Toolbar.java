package com.turpgames.framework.v0.component;

import com.turpgames.framework.v0.impl.GameObject;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.Utils;

public abstract class Toolbar extends GameObject {
	public static interface IToolbarListener {
		void onToolbarBack();
	}

	private boolean isActive;

	protected ImageButton backButton;
	protected ImageButton settingsButton;

	protected ToggleButton soundButton;
	protected ToggleButton vibrationButton;

	private IToolbarListener listener;

	protected Toolbar() {
		addBackButton();
		addSettingsButton();
		addSoundButton();
		addVibrationButton();
		
		updateSizeAndLocation();

		listenInput(true);
	}
	
	private void updateSizeAndLocation() {
		setWidth(Game.getVirtualWidth() - vibrationButton.getLocation().x);
		setHeight(vibrationButton.getHeight() + 20);
		getLocation().set(vibrationButton.getLocation());
	}

	public void setListener(IToolbarListener listener) {
		this.listener = listener;
	}

	public void activateBackButton() {
		backButton.activate();
	}

	public void deactivateBackButton() {
		backButton.deactivate();
	}

	abstract protected void concreteAddBackButton();

	private final void addBackButton() {
		concreteAddBackButton();
		backButton.deactivate();
		backButton.setListener(new IButtonListener() {
			@Override
			public void onButtonTapped() {
				if (listener != null)
					listener.onToolbarBack();
			}
		});
	}

	abstract protected void concreteAddSettingsButton();

	private final void addSettingsButton() {
		concreteAddSettingsButton();
		settingsButton.activate();
		settingsButton.setListener(new IButtonListener() {
			@Override
			public void onButtonTapped() {
				toggleActivation();
			}
		});
	}

	private void toggleActivation() {
		soundButton.toggleActivation();
		if (!Game.isIOS())
			vibrationButton.toggleActivation();
		isActive = !isActive;
	}

	abstract protected void concreteAddSoundButton();

	private final void addSoundButton() {
		concreteAddSoundButton();
		soundButton.deactivate();
	}

	abstract protected void concreteAddVibrationButton();

	private final void addVibrationButton() {
		concreteAddVibrationButton();
		vibrationButton.deactivate();
	}
	
	@Override
	public boolean ignoreViewport() {
		return true;
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		if (isActive && !isTouched())
			toggleActivation();
		return super.touchDown(x, y, pointer, button);
	}

	@Override
	public void draw() {
		backButton.draw();
		settingsButton.draw();
		soundButton.draw();
		vibrationButton.draw();
	}

	@Override
	public void registerSelf() {
		Game.getInputManager().register(this, Utils.LAYER_SCREEN);
	}
}
