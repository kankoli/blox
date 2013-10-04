package com.blox.setgame.model;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.IDrawingInfo;
import com.blox.framework.v0.IFont;
import com.blox.framework.v0.util.FontManager;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.TextDrawer;
import com.blox.setgame.utils.R;

class LearningModeTutorial extends SetGameObject {
	private IFont font;
	private int pageIndex;
	private List<String> pages;

	private SetGameTextButton nextButton;
	private SetGameTextButton prevButton;
	private SetGameTextButton skipButton;

	private ILearningModeTutorialListener listener;

	LearningModeTutorial(ILearningModeTutorialListener listener) {
		this.listener = listener;

		font = FontManager.createDefaultFontInstance();
		font.setScale(R.fontSize.small);

		pages = new ArrayList<String>();

		pages.add("Page \n1\n Text");
		pages.add("Page \n2 \nText");
		pages.add("Page \n3\n Text");
		pages.add("Page 4 \nText");
		pages.add("Page \n5 Text");

		nextButton = new SetGameTextButton();
		nextButton.setFont(FontManager.createDefaultFontInstance());
		nextButton.setText("Next");
		nextButton.getLocation().set(Game.getVirtualWidth() - (nextButton.getWidth() + 10), 100);
		nextButton.setListener(new ISetGameButtonListener() {
			@Override
			public void onButtonTapped() {
				next();
			}
		});

		prevButton = new SetGameTextButton();
		prevButton.setFont(FontManager.createDefaultFontInstance());
		prevButton.setText("Prev");
		prevButton.deactivate();
		prevButton.getLocation().set(10, 100);
		prevButton.setListener(new ISetGameButtonListener() {
			@Override
			public void onButtonTapped() {
				prev();
			}
		});

		skipButton = new SetGameTextButton();
		skipButton.setWidth(100);
		skipButton.setHeight(30);
		skipButton.setFont(FontManager.createDefaultFontInstance());
		skipButton.setText("Skip");
		skipButton.getLocation().set((Game.getVirtualWidth() - skipButton.getWidth()) / 2, 50);
		skipButton.setListener(new ISetGameButtonListener() {
			@Override
			public void onButtonTapped() {
				skip();
			}
		});
	}

	void start() {
		pageIndex = 0;
		prevButton.deactivate();
		nextButton.activate();
		skipButton.activate();	
		nextButton.setText("Next");		
	}

	void end() {
		prevButton.deactivate();
		nextButton.deactivate();
		skipButton.deactivate();	
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
		prevButton.activate();
	}

	private void prev() {
		if (pageIndex > 0) {
			pageIndex--;
		}
		if (pageIndex > 0) {
			prevButton.activate();
		}
		else {
			prevButton.deactivate();
		}
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
		prevButton.draw();
		skipButton.draw();
	}

	private void drawPage() {
		Game.pushRenderingShift(0, -100, false);
		TextDrawer.draw(FontManager.defaultFont, (pageIndex + 1) + "/" + pages.size(), IDrawingInfo.viewport, TextDrawer.AlignN);
		Game.popRenderingShift();
		
		Game.pushRenderingShift(0, -175, false);
		TextDrawer.draw(font, pages.get(pageIndex), IDrawingInfo.viewport, TextDrawer.AlignN);
		Game.popRenderingShift();
	}
}
