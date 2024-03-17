package com.mygdx.game.person;

import com.mygdx.game.behavior.ActionInterface;
import com.mygdx.game.behavior.CoordXY;

import java.util.ArrayList;

/**
 * Абстрактный класс Пехота, в данном случае база для Разбойников и Копейщиков,
 * но можно добавить Мечников, Варваров и тд.
 */
public abstract class InfantryBase extends PersonBase {

    protected int level;            // уровень, увеличивается по мере опыта

    /**
     * Конструктор базы Пехоты
     *
     * @param name     Имя
     * @param priority Приоритет хода
     * @param health   Текущее здоровье
     * @param power    Сила
     * @param agility  Ловкость (%). 3 ловкости = 1% к увороту, и 10 ловкости = 1% к критическому удару
     * @param defence  Защита (% к сопротивлению урону)
     * @param distance Дистанция воздействия на другой объект (10 у мага, 1 у крестьянина и тд)
     * @param pos      Положение в прогстранстве
     */
    protected InfantryBase(String name, int priority, int health, int power, int agility, int defence, int distance, CoordXY pos)
    {
        super(name, priority, health, power, agility, defence, distance, pos);
        level = 1;
    }

    private void move(PersonBase target)
    {
        float vx = target.position.getX() - position.getX();
        float vy = target.position.getY() - position.getY();
        float len = (float) Math.sqrt(vx*vx + vy*vy);
        if (len != 0f)
        {
            len = 1.0f / len;
            vx = (float) Math.rint(vx * len);
            vy = (float) Math.rint(vy * len);

        }
        position.moveTo((int) (vx), (int) (vy));
    }

    private void kick(PersonBase target, boolean isMoved)
    {
        System.out.print(name + ": бьёт " + target);
        int damage = getRound(power, 10) + (power / 10) * level;
        boolean critical = (this.agility/3) >= rnd.nextInt(100);
        if (critical)
        {
            damage *= 2.0f;
        }
        int res = target.getDamage(damage);
        if (res > 0)
        {
            if (critical)
                System.out.print(" и наносит критический удар в " + res + " повреждений!");
            else
                System.out.print(" и наносит " + res + " повреждений.");
        } else {
            System.out.print(", но " + target.name + " увернулся!");
        }
        if (target.health <= 0)
        {
            System.out.print("\n" + target + " вышел из чата!");
        }
    }

    @Override
    public void step(ArrayList<PersonBase> enemies) {
        if (health <= 0)
            return;
        PersonBase target = this.findNearestPerson(enemies);
        if (target!= null)
        {
            int dist = (int) position.distanceTo(target.position);
            System.out.println("  - dist = " + dist);
            if (dist <= 1)
            {
                // бьём
                kick(target, false);
            } else {
                move(target);
                if (position.distanceTo(target.position) <= 1.0f)
                {
                    // бьём с ходу, с меньшей силой
                    kick(target, true);
                }
            }
        }

    }

}
