package com.mygdx.game.persons;

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
     * @param name имя
     * @param priority приоритет хода
     */
    public Spearman(String name, int priority)
    {
        super(name, priority, HEALTH, POWER, AGILITY, DEFENCE, DISTANCE);
    }

    @Override
    public String toString() {
        return String.format("[Копейщик] %s", name);
    }

    @Override
    public void action() {

    }

}
