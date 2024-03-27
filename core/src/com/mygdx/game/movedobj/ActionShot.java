package com.mygdx.game.movedobj;

import com.mygdx.game.MapRender;
import com.mygdx.game.behavior.CoordXY;
import com.mygdx.game.config.ConfigGame;

public class ActionShot extends ActionBase
{
    private static final int SIZE_STEP = 16;

    private float deltaX;       // вектор на цель по X
    private float deltaY;       // вектор на цель по Y

    protected int curX, curY;             // текущие

    protected CoordXY to;

    private int targetDamage;

    public ActionShot(CoordXY pos, CoordXY to, int targetDamage)
    {
        super(pos.getX(), pos.getY(), 15f);
        this.targetDamage = targetDamage;
        this.to = to;

        float w = CoordXY.getWidth() * MapRender.getMapTileWidth();
        float h = CoordXY.getHeight() * MapRender.getMapTileHeight();
        float maxLen = (float) Math.sqrt(w*w + h*h);
        deltaX = (to.getX() * MapRender.getMapTileWidth()) - fromX;
        deltaY = (to.getY() * MapRender.getMapTileHeight()) - fromY;
        float len = (float) Math.sqrt(deltaX*deltaX + deltaY*deltaY);
        float time = (1.5f / maxLen) * len;
        setLiveTime(time);

        System.out.println("------------");
        System.out.println("- max len = " + maxLen);
        System.out.println("- len = " + len);
        System.out.println("- deltaX = " + deltaX);
        System.out.println("- deltaY = " + deltaY);

        System.out.println("- len = " + len);
        System.out.println("- time = " + time);
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
    public boolean update(float delta)
    {
        if (!super.update(delta))
            return false;

        curX = fromX + (int) ((deltaX / getLiveTime()) * getTime());
        curY = fromY + (int) ((deltaY / getLiveTime()) * getTime());
        return true;
    }

}
