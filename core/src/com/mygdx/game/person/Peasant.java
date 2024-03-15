package com.mygdx.game.person;

import com.mygdx.game.behavior.CoordXY;

import java.util.ArrayList;

public class Peasant extends PersonBase{

    private static final int HEALTH = 500;
    private static final int POWER = 30;
    private static final int AGILITY = 30;
    private static final int DEFENCE = 0;
    private static final int DISTANCE = 1;
    private static final int FULL_BAG = 12;

    private final int bag;                      // сколько стрел имеем с собой

    /**
     * Создание экземпляра Крестьянина
     * 
     * @param name Имя
     * @param pos  Положение в прогстранстве
     *
     */
    public Peasant(String name, CoordXY pos) {
        super(name, 0, HEALTH, POWER, AGILITY, DEFENCE, DISTANCE, pos);
        bag = 0;
    }

    /**
     * Ход персонажа
     *
     * @param enemies Список его врагов
     */
    @Override
    public void step(ArrayList<PersonBase> enemies) {

    }

    @Override
    public String toString() {
        return String.format("[Крестьянин] %s", name  + " " + position.toString());
    }

}

