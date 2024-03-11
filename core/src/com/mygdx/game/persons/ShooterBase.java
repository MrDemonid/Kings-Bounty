package com.mygdx.game.persons;

/**
 * Базовый класс для стрелков, в данном случае для Снайперов и Арбалетчиков,
 * но можно добавить Мушкетёров или Катапульту :)
 */
public abstract class ShooterBase extends PersonBase {

    protected int ammo;                         // боеприпасов в наличии
    protected int level;                        // уровень (зависит от опыта и даёт увеличение урона)
    protected int effectiveDistance;            // макс. дальность эффективной стрельбы

    /**
     * Конструктор базы Стрелков
     *
     * @param name     Имя
     * @param priority Приоритет хода
     * @param health   Текущее здоровье
     * @param power    Сила
     * @param agility  Ловкость (%). 3 ловкости = 1% к увороту, и 10 ловкости = 1% к критическому удару
     * @param defence  Защита (% к сопротивлению урону)
     * @param distance Дистанция воздействия на другой объект (10 у мага, 1 у крестьянина и тд)
     */
    protected ShooterBase(String name, int priority, int health, int power, int agility, int defence, int distance, int ammo, int effectiveDistance)
    {
        super(name, priority, health, power, agility, defence, distance);
        this.ammo = ammo;
        this.effectiveDistance = effectiveDistance;
        this.level = 1;
    }
}
