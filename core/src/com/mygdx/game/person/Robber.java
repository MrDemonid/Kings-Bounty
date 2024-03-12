package com.mygdx.game.person;

import com.mygdx.game.behavior.CoordXY;

/**
 * Класс Разбойник
 */
public class Robber extends InfantryBase {

    private static final int HEALTH = 1000;
    private static final int POWER = 80;
    private static final int AGILITY = 10;
    private static final int DEFENCE = 12;
    private static final int DISTANCE = 1;

    /**
     * Создание экземпляра Разбойника
     *
     * @param name Имя
     * @param pos  Положение в прогстранстве
     */
    public Robber(String name, CoordXY pos)
    {
        super(name, 0, HEALTH, POWER, AGILITY, DEFENCE, DISTANCE, pos);
    }


    @Override
    public String toString() {
        return String.format("[Разбойник] %s", name  + " " + position.toString());
    }

}
