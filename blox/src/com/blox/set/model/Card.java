package com.blox.set.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.blox.framework.v0.ITappedListener;
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
	
	private List<ITappedListener> tappedListeners;
	
	static {
		textureClosed = Game.getResourceManager().loadTexture("setcards/closed.png");
		textureBorder = Game.getResourceManager().loadTexture("setcards/border.png");
	}
	
	public static Card[] getDeck() {
		createDeck();
		shuffleDeck();		
		return deck;
	}

	public static Card[] getUnshuffledDeck() {
		createDeck();
		return deck;
	}
	
	private static void createDeck() {
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
						Card card = new Card();
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
	
	
	private boolean isOpened;
	private boolean isSelected;
	
	private ITexture texture;
	
	public Card() {
		this.width = Card.Width;
		this.height = Card.Height;
		this.tappedListeners = new ArrayList<ITappedListener>();
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
	
	public void registerTappedListener(ITappedListener listener) {
		tappedListeners.add(listener);
	}

	public void unregisterTappedListener(ITappedListener listener) {
		tappedListeners.remove(listener);
	}

	public void notifyTappedListeners() {
		for (int i = tappedListeners.size()-1; i >= 0; i--) {
			tappedListeners.get(i).onTapped(this);
		}
	}
	
	@Override
	protected void onTap() {
		notifyTappedListeners();
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
		isSelected = !isSelected;
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
