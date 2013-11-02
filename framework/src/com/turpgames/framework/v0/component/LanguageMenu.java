package com.turpgames.framework.v0.component;

import java.util.ArrayList;
import java.util.List;

import com.turpgames.framework.v0.impl.GameObject;
import com.turpgames.framework.v0.impl.Settings;
import com.turpgames.framework.v0.metadata.GameMetadata;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Drawer;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.ShapeDrawer;
import com.turpgames.framework.v0.util.Utils;

public abstract class LanguageMenu extends GameObject implements IButtonListener {
	protected static final Color modalBackgroundColor = new Color(0, 0, 0, 0.75f);
	
	private float buttonSpacing;
	private float buttonWidth;
	private float buttonHeight;
	
	protected ImageButton controlButton;
	private List<LanguageButton> buttons;
	private boolean isActive;
	
	public LanguageMenu(float buttonSpacing, float buttonWidth, float buttonHeight, float controlWidth, float controlHeight) {
		buttons = new ArrayList<LanguageButton>();
		this.buttonSpacing = buttonSpacing;
		this.buttonWidth = buttonWidth;
		this.buttonHeight = buttonHeight;

		addControlButton();
		setControlButtonTexture();
		
		loadLanguageButtons();
		deactivate();
	}

	abstract protected void concreteAddControlButton();
	
	private final void addControlButton() {
		concreteAddControlButton();
		controlButton.setListener(new IButtonListener() {
			@Override
			public void onButtonTapped() {
				toggleActive();
			}
		});
		controlButton.activate();
	}

	private final void loadLanguageButtons() {
		Object[] languages = GameMetadata.getLanguages().toArray();
		LanguageButton btn;
		
		// 3 per row or col
		float x = (Game.getScreenWidth() - (buttonWidth + buttonSpacing) * 3 + buttonSpacing) / 2;
		float y = (Game.getScreenHeight() - (buttonHeight + buttonSpacing) * 3 + buttonSpacing) / 2;
		for(int i = 0; i < languages.length; i++) {
			btn = new LanguageButton((String) languages[i], buttonWidth, buttonHeight, Color.white(), Color.white());
			btn.setLocation(Button.AlignSW, x + (i % 3) * (buttonWidth + buttonSpacing), y + (i / 3) * (buttonWidth + buttonSpacing));
			btn.setListener(this);
			buttons.add(btn);
		}
	}

	private void toggleActive() {
		if (isActive) {
			deactivate();
		}
		else {
			activate();
		}
	}
	
	private void activate() {
		isActive = true;
		for(LanguageButton btn : buttons)
			btn.activate();
	}
	
	private void deactivate() {
		isActive = false;
		for(LanguageButton btn : buttons)
			btn.deactivate();
	}

	private void setControlButtonTexture() {
		controlButton.setTexture(GameMetadata.getLanguage(Settings.getString("language", "en-US")).getFlagTextureId());
	}
	
	@Override
	public void draw() {
		if (isActive)
			ShapeDrawer.drawRect(0, 0, Game.getScreenWidth(), Game.getScreenHeight(), modalBackgroundColor, true, true);
		for(LanguageButton btn : buttons)
			btn.draw();
		controlButton.draw();
	}

	@Override
	public void registerSelf() {
		Drawer.getCurrent().register(this, Utils.LAYER_DIALOG);
		for(LanguageButton btn : buttons)
			btn.registerSelf();
	}

	@Override
	public void onButtonTapped() {
		setControlButtonTexture();
		deactivate();
	}
}
