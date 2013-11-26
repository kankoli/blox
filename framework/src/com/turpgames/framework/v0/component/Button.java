package com.turpgames.framework.v0.component;

import com.turpgames.framework.v0.ISound;
import com.turpgames.framework.v0.impl.GameObject;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Game;

public abstract class Button extends GameObject {
	public static boolean defaultVibrateOnClick = true;;
	public static ISound defaultClickSound;
	
	private static final int n = 1 << 0;
	private static final int e = 1 << 1;
	private static final int s = 1 << 2;
	private static final int w = 1 << 3;

	public static final int AlignN = n;
	public static final int AlignNE = n | e;
	public static final int AlignE = e;
	public static final int AlignSE = s | e;
	public static final int AlignS = s;
	public static final int AlignSW = s | w;
	public static final int AlignW = w;
	public static final int AlignNW = n | w;
	public static final int AlignCentered = 0;
	
	private boolean vibrateOnClick;
	private ISound clickSound;
	
	protected boolean isActive;
	protected IButtonListener listener;
	protected Color defaultColor;
	protected Color touchedColor;
	
	protected Button(Color defaultColor, Color touchedColor) {
		this();
		setDefaultColor(defaultColor);
		setTouchedColor(touchedColor); 
		vibrateOnClick = defaultVibrateOnClick;
		clickSound = defaultClickSound;
	}

	private Button() {
		activate();
		defaultColor = Color.white();
		touchedColor = new Color();
	}

	public void activate() {
		isActive = true;
		listenInput(true);
	}

	public void deactivate() {
		isActive = false;
		listenInput(false);
	}

	public boolean isActive() {
		return isActive;
	}

	public IButtonListener getListener() {
		return listener;
	}

	public void setListener(IButtonListener listener) {
		this.listener = listener;
	}

	public void setDefaultColor(Color color) {
		defaultColor.set(color.r, color.g, color.b, color.a);
	}
	
	public void setTouchedColor(Color color) {
		touchedColor.set(color.r, color.g, color.b, color.a);
	}
	
	public void setClickSound(ISound sound) {
		this.clickSound = sound;
	}
	
	public void setVibrateOnClick(boolean vibrate) {
		this.vibrateOnClick = vibrate;
	}
	
	@Override
	public boolean ignoreViewport() {
		return true;
	}

	@Override
	public boolean ignoreShifting() {
		return true;
	}
	
	@Override
	protected boolean onTap() {	
		if (clickSound != null)
			clickSound.play();
		
		if (vibrateOnClick)
			Game.vibrate(50);

		if (listener != null)
			listener.onButtonTapped();

		return true;
	}

	@Override
	public void draw() {
		if (!isActive)
			return;

		if (isTouched()) {
			getColor().set(touchedColor.r, touchedColor.g, touchedColor.b, getColor().a);
			getScale().set(1.2f);
			getRotation().origin.set(getLocation().x + getWidth() / 2, getLocation().y + getHeight() / 2);
		}
		else {
			getColor().set(defaultColor.r, defaultColor.g, defaultColor.b, getColor().a);
			getScale().set(1f);
		}

		onDraw();
	}
	
	public void setLocation(int align, float marginX, float marginY) {
		float x = 0;
		float y = 0;
		float width = getWidth();
		float height = getHeight();

		if ((align & e) == e)
			x = Game.getScreenWidth() - width - marginX;
		else if ((align & w) == w)
			x = marginX;
		else
			x = (Game.getScreenWidth() - width) / 2 + marginX;

		if ((align & n) == n)
			y = Game.getScreenHeight() - height - marginY;
		else if ((align & s) == s)
			y = marginY;
		else
			y = (Game.getScreenHeight() - height) / 2 + marginY;

		getLocation().set(x, y);
	}
	
	protected abstract void onDraw();
}