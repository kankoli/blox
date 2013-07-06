package com.blox.test.mummy;

import com.badlogic.gdx.math.Vector2;
import com.blox.framework.AnimatedSprite;
import com.blox.framework.AnimationBuilder;
import com.blox.framework.AnimationFinishListener;
import com.blox.framework.BloxAnimation;

public class Mummy extends AnimatedSprite implements AnimationFinishListener {
	public final class Animations {
		public static final String Walk = "MummyWalkAnimation";
		private static final String WalkImagePath = "mummyImages/mummyWalk.png";
		private static final int WalkFrameWidth = 41;
		private static final int WalkFrameHeight = 48;
		private static final float WalkFrameDuration = 0.1f;

		public static final String Stand = "MummyStandAnimation";
		private static final String StandImagePath = "mummyImages/mummyStand.png";
		private static final int StandFrameWidth = 41;
		private static final int StandFrameHeight = 48;
		private static final float StandFrameDuration = 0.1f;

		public static final String Turn = "MummyTurnAnimation";
		private static final String TurnImagePath = "mummyImages/mummyTurn.png";
		private static final int TurnFrameWidth = 41;
		private static final int TurnFrameHeight = 48;
		private static final float TurnFrameDuration = 0.1f;
	}

	public Mummy() {
		BloxAnimation walkAnimation = AnimationBuilder
				.createAnimation(Animations.Walk)
				.fromImageFile(Animations.WalkImagePath)
				.withFrameDuration(Animations.WalkFrameDuration)
				.withFrameSize(Animations.WalkFrameWidth,
						Animations.WalkFrameHeight).setAsLooping().build();
		
		BloxAnimation standAnimation = AnimationBuilder
				.createAnimation(Animations.Stand)
				.fromImageFile(Animations.StandImagePath)
				.withFrameDuration(Animations.StandFrameDuration)
				.withFrameSize(Animations.StandFrameWidth,
						Animations.StandFrameHeight).setAsLooping().build();
		
		BloxAnimation turnAnimation = AnimationBuilder
				.createAnimation(Animations.Turn)
				.fromImageFile(Animations.TurnImagePath)
				.withFrameDuration(Animations.TurnFrameDuration)
				.withFrameSize(Animations.TurnFrameWidth, Animations.TurnFrameHeight)
				.withFinishListeners(this)
				.build();

		addAnimation(walkAnimation);
		addAnimation(standAnimation);
		addAnimation(turnAnimation);
		
		setDefaultAnimation(Animations.Stand);
		startAnimation(Animations.Stand);
	}

	@Override
	public void onAnimationFinished(BloxAnimation animation) {
		if (Animations.Turn == animation.getName()) {
			animation.stopAnimation();
			animation.flip();
			startAnimation(Animations.Stand);
			currentAnimation.flip();
		}		
	}

// dev1 comment
	
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		startAnimation(Animations.Turn);
		return super.touchDown(screenX, screenY, pointer, button);
	}

//	@Override
//	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
//		startAnimation(Animations.Turn);
//		return super.touchUp(screenX, screenY, pointer, button);
//	}

//	@Override
//	public boolean touchDragged(int screenX, int screenY, int pointer) {
//		startAnimation(Animations.Turn);
//		return super.touchDragged(screenX, screenY, pointer);
//	}

//	@Override
//	public boolean touchDown(float x, float y, int pointer, int button) {
//		startAnimation(Animations.Turn);
//		return super.touchDown(x, y, pointer, button);
//	}

//	@Override
//	public boolean tap(float x, float y, int count, int button) {
//		startAnimation(Animations.Turn);
//		return super.tap(x, y, count, button);
//	}

//	@Override
//	public boolean longPress(float x, float y) {
//		startAnimation(Animations.Turn);
//		return super.longPress(x, y);
//	}

//	@Override
//	public boolean fling(float velocityX, float velocityY, int button) {
//		startAnimation(Animations.Turn);
//		return super.fling(velocityX, velocityY, button);
//	}

//	@Override
//	public boolean pan(float x, float y, float deltaX, float deltaY) {
//		startAnimation(Animations.Turn);
//		return super.pan(x, y, deltaX, deltaY);
//	}

//	@Override
//	public boolean zoom(float initialDistance, float distance) {
//		startAnimation(Animations.Turn);
//		return super.zoom(initialDistance, distance);
//	}
	
//	@Override
//	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
//			Vector2 pointer1, Vector2 pointer2) {
//		startAnimation(Animations.Turn);
//		return super.pinch(initialPointer1, initialPointer2, pointer1, pointer2);
//	}
}
