package com370.sjc.ambNet.client;


import com370.sjc.ambNet.core.*;
import com370.sjc.ambNet.core.SQLdata.*;
import java.sql.*;

public class Main {

    public static void main(String[] args) {
        //Define variables that will be used to manage the SQL connection
        Connection connection;
        Statement statement;

        //Defines the input-manager, which will be used to receive user input.
        InputManager input = new InputManager(System.in);


        try {
            //Set up the Connection and the statement variables to process SQL commands
            //Since this is a "client" application, the com_client user should be setup to only have access to the SELECT command
            //A separate user should be used to provide sports information to the database. The GenerateData class in the generation package
            //is an example of such a program
            connection = DriverManager.getConnection("jdbc:mysql://localhost/com370project","com_client","");
            statement = connection.createStatement();

            //Prints an introductory message and waits for user input
            System.out.println("Welcome to the AMBNet Sport Stat Viewer.\n" +"Press enter to continue");
                 input.nextLine();

                 System.out.println("What would you like to see?" +
                         "\nTo see all the leagues enter 'Leagues'" +
                         "\nTo see all the players enter 'players'" +
                         "\nTo see all the teams enter 'teams'" +
                         "\nPlease input phrase 'view all' to view information on all current teams and players." +
                    "\nIf you only want to view specific teams, players, and leagues." +
                         " \nEnter 'T' for teams, or 'P' players, or 'L' for Leagues.");
                String first_decision = input.nextLine();

            if (first_decision.equals("view all")) {
                printTable(SQLDataType.Teams, statement);
                printTable(SQLDataType.Players, statement);
                ClientSearchHelper.doSecondSearch(input, statement);
            } else if (first_decision.equals("T")) {
                    ClientSearchHelper.doTeamRosterSearch(input, statement);
                ClientSearchHelper.doSecondSearch(input, statement);
            } else if (first_decision.equals("P")) {
                ClientSearchHelper.doPlayerSearch(input, statement);
                ClientSearchHelper.doSecondSearch(input, statement);
            } else if (first_decision.equals("L")) {
                ClientSearchHelper.doLeaguesSearch(input, statement);
                ClientSearchHelper.doSecondSearch(input, statement);
            }else if (first_decision.equals("Leagues")){
                printTable(SQLDataType.Leagues, statement);
            }else if (first_decision.equals("players")){
                printTable(SQLDataType.Players, statement);
            }else if (first_decision.equals("teams")){
                printTable(SQLDataType.Teams, statement);
            }


        } catch (SQLException e) {
            //Prints information on the SQLException.
            System.out.println("Message: " + e.getMessage());
            System.out.println("State: " + e.getSQLState());
            System.out.println("Error: " + e.getErrorCode());
        }
    }

    /**
     * Runs a Select * SQL Query on the passed table and uses the response to print the details of each table member
     *
     * @param statement SQL Statement variable that the request for team
     * @throws SQLException SQL based exception that should be handled from wherever this method is called.
     */
    public static void printTable(SQLDataType type,Statement statement) throws SQLException {
        ResultSet results = statement.executeQuery("SELECT * FROM " + type.toString());
        while (results.next()) {
            SQLRecordFactory.createSQLRecord(results,type).print();
            System.out.println();
        }
        results.close();
    }

}