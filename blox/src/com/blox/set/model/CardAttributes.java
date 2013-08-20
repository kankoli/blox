package com.blox.set.model;

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

	public static boolean isSet(int a1, int a2, int a3) {
		return (a1 & a2) == a3 || (a1 | a2 | a3) == DiffSet;
	}

	private CardAttributes() {

	}
}
