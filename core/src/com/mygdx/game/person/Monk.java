package com.mygdx.game.person;

import com.mygdx.game.behavior.CoordXY;

public class Monk extends MagicianBase {

    private static final int HEALTH = 600;
    private static final int POWER = 40;
    private static final int AGILITY = 10;
    private static final int DEFENCE = 0;
    private static final int DISTANCE = 8;
    private static final int MANA = 100;

    /**
     * Создание экземпляра Монаха
     *
     * @param name имя
     */
    public Monk(String name, CoordXY pos)
    {
        super(name, 0, HEALTH, POWER, AGILITY, DEFENCE, DISTANCE, MANA, pos);
    }

    @Override
    public String toString()
    {
        return String.format("[Монах] %s", name  + " " + position.toString());
    }

}
