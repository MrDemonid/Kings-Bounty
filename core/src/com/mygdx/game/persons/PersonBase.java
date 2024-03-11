package com.mygdx.game.persons;

import java.util.Random;


/**
 * База для персонажей.
 */
public abstract class PersonBase {
    protected static Random rnd;
    static {
        rnd = new Random();
    }

    protected String name;
    protected int priority;                 // приоритет хода
    protected int health;                       // здоровье
    protected final int maxHealth;
    protected final int power;                //
    protected final int agility;                  // ловкость
    protected final int defence;              //
    protected int distance;                 // дистанция воздействия на другой объект

    protected int curX, curY;

    /**
     * Конструктор базы
     * @param name Имя
     * @param priority Приоритет хода
     * @param health Текущее здоровье
     * @param power Сила
     * @param agility Ловкость (%). 3 ловкости = 1% к увороту, и 10 ловкости = 1% к критическому удару
     * @param defence Защита (% к сопротивлению урону)
     * @param distance Дистанция воздействия на другой объект (10 у мага, 1 у крестьянина и тд)
     */
    protected PersonBase(String name, int priority, int health, int power, int agility, int defence, int distance)
    {
        this.name = name;
        this.priority = priority;
        this.health = getRound(health, 10);
        this.maxHealth = this.health;
        this.power = getRound(power, 10);
        this.agility = getRound(agility, 10);
        this.defence = defence;
        this.distance = distance;
        this.curX = 0;
        this.curY = 0;
    }

    /**
     * Возвращает значение со случайной погрешностью в +-percent%
     * @param origin Начальное значение
     * @param percent Погрешность
     * @return Значение с внесённой погрешностью
     */
    private int getRound(int origin, int percent)
    {
        if (percent > origin)
            return origin;
        int n = (origin * percent) / 100;
        return origin + (rnd.nextInt(0, n * 2+1)- n);
    }

    /**
     * Задаёт местоположение персонажа (нужно будет добавить проверку на границы карты)
     * @param x По оси X
     * @param y По оси Y
     */
    public void setPosition(int x, int y)
    {
        this.curX = x;
        this.curY = y;
    }

    /**
     * Лечение персонажа
     * @param health Количество добавляемого здоровья
     */
    public void healed(int health)
    {
        this.health = Math.min(this.health +health, this.maxHealth);
    }

    /**
     * Персонаж принимает урон
     * @param damage Величина урона (конечная будет зависеть от @defence и ловкости)
     * @return Количество действительно нанесенного урона
     */
    public int getDamage(int damage)
    {
        boolean probability = (this.agility/3) >= rnd.nextInt(100);
        if (probability)
            return 0;           // увернулись
        int loss = damage - (this.defence * damage) / 100;
        loss = Math.min(loss, this.health);
        this.health -= loss;
        return loss;
    }

    /**
     * Оценка расстояния до другого персонажа
     * @param target Объект до которого замеряем расстояние
     * @return Расстояние от текущего персонажа до заданного
     */
    public int distanceTo(PersonBase target)
    {
        return (int) Math.sqrt(Math.pow(this.curX - target.curX, 2) + Math.pow(this.curY - target.curY, 2));
    }

    /**
     * Действие персонажа (ход)
     */
    abstract public void action();


//    @Override
//    public String toString()
//    {
//        return String.format("[%s] %s", this.getClass().getSimpleName(), this.name);
//    }

}
