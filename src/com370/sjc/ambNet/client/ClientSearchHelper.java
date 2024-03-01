package com370.sjc.ambNet.client;

import com370.sjc.ambNet.core.InputManager;
import com370.sjc.ambNet.core.SQLdata.League;
import com370.sjc.ambNet.core.SQLdata.Player;
import com370.sjc.ambNet.core.SQLdata.SQLDataType;
import com370.sjc.ambNet.core.SQLdata.Team;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientSearchHelper {
    protected static void doTeamRosterSearch(InputManager input, Statement statement) throws SQLException {
        //This "do while" loop is used to make sure that the user's search has at least one result
        boolean doNext;

        //This value will hold the eventual result of the query
        //Needs to be defined before the doWhile loop, so it will remain in scope afterwards
        ResultSet result;
        do {
            input.resetMode();
            while (input.getMode() == 0) {
                System.out.println("For more information on a specific team's roster, input the team's name or the team's id");
                input.nextLine();
                if (input.getQuery().equals("")) {
                    System.out.println("Empty input received.");
                    continue;
                }
                if (input.getMode() == 0)
                    System.out.println("Please only include letters or numbers inside your input");
            }

            if (input.getMode() == 1)
                //Performs a search for the team ID supplied
                result = statement.executeQuery("SELECT * FROM teams WHERE teams.ID = " + input.getQuery());
            else
                //Performs a search for the given string within any team name
                result = statement.executeQuery("SELECT * FROM teams WHERE teams.name like '%" + input.getQuery() + "%'");

            //Print out the retrieved information for each player
            doNext = result.next();
            if (!doNext) {
                System.out.println("No results found, please try again");
            }
        } while (!doNext);

        //Do-while needs to be used here, because result.next() has already been called.
        do {
            new Team(result).print();
            System.out.println();
        } while (result.next());
    }


    protected static void doPlayerSearch(InputManager input, Statement statement) throws SQLException {
        //
        //This "do while" loop is used to make sure that the user's search has at least one result
        boolean doNext;

        //This value will hold the eventual result of the query
        //Needs to be defined before the doWhile loop, so it will remain in scope afterwards
        ResultSet result;
        do {
            input.resetMode();
            while (input.getMode() == 0) {
                System.out.println("For more information on a specific player information, input the players first name or player id");
                input.nextLine();


                if (input.getQuery().equals("")) {
                    System.out.println("Empty input received.");
                    continue;
                }
                if (input.getMode() == 0)
                    System.out.println("Please only include proper player id number inside your input");
            }

            if (input.getMode() == 1)
                //Performs a search for the player ID supplied
                result = statement.executeQuery("SELECT * FROM players WHERE players.ID = " + input.getQuery());
            else
                //Performs a search for the given string within any player's first name
                result = statement.executeQuery("SELECT * FROM players WHERE players.FirstName like '%" + input.getQuery() + "%'");

            //Print out the retrieved information for each player
            doNext = result.next();
            if (!doNext) {
                System.out.println("No results found, please try again");
            }
        } while (!doNext);

        //Do-while needs to be used here, because result.next() has already been called.
        do {
            new Player(result).print();
            System.out.println();
        } while (result.next());

    }


    protected static void doLeaguesSearch(InputManager input, Statement statement) throws SQLException {
        //This "do while" loop is used to make sure that the user's search has at least one result
        boolean doNext;

        //This value will hold the eventual result of the query
        //Needs to be defined before the doWhile loop, so it will remain in scope afterwards
        ResultSet result;
        do {
            input.resetMode();
            while (input.getMode() == 0) {
                System.out.println("For more information on a specific Leagues, input the League's name or the League's id");
                input.nextLine();
                if (input.getQuery().equals("")) {
                    System.out.println("Empty input received.");
                    continue;
                }
                if (input.getMode() == 0)
                    System.out.println("Please only include letters or numbers inside your input");
            }

            if (input.getMode() == 1)
                //Performs a search for the league ID supplied
                result = statement.executeQuery("SELECT * FROM leagues WHERE leagues.ID = " + input.getQuery());
            else
                //Performs a search for the given string within any league name
                result = statement.executeQuery("SELECT * FROM leagues WHERE leagues.name like '%" + input.getQuery() + "%'");

            //Print out the retrieved information for each league
            doNext = result.next();
            if (!doNext) {
                System.out.println("No results found, please try again");

            }
        } while (!doNext);

        //Do-while needs to be used here, because result.next() has already been called.
        do {
            new League(result).print();
            System.out.println();
        } while (result.next());




    }


    protected static void doSecondSearch(InputManager input, Statement statement) throws SQLException {
        //This "do while" loop is used to make sure that the user's search has at least one result
        boolean doNext;

        //This value will hold the eventual result of the query
        //Needs to be defined before the doWhile loop, so it will remain in scope afterwards
        ResultSet result;

        System.out.println("Is there anything else you are looking for?" +
                "\n Reply 'Yes' or 'No' below:");
        String second_decision = input.nextLine();

        if (second_decision.equals("Yes")) {


            try {
                System.out.println("Hello again," +
                        "\nIf you only want to view specific teams, players, and leagues." +
                        " \nEnter 'T' for teams, or 'P' players, or 'L' for Leagues.");
                String first_decision = input.nextLine();

                if (first_decision.equals("T")) {
                    ClientSearchHelper.doTeamRosterSearch(input, statement);
                    ClientSearchHelper.doSecondSearch(input, statement);
                } else if (first_decision.equals("P")) {
                    ClientSearchHelper.doPlayerSearch(input, statement);
                    ClientSearchHelper.doSecondSearch(input, statement);
                } else if (first_decision.equals("L")) {
                    ClientSearchHelper.doLeaguesSearch(input, statement);
                    ClientSearchHelper.doSecondSearch(input, statement);
                }


            } catch (SQLException e) {
                //Prints information on the SQLException.
                System.out.println("Message: " + e.getMessage());
                System.out.println("State: " + e.getSQLState());
                System.out.println("Error: " + e.getErrorCode());
            }

        }else if (second_decision.equals("No")){
            System.out.println("Thank you for using this program, have a good day.");
        }

    }
}
