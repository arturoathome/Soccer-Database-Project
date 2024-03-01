package com370.sjc.ambNet;

import com370.sjc.ambNet.core.SQLdata.Player;
import com370.sjc.ambNet.core.SQLdata.Team;

import java.sql.*;

public class TestMain {
    public static void main(String[] args) {
        try {
            //Connect to the Server and run a query selecting every player
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/com370project?user=com_client");
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM players");

            //Run the following code for each player retrieved
            while (results.next()) {


                //Create a Player Object that holds the retrieved properties
                Player player = new Player(results);

                //Create a Team Object representing that player's team
                //A new statement must be created to avoid closing the first result set with a new query
                Statement lStatement = connection.createStatement();
                ResultSet rTeam = lStatement.executeQuery("SELECT * FROM teams WHERE ID = " + player.getID());
                rTeam.next();
                Team team = new Team(rTeam);

                //Print out the player's information and their team
                System.out.printf("%s %s is %d years old and plays for the %s!\n",
                        player.getFirstName(),
                        player.getLastName(),
                        player.getAge(),
                        team.getName());

                //Close the local statement(also closes the resultSet)
                lStatement.close();
            }
            connection.close();

        } catch (SQLException e) {
            //Prints information on the SQLException.
            System.out.println("Message: " + e.getMessage());
            System.out.println("State: " + e.getSQLState());
            System.out.println("Error: " + e.getErrorCode());
            e.printStackTrace();
        }
    }
}
