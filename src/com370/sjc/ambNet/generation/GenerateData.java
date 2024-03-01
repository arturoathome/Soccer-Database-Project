package com370.sjc.ambNet.generation;

import com370.sjc.ambNet.generation.player.NameGenerator;
import com370.sjc.ambNet.generation.team.StadiumGenerator;
import com370.sjc.ambNet.generation.team.TeamNameGenerator;

import java.sql.*;
import java.util.Random;

public class GenerateData {
    final static int TEAMS_TO_GENERATE = 5;
    final static int PLAYERS_PER_TEAM = 5;

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Random random = new Random();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/com370project?user=root");
            stmt = conn.createStatement();
            //Clear Players and teams(clearing players before teams avoids a PK/FK exception)
            stmt.addBatch("DELETE from players");
            stmt.addBatch("DELETE from teams");

            stmt.executeBatch();

            System.out.println("Players and Teams Cleared");

            for (int i = 0; i < TEAMS_TO_GENERATE; i++)
                stmt.addBatch(
                        String.format("INSERT INTO teams VALUES('%d','%s','%s','%s','%s','%d')", i,
                                TeamNameGenerator.getTeamName(), StadiumGenerator.getStadium(random),
                                random.nextInt(18), "", 0));

            stmt.executeBatch();
            System.out.println("Teams Created!");
            rs = stmt.executeQuery("SELECT * FROM teams");
            while (rs.next()) {
                int team_id = rs.getInt("id");
                for (int i = 0; i < PLAYERS_PER_TEAM; i++)
                    stmt.addBatch(
                            String.format("INSERT INTO players VALUES('%d','%s','%s','%d','%d')",
                                    (team_id * PLAYERS_PER_TEAM) + i, NameGenerator.getName(random), NameGenerator.getName(random), 18 + random.nextInt(16), team_id));
            }
            stmt.executeBatch();
            System.out.println("Players Created!");

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
