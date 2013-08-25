package com.blox.maze.view;

import com.badlogic.gdx.Input.Keys;
import com.blox.framework.v0.forms.xml.Button;
import com.blox.framework.v0.forms.xml.Control;
import com.blox.framework.v0.forms.xml.IClickListener;
import com.blox.framework.v0.forms.xml.Form;
import com.blox.framework.v0.forms.xml.UIManager;
import com.blox.framework.v0.util.Game;
import com.blox.maze.model.Background;
import com.blox.maze.util.R;

public class MainMenuScreen extends MazeScreenBase implements IClickListener {
	private Form panel;
	
	public MainMenuScreen(MazeGame game) {
		super(game);
	}
	
	@Override
	public void init() {
		super.init();

		panel = UIManager.getForm(R.menus.main.xmlPath);
		
		Button btnNew = panel.getControl(R.menus.main.btnNewGame);
		btnNew.addClickListener(this);
		
		registerInputListener(this);
		registerDrawable(new Background(R.animations.Background.first), 1);
	}
		
	@Override
	public void render() {
		super.render();
	}
			
	@Override
    public boolean keyDown(int keycode) {
    	if (keycode == Keys.BACK || keycode == Keys.ESCAPE)
    		Game.exit();
    	return super.keyDown(keycode);
    }

	@Override
	public void onClick(Control control) {
		game.showMaze();		
	}
}
