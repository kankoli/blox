package com.blox.setgame.model;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.IDrawingInfo;
import com.blox.framework.v0.IFont;
import com.blox.framework.v0.util.FontManager;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.TextDrawer;

class LearningModeTutorial extends SetGameObject {
	public static interface ILearningModeTutorialListener {
		void onTutorialEnd();
	}

	private IFont font;
	private int pageIndex;
	private List<String> pages;

	private SetGameButton nextButton;
	private SetGameButton prevButton;
	private SetGameButton skipButton;

	private ILearningModeTutorialListener listener;

	LearningModeTutorial(ILearningModeTutorialListener listener) {
		this.listener = listener;

		font = FontManager.createDefaultFontInstance();
		font.setSize(18);

		pages = new ArrayList<String>();

		pages.add("Page \n1\n Text");
		pages.add("Page \n2 \nText");
		pages.add("Page \n3\n Text");
		pages.add("Page 4 \nText");
		pages.add("Page \n5 Text");

		nextButton = new SetGameButton();
		nextButton.setWidth(100);
		nextButton.setHeight(30);
		nextButton.setFont(FontManager.createDefaultFontInstance());
		nextButton.setText("Next");
		nextButton.getLocation().set(Game.getVirtualWidth() - 110, 60);
		nextButton.setListener(new SetGameButton.ISetGameButtonListener() {
			@Override
			public void onButtonTapped() {
				next();
			}
		});

		prevButton = new SetGameButton();
		prevButton.setWidth(100);
		prevButton.setHeight(30);
		prevButton.setFont(FontManager.createDefaultFontInstance());
		prevButton.setText("Prev");
		prevButton.getLocation().set(10, 60);
		prevButton.setListener(new SetGameButton.ISetGameButtonListener() {
			@Override
			public void onButtonTapped() {
				prev();
			}
		});

		skipButton = new SetGameButton();
		skipButton.setWidth(100);
		skipButton.setHeight(30);
		skipButton.setFont(FontManager.createDefaultFontInstance());
		skipButton.setText("Skip");
		skipButton.getLocation().set((Game.getVirtualWidth() - 100) / 2, 0);
		skipButton.setListener(new SetGameButton.ISetGameButtonListener() {
			@Override
			public void onButtonTapped() {
				skip();
			}
		});
	}

	void reset() {
		pageIndex = 0;
	}

	private void next() {
		if (pageIndex < pages.size() - 1) {
			pageIndex++;
			if (pageIndex == pages.size() - 1)
				nextButton.setText("Play");
			else
				nextButton.setText("Next");
		}
		else
			notifyTutorialEnd();
	}

	private void prev() {
		if (pageIndex > 0)
			pageIndex--;
		nextButton.setText("Next");
	}

	private void skip() {
		notifyTutorialEnd();
	}

	private void notifyTutorialEnd() {
		if (listener != null)
			listener.onTutorialEnd();
	}

	@Override
	public void draw() {
		drawPage();
		nextButton.draw();
		if (pageIndex > 0)
			prevButton.draw();
		skipButton.draw();
	}

	@Override
	public void listenInput(boolean listen) {
		super.listenInput(listen);
		nextButton.listenInput(listen);
		prevButton.listenInput(listen);
		skipButton.listenInput(listen);
	}

	private void drawPage() {
		TextDrawer.draw(FontManager.defaultFont, (pageIndex + 1) + "/" + pages.size(), IDrawingInfo.viewport, TextDrawer.AlignN);
		Game.renderingShiftY = -100;
		TextDrawer.draw(font, pages.get(pageIndex), IDrawingInfo.viewport, TextDrawer.AlignN);
		Game.renderingShiftY = 0;
	}
}
