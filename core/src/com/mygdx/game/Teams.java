package com.mygdx.game;

import com.mygdx.game.behavior.CoordXY;
import com.mygdx.game.behavior.HeroesNames;
import com.mygdx.game.behavior.TeamType;
import com.mygdx.game.person.*;

import java.util.ArrayList;
import java.util.Random;

public class Teams {
    ArrayList<PersonBase> red;
    ArrayList<PersonBase> blue;

    public Teams() {
        red = new ArrayList<>();
        blue = new ArrayList<>();
    }

    public void addToTeam(TeamType team, PersonBase person)
    {
        if (team == TeamType.RED)
        {
            red.add(person);
        } else {
            blue.add(person);
        }
    }

    public void createTeams(int numPersons)
    {
        createOneTeam(red,numPersons,0);
        createOneTeam(blue,numPersons,3);
    }

    public ArrayList<PersonBase> getTeam(TeamType team)
    {
        if (team == TeamType.BLUE)
            return blue;
        return red;
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
