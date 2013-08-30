package com.blox.framework.v0.util;

import com.blox.framework.v0.IDrawingInfo;
import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.ITextureDrawer;

public final class TextureDrawer {
	private TextureDrawer() {

	}

	private static final ITextureDrawer drawer = Game.getTextureDrawer();
	private static final DrawingInfo adaptedDrawingInfo = new DrawingInfo();

	public static void draw(ITexture texture, IDrawingInfo info) {
		float scale = info.ignoreViewportScaling() ? 1f : Game.getScale();
		float offsetX = info.ignoreViewportOffset() ? 0f : Game.getViewportOffsetX();
		float offsetY = info.ignoreViewportOffset() ? 0f : Game.getViewportOffsetY();

		float dx = Game.viewportToScreenX(Game.renderingShiftX);
		float dy = Game.viewportToScreenX(Game.renderingShiftY);

		Vector l = info.getLocation();
		Rotation r = info.getRotation();
		Vector s = info.getScale();

		adaptedDrawingInfo.l.x = scale * (l.x + dx) + offsetX;
		adaptedDrawingInfo.l.y = scale * (l.y + dy) + offsetY;
		adaptedDrawingInfo.r.origin.x = scale * (r.origin.x - l.x);
		adaptedDrawingInfo.r.origin.y = scale * (r.origin.y - l.y);
		adaptedDrawingInfo.w = scale * info.getWidth();
		adaptedDrawingInfo.h = scale * info.getHeight();
		adaptedDrawingInfo.s.x = s.x;
		adaptedDrawingInfo.s.y = s.y;
		adaptedDrawingInfo.r.rotation.z = r.rotation.z;
		adaptedDrawingInfo.flipX = info.isFlipX();
		adaptedDrawingInfo.flipY = info.isFlipY();

		drawer.draw(texture, adaptedDrawingInfo);
	}

	private static class DrawingInfo implements IDrawingInfo {
		private float w;
		private float h;
		private Vector l;
		private Color c;
		private Vector s;
		private Rotation r;
		private boolean flipX;
		private boolean flipY;
		private boolean ignoreViewportOffSet;
		private boolean ignoreViewportScaling;
		
		private DrawingInfo() {
			l = new Vector();
			s = new Vector();
			c = new Color();
			r = new Rotation();
		}

		@Override
		public float getWidth() {
			return w;
		}

		@Override
		public float getHeight() {
			return h;
		}

		@Override
		public Vector getLocation() {
			return l;
		}

		@Override
		public Vector getScale() {
			return s;
		}

		@Override
		public Rotation getRotation() {
			return r;
		}

		@Override
		public boolean isFlipX() {
			return flipX;
		}

		@Override
		public boolean isFlipY() {
			return flipY;
		}

		@Override
		public boolean ignoreViewportOffset() {
			return ignoreViewportOffSet;
		}

		@Override
		public boolean ignoreViewportScaling() {
			return ignoreViewportScaling;
		}

		@Override
		public Color getColor() {
			return c;
		}
	}
}
