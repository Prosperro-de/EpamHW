package com.epam.rd.java.basic.practice8;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.rd.java.basic.practice8.db.DBManager;
import com.epam.rd.java.basic.practice8.db.entity.Team;

public class Part2StudentTest {

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/test";
    private static final String URL_CONNECTION = "jdbc:h2:~/test;user=youruser;password=yourpassword;";
    private static final String USER = "youruser";
    private static final String PASS = "yourpassword";

    private static DBManager dbManager;

    @BeforeClass
    public static void beforeTest() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);

        try (OutputStream output = new FileOutputStream("app.properties")) {
            Properties prop = new Properties();
            prop.setProperty("connection.url", URL_CONNECTION);
            prop.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        }

        dbManager = DBManager.getInstance();

        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement statement = con.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS users (\n" + "  id INTEGER(11) NOT NULL AUTO_INCREMENT,\n"
                    + " login VARCHAR(20) NOT NULL, \n" + "  PRIMARY KEY (id));";

            statement.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS teams (\n" + "  id INTEGER(11) NOT NULL AUTO_INCREMENT,\n"
                    + " login VARCHAR(20) NOT NULL, \n" + "  PRIMARY KEY (id));";

            statement.executeUpdate(sql);
        }
    }

    @Test
    public void createTeamTest() {
        String name = "epam";
        Team team = Team.createTeam(name);
        Assert.assertEquals(team, new Team(name));
    }

    @Test
    public void insertTeamTest() throws SQLException {
        Team team = Team.createTeam("epam");
        DBManager dbManager = DBManager.getInstance();
        dbManager.insertTeam(team);
        Assert.assertTrue(team.getId() != 0);
    }
}