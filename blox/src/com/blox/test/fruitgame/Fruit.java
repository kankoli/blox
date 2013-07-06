package com.blox.test.fruitgame;

import com.blox.framework.AnimatedSprite;
import com.blox.framework.AnimationBuilder;
import com.blox.framework.BloxAnimation;

public class Fruit extends AnimatedSprite {
	public final class Animations {
		public static final String Watermelon = "FruitWatermelonAnimation";
		private static final String WatermelonPath = "watermelon.png";
		private static final int WatermelonFrameWidth = 30;
		private static final int WatermelonFrameHeight = 30;
		private static final float WatermelonFrameDuration = 0.2f;
	}
	
	public Fruit(float frameDur, float posX, float posY) {
		BloxAnimation watermelonAnimation = AnimationBuilder
				.createAnimation(Animations.Watermelon)
				.fromImageFile(Animations.WatermelonPath)
				.withFrameSize(Animations.WatermelonFrameWidth,
						Animations.WatermelonFrameHeight)
				.withFrameDuration(frameDur).setAsLooping().build();
		
		addAnimation(watermelonAnimation);
		
		setDefaultAnimation(Animations.Watermelon);
		startAnimation(Animations.Watermelon);
		
		this.position.x = posX;
		this.position.y = posY;
	}

}
