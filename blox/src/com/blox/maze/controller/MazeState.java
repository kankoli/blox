package com.blox.maze.controller;

import com.badlogic.gdx.Input.Keys;
import com.blox.framework.v0.impl.State;
import com.blox.framework.v0.util.Game;

public class MazeState extends State {
    @Override
    public boolean keyDown(int keycode) {
    	if (keycode == Keys.BACK || keycode == Keys.ESCAPE)
    		Game.exit();
    	return super.keyDown(keycode);
    }
}
