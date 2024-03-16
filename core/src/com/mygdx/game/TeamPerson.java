package com.mygdx.game;

import com.mygdx.game.behavior.TeamType;
import com.mygdx.game.person.PersonBase;

public class TeamPerson {
    public TeamType team;
    public PersonBase person;

    public TeamPerson(TeamType team, PersonBase person)
    {
        this.team = team;
        this.person = person;
    }

}
