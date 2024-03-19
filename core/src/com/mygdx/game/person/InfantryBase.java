package com.mygdx.game.person;

import com.mygdx.game.behavior.CoordXY;
import com.mygdx.game.Map;
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

    private boolean isMoved(CoordXY pos, ArrayList<PersonBase> persons)
    {
        for (PersonBase p : persons)
        {
            if (p.position.equal(pos))
                return false;
        }
        return true;
    }

    private void move(PersonBase target, ArrayList<PersonBase> friends)
    {
        int[] px = {1, 0, -1, 0};
        int[] py = {0, 1, 0, -1};

        CoordXY newPos = new CoordXY(position.getX(),position.getY());
        int minIdx = -1;
        float minDist = Float.MAX_VALUE;
        for (int i = 0; i < 4; i++)
        {
            newPos.setXY(position.getX()+px[i], position.getY()+py[i]);
            if (isMoved(newPos, friends))
            {
                float dist = position.fastDistance(target.position, px[i], py[i]);
                if (dist < minDist)
                {
                    minIdx = i;
                    minDist = dist;                }
            }
        }
        if (minIdx == -1.0f)
            return;

        position.increment(px[minIdx], py[minIdx]);

        /*
        CoordXY delta = position.getDelta(target.position);
        CoordXY newPoz = new CoordXY(position.getX(),position.getY());

        int dx = delta.getX();
        if (dx != 0)
            dx = Math.abs(dx)/dx;
        int dy = delta.getY();
        if (dy != 0)
            dy = Math.abs(dy)/dy;
        if (dx != 0 && dy != 0)
            dy = 0;
        newPoz.increment(dx,dy);

        for (PersonBase vin: friends){
            if(vin.position.equal(newPoz))
                return;
        }
        position = newPoz;
        */

        System.out.println(name + ": перемещается на (" + position.getX() + ", " + position.getY() + ")");
    }

    private void attack(PersonBase target, boolean isMoved)
    {
        Map.makeShot(position, target.position);
//        Map.makeKick(target.position.getX(), target.position.getY());

        System.out.print(name + ": бьёт " + target);
        int damage = getRound(power, 10) + (power / 10) * level;
        boolean critical = (this.agility/3) >= rnd.nextInt(100);
        if (critical)
        {
            damage *= 2.0f;
        }
        if (isMoved)
            damage /= 2;                        // удар с хода

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
    public void step(ArrayList<PersonBase> enemies, ArrayList<PersonBase> friends)
    {
        PersonBase target = this.findNearestPerson(enemies);
        if (health <= 0 || target == null)
            return;

        if (position.distanceTo(target.position) < 1.5f)
        {
            // бьём
            attack(target, false);
        } else {
            move(target, friends);
            if (position.distanceTo(target.position) < 1.5f)
            {
                // бьём с ходу, с меньшей силой
                attack(target, true);
            }
        }

    }
}
