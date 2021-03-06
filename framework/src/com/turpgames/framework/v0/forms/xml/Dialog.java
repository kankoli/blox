package com.turpgames.framework.v0.forms.xml;

import java.util.ArrayList;
import java.util.List;

import com.turpgames.framework.v0.ILanguageListener;
import com.turpgames.framework.v0.ISound;
import com.turpgames.framework.v0.ITexture;
import com.turpgames.framework.v0.impl.AttachedText;
import com.turpgames.framework.v0.impl.GameObject;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Drawer;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.ShapeDrawer;
import com.turpgames.framework.v0.util.TextureDrawer;
import com.turpgames.framework.v0.util.Utils;
import com.turpgames.framework.v0.util.Vector;

public class Dialog extends GameObject {
	public static float padding = 15f;
	public static final Color borderColor = Color.white();
	public static final Color activeButtonColor = Color.white();
	public static final Color closeButtonFocusColor = Color.red();
	public static final Color modalBackgroundColor = new Color(0, 0, 0, 0.5f);
	public static final Color dialogBackgroundColor = new Color(0, 0, 0, 0.75f);
	public static ISound clickSound;

	public static float dialogWidth = 0.90f;

	public static interface IDialogListener {
		void onDialogButtonClicked(String id);

		void onDialogClosed();
	}

	private final Text message;
	private float fontScale;
	private IDialogListener listener;
	private List<DialogButton> buttons;
	private DialogCloseButton closeButton;
	private boolean isOpened;

	public Dialog() {
		buttons = new ArrayList<Dialog.DialogButton>();

		closeButton = new DialogCloseButton(this);

		setWidth(Game.getVirtualWidth() * dialogWidth);

		message = new AttachedText(this);
		message.setHorizontalAlignment(Text.HAlignCenter);
		message.setVerticalAlignment(Text.VAlignTop);
		message.setPadX(padding);
		message.setPadY(padding);
		fontScale = 1f;
	}

	public void setListener(IDialogListener listener) {
		this.listener = listener;
	}

	public void setFontScale(float scale) {
		fontScale = scale;
		message.setFontScale(fontScale);
	}

	public DialogButton addButton(String id, String resourceTextId) {
		DialogButton btn = new DialogButton(this);
		btn.setId(id);
		btn.setResourceTextId(resourceTextId);

		buttons.add(btn);

		return btn;
	}

	public void open(String messageText) {
		if (isOpened)
			return;
		isOpened = true;

		message.setText(messageText);
		listenInput(true);

		this.setHeight(message.getTextAreaHeight() + 2 * padding + 100);

		Vector l = getLocation();
		l.x = (Game.getVirtualWidth() - getWidth()) / 2;
		l.y = (Game.getVirtualHeight() - getHeight()) / 2;

		float buttonWidth = (getWidth() - (buttons.size() + 1) * padding) / buttons.size();
		for (int i = 0; i < buttons.size(); i++) {
			DialogButton b = buttons.get(i);
			b.listenInput(true);
			b.setWidth(buttonWidth);
			b.getLocation().set(l.x + (i + 1) * padding + i * b.getWidth(), l.y - 3 * padding - b.getHeight() + 100);
		}

		closeButton.getLocation().set(
				l.x + getWidth() - closeButton.getWidth() / 2,
				l.y + getHeight() - closeButton.getHeight() / 2);

		closeButton.listenInput(true);
		Drawer.getCurrent().register(this, Utils.LAYER_DIALOG);
	}

	public void close() {
		close(false);
	}
	
	private void close(boolean notifyClosed) {
		if (!isOpened)
			return;
		isOpened = false;

		if (clickSound != null)
			clickSound.play();
		Game.vibrate(50);

		closeButton.listenInput(false);
		listenInput(false);
		if (Drawer.getCurrent() != null)
			Drawer.getCurrent().unregister(this);
		for (int i = 0; i < buttons.size(); i++)
			buttons.get(i).listenInput(false);
		
		if (listener != null && notifyClosed)
			listener.onDialogClosed();
	}

	@Override
	public void draw() {
		ShapeDrawer.drawRect(0, 0, Game.getScreenWidth(), Game.getScreenHeight(), modalBackgroundColor, true, true);

		Vector l = getLocation();
		float w = getWidth();
		float h = getHeight();

		ShapeDrawer.drawRect(l.x, l.y, w, h, dialogBackgroundColor, true, false);

		message.draw();

		ShapeDrawer.drawLine(l.x, l.y, l.x + w, l.y, borderColor, false);
		ShapeDrawer.drawLine(l.x, l.y, l.x, l.y + h, borderColor, false);

		ShapeDrawer.drawLine(l.x + w, l.y, l.x + w, l.y + h - closeButton.getHeight() / 2, borderColor, false);
		ShapeDrawer.drawLine(l.x, l.y + h, l.x + w - closeButton.getWidth() / 2, l.y + h, borderColor, false);

		for (int i = 0; i < buttons.size(); i++)
			buttons.get(i).draw();

		closeButton.draw();
	}

	private void buttonTapped(DialogButton button) {
		close(false);
		if (listener != null)
			listener.onDialogButtonClicked(button.getId());
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		if (!isIn(x, y)) {
			close(true);
		}
		return true;
	}

	public static class DialogCloseButton extends GameObject {
		private Dialog parent;
		private ITexture texture;

		DialogCloseButton(Dialog parent) {
			this.parent = parent;
			texture = Game.getResourceManager().getTexture("fw_close");
			setWidth(48f);
			setHeight(48f);
		}

		@Override
		public void draw() {
			if (isTouched())
				getColor().set(closeButtonFocusColor);
			else
				getColor().set(1);
			TextureDrawer.draw(texture, this);
		}

		@Override
		protected boolean onTap() {
			parent.close(true);
			return true;
		}

		@Override
		public void registerSelf() {
			Game.getInputManager().register(this, Utils.LAYER_DIALOG);
		}
	}

	public static class DialogButton extends GameObject implements ILanguageListener {
		private String id;
		private Text text;
		private final Dialog dialog;

		private String resourceTextId;

		DialogButton(Dialog dialog) {
			this.dialog = dialog;
			this.text = new AttachedText(this);
			this.text.setHorizontalAlignment(Text.HAlignCenter);
			this.text.setVerticalAlignment(Text.VAlignCenter);

			Game.getLanguageManager().register(this);
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public void setResourceTextId(String resourceTextId) {
			this.resourceTextId = resourceTextId;
			setText();
		}

		private void setText() {
			this.text.setText(Game.getLanguageManager().getString(this.resourceTextId));
			setHeight(this.text.getTextAreaHeight());
		}

		@Override
		public void draw() {
			if (isTouched())
				text.getColor().set(activeButtonColor);
			else
				text.getColor().set(getColor());
			text.draw();
		}

		@Override
		protected boolean onTap() {
			dialog.buttonTapped(this);
			return true;
		}

		@Override
		public void registerSelf() {
			Game.getInputManager().register(this, Utils.LAYER_DIALOG);
		}

		@Override
		public void onLanguageChanged() {
			setText();
		}
	}

	@Override
	public void registerSelf() {
		Game.getInputManager().register(this, Utils.LAYER_DIALOG);
	}
}