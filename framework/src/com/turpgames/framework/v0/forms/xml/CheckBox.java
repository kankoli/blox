package com.turpgames.framework.v0.forms.xml;

import java.util.ArrayList;
import java.util.List;

import com.turpgames.framework.v0.ISound;
import com.turpgames.framework.v0.ITexture;
import com.turpgames.framework.v0.util.Game;

public class CheckBox extends DrawableControl {
	private List<IClickListener> clickListeners;
	private boolean isChecked;
	private Style style;
	
	public CheckBox() {
		style = new Style(); 
		clickListeners = new ArrayList<IClickListener>();
	}

	public void addClickListener(IClickListener listener) {
		clickListeners.add(listener);
	}

	public void removeClickListener(IClickListener listener) {
		clickListeners.remove(listener);
	}

	protected void notifyClickListeners() {
		for (IClickListener listener : clickListeners)
			listener.onClick(this);
	}
	
	@Override
	protected void setAttribute(String attribute, String value) {
		if ("texture-checked".equals(attribute))
			style.textureChecked = Game.getResourceManager().getTexture(value);

		else if ("texture-unchecked".equals(attribute))
			style.textureUnchecked = Game.getResourceManager().getTexture(value);

		else if ("sound-click".equals(attribute))
			style.soundClick = Game.getResourceManager().getSound(value);
		
		else
			super.setAttribute(attribute, value);
	}
	
	@Override
	protected ITexture getTexture() {
		return isChecked ? style.textureChecked : style.textureUnchecked;
	}

	@Override
	protected String getNodeName() {
		return "checkbox";
	}

	@Override
	protected void onTap() {
		isChecked = !isChecked;
		notifyClickListeners();
		if (style.soundClick != null)
			style.soundClick.play();
	}	
	
	@Override
	protected void setAction(String action) {
		final IControlActionHandler handler = Game.getActionHandlerFactory().create(this, action);
		addClickListener(new IClickListener() {
			@Override
			public void onClick(Control control) {
				handler.handle(control);				
			}
		});
	}
	
	public boolean isChecked() {
		return isChecked;
	}
	
	public void setChecked(boolean checked) {
		isChecked = checked;
	}

	public class Style {
		public ITexture textureChecked;
		public ITexture textureUnchecked;
		public ISound soundClick;

		private Style() {

		}
	}
}
