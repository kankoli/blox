package com.blox.framework.v0.forms.xml;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.impl.AttachedText;
import com.blox.framework.v0.impl.GameObject;
import com.blox.framework.v0.impl.Text;
import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.Drawer;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.ShapeDrawer;
import com.blox.framework.v0.util.TextureDrawer;
import com.blox.framework.v0.util.Vector;

public class Dialog extends GameObject {
	public static float padding = 15f;
	public static Color white = Color.white();
	public static Color activeButtonColor = Color.white();
	public static Color closeButtonFocusColor = Color.red();

	public static float dialogWidth = 0.75f;

	public static interface IDialogListener {
		void onDialogButtonClicked(String id);
	}

	private static final Color fadingBackground = new Color(0, 0, 0, 0.9f);

	private final Text message;
	private float fontScale;
	private IDialogListener listener;
	private List<DialogButton> buttons;
	private DialogCloseButton closeButton;

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

		addButton("Yes", "Yes");
		addButton("No", "No");
	}

	public void setListener(IDialogListener listener) {
		this.listener = listener;
	}

	public void setFontScale(float scale) {
		fontScale = scale;
		message.setFontScale(fontScale);
	}

	private DialogButton addButton(String id, String text) {
		DialogButton btn = new DialogButton(this);
		btn.setId(id);
		btn.setText(text);

		buttons.add(btn);

		return btn;
	}

	public void open(String messageText) {
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
		Drawer.getCurrent().register(this, 1000);
	}

	public void close() {
		closeButton.listenInput(false);
		listenInput(false);
		Drawer.getCurrent().unregister(this);
		for (int i = 0; i < buttons.size(); i++)
			buttons.get(i).listenInput(false);
	}

	@Override
	public void draw() {
		ShapeDrawer.drawRect(0, 0, Game.getScreenWidth(), Game.getScreenHeight(), fadingBackground, true, true);

		message.draw();

		Vector l = getLocation();
		float w = getWidth();
		float h = getHeight();

		ShapeDrawer.drawLine(l.x, l.y, l.x + w, l.y, white, false);
		ShapeDrawer.drawLine(l.x, l.y, l.x, l.y + h, white, false);
		
		ShapeDrawer.drawLine(l.x + w, l.y, l.x + w, l.y + h - closeButton.getHeight() / 2, white, false);
		ShapeDrawer.drawLine(l.x, l.y + h, l.x + w - closeButton.getWidth() / 2, l.y + h, white, false);

		for (int i = 0; i < buttons.size(); i++)
			buttons.get(i).draw();

		closeButton.draw();
	}

	private void buttonTapped(DialogButton button) {
		if (listener != null)
			listener.onDialogButtonClicked(button.getId());
		close();
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		if (!isIn(x, y)) {
			close();
		}
		return true;
	}

	private static class DialogCloseButton extends GameObject {
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
			parent.close();
			return true;
		}
	}

	private static class DialogButton extends GameObject {
		private String id;
		private Text text;
		private final Dialog dialog;

		DialogButton(Dialog dialog) {
			this.dialog = dialog;
			this.text = new AttachedText(this);
			this.text.setHorizontalAlignment(Text.HAlignCenter);
			this.text.setVerticalAlignment(Text.VAlignCenter);
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public void setText(String text) {
			this.text.setText(text);
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
	}
}