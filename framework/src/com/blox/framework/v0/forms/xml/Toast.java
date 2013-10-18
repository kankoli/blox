package com.blox.framework.v0.forms.xml;

import com.blox.framework.v0.effects.IEffectEndListener;
import com.blox.framework.v0.effects.MoveEffect;
import com.blox.framework.v0.impl.AttachedText;
import com.blox.framework.v0.impl.GameObject;
import com.blox.framework.v0.impl.Text;
import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.Drawer;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.ShapeDrawer;
import com.blox.framework.v0.util.Timer;

public class Toast extends GameObject {
	public static interface IToastListener {
		void onToastHidden(Toast toast);
	}

	private Timer timer;
	private Text text;
	private MoveEffect effect;
	private boolean isActive;
	private IToastListener listener;

	public Toast() {
		text = new AttachedText(this);
		text.setHorizontalAlignment(Text.HAlignCenter);
		text.setVerticalAlignment(Text.VAlignTop);
		text.setPadX(Game.scale(30));
		text.setPadY(Game.scale(30));

		this.getLocation().set(0, Game.getScreenHeight() + 10);
		this.setWidth(Game.getScreenWidth());
		this.getColor().set(0, 0, 0, 0.8f);

		timer = new Timer();
		timer.setTickListener(timerListener);

		effect = new MoveEffect(this);
		effect.setDuration(0.5f);
		effect.setListener(effectListener);
	}

	public void setPadX(float padX) {
		text.setPadX(Game.scale(padX));
	}

	public void setPadY(float padY) {
		text.setPadY(Game.scale(padY));
	}

	public void setListener(IToastListener listener) {
		this.listener = listener;
	}

	public void setFontScale(float scale) {
		text.setFontScale(scale);
	}

	public void setTextColor(Color color) {
		text.getColor().set(color);
	}

	public void show(String message, float millis) {
		if (isActive)
			return;

		this.isActive = true;

		text.setText(message);

		this.setHeight(text.getTextAreaHeight() + 2 * text.getPadY());

		timer.setInterval(millis / 1000f);

		effect.setDestination(0, Game.getScreenHeight() - this.getHeight());
		effect.start();

		this.listenInput(true);
		Drawer.getCurrent().register(this, 1000);
	}

	public void dispose() {
		hide();
		effect.stop();
	}

	public void hide() {
		if (!isActive)
			return;

		this.isActive = false;
		timer.stop();

		effect.setDestination(0, Game.getScreenHeight() + 10);
		effect.start();

		this.listenInput(false);

		if (listener != null)
			listener.onToastHidden(this);
	}

	private void effectEnd() {
		if (isActive)
			timer.start();
		else
			Drawer.getCurrent().unregister(this);
	}

	private Timer.ITimerTickListener timerListener = new Timer.ITimerTickListener() {
		@Override
		public void timerTick(Timer timer) {
			hide();
		}
	};

	private IEffectEndListener effectListener = new IEffectEndListener() {
		@Override
		public boolean onEffectEnd(Object obj) {
			effectEnd();
			return true;
		}
	};

	@Override
	public boolean ignoreViewport() {
		return true;
	}

	@Override
	public void draw() {
		ShapeDrawer.drawRect(this, true);
		text.draw();
	}

	@Override
	protected boolean onTap() {
		hide();
		return true;
	}
}