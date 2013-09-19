package com.blox.setgame.model;

import com.blox.framework.v0.effects.IEffectEndListener;
import com.blox.framework.v0.util.FontManager;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.TextSlider;

class FullGameHint extends SetGameObject implements TextSlider.ITextSliderListener, IEffectEndListener {
	private SetGameButton button;
	private FullGameSetInfo setInfo;
	private Card[] cards;
	private TextSlider textSlider;
	private String text;
	private int hintIndex;
	private boolean isActive;

	public FullGameHint() {
		button = new SetGameButton();
		button.getLocation().set(7, 60);
		button.setWidth(100);
		button.setHeight(30);
		button.setFont(FontManager.createDefaultFontInstance());
		button.setText("Hint");
		button.setListener(new SetGameButton.ISetGameButtonListener() {			
			@Override
			public void onButtonTapped() {
				showNextHint();
			}
		});

		setInfo = new FullGameSetInfo();
		textSlider = new TextSlider();
		textSlider.setListener(this);
	}

	public void update(Card[] cards) {
		setInfo.update(cards);
		updateText();
		hintIndex = 0;
		isActive = false;
		this.cards = cards;
	}

	public int getSetCount() {
		return setInfo.getSetCount();
	}

	private void showNextHint() {
		if (isActive)
			return;

		if (hintIndex == 0) {
			textSlider.slide(FontManager.defaultFont, text, Game.getVirtualHeight() - 120);
		}
		else {
			int cardIndex = setInfo.getSetCardIndex(hintIndex - 1, 1);
			cards[cardIndex].blink(this, false);
		}

		isActive = true;
	}

	private void updateText() {
		int count = setInfo.getSetCount();
		if (count < 1)
			text = "There is no set exists on table";
		else if (count == 1)
			text = "There is 1 set exists on table";
		else
			text = "There are " + count + " sets exist on table";
	}

	private void hintEnd() {
		hintIndex = (hintIndex + 1) % (1 + setInfo.getSetCount());
		isActive = false;
	}
	
	@Override
	public boolean onEffectEnd(Object card) {
		hintEnd();
		return true;
	}

	@Override
	public void onSlideEnd(TextSlider slider) {
		hintEnd();
	}

	@Override
	public void draw() {
		drawButton();
		drawHint();
	}

	private void drawButton() {
		button.draw();
	}

	private void drawHint() {
		if (isActive && hintIndex == 0)
			textSlider.draw();
	}

	public void activate() {
		button.listenInput(true);
	}

	public void deactivate() {
		button.listenInput(false);		
	}
}