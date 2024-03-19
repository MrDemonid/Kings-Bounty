package com.mygdx.game.movedobj;

import com.mygdx.game.MapRender;
import com.mygdx.game.behavior.CoordXY;

public class ActionShot extends ActionBase
{
    private static final int SIZE_STEP = 16;

    protected int curX, curY;             // текущие
    private float vx, vy;       // направление веткора до цели
    protected int step;                 // текущий шаг

    protected CoordXY to;

    private int targetDamage;

    public ActionShot(CoordXY pos, CoordXY to, int targetDamage)
    {
        super(pos.getX(), pos.getY(), 0);
        this.to = to;
        this.targetDamage = targetDamage;

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

    public int getTargetDamage()
    {
        return this.targetDamage;
    }

    @Override
    public int getCurX() {
        return curX;
    }

    @Override
    public int getCurY() {
        return curY;
    }

    public int getTargetX()
    {
        return to.getX();
    }
    public int getTargetY()
    {
        return to.getY();
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
