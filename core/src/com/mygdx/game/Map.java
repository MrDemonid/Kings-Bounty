package com.mygdx.game;

import com.mygdx.game.behavior.CoordXY;
import com.mygdx.game.movedobj.ActionKick;
import com.mygdx.game.movedobj.ActionShot;
import com.mygdx.game.movedobj.ActionText;

import java.util.Random;

public class Map {
    public static final int CELL_MAP_TYPES = 5;

    private final int mapWidth;
    private final int mapHeight;
    private final int[][] tiles;

    public Teams teams;
    public static ActionShot actionShoot;
    public static ActionKick actionExplode;
    public static ActionText actionText;
    public static ActionKick actionHealed;


    public Map(int mapWidth, int mapHeight)
    {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.tiles = new int[mapHeight][mapWidth];

        this.teams = new Teams();
        teams.createTeams(10);

        actionShoot = null;
        actionExplode = null;
        actionText = null;
        actionHealed = null;
    }

    /**
     * Тупо рандомно генерируем карту уровня
     */
    public void makeMap()
    {
        Random rnd = new Random();

        for (int y = 0; y < this.mapHeight; y++)
        {
            for (int x = 0; x < this.mapWidth; x++)
            {
                this.tiles[y][x] = rnd.nextInt(CELL_MAP_TYPES);
            }
        }
    }


    public static void makeActionShoot(CoordXY from, CoordXY to, int targetDamage)
    {
        actionShoot = new ActionShot(from, to, targetDamage);
    }
    public static void makeActionHealed(int x, int y, String txt)
    {
        actionHealed = new ActionKick(x, y);
        makeActionText(x, y+1, txt);
    }
    public static void makeActionExplode(int x, int y)
    {
        actionExplode = new ActionKick(x, y);
    }
    public static void makeActionText(int x, int y, String txt)
    {
        actionText = new ActionText(x, y, txt);
    }



    public int getWidth()
    {
        return this.mapWidth;
    }

    public int getHeight()
    {
        return this.mapHeight;
    }

    public int getTile(int x, int y)
    {
        if (!boundMap(x, y))
        {
            return this.tiles[0][0];
        }
        return this.tiles[y][x];
    }
    
    /**
     * Проверка координат на принадлежность карте
     * @param x по оси X
     * @param y по оси Y
     * @return true, если координаты лежат в пределах карты
     */
    public boolean boundMap(int x, int y)
    {
        return x >= 0 && y >= 0 && x < this.mapWidth && y < this.mapHeight;
    }

    /**
     * Ход какого либо персонажа
     *
     * @param deltaTime Прошедшее с предыдущего вызова время
     */
    public void update(float deltaTime)
    {
        if (actionExplode != null || actionShoot != null || actionText != null || actionHealed != null)
        {
            if (actionExplode != null)
                if (!actionExplode.update(deltaTime))
                    actionExplode = null;

            if (actionText != null)
                if (!actionText.update(deltaTime))
                    actionText = null;

            if (actionHealed != null)
                if (!actionHealed.update(deltaTime))
                    actionHealed = null;

            if (actionShoot != null)
                if (!actionShoot.update(deltaTime))
                {
                    makeActionExplode(actionShoot.getTargetX(), actionShoot.getTargetY());
                    makeActionText(actionShoot.getTargetX(), actionShoot.getTargetY()+1, "" + actionShoot.getTargetDamage());
                    actionShoot = null;
                }
        } else {
            teams.update();
        }
    }
}
