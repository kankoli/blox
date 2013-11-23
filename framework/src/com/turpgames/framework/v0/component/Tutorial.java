package com.turpgames.framework.v0.component;

import java.util.List;

import com.turpgames.framework.v0.ILanguageListener;
import com.turpgames.framework.v0.IView;
import com.turpgames.framework.v0.IViewFinder;
import com.turpgames.framework.v0.IViewSwitcher;
import com.turpgames.framework.v0.impl.GameObject;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.impl.ViewSwitcher;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.Utils;

public abstract class Tutorial extends GameObject implements IViewFinder, ILanguageListener {
	private int pageIndex;
	protected List<TutorialPage> pages;
	private IViewSwitcher switcher;

	private ITutorialListener listener;

	protected ImageButton nextButton;
	protected ImageButton prevButton;
	protected Text pageTitle;
	protected Text pagesInfo;

	protected Tutorial(ITutorialListener listener) {
		this.listener = listener;

		String pageSwitcher = Game.getParam("tutorial-page-switcher");
		switcher = ViewSwitcher.createInstance(pageSwitcher);
		switcher.setViewFinder(this);

		pageTitle = new Text();
		addPageTitle();
		
		pagesInfo = new Text();
		addPagesInfo();
		
		addNextButton();
		addPrevButton();
		
		populatePages();

		updatePageInfoText();
		
		Game.getLanguageManager().register(this);
	}

	abstract protected void addPageTitle();
	
	abstract protected void addPagesInfo();
	
	abstract protected void concreteAddNextButton();

	private final void addNextButton() {
		concreteAddNextButton();
		nextButton.setListener(new IButtonListener() {
			@Override
			public void onButtonTapped() {
				next();
			}
		});
	}
	
	abstract protected void concreteAddPrevButton();
	
	private final void addPrevButton() {
		concreteAddPrevButton();
		prevButton.setListener(new IButtonListener() {
			@Override
			public void onButtonTapped() {
				prev();
			}
		});
	}
	
	abstract protected void populatePages();
	
	public void start() {
		pageIndex = 0;
		switcher.setView(pages.get(pageIndex).getId());
		prevButton.deactivate();
		nextButton.activate();
		updatePageInfoText();
	}

	public void end() {
		prevButton.deactivate();
		nextButton.deactivate();
	}

	private void next() {
		if (pageIndex < pages.size() - 1) {
			pageIndex++;
			switcher.switchTo(pages.get(pageIndex).getId(), false);
		}
		else
			notifyTutorialEnd();
		prevButton.activate();
		updatePageInfoText();
	}

	private void prev() {
		if (pageIndex > 0) {
			pageIndex--;
			switcher.switchTo(pages.get(pageIndex).getId(), true);
		}
		if (pageIndex > 0) {
			prevButton.activate();
		}
		else {
			prevButton.deactivate();
		}
		updatePageInfoText();
	}

	private void updatePageInfoText() {
		pagesInfo.setText((pageIndex + 1) + "/" + pages.size());
	}

	private void notifyTutorialEnd() {
		if (listener != null)
			listener.onModeEnd();
	}

	@Override
	public void draw() {
		drawPage();
		nextButton.draw();
		prevButton.draw();
	}

	private void drawPage() {
		pageTitle.draw();
		pagesInfo.draw();
		switcher.draw();
	}

	@Override
	public IView findView(String id) {
		for (TutorialPage page : pages) {
			if (id.equals(page.getId()))
				return page;
		}
		return null;
	}

	@Override
	public void registerSelf() {
		Game.getInputManager().register(this, Utils.LAYER_SCREEN);
	}

	@Override
	public void onLanguageChanged() {
		populatePages();
		addPageTitle();
	}
}
