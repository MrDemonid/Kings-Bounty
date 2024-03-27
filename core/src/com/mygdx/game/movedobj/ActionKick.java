package com.mygdx.game.movedobj;

import com.mygdx.game.MapRender;
import com.mygdx.game.behavior.CoordXY;
import com.mygdx.game.config.ConfigGame;

public class ActionKick extends ActionBase
{

    public ActionKick(int x, int y)
    {
        super(x, y, ConfigGame.getExplodeLiveTime());
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
