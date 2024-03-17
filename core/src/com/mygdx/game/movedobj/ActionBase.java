package com.mygdx.game.movedobj;

import com.mygdx.game.MapRender;
import com.mygdx.game.behavior.CoordXY;

public abstract class ActionBase
{
    protected float fromX, fromY;       // начальные координаты
    protected int numSteps;             // количество шагов (distance * 4)

    public ActionBase(int x, int y, int steps)
    {
        this.fromX = x * MapRender.getMapTileWidth();
        this.fromY = y * MapRender.getMapTileHeight();
        numSteps = steps;
    }

    abstract public int getCurX();
    abstract public int getCurY();

    public boolean update()
    {
        if (numSteps <= 0)
            return false;
        numSteps--;
        return true;
    }

}
