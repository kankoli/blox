package com.blox.framework.v0.impl.libgdx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.utils.Array;
import com.blox.framework.v0.IFont;
import com.blox.framework.v0.metadata.ResourceMetadata;
import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Vector;

public class GdxFont implements IFont {

	private class ScaleMap {
		private int min;
		private int max;
		private BitmapFont font;

		private ScaleMap(int min, int max, BitmapFont font) {
			this.min = min;
			this.max = max;
			this.font = font;
		}

		private boolean isIn(int size) {
			return size >= min && size < max;
		}
	}

	private BitmapFont font;
	private Map<Integer, BitmapFont> fonts;
	private List<ScaleMap> scales;
	private Vector size;
	private int defaultSize;

	GdxFont(Map<Integer, BitmapFont> fonts, int defaultSize) {
		this.fonts = fonts;
		this.size = new Vector();
		this.defaultSize = defaultSize;
		initScales();
		registerDisposables(fonts);
		scale(1);
	}

	private void initScales() {
		Set<Integer> keys = fonts.keySet();
		int[] sizes = new int[keys.size()];
		int i = 0;
		for (int k : keys)
			sizes[i++] = k;
		Arrays.sort(sizes);

		scales = new ArrayList<ScaleMap>();

		for (i = 0; i < sizes.length; i++) {
			if (i == 0) {
				scales.add(new ScaleMap(Integer.MIN_VALUE, (int) ((sizes[i] + sizes[i + 1]) / 2), fonts.get(sizes[i])));
			} else if (i == sizes.length - 1) {
				scales.add(new ScaleMap((int) ((sizes[i] + sizes[i - 1]) / 2), Integer.MAX_VALUE, fonts.get(sizes[i])));
			} else {
				scales.add(new ScaleMap((int) ((sizes[i] + sizes[i - 1]) / 2), (int) ((sizes[i] + sizes[i + 1]) / 2), fonts.get(sizes[i])));
			}
		}
	}

	private void registerDisposables(Map<Integer, BitmapFont> fonts) {
		for (BitmapFont font : fonts.values())
			Game.registerDisposable(new GdxDisposable(font));
	}

	@Override
	public void setColor(Color color) {
		font.setColor(color.r / 255f, color.g / 255, color.b / 255f, color.a / 255f);
	}

	@Override
	public void dispose() {
		font.dispose();
	}

	@Override
	public Vector getSize(String text) {
		TextBounds bounds = font.getBounds(text);
		size.x = bounds.width;
		size.y = bounds.height;
		return size;
	}

	@Override
	public void draw(String text, float x, float y) {
		float dx = Game.viewportToScreenX(Game.renderingShiftX);
		float dy = Game.viewportToScreenX(Game.renderingShiftY);

		com.badlogic.gdx.graphics.Color fontColor = font.getColor();
		font.setColor(fontColor.r, fontColor.g, fontColor.b, Game.renderingAlpha);
		font.drawMultiLine(GdxGame.spriteBatch, text, x + dx, y + dy);
	}

	@Override
	public void scale(float f) {
		int size = (int) (f * defaultSize);
		for (ScaleMap sm : scales) {
			if (sm.isIn(size)) {
				font = sm.font;
				return;
			}
		}
	}
	
	static class GdxFontLoaderParameters extends AssetLoaderParameters<GdxFont> {
		ResourceMetadata metadata;
	}
	
	static class GdxFontLoader extends AsynchronousAssetLoader<GdxFont, GdxFontLoaderParameters>{
		public GdxFontLoader() {
			super(new InternalFileHandleResolver());
		}

		@Override
		public void loadAsync(AssetManager manager, String fileName, GdxFontLoaderParameters parameter) {
			
		}

		@Override
		public GdxFont loadSync(AssetManager manager, String fileName, GdxFontLoaderParameters parameter) {
			return GdxFontFactory.create(parameter.metadata);
		}

		@Override
		@SuppressWarnings("rawtypes")
		public Array<AssetDescriptor> getDependencies(String fileName, GdxFontLoaderParameters parameter) {
			return null;
		}		
	}
}
