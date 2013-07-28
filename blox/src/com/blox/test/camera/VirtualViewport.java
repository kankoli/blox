package com.blox.test.camera;

import com.blox.framework.v0.util.Game;

public class VirtualViewport {
    float virtualWidth;  
    float virtualHeight;  
    float screenWidth;  
    float screenHeight;  
  
    public float getVirtualWidth() {  
        return virtualWidth;  
    }  
  
    public float getVirtualHeight() {  
        return virtualHeight;  
    }  
  
    public VirtualViewport(float virtualWidth, float virtualHeight) {  
        this(virtualWidth, virtualHeight, false);  
    }  
  
    public VirtualViewport(float virtualWidth, float virtualHeight, boolean shrink) {  
        this.virtualWidth = virtualWidth;  
        this.virtualHeight = virtualHeight;
        this.screenWidth = Game.world.screenWidth;
        this.screenHeight = Game.world.screenHeight;        		
    }  
  
    public float getWidth() {  
        return getWidth(screenWidth, screenHeight);  
    }  
  
    public float getHeight() {  
        return getHeight(screenWidth, screenHeight);  
    }  
  
    /** 
     * Returns the view port width to let all the virtual view port to be shown on the screen. 
     *  
     * @param screenWidth 
     *            The screen width. 
     * @param screenHeight 
     *            The screen Height. 
     */  
    public float getWidth(float screenWidth, float screenHeight) {  
        float virtualAspect = virtualWidth / virtualHeight;  
        float aspect = screenWidth / screenHeight;  
        if (aspect > virtualAspect || (Math.abs(aspect - virtualAspect) < 0.01f)) {  
            return virtualHeight * aspect;  
        } else {  
            return virtualWidth;  
        }  
    }  
  
    /** 
     * Returns the view port height to let all the virtual view port to be shown on the screen. 
     *  
     * @param screenWidth 
     *            The screen width. 
     * @param screenHeight 
     *            The screen Height. 
     */  
    public float getHeight(float screenWidth, float screenHeight) {  
        float virtualAspect = virtualWidth / virtualHeight;  
        float aspect = screenWidth / screenHeight;  
        if (aspect > virtualAspect || (Math.abs(aspect - virtualAspect) < 0.01f)) {  
            return virtualHeight;  
        } else {  
            return virtualWidth / aspect;  
        }  
    }  
}
