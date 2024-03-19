package com.mygdx.game.movedobj;

import com.mygdx.game.MapRender;
import com.mygdx.game.behavior.CoordXY;

public class ActionKick extends ActionBase
{
    private int targetDamage;

    public ActionKick(int x, int y, int targetDamage)
    {
        super(x, y, 20);     // пол секунды
        this.targetDamage = targetDamage;
    }

    public int getTargetDamage()
    {
        return this.targetDamage;
    }

    @Override
    public int getCurX() {
        return (int) fromX;
    }

    @Override
    public int getCurY() {
        return (int) fromY;
    }
}
