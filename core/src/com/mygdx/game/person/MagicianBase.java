package com.mygdx.game.person;

import com.mygdx.game.behavior.CoordXY;

/**
 * Базовый класс для Волшебников, в данном случае для Кодуна и Монаха,
 * но можно добавить Некроманты или Шамана.
 */
public abstract class MagicianBase extends PersonBase {

    protected int mana;                     // мана для волшебства
    protected int maxMana;

    /**
     * Конструктор базы Волшебников
     *
     * @param name     Имя
     * @param priority Приоритет хода
     * @param health   Текущее здоровье
     * @param power    Сила
     * @param agility  Ловкость (%). 3 ловкости = 1% к увороту, и 10 ловкости = 1% к критическому удару
     * @param defence  Защита (% к сопротивлению урону)
     * @param distance Дистанция воздействия на другой объект (10 у мага, 1 у крестьянина и тд)
     * @param mana     Маны в наличии
     * @param pos      Положение в прогстранстве
     */
    protected MagicianBase(String name, int priority, int health, int power, int agility, int defence, int distance, int mana, CoordXY pos)
    {
        super(name, priority, health, power, agility, defence, distance, pos);
        this.mana = mana;
        this.maxMana = mana;
    }


    @Override
    public void action()
    {

    }

}
