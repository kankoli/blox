package com.blox.framework.v0.forms.xml;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.IFont;
import com.blox.framework.v0.impl.GameObject;
import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.Drawer;
import com.blox.framework.v0.util.FontManager;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.ShapeDrawer;
import com.blox.framework.v0.util.TextDrawer;
import com.blox.framework.v0.util.Vector;

public class Dialog extends GameObject {
	public static float buttonSpace = 15f;
	public static Color activeButtonColor = Color.white();

	public static float dialogWidth = 0.75f;

	public static interface IDialogListener {
		void onDialogButtonClicked(String id);
	}

	private static final Color fadingBackground = new Color(0, 0, 0, 0.8f);
	private static final Color borderColor = new Color(1);

	private String message;
	private IFont font;
	private float fontScale;
	private IDialogListener listener;
	private List<DialogButton> buttons;

	public Dialog() {
		buttons = new ArrayList<Dialog.DialogButton>();
		setWidth(Game.getVirtualWidth() * dialogWidth);
		font = FontManager.createDefaultFontInstance();
		fontScale = 1f;
		
		addButton("Yes", "Yes");
		addButton("No", "No");
	}

	public void setListener(IDialogListener listener) {
		this.listener = listener;
	}

	public void setFontScale(float scale) {
		fontScale = scale;
		font.setScale(fontScale);
	}

	private void addButton(String id, String text) {
		DialogButton btn = new DialogButton(this);
		btn.setId(id);
		btn.setText(text);

		buttons.add(btn);
	}

	public void open(String message) {
		this.message = message;
		this.listenInput(true);
		
		Vector size = font.measureText(message);
		setHeight(size.y + 100);

		Vector l = getLocation();
		l.x = (Game.getVirtualWidth() - getWidth()) / 2;
		l.y = (Game.getVirtualHeight() - getHeight()) / 2;

		float buttonWidth = (getWidth() - (buttons.size() + 1) * buttonSpace) / buttons.size();
		for (int i = 0; i < buttons.size(); i++) {
			DialogButton b = buttons.get(i);
			b.setWidth(buttonWidth);
			b.setHeight(b.font.measureText(b.text).y + 20);
			b.listenInput(true);
			b.getLocation().set(l.x + (i + 1) * buttonSpace + i * b.getWidth(), l.y + buttonSpace);
		}

		Drawer.getCurrent().register(this, 1000);
	}

	public void close() {
		listenInput(false);
		Drawer.getCurrent().unregister(this);
		for (int i = 0; i < buttons.size(); i++)
			buttons.get(i).listenInput(false);
	}

	@Override
	public void draw() {
		ShapeDrawer.drawRect(0, 0, Game.getScreenWidth(), Game.getScreenHeight(), fadingBackground, true, true);

		Game.pushRenderingShift(0, -buttonSpace, false);
		TextDrawer.draw(font, message, this, TextDrawer.AlignN);
		Game.popRenderingShift();

		float y = getLocation().y;
		float h = getHeight();

		ShapeDrawer.drawLine(0, y, Game.getVirtualWidth(), y, borderColor, false);
		ShapeDrawer.drawLine(0, y + h, Game.getVirtualWidth(), y + h, borderColor, false);

		for (int i = 0; i < buttons.size(); i++)
			buttons.get(i).draw();
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

	private static class DialogButton extends GameObject {
		private String id;
		private String text;
		private final Dialog dialog;
		private IFont font;

		DialogButton(Dialog dialog) {
			this.dialog = dialog;
			this.font = FontManager.createDefaultFontInstance();
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public void setText(String text) {
			this.text = text;
		}

		@Override
		public void draw() {
			if (isTouched())
				font.getColor().set(activeButtonColor);
			else
				font.getColor().set(1);
			TextDrawer.draw(font, text, this, TextDrawer.AlignCentered);
		}

		@Override
		protected boolean onTap() {
			dialog.buttonTapped(this);
			return true;
		}
	}
}