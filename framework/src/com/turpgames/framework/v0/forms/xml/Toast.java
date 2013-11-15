package com.turpgames.framework.v0.forms.xml;

import com.turpgames.framework.v0.effects.IEffectEndListener;
import com.turpgames.framework.v0.effects.MoveEffect;
import com.turpgames.framework.v0.impl.AttachedText;
import com.turpgames.framework.v0.impl.GameObject;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Drawer;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.ShapeDrawer;
import com.turpgames.framework.v0.util.Timer;
import com.turpgames.framework.v0.util.Utils;

public class Toast extends GameObject {
	public static interface IToastListener {
		void onToastHidden(Toast toast);
	}
	
	private Timer timer;
	private Text text;
	private MoveEffect showEffect;
	private MoveEffect hideEffect;
	private IToastState state;
	private IToastListener listener;

	public Toast() {
		text = new AttachedText(this);
		text.setHorizontalAlignment(Text.HAlignCenter);
		text.setVerticalAlignment(Text.VAlignTop);
		text.setPadX(Game.scale(30));
		text.setPadY(Game.scale(30));
		text.getColor().set(Color.white());

		this.getLocation().set(0, Game.getScreenHeight() + 10);
		this.setWidth(Game.getScreenWidth());
		this.getColor().set(Color.black());

		timer = new Timer();
		timer.setTickListener(timerListener);

		showEffect = new MoveEffect(this);
		showEffect.setDuration(0.5f);
		showEffect.setListener(showEffectListener);
		
		hideEffect = new MoveEffect(this);
		hideEffect.setDuration(0.5f);
		hideEffect.setListener(hideEffectListener);
		hideEffect.setDestination(0, Game.getScreenHeight() + 10);
		
		state = ToastHiddenState.instance;
	}

	public void setSlideDuration(float f) {
		showEffect.setDuration(f);
		hideEffect.setDuration(f);
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

	public void setToastColor(Color color) {
		float alpha = getColor().a;
		getColor().set(color);
		getColor().a = alpha;
	}

	public void setTextColor(Color color) {
		text.getColor().set(color);
	}

	public void dispose() {
		this.listenInput(false);
		if (Drawer.getCurrent() != null)
			Drawer.getCurrent().unregister(this);
		showEffect.stop();
		hideEffect.stop();
	}
	
	private void beginShow(String message, float seconds) {
		text.setText(message);

		this.setHeight(text.getTextAreaHeight() + 2 * text.getPadY());

		timer.setInterval(seconds);

		showEffect.setDestination(0, Game.getScreenHeight() - this.getHeight());
		showEffect.start();

		this.listenInput(true);
		Drawer.getCurrent().register(this, Utils.LAYER_INFO);
		
		state = ToastShowingState.instance;
	}
	
	private void beginHide() {
		timer.stop();

		hideEffect.start();

		this.listenInput(false);

		if (listener != null)
			listener.onToastHidden(this);
		
		state = ToastHidingState.instance;
	}
	
	private void endHide() {
		Drawer.getCurrent().unregister(this);
		state = ToastHiddenState.instance;
	}
	
	private void endShow() {
		timer.start();
		state = ToastShownState.instance;
	}
	
	private void cancelHiding() {
		hideEffect.stop();
	}
	
	private void cancelShowing() {
		showEffect.stop();
	}

	public void show(String message, float seconds) {
		state.beginShow(this, message, seconds);
	}

	public void hide() {
		state.beginHide(this);
	}

	private void onShowComplete() {
		state.endShow(this);
	}

	private void onHideComplete() {
		state.endHide(this);
	}

	private IEffectEndListener showEffectListener = new IEffectEndListener() {
		@Override
		public boolean onEffectEnd(Object obj) {
			onShowComplete();
			return true;
		}
	};

	private IEffectEndListener hideEffectListener = new IEffectEndListener() {
		@Override
		public boolean onEffectEnd(Object obj) {
			onHideComplete();
			return true;
		}
	};

	private Timer.ITimerTickListener timerListener = new Timer.ITimerTickListener() {
		@Override
		public void timerTick(Timer timer) {
			hide();
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

	public void setText(String message) {
		text.setText(message);
	}

	public void setAlpha(float f) {
		getColor().a = f;
	}

	@Override
	public void registerSelf() {
		Game.getInputManager().register(this, Utils.LAYER_INFO);
	}

	private static interface IToastState {
		void beginHide(Toast toast);

		void beginShow(Toast toast, String message, float seconds);

		void endHide(Toast toast);

		void endShow(Toast toast);
	}

	private static abstract class ToastState implements IToastState {
		@Override
		public void beginHide(Toast toast) {
			
		}

		@Override
		public void beginShow(Toast toast,String message, float seconds) {
			
		}

		@Override
		public void endHide(Toast toast) {
			
		}

		@Override
		public void endShow(Toast toast) {
			
		}
	}

	private static class ToastHiddenState extends ToastState {
		public static final ToastHiddenState instance = new ToastHiddenState();
		
		private ToastHiddenState() {
			
		}

		@Override
		public void beginShow(Toast toast,String message, float seconds) {
			toast.beginShow(message, seconds);
		}
	}

	private static class ToastHidingState extends ToastState {
		public static final ToastHidingState instance = new ToastHidingState();
		
		private ToastHidingState() {
			
		}
		
		@Override
		public void beginShow(Toast toast,String message, float seconds) {			
			toast.cancelHiding();
			toast.beginShow(message, seconds);
		}

		@Override
		public void endHide(Toast toast) {
			toast.endHide();
		}
	}

	private static class ToastShownState extends ToastState {
		public static final ToastShownState instance = new ToastShownState();
		
		private ToastShownState() {
			
		}

		@Override
		public void beginHide(Toast toast) {
			toast.beginHide();
		}
	}

	private static class ToastShowingState extends ToastState {
		public static final ToastShowingState instance = new ToastShowingState();
		
		private ToastShowingState() {
			
		}
		
		@Override
		public void beginHide(Toast toast) {			
			toast.cancelShowing();
			toast.beginHide();
		}

		@Override
		public void endShow(Toast toast) {
			toast.endShow();
		}
	}
}