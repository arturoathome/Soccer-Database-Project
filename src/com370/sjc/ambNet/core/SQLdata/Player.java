package com370.sjc.ambNet.core.SQLdata;

import java.sql.ResultSet;

public class Player extends SQLRecord {

    /**
     * Calls super with the result set and a list of properties to put into the parent class's data HashMap
     * @param results The ResultSet containing set to the row with the data that this object will store
     */
    public Player(ResultSet results) {
        super(new String[]{"ID", "FirstName", "LastName", "Age", "TeamID"},
                results);
    }

    //Getter Functions below

    /**
     * @return the value of the ID column from the ResultSet used to initiate this object as an int
     */
    public int getID() {
        return (int) this.getValue("ID");
    }

    /**
     * @return the value of the FirstName column from the ResultSet used to initiate this object as a String
     */
    public String getFirstName() {
        return (String) this.getValue("FirstName");
    }

    /**
     * @return the value of the LastName column from the ResultSet used to initiate this object as a String
     */
    public String getLastName() {
        return (String) this.getValue("LastName");
    }

    /**
     * @return the value of the Age column from the ResultSet used to initiate this object as an int
     */
    public int getAge() {
        return (int) this.getValue("Age");
    }

    /**
     * @return the value of the TeamID column from the ResultSet used to initiate this object as an int
     */
    public int getTeamID() {
        return (int) this.getValue("TeamID");
    }

    @Override
    public void print(){
        System.out.println("\tFirst Name: " + getFirstName());
        System.out.println("\tLast Name: " + getLastName());
        System.out.println("\tAge:  " + getAge());
    }

}
