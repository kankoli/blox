package com.blox.setgame.model;

import com.blox.framework.v0.IFont;
import com.blox.framework.v0.effects.IEffectEndListener;
import com.blox.framework.v0.util.FontManager;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.TextDrawer;
import com.blox.framework.v0.util.TextSlider;
import com.blox.framework.v0.util.Utils;

class Hint extends SetGameObject implements TextSlider.ITextSliderListener, IEffectEndListener {

	private final static float slideDuration = 1f;

	private IFont font;

	private FullGameSetInfo setInfo;
	private Card[] cards;
	private TextSlider textSlider;
	private String text;
	private int hintIndex;
	private boolean isActive;

	public Hint() {
		super.getLocation().set(7, 60);
		super.setWidth(100);
		super.setHeight(30);

		setInfo = new FullGameSetInfo();
		textSlider = new TextSlider();
		textSlider.setListener(this);
		font = FontManager.createDefaultFontInstance();
	}

	public void update(Card[] cards) {
		setInfo.update(cards);
		updateText();
		hintIndex = 0;
		isActive = false;
		this.cards = cards;
	}

	private void showNextHint() {
		if (isActive)
			return;

		if (hintIndex == 0) {
			textSlider.slide(FontManager.defaultFont, text, slideDuration, Game.getVirtualHeight() + 40);
		}
		else {
			int cardIndex = setInfo.getSetCardIndex(hintIndex - 1, 1);
			cards[cardIndex].blink(this);
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
	protected boolean onTap() {		
		showNextHint();
		return true;
	}
	
	@Override
	public void draw() {
		drawButton();
		drawHint();
	}

	private void drawButton() {
		if (Utils.isIn(Game.getInputManager().getX(), Game.getInputManager().getY(), this))
			font.getColor().set(0, 1, 0);
		else
			font.getColor().set(1, 1, 1);
		TextDrawer.draw(font, "Hint", this, TextDrawer.AlignSW);
	}

	private void drawHint() {
		if (!isActive)
			return;

		if (hintIndex == 0)
			textSlider.draw();
	}
}