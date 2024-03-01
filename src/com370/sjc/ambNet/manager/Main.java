package com370.sjc.ambNet.manager;

import com370.sjc.ambNet.core.SQLdata.*;
import com370.sjc.ambNet.core.*;

import java.sql.*;

public class Main {
    static Connection connection;
    static Statement statement;
    static InputManager inputManager;

    public static void main(String[] args) {
        inputManager = new InputManager(System.in);
        try {
            //TODO
            connection = DriverManager.getConnection("jdbc:mysql://localhost/com370project?user=root");
            statement = connection.createStatement();

            displayWelcomeMessage();
            tableSelection();
            connection.close();

        } catch (SQLException e) {
            //Prints information on the SQLException.
            System.out.println("Message: " + e.getMessage());
            System.out.println("State: " + e.getSQLState());
            System.out.println("Error: " + e.getErrorCode());
            e.printStackTrace();
        }
    }

    private static void displayWelcomeMessage() throws SQLException {
        System.out.println("Welcome to the AMBNet Database Manager.");
        for (SQLDataType t : SQLDataType.values()) {
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM " + t.name());
            rs.next();
            System.out.printf("\tYou have (%s) %s\n", rs.getObject(1), t.name());
            rs.close();
        }
    }

    /**
     * Handles the user input process for selecting a table to modify.
     *
     * @throws SQLException
     */
    private static void tableSelection() throws SQLException {
        System.out.println("To Begin Navigation, Select A Table to Modify:");
        for (int i = 0; i < SQLDataType.values().length; i++) {
            System.out.printf("%d:%s\t\t", i, SQLDataType.values()[i]);
        }
        System.out.println();
        do {
            System.out.println("Please enter the number corresponding to the table you wish to modify. To cancel, input 'x': ");
            inputManager.nextLine();
        }
        while (inputManager.getMode() != 1 && !inputManager.getQuery().equals("x"));
        if (inputManager.getQuery().equals("x"))
            return;
        int selection = Integer.parseInt(inputManager.getQuery());
        if (selection > SQLDataType.values().length - 1) {
            System.out.println("Invalid Table Selection, please try again");
            tableSelection();
            return;
        }
        navigateTable(SQLDataType.values()[selection]);
    }

    public static void navigateTable(SQLDataType type) throws SQLException {
        ResultSet results = statement.executeQuery("SELECT * FROM " + type.toString());
        SQLRecord selection = navigateTableRecursive(results, type);
        //results.close();
    }

    private static SQLRecord navigateTableRecursive(ResultSet results, SQLDataType type) throws SQLException {
        int i = 0;
        SQLRecord[] records = new SQLRecord[3];
        while (results.next() && i != 3) {
            records[i] = SQLRecordFactory.createSQLRecord(results, type);
            System.out.printf("%d:", i);
            records[i].print();
            System.out.println();
            i++;
        }
        System.out.println("Select a record to modify, or press enter to view more");
        inputManager.nextLine();
        if (inputManager.getQuery().equals(""))
            navigateTableRecursive(results, type);
        if (inputManager.getMode() == 1)
            if (inputManager.getQueryInt() < 3)
                return records[inputManager.getQueryInt()];
        System.out.println("Invalid input");
        return null;
    }
}
