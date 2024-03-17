package com.mygdx.game.movedobj;

import com.mygdx.game.MapRender;
import com.mygdx.game.behavior.CoordXY;

public class ActionShot extends ActionBase
{
    private static final int SIZE_STEP = 16;

    protected int curX, curY;             // текущие
    private float vx, vy;       // направление веткора до цели
    protected int step;                 // текущий шаг

    public ActionShot(CoordXY pos, CoordXY to)
    {
        super(pos.getX(), pos.getY(), 0);

        curX = (int) fromX;
        curY = (int) fromY;
        float toX =  to.getX() * MapRender.getMapTileWidth();
        float toY = to.getY() * MapRender.getMapTileHeight();
        // получаем вектор на цель
        vx = toX - fromX;
        vy = toY - fromY;
        // нормализуем его
        float len = (float) Math.sqrt(vx*vx + vy*vy);
        numSteps = (int) len / SIZE_STEP;
        step = 0;
        if (len != 0.0f)
        {
            len = 1.0f / len;
            vx *= len;
            vy *= len;
        }
    }

    @Override
    public int getCurX() {
        return curX;
    }

    @Override
    public int getCurY() {
        return curY;
    }

    @Override
    public boolean update()
    {
        if (step > numSteps)
            return false;

        curX = (int) (fromX + (vx * step * SIZE_STEP));
        curY = (int) (fromY + (vy * step * SIZE_STEP));
        step++;
        return true;
    }

}
