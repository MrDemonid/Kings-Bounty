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
    public static ActionShot shot;
    public static ActionKick kick;
    public static ActionText text;


    public Map(int mapWidth, int mapHeight)
    {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.tiles = new int[mapHeight][mapWidth];

        this.teams = new Teams();
        teams.createTeams(10);

        shot = null;
        kick = null;
        text = null;
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


    public static void makeShot(CoordXY from, CoordXY to, int targetDamage)
    {
        shot = new ActionShot(from, to, targetDamage);
    }
    public static void makeKick(int x, int y) { kick = new ActionKick(x, y); }
    public static void makeText(int x, int y, String txt) { text = new ActionText(x, y, txt); }



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
     * @param deltaTime
     */
    public void update(float deltaTime)
    {
        if (shot != null) {
            if (!shot.update(deltaTime))
            {
                makeKick(shot.getTargetX(), shot.getTargetY());
                makeText(shot.getTargetX(), shot.getTargetY()+1, "" + shot.getTargetDamage());
                shot = null;
            }
        } else if (kick != null) {
            if (!kick.update(deltaTime)) {
                kick = null;
            }
        } else if (text != null) {
            if (!text.update(deltaTime)) {
                text = null;
            }
        } else {
            teams.update();
        }
    }
}
