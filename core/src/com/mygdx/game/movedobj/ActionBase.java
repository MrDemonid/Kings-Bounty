package com.mygdx.game.movedobj;

import com.mygdx.game.MapRender;
import com.mygdx.game.behavior.CoordXY;

public abstract class ActionBase
{
    protected float fromX, fromY;       // начальные координаты
    protected int numSteps;             // количество шагов (distance * 4)
    private float time;                 // время жизни объекта

    public ActionBase(int x, int y, int steps)
    {
        this.fromX = x * MapRender.getMapTileWidth();
        this.fromY = y * MapRender.getMapTileHeight();
        this.time = 0f;
        numSteps = steps;
    }

    abstract public int getCurX();
    abstract public int getCurY();

    public float getTime() {
        return time;
    }

    public boolean update(float delta)
    {
        time += delta;
        if (numSteps <= 0)
            return false;
        numSteps--;
        return true;
    }

}
