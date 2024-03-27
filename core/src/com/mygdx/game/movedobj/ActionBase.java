package com.mygdx.game.movedobj;

import com.mygdx.game.MapRender;

public abstract class ActionBase
{
    protected int fromX, fromY;       // начальные координаты
    private float time;               // текущее время объекта (с момента его создания)
    private float liveTime;           // время жизни объекта

    /**
     * Создание анимационного объекта
     * @param x        Координата по оси X
     * @param y        Координата по оси Y
     * @param liveTime Время жизни объекта в секундах (1 сек - это наш FPS (из ConfigGame)
     */
    public ActionBase(int x, int y, float liveTime)
    {
        this.fromX = x * MapRender.getMapTileWidth();
        this.fromY = y * MapRender.getMapTileHeight();
        this.time = 0f;
        this.liveTime = liveTime;
    }

    protected void setLiveTime(float time)
    {
        liveTime = time;
    }

    protected float getLiveTime()
    {
        return liveTime;
    }

    abstract public int getCurX();
    abstract public int getCurY();

    public float getTime() {
        return time;
    }

    public boolean update(float delta)
    {
        time += delta;
        if (time >= liveTime)
            return false;
        return true;
    }

}
