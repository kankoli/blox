package com.blox;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.blox.framework.v0.impl.libgdx.GdxGame;
import com.blox.maze.view.MazeGame;

import android.os.Bundle;
import android.view.WindowManager;

public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		cfg.useGL20 = false;
		cfg.useAccelerometer = true;
		cfg.useCompass = true;
		
		initialize(new GdxGame(new MazeGame()), cfg);
    }
}