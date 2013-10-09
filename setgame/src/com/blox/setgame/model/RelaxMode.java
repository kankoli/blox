package com.blox.setgame.model;

import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.TextDrawer;
import com.blox.setgame.utils.R;

public class RelaxMode extends FullGameMode {
	protected SetGameImageButton resetButton;
	
	public RelaxMode() {
		super();
		hint.getLocation().set(Game.getVirtualWidth() - hint.getWidth() - 10, 50);
		hint.setSlideY(Game.getVirtualHeight() - 100);

		resetButton = new SetGameImageButton();
		resetButton.getLocation().set(10, 50);
		resetButton.setTexture(R.game.textures.refresh);
		resetButton.setListener(new ISetGameButtonListener() {
			@Override
			public void onButtonTapped() {
				endMode();
				startMode();
				deal();
			}
		});
	}

	public void pause() {
		timer.pause();
		hint.deactivate();
		resetButton.listenInput(false);
	}

	public void resume() {
		timer.start();
		hint.activate();
		resetButton.listenInput(true);
	}
	
	@Override
	public void startMode() {
		super.startMode();
		resetButton.listenInput(true);
	}
	
	@Override
	public void endMode() {
		super.endMode();
		resetButton.listenInput(false);
	}
	
	@Override
	public void exitMode() {
		super.exitMode();
		resetButton.listenInput(false);
	}

	@Override
	public void drawGame() {
		drawHint();
		drawResetButton();
		super.drawGame();
	}

	public void drawResult() {
		info.draw("Congratulations,", TextDrawer.AlignCentered, 170);
		info.draw(String.format("You found %d set%s!", setsFound, setsFound != 1 ? "s" : ""), TextDrawer.AlignCentered, 120);
		info.draw("Total Time " + modeCompleteTime, TextDrawer.AlignCentered, 0);
		info.draw("Touch Screen", TextDrawer.AlignCentered, -120);
		info.draw("To Continue", TextDrawer.AlignCentered, -170);
	}

	protected void drawHint() {
		hint.draw();
	}

	protected void drawResetButton() {
		resetButton.draw();
	}
}