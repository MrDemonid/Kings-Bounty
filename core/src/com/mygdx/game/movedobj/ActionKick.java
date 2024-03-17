package com.mygdx.game.movedobj;

import com.mygdx.game.MapRender;
import com.mygdx.game.behavior.CoordXY;

public class ActionKick extends ActionBase
{

    public ActionKick(int x, int y)
    {
        super(x, y, 10);     // пол секунды
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
