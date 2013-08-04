package com.blox.maze.view;

import com.badlogic.gdx.Input.Keys;
import com.blox.framework.v0.forms.Button;
import com.blox.framework.v0.forms.ControlBase;
import com.blox.framework.v0.forms.ControlManager;
import com.blox.framework.v0.forms.IClickListener;
import com.blox.framework.v0.util.Game;
import com.blox.maze.model.Background;
import com.blox.maze.util.R;

public class MainMenuScreen extends MazeScreenBase implements IClickListener {
	public MainMenuScreen(MazeGame game) {
		super(game);
	}
	
	@Override
	public void init() {
		super.init();
		registerDrawable(new Background(R.animations.Background.first), 1);
		registerInputListener(this);
		
		Button btn = new Button();
		btn.setLocation(162, 380);
		btn.setSize(125, 40);
		btn.setText("New Game");
		btn.registerClickListener(this);
		registerDrawable(btn, 2);
		
		ControlManager mgr = new ControlManager();
		mgr.register(btn);
		registerInputListener(mgr);
	}
			
	@Override
    public boolean keyDown(int keycode) {
    	if (keycode == Keys.BACK || keycode == Keys.ESCAPE)
    		Game.exit();
    	return super.keyDown(keycode);
    }

	@Override
	public void clicked(ControlBase control) {
		game.showMaze();
	}
}
