package com.mygdx.game.config;

public class ConfigGame {
    private final static int FPS = 60;
    private final static float EXPLODE_LIVE_TIME = 48.0f / FPS;

    private ConfigGame() {}

    public static int getFps()
    {
        return FPS;
    }
    public static float getExplodeLiveTime() { return EXPLODE_LIVE_TIME; }
    
}
