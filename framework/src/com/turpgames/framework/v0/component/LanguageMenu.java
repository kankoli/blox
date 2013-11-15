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
	public static interface ILanguageMenuListener {
		void onLanguageMenuActivated();

		void onLanguageMenuDeactivated();
	}

	protected static final Color modalBackgroundColor = new Color(0, 0, 0, 0.75f);

	private float buttonSpacing;
	private float buttonWidth;
	private float buttonHeight;

	private ILanguageMenuListener listener;

	protected ImageButton controlButton;
	private List<LanguageButton> buttons;
	private boolean isActive;

	public LanguageMenu(float buttonSpacing, float buttonWidth, float buttonHeight) {
		buttons = new ArrayList<LanguageButton>();
		this.buttonSpacing = buttonSpacing;
		this.buttonWidth = buttonWidth;
		this.buttonHeight = buttonHeight;

		addControlButton();
		setControlButtonTexture();

		loadLanguageButtons();
		deactivate();

		Drawer.getCurrent().register(this, Utils.LAYER_DIALOG);
	}

	abstract protected void concreteAddControlButton();

	public void setListener(ILanguageMenuListener listener) {
		this.listener = listener;
	}

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

		float w = buttonWidth;
		float h = buttonHeight;
		float s = buttonSpacing;

		float x = (Game.getScreenWidth() - (w + s) * 2 + s) / 2;
		float y = (Game.getScreenHeight() - (h + s) * (languages.length / 2) + s) / 2;
		LanguageButton btn;
		for (int i = 0; i < languages.length; i++) {
			btn = new LanguageButton((String) languages[i], buttonWidth, buttonHeight, Color.white(), Color.white());
			btn.setLocation(Button.AlignSW, x + (i % 2) * (w + s), y + (i / 2) * (h + s));
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

	public void activate() {
		isActive = true;
		for (LanguageButton btn : buttons)
			btn.activate();
		
		if (listener != null)
			listener.onLanguageMenuActivated();
	}

	public void deactivate() {
		isActive = false;
		for (LanguageButton btn : buttons)
			btn.deactivate();
		
		if (listener != null)
			listener.onLanguageMenuDeactivated();
	}

	private void setControlButtonTexture() {
		controlButton.setTexture(GameMetadata.getLanguage(Settings.getString("language", "en-US")).getFlagTextureId());
	}

	@Override
	public void listenInput(boolean listen) {
		if (!listen) {
			controlButton.deactivate();
		}
		else {
			controlButton.activate();
		}
		super.listenInput(listen);
	}
	
	@Override
	public void draw() {
		if (isActive) {
			ShapeDrawer.drawRect(0, 0, Game.getScreenWidth(), Game.getScreenHeight(), modalBackgroundColor, true, true);
			for (int i = 0; i < buttons.size(); i++)
				buttons.get(i).draw();
		}
		controlButton.draw();
	}

	@Override
	public void registerSelf() {
		controlButton.registerSelf();
	}

	@Override
	public void onButtonTapped() {
		setControlButtonTexture();
		deactivate();
	}

	public boolean isActive() {
		return isActive;
	}	
}
