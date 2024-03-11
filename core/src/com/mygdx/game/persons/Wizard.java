package com.mygdx.game.persons;

/**
 * Класс Колдун
 */
public class Wizard extends MagicianBase {

    private static final int HEALTH = 600;
    private static final int POWER = 40;
    private static final int AGILITY = 10;
    private static final int DEFENCE = 0;
    private static final int DISTANCE = 8;
    private static final int MANA = 100;

    /**
     * Создание экземпляра Колдуна
     *
     * @param name имя
     * @param priority приоритет хода
     */
    public Wizard(String name, int priority)
    {
        super(name, priority, HEALTH, POWER, AGILITY, DEFENCE, DISTANCE, MANA);
    }


    @Override
    public void action()
    {

    }

    @Override
    public String toString()
    {
        return String.format("[Колдун] %s", name);
    }

}
