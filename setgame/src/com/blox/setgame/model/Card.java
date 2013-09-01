package com.blox.setgame.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Utils;
import com.blox.setgame.controller.SetGameController;
import com.blox.setgame.utils.R;

public class Card extends CardGameObject {

	// region static

	public static final float scale = 0.7f;
	public static final int Width = (int) (140 * scale);
	public static final int Height = (int) (240 * scale);
	public static final int SymbolWidth = (int) (50 * scale);
	public static final int SymbolHeight = (int) (50 * scale);
	public static final int Space = 7;

	public static Card[] getDeck() {
		Card[] deck = new Card[81];
		createDeck(deck);
		shuffleDeck(deck);
		return deck;
	}

	private static void createDeck(Card[] deck) {
		int[] colors = new int[] { CardAttributes.Color_Red, CardAttributes.Color_Green, CardAttributes.Color_Blue };
		int[] shapes = new int[] { CardAttributes.Shape_Circle, CardAttributes.Shape_Square, CardAttributes.Shape_Triangle };
		int[] counts = new int[] { CardAttributes.Count_1, CardAttributes.Count_2, CardAttributes.Count_3 };
		int[] patterns = new int[] { CardAttributes.Pattern_Empty, CardAttributes.Pattern_Filled, CardAttributes.Pattern_Striped };

		for (int i = 0; i < colors.length; i++) {
			for (int j = 0; j < shapes.length; j++) {
				for (int k = 0; k < counts.length; k++) {
					for (int n = 0; n < patterns.length; n++) {
						Card card = new Card(new CardAttributes(colors[i], shapes[j], counts[k], patterns[n]));
						deck[i + j * 3 + k * 9 + n * 27] = card;
					}
				}
			}
		}
	}

	private static void shuffleDeck(Card[] deck) {
		Random rnd = new Random(1);
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
	
	public static int getSetScore(Card card1, Card card2, Card card3) {
		return CardAttributes.getSetScore(card1.attributes, card2.attributes, card3.attributes);
	}

	// endregion

	private List<Symbol> symbols;
	private CardAttributes attributes;
	private ICardTapListener tapListener;

	public Card(CardAttributes cardAttributes) {
		this.attributes = cardAttributes;
		this.width = Card.Width;
		this.height = Card.Height;

		open();
		initSymbols();
	}

	private void initSymbols() {
		String symbolName = "card-" + attributes.getColor() + attributes.getShape() + attributes.getPattern();

		ITexture symbolTexture = Game.getResourceManager().getTexture(symbolName);
		symbols = new ArrayList<Symbol>();
		if (attributes.getCount() == 1) {
			symbols.add(new Symbol(symbolTexture, R.symbolpositions.firstOfOne, this.location));
		}
		else if (attributes.getCount() == 2) {
			symbols.add(new Symbol(symbolTexture, R.symbolpositions.firstOfTwo, this.location));
			symbols.add(new Symbol(symbolTexture, R.symbolpositions.secondOfTwo, this.location));
		}
		else if (attributes.getCount() == 4) {
			symbols.add(new Symbol(symbolTexture, R.symbolpositions.firstOfThree, this.location));
			symbols.add(new Symbol(symbolTexture, R.symbolpositions.secondOfThree, this.location));
			symbols.add(new Symbol(symbolTexture, R.symbolpositions.thirdOfThree, this.location));
		}
	}

	private boolean isOpened;
	private boolean isSelected;
	
	public void open() {
		isOpened = true;
	}
	
	public void close() {
		isOpened = false;
	}
	
	public boolean isOpened() {
		return isOpened;
	}

	public void select() {
		isSelected = true;
	}
	
	public void unselect() {
		isSelected = false;
	}
	
	public boolean isSelected() {
		return isSelected;
	}
	
	@Override
	public boolean tap(float x, float y, int count, int button) {
		if (Utils.isIn(x, y, location, width, height)) {
			if (tapListener != null)
				tapListener.cardTapped(this);
			return true;
		}
		return false;
	}
	
	@Override
	public void draw() {
		if (!isOpened) {
			SetGameController.drawTextureCardClosed(this);
			return;
		}
		
		SetGameController.drawTextureCardEmpty(this);
		for (int i = 0; i < symbols.size(); i++) {
			symbols.get(i).draw();
		}
		
		if (isSelected)
			SetGameController.drawTextureCardBorder(this);
	}

	public void setTapListener(ICardTapListener listener) {
		tapListener = listener;
	}
	
	public void unsetTapListener() {
		tapListener = null;
	}
	
	public CardAttributes getAttributes() {
		return attributes;
	}

	public static interface ICardTapListener {
		void cardTapped(Card card);
	}

}
