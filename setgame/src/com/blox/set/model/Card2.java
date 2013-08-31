package com.blox.set.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.TextureDrawer;
import com.blox.framework.v0.util.Utils;
import com.blox.set.utils.R;

public class Card2 extends CardGameObject2 {

	// region static

	public static final float scale = 0.7f;
	public static final int Width = (int) (140 * scale);
	public static final int Height = (int) (240 * scale);
	public static final int SymbolWidth = (int) (50 * scale);
	public static final int SymbolHeight = (int) (50 * scale);
	public static final int Space = 7;

	private static ITexture textureDefault;
	private static ITexture textureClosed;

	static {
		textureDefault = Game.getResourceManager().getTexture(R.game.textures.cardEmpty);
		textureClosed = Game.getResourceManager().getTexture(R.game.textures.cardClosed);
	}

	public static Card2[] getDeck() {
		Card2[] deck = new Card2[81];
		createDeck(deck);
		shuffleDeck(deck);
		return deck;
	}

	private static void createDeck(Card2[] deck) {
		int[] colors = new int[] { CardAttributes.Color_Red, CardAttributes.Color_Green, CardAttributes.Color_Blue };
		int[] shapes = new int[] { CardAttributes.Shape_Circle, CardAttributes.Shape_Square, CardAttributes.Shape_Triangle };
		int[] counts = new int[] { CardAttributes.Count_1, CardAttributes.Count_2, CardAttributes.Count_3 };
		int[] patterns = new int[] { CardAttributes.Pattern_Empty, CardAttributes.Pattern_Filled, CardAttributes.Pattern_Striped };

		for (int i = 0; i < colors.length; i++) {
			for (int j = 0; j < shapes.length; j++) {
				for (int k = 0; k < counts.length; k++) {
					for (int n = 0; n < patterns.length; n++) {
						Card2 card = new Card2(new CardAttributes(colors[i], shapes[j], counts[k], patterns[n]));
						deck[i + j * 3 + k * 9 + n * 27] = card;
					}
				}
			}
		}
	}

	private static void shuffleDeck(Card2[] deck) {
		Random rnd = new Random();
		for (int i = 0; i < deck.length * deck.length; i++) {
			int x = rnd.nextInt(deck.length);
			int y = rnd.nextInt(deck.length);

			Card2 tmp = deck[x];
			deck[x] = deck[y];
			deck[y] = tmp;
		}
	}

	public static boolean isSet(Card2 card1, Card2 card2, Card2 card3) {
		return CardAttributes.isSet(card1.attributes, card2.attributes, card3.attributes);
	}

	// endregion

	private CardAttributes attributes;
	private ICardSelectedListener selectedListener;

	private boolean isOpened;
	private boolean isSelected;

	private List<Symbol> symbols;

	public Card2(CardAttributes cardAttributes) {
		this.attributes = cardAttributes;
		this.width = Card2.Width;
		this.height = Card2.Height;

		calculateTextures();
	}

	private void calculateTextures() {
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

	void setSelectedListener(ICardSelectedListener listener) {
		selectedListener = listener;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		if (Utils.isIn(x, y, location, width, height))
			switchSelected();
		return false;
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
		if (selectedListener != null)
			selectedListener.cardSelected(this);
	}

	public boolean isSelected() {
		return isSelected;
	}

	@Override
	public void draw() {
		if (!isOpened) {
			TextureDrawer.draw(textureClosed, this);
			return;
		}

		TextureDrawer.draw(textureDefault, this);

		for (int i = 0; i < symbols.size(); i++) {
			symbols.get(i).draw();
		}
	}

	public CardAttributes getAttributes() {
		return attributes;
	}

	public static interface ICardSelectedListener {
		void cardSelected(Card2 card);
	}
}
