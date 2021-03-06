package com.turpgames.ichigu.model.display;

import com.turpgames.framework.v0.ITexture;
import com.turpgames.framework.v0.effects.IEffectEndListener;
import com.turpgames.framework.v0.effects.fading.FadeOutEffect;
import com.turpgames.framework.v0.effects.fading.IFadingEffectSubject;
import com.turpgames.framework.v0.impl.AttachedText;
import com.turpgames.framework.v0.impl.GameObject;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Drawer;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.TextureDrawer;
import com.turpgames.framework.v0.util.Timer;
import com.turpgames.framework.v0.util.Utils;
import com.turpgames.ichigu.model.game.CardAttributes;
import com.turpgames.ichigu.utils.R;

public class FadingScoreInfo extends GameObject implements IFadingEffectSubject {
	private Timer timer;
	private FadeOutEffect fadeOutEffect;
	private boolean isActive;
	private float fadeDuration = 5f;

	class ScoreInfo extends GameObject implements IFadingEffectSubject {
		static final int scoreImageSize = 64;
		ITexture texture;
		AttachedText text;
		AttachedText extraText;

		ScoreInfo() {
			this.text = new AttachedText(this);
			this.text.setHorizontalAlignment(Text.HAlignCenter);
			this.text.setPadY(-15);
			this.text.setWrapped(false);
			
			this.extraText = new AttachedText(this);
			this.extraText.setPadY(-15);
			this.extraText.setText(" +");
			
			setWidth(scoreImageSize);
			setHeight(scoreImageSize);
		}

		public void setText(String text) {
			this.text.setText(text);
			this.extraText.setPadX(60);			
		}
		
		public void setExtraText(String text) {
			this.extraText.setText(text);
		}

		public void setTexture(ITexture texture) {
			this.texture = texture;
		}

		public float getTotalWidth() {
			return 60 + this.extraText.getTextAreaWidth();
		}
		
		@Override
		public void draw() {
			TextureDrawer.draw(texture, this);
			this.text.draw();
			this.extraText.draw();
		}

		@Override
		public void setAlpha(float alpha) {
			this.text.getColor().a = alpha;
			this.extraText.getColor().a = alpha;
			getColor().a = alpha;
		}

		@Override
		public void registerSelf() {
			Game.getInputManager().register(this, Utils.LAYER_INFO);
		}
	}

	private int score;
	private ScoreInfo shapeScoreInfo;
	private ScoreInfo colorScoreInfo;
	private ScoreInfo patternScoreInfo;
	private ScoreInfo countScoreInfo;

	private void setPoints(CardAttributes[] attr) {
		score = 0;
		
		if (CardAttributes.isSameColor(attr[0], attr[1], attr[2])) {
			score += 1;
			colorScoreInfo.setText("1");
			colorScoreInfo.setTexture(Game.getResourceManager().getTexture(R.game.textures.points.colorone));
			colorScoreInfo.getColor().set(
					attr[0].getColor() == CardAttributes.Color_Blue ? R.colors.ichiguBlue : attr[0].getColor() == CardAttributes.Color_Green ? R.colors.ichiguGreen : R.colors.ichiguRed);
		} else {
			score += 3;
			colorScoreInfo.setText("3");
			colorScoreInfo.setTexture(Game.getResourceManager().getTexture(R.game.textures.points.colorall));
			colorScoreInfo.getColor().set(1, 1, 1);
		}
		
		if (CardAttributes.isSameShape(attr[0], attr[1], attr[2])) {
			score += 1;
			shapeScoreInfo.setText("1");
			shapeScoreInfo.setTexture(Game.getResourceManager().getTexture(
					attr[0].getShape() == CardAttributes.Shape_Circle ? R.game.textures.points.shapecircle : attr[0].getShape() == CardAttributes.Shape_Square ? R.game.textures.points.shaperectangle
							: R.game.textures.points.shapetriangle));
		} else {
			score += 3;
			shapeScoreInfo.setText("3");
			shapeScoreInfo.setTexture(Game.getResourceManager().getTexture(R.game.textures.points.shapeall));
		}
		
		if (CardAttributes.isSamePattern(attr[0], attr[1], attr[2])) {
			score += 1;
			patternScoreInfo.setText("1");
			patternScoreInfo.setTexture(Game.getResourceManager().getTexture(
					attr[0].getPattern() == CardAttributes.Pattern_Empty ? R.game.textures.points.fillempty : attr[0].getPattern() == CardAttributes.Pattern_Filled ? R.game.textures.points.fillfull
							: R.game.textures.points.fillstriped));
		} else {
			score += 3;
			patternScoreInfo.setText("3");
			patternScoreInfo.setTexture(Game.getResourceManager().getTexture(R.game.textures.points.fillall));
		}

		if (CardAttributes.isSameCount(attr[0], attr[1], attr[2])) {
			score += 1;
			countScoreInfo.setText("1");
			countScoreInfo.setTexture(Game.getResourceManager().getTexture(
					attr[0].getCount() == CardAttributes.Count_1 ? R.game.textures.points.countone : attr[0].getCount() == CardAttributes.Count_2 ? R.game.textures.points.counttwo
							: R.game.textures.points.countthree));
		} else {
			score += 3;
			countScoreInfo.setText("3");
			countScoreInfo.setTexture(Game.getResourceManager().getTexture(R.game.textures.points.countall));
		}
		countScoreInfo.setExtraText(" = " + score);
		
		setScoreInfosPositions();
	}

