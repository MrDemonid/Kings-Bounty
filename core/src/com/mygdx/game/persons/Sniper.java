package com.mygdx.game.persons;

/**
 * Класс Снайпер (лучник)
 */
public class Sniper extends ShooterBase {

    private static final int HEALTH = 800;
    private static final int POWER = 30;
    private static final int AGILITY = 30;
    private static final int DEFENCE = 5;
    private static final int DISTANCE = 6;
    private static final int ARROWS = 12;
    private static final int EFFECTIVE_DISTANCE = 4;


    /**
     * Создание экзеспляра Снайпера
     * @param name Имя
     * @param priority Приоритет хода
     */
    public Sniper(String name, int priority)
    {
        super(name, priority, HEALTH, POWER, AGILITY, DEFENCE, DISTANCE, ARROWS, EFFECTIVE_DISTANCE);
    }


    /**
     * Выбираем цель и атакуем, если есть стрелы
     */
    @Override
    public void action()
    {

    }

    @Override
    public String toString()
    {
        return String.format("[Снайпер] %s", name);
    }

}
