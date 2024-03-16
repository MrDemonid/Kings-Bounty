package com.mygdx.game.movedobj;

import com.mygdx.game.behavior.CoordXY;

public abstract class ActionBase {
    CoordXY pos;
    CoordXY to;

    public ActionBase(CoordXY pos, CoordXY to)
    {
        this.pos = pos;
        this.to = to;
    }

    public boolean update()
    {
        if (to.getX() == pos.getX() && to.getY() == pos.getY())
        {
            return false;   // заканчиваем
        }
        float vx = to.getX() - pos.getX();
        float vy = to.getY() - pos.getY();

        // нормализуем вектор
        float len = to.distanceTo(pos);
        if (len == 0.0f)
            len = 1.0f;
        len = 1.0f / len;
        vx *= len;
        vy *= len;

        vx += pos.getX() + 0.5f;
        vy += pos.getY() + 0.5f;
        pos.setXY((int) vx, (int) vy);

        return true;
    }

}
