package com.mygdx.game.person;

import com.mygdx.game.behavior.CoordXY;

/**
 * Класс Копейщик
 */
public class Spearman extends InfantryBase {

    private static final int HEALTH = 1000;
    private static final int POWER = 70;
    private static final int AGILITY = 60;
    private static final int DEFENCE = 10;
    private static final int DISTANCE = 1;

    /**
     * Создание экземпляра Копейщика
     *
     * @param name Имя
     * @param pos  Положение в прогстранстве
     */
    public Spearman(String name, CoordXY pos)
    {
        super(name, 0, HEALTH, POWER, AGILITY, DEFENCE, DISTANCE, pos);
    }

    @Override
    public String toString() {
        return String.format("[Копейщик] %s", name  + " " + position.toString());
    }

}
