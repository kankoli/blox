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
		float scale = info.ignoreViewport() ? 1f : Game.getScale();
		float offsetX = info.ignoreViewport() ? 0f : Game.getViewportOffsetX();
		float offsetY = info.ignoreViewport() ? 0f : Game.getViewportOffsetY();

		float dx = info.ignoreShifting() ? 0f : Game.getRenderingShiftX();
		float dy = info.ignoreShifting() ? 0f :Game.getRenderingShiftY();

		Vector l = info.getLocation();
		Rotation r = info.getRotation();
		Vector s = info.getScale();

		adaptedDrawingInfo.l.x = scale * (l.x + dx) + offsetX;
		adaptedDrawingInfo.l.y = scale * (l.y + dy) + offsetY;
		adaptedDrawingInfo.r.origin.x = scale * (r.origin.x - l.x);
		adaptedDrawingInfo.r.origin.y = scale * (r.origin.y - l.y);
		adaptedDrawingInfo.w = scale * info.getWidth();
		adaptedDrawingInfo.h = scale * info.getHeight();
		adaptedDrawingInfo.s.set(s);
		adaptedDrawingInfo.r.rotation.z = r.rotation.z;
		adaptedDrawingInfo.flipX = info.isFlipX();
		adaptedDrawingInfo.flipY = info.isFlipY();
		adaptedDrawingInfo.c.set(info.getColor());

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
		private boolean ignoreViewport;
		private boolean ignoreShifting;
		
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
		public boolean ignoreViewport() {
			return ignoreViewport;
		}
		
		@Override
		public boolean ignoreShifting() {
			return ignoreShifting;
		}	
		
		@Override
		public Color getColor() {
			return c;
		}
	}
}
