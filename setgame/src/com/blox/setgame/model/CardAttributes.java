package com.blox.setgame.model;

public final class CardAttributes {
	public static final int DiffSet = 7;

	public static final int Color_Red = 0x01;
	public static final int Color_Green = 0x01 << 1;
	public static final int Color_Blue = 0x01 << 2;

	public static final int Shape_Circle = 0x01;
	public static final int Shape_Square = 0x01 << 1;
	public static final int Shape_Triangle = 0x01 << 2;

	public static final int Count_1 = 0x01;
	public static final int Count_2 = 0x01 << 1;
	public static final int Count_3 = 0x01 << 2;

	public static final int Pattern_Empty = 0x01;
	public static final int Pattern_Filled = 0x01 << 1;
	public static final int Pattern_Striped = 0x01 << 2;

	private int color;
	private int shape;
	private int count;
	private int pattern;

	private static int getSetScore(int a1, int a2, int a3) {
		if ((a1 & a2) == a3)
			return 2;
		if ((a1 | a2 | a3) == DiffSet)
			return 1;
		return 0;
	}

	private CardAttributes() {
	}

	public CardAttributes(int color, int shape, int count, int pattern) {
		this.color = color;
		this.shape = shape;
		this.count = count;
		this.pattern = pattern;
	}

	public static int getSetScore(CardAttributes a1, CardAttributes a2, CardAttributes a3) {
		int colorScore = getSetScore(a1.color, a2.color, a3.color);
		int shapeScore = getSetScore(a1.shape, a2.shape, a3.shape);
		int countScore = getSetScore(a1.count, a2.count, a3.count);
		int patternScore = getSetScore(a1.pattern, a2.pattern, a3.pattern);
		
		if (colorScore > 0 && shapeScore > 0 && countScore > 0 && patternScore > 0)
			return colorScore + shapeScore + countScore + patternScore;		
		return 0;
	}

	public static boolean isSet(CardAttributes a1, CardAttributes a2, CardAttributes a3) {
		return getSetScore(a1, a2, a3) > 0;
	}
	
	public static CardAttributes getThirdCardAttributes(CardAttributes a1, CardAttributes a2) {
		CardAttributes a3 = new CardAttributes();
		a3.color = a1.color == a2.color ? a1.color : DiffSet ^ a1.color ^ a2.color;
		a3.shape = a1.shape == a2.shape ? a1.shape : DiffSet ^ a1.shape ^ a2.shape;
		a3.count = a1.count == a2.count ? a1.count : DiffSet ^ a1.count ^ a2.count;
		a3.pattern = a1.pattern == a2.pattern ? a1.pattern : DiffSet ^ a1.pattern ^ a2.pattern;
		return a3;
	}

	public boolean equals(CardAttributes that) {
		return this.color == that.color
				&& this.shape == that.shape
				&& this.count == that.count
				&& this.pattern == that.pattern;
	}

	public int getColor() {
		return color;
	}

	public int getShape() {
		return shape;
	}

	public int getCount() {
		return count;
	}

	public int getPattern() {
		return pattern;
	}

	@Override
	public String toString() {
		return "" + color + shape + count + pattern;
	}
}