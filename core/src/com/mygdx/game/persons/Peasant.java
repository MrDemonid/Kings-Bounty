package com.mygdx.game.persons;

public class Peasant extends PersonBase {

    private static final int HEALTH = 500;
    private static final int POWER = 30;
    private static final int AGILITY = 30;
    private static final int DEFENCE = 0;
    private static final int DISTANCE = 1;
    private static final int FULL_BAG = 12;

    private final int bag;                      // сколько стрел имеем с собой

    /**
     * Создание экземпляра Крестьянина
     * @param name имя
     * @param priority приоритет хода
     */
    public Peasant(String name, int priority) {
        super(name, priority, HEALTH, POWER, AGILITY, DEFENCE, DISTANCE);
        bag = 0;
    }

    /**
     * Ход крестьянина (если загружены, то к стрелку, иначе на "базу" загружаться)
     */
    @Override
    public void action() {

    }

    @Override
    public String toString() {
        return String.format("[Крестьянин] %s", name);
    }

}
