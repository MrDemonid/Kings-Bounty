package com.mygdx.game;

import com.mygdx.game.behavior.CoordXY;
import com.mygdx.game.behavior.HeroesNames;
import com.mygdx.game.person.*;

import java.util.ArrayList;
import java.util.Random;

public class Teams {
    ArrayList<PersonBase> red;
    ArrayList<PersonBase> blue;
    public ArrayList<PersonBase> all;

    int curPerson;

    public Teams()
    {
        this.red = new ArrayList<>();
        this.blue = new ArrayList<>();
        this.all = new ArrayList<>();
        curPerson = 0;
    }

    public void createTeams(int numPersons)
    {
        createOneTeam(red, numPersons, 0);
        createOneTeam(blue, numPersons, 3);
        // создаём список всех персонажей, отсортированных по приоритету хода
        all.addAll(red);
        all.addAll(blue);
        all.sort((o1, o2) -> Integer.compare(o2.priority, o1.priority));
    }

    public ArrayList<PersonBase> getTeam(int team)
    {
    }

    private void createOneTeam(ArrayList<PersonBase> team, int num, int start)
    {
        Random rnd = new Random();
        while (--num >= 0)
        {
            int n = start + rnd.nextInt(4);
            switch (n)
            {
                case 0:
                    team.add(new Crossbowman(HeroesNames.getRandomName(), new CoordXY(0, num)));
                    break;
                case 1:
                    team.add(new Spearman(HeroesNames.getRandomName(), new CoordXY(0, num)));
                    break;
                case 2:
                    team.add(new Wizard(HeroesNames.getRandomName(), new CoordXY(0, num)));
                    break;
                case 3:
                    team.add(new Peasant(HeroesNames.getRandomName(), new CoordXY(start > 0 ? 9 : 0, num)));
                    break;
                case 4:
                    team.add(new Sniper(HeroesNames.getRandomName(), new CoordXY(9, num)));
                    break;
                case 5:
                    team.add(new Monk(HeroesNames.getRandomName(), new CoordXY(9, num)));
                    break;
                case 6:
                    team.add(new Robber(HeroesNames.getRandomName(), new CoordXY(9, num)));
                    break;
                default:
                    Console.println("ERROR! Пересмотри алгоритм, ламер!");
            }

        }
    }

}
