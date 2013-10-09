package com.blox.setgame.model;

import com.blox.framework.v0.IFont;
import com.blox.framework.v0.effects.IEffectEndListener;
import com.blox.framework.v0.util.FontManager;
import com.blox.framework.v0.util.ITextSliderListener;
import com.blox.framework.v0.util.TextSlider;
import com.blox.framework.v0.util.Timer;
import com.blox.framework.v0.util.Vector;
import com.blox.setgame.utils.R;

class FullGameHint extends SetGameObject implements ITextSliderListener, IEffectEndListener {
	private final static int notificationInterval = 30;
	
	private SetGameImageButton button;
	private FullGameSetInfo setInfo;
	private Card[] cards;
	private TextSlider textSlider;
	private String text;
	private int hintIndex;
	private float slideY;
	private boolean isActive;
	private IFont font;
	private Timer notificationTimer;

	FullGameHint() {
		button = new SetGameImageButton();
		button.setTexture(R.game.textures.hint);
		button.setListener(new ISetGameButtonListener() {
			@Override
			public void onButtonTapped() {
				showNextHint();
				restartNotificationTimer();
			}
		});

		setInfo = new FullGameSetInfo();
		textSlider = new TextSlider();
		textSlider.setListener(this);
		
		notificationTimer = new Timer();
		notificationTimer.setInterval(notificationInterval);
		notificationTimer.setTickListener(new Timer.ITimerTickListener() {
			@Override
			public void timerTick(Timer timer) {
				button.blink(null, false);
			}
		});
		
		font = FontManager.createDefaultFontInstance();
		font.setScale(R.fontSize.small);
	}
	
	@Override
	public Vector getLocation() {
		return button.getLocation();
	}

	@Override
	public float getWidth() {
		return button.getWidth();
	}
	
	@Override
	public float getHeight() {
		return button.getHeight();
	}
	
	public void restartNotificationTimer() {
		notificationTimer.restart();
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

	public void setSlideY(float slideY) {
		this.slideY = slideY;
	}
	
	private void showNextHint() {
		if (isActive)
			return;

		if (hintIndex == 0) {
			textSlider.slide(font, text, slideY);
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
			text = "No set exists on table";
		else if (count == 1)
			text = "1 set exists on table";
		else
			text = count + " sets exist on table";
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
	public void onTextSlideEnd(TextSlider slider) {
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