
package com.epam.rd.java.basic.practice8;

import com.epam.rd.java.basic.practice8.db.DBManager;
import com.epam.rd.java.basic.practice8.db.entity.Team;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class Part4StudentTest {

    @Test
    public void shouldDeleteTeam() {

        DBManager dbManager = mock(DBManager.class);

        Team team = new Team();
        team.setName("team1");

        dbManager.deleteTeam(team);

        Assert.assertNotNull(dbManager);
    }

}