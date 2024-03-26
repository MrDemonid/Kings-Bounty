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

    public ArrayList<TeamPerson> allPersons;

    int curPerson;


    public Teams()
    {
        this.red = new ArrayList<>();
        this.blue = new ArrayList<>();
        this.allPersons = new ArrayList<>();
        curPerson = -1;
    }

    public void update()
    {
        allPersons.get(curPerson).active = false;
        curPerson++;
        if (curPerson >= allPersons.size())
        {
            curPerson = 0;
        }
        TeamPerson p = allPersons.get(curPerson);
        p.active = true;
//    System.out.print(p.person + " ходит. ");
        if (p.team == TeamType.RED)
        {
            p.person.step(blue, red);
        } else {
            p.person.step(red, blue);
        }
        System.out.println();
    }

    public void createTeams(int numPersons)
    {
        createOneTeam(red, numPersons, 0);
        createOneTeam(blue, numPersons, 3);
        // создаём список всех персонажей, отсортированных по приоритету хода
        ArrayList<PersonBase> all = new ArrayList<>();
        all.addAll(red);
        all.addAll(blue);
        all.sort((o1, o2) -> Integer.compare(o2.priority, o1.priority));
        // переносим их в команды
        for (PersonBase p : all)
        {
            if (red.contains(p))
            {
                allPersons.add(new TeamPerson(TeamType.RED, p));
            } else {
                allPersons.add(new TeamPerson(TeamType.BLUE, p));
            }
        }
        curPerson = allPersons.size()-1;
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
                    System.out.println("ERROR! Пересмотри алгоритм, ламер!");
            }

        }
    }

}