	public FadingScoreInfo() {
		this.setWidth(Game.getVirtualWidth());
		this.setHeight(ScoreInfo.scoreImageSize + 10);
		this.getLocation().set(0, Game.getVirtualHeight() - this.getHeight());
		this.getColor().set(R.colors.ichiguRed);

		timer = new Timer();
		timer.setTickListener(timerListener);

		fadeOutEffect = new FadeOutEffect(this);
		fadeOutEffect.setMinAlpha(0);
		fadeOutEffect.setMaxAlpha(1);
		fadeOutEffect.setListener(effectListener);

		initScoreInfos();
	}

	public void initScoreInfos() {
		colorScoreInfo = new ScoreInfo();

		shapeScoreInfo = new ScoreInfo();
		shapeScoreInfo.getColor().set(R.colors.ichiguYellow);
		
		patternScoreInfo = new ScoreInfo();
		patternScoreInfo.getColor().set(R.colors.ichiguYellow);

		countScoreInfo = new ScoreInfo();
		countScoreInfo.getColor().set(R.colors.ichiguYellow);
	}
	
	public void setScoreInfosPositions() {
		int totalWidth = (int) (3 * ScoreInfo.scoreImageSize + countScoreInfo.getTotalWidth());
		int x = (int) (Game.getVirtualWidth() - totalWidth) / 2;
		int y = (int) this.getLocation().y;
		colorScoreInfo.getLocation().set(x, y);
		shapeScoreInfo.getLocation().set(x + ScoreInfo.scoreImageSize, y);
		patternScoreInfo.getLocation().set(x + 2 * ScoreInfo.scoreImageSize, y);
		countScoreInfo.getLocation().set(x + 3 * ScoreInfo.scoreImageSize, y);
	}

	public void show(CardAttributes[] attr) {
		if (isActive) {
			timer.start();
			fadeOutEffect.stop();
			setAlpha(1);
		}

		this.isActive = true;

		setPoints(attr);

		timer.setInterval(fadeDuration);

		fadeOutEffect.setDuration(fadeDuration);
		fadeOutEffect.start();

		this.listenInput(true);
		Drawer.getCurrent().register(this, Utils.LAYER_INFO);
	}

	public void dispose() {
		hide();
		fadeOutEffect.stop();
	}

	public void hide() {
		if (!isActive)
			return;

		this.isActive = false;
		timer.stop();

		this.listenInput(false);
	}

	private void effectEnd() {
		if (isActive)
			timer.start();
		else if (Drawer.getCurrent() != null)
			Drawer.getCurrent().unregister(this);
	}

	private Timer.ITimerTickListener timerListener = new Timer.ITimerTickListener() {
		@Override
		public void timerTick(Timer timer) {
			hide();
		}
	};

	private IEffectEndListener effectListener = new IEffectEndListener() {
		@Override
		public boolean onEffectEnd(Object obj) {
			effectEnd();
			return true;
		}
	};

	@Override
	public void draw() {
		shapeScoreInfo.draw();
		colorScoreInfo.draw();
		countScoreInfo.draw();
		patternScoreInfo.draw();
	}

	@Override
	protected boolean onTap() {
		hide();
		return true;
	}

	@Override
	public void setAlpha(float alpha) {
		getColor().a = alpha;
		shapeScoreInfo.setAlpha(alpha);
		colorScoreInfo.setAlpha(alpha);
		countScoreInfo.setAlpha(alpha);
		patternScoreInfo.setAlpha(alpha);
	}

	@Override
	public void registerSelf() {
		Game.getInputManager().register(this, Utils.LAYER_INFO);
	}
}
