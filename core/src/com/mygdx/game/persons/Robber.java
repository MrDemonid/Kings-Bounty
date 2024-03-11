package com.mygdx.game.persons;

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
     * @param name имя
     * @param priority приоритет хода
     */
    public Robber(String name, int priority)
    {
        super(name, priority, HEALTH, POWER, AGILITY, DEFENCE, DISTANCE);
    }

    @Override
    public void action()
    {

    }

    @Override
    public String toString() {
        return String.format("[Разбойник] %s", name);
    }

}
