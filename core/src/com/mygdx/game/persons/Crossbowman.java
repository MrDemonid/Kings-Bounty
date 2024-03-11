package com.mygdx.game.persons;

/**
 * Класс Арбалетчик
 */
public class Crossbowman extends ShooterBase {

    private static final int HEALTH = 800;
    private static final int POWER = 35;
    private static final int AGILITY = 20;
    private static final int DEFENCE = 5;
    private static final int DISTANCE = 6;
    private static final int ARROWS = 12;
    private static final int EFFECTIVE_DISTANCE = 3;

    /**
     * Создание экзеспляра Арбалетчика
     * @param name Имя
     * @param priority Приоритет хода
     */
    public Crossbowman(String name, int priority) {
        super(name, priority, HEALTH, POWER, AGILITY, DEFENCE, DISTANCE, ARROWS, EFFECTIVE_DISTANCE);
    }

    @Override
    public void action()
    {

    }

    @Override
    public String toString() {
        return String.format("[Арбалетчик] %s", name);
    }

}
