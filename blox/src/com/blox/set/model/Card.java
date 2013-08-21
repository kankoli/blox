package com.blox.set.model;

import java.util.Random;

import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.util.Game;

public class Card extends CardGameObject {

	// region static
	
	public static final int Width = 100;
	public static final int Height = 175;
	public static final int Space = 7;
	
	private static Card[] deck;
	private static ITexture textureClosed;
	private static ITexture textureBorder;
	
	static {
		textureClosed = Game.getResourceManager().loadTexture("setcards/closed.png");
		textureBorder = Game.getResourceManager().loadTexture("setcards/border.png");
	}
	
	public static Card[] getDeck(GameTable table) {
		createDeck(table);
		shuffleDeck();		
		return deck;
	}

	private static void createDeck(GameTable table) {
		if (deck != null)
			return;
		
		deck = new Card[81];

		int[] colors = new int[] { CardAttributes.Color_Red, CardAttributes.Color_Green, CardAttributes.Color_Blue };
		int[] shapes = new int[] { CardAttributes.Shape_Circle, CardAttributes.Shape_Square, CardAttributes.Shape_Triangle };
		int[] counts = new int[] { CardAttributes.Count_1, CardAttributes.Count_2, CardAttributes.Count_3 };
		int[] patterns = new int[] { CardAttributes.Pattern_Empty, CardAttributes.Pattern_Filled, CardAttributes.Pattern_Striped };

		for (int i = 0; i < colors.length; i++) {
			for (int j = 0; j < shapes.length; j++) {
				for (int k = 0; k < counts.length; k++) {
					for (int n = 0; n < patterns.length; n++) {
						Card card = new Card(table);
						card.attributes = new CardAttributes(colors[i], shapes[j], counts[k], patterns[n]);
						deck[i + j * 3 + k * 9 + n * 27] = card;
					}
				}
			}
		}		
	}
	
	private static void shuffleDeck() {
		Random rnd = new Random(3);
		for (int i = 0; i < deck.length * deck.length; i++) {
			int x = rnd.nextInt(deck.length);
			int y = rnd.nextInt(deck.length);
			
			Card tmp = deck[x];			
			deck[x] = deck[y];
			deck[y] = tmp;
		}
	}

	public static boolean isSet(Card card1, Card card2, Card card3) {
		return CardAttributes.isSet(card1.attributes, card2.attributes, card3.attributes);
	}

	// endregion
	
	private CardAttributes attributes;
	
	private GameTable table;
	
	private boolean isOpened;
	private boolean isSelected;
	
	private ITexture texture;
	
	public Card(GameTable table) {
		this.table = table;
		this.width = Card.Width;
		this.height = Card.Height;
	}

	private ITexture getTexture() {
		if (!isOpened)
			return textureClosed;
		
		if (texture != null)
			return texture;

		String textureName = "setcards/" + attributes.getColor() + attributes.getShape() + attributes.getCount() + 
				attributes.getPattern() + ".png";
		texture = Game.getResourceManager().loadTexture(textureName);
		return texture;
	}
	
	@Override
	protected void onTap() {
		switchSelected();
	}
	
	public void open() {
		isOpened = true;
	}
	
	public void close() {
		isOpened = false;
	}
	
	public boolean isOpened() {
		return isOpened;
	}

	public void switchSelected() {
		if (isOpened) {
			isSelected = !isSelected;
			if (isSelected) {
				table.cardSelected(this);
			}
			else {
				table.cardUnselected(this);
			}
		}
		else {
			table.openExtraCards();
		}
	}
	
	public boolean isSelected() {
		return isSelected;
	}
	
	@Override
	public void draw() {
		getTexture().draw(this);
		if (isSelected)
			textureBorder.draw(this);
	}

	public CardAttributes getAttributes() {
		return attributes;
	}
}
