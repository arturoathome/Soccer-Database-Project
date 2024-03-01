package com370.sjc.ambNet.core.SQLdata;

import java.sql.ResultSet;

public class Team extends SQLRecord {

    /**
     * Calls super with the result set and a list of properties to put into the parent class's data HashMap
     *
     * @param results The ResultSet containing set to the row with the data that this object will store
     */
    public Team(ResultSet results) {
        super(new String[]{"ID", "Name", "StadiumName", "TrophyCount", "TeamHistory", "LeagueID"}, results);
    }

    /**
     * @return the value of the ID column from the ResultSet used to initiate this object as an int
     */
    public int getID() {
        return (int) this.getValue("ID");
    }

    /**
     * @return the value of the Name column from the ResultSet used to initiate this object as a String
     */
    public String getName() {
        return (String) this.getValue("Name");
    }

    /**
     * @return the value of the StadiumName column from the ResultSet used to initiate this object as a String
     */
    public String getStadiumName() {
        return (String) this.getValue("StadiumName");
    }

    /**
     * @return the value of the TrophyCount column from the ResultSet used to initiate this object as an int
     */
    public int getTrophyCount() {
        return (int) this.getValue("TrophyCount");
    }

    /**
     * @return the value of the LeagueHistory column from the ResultSet used to initiate this object as a String
     */
    public String getLeagueHistory() {
        return (String) this.getValue("LeagueHistory");
    }

    /**
     * @return the value of the LeagueID column from the ResultSet used to initiate this object as an int
     */
    public int getLeagueID() {
        return (int) this.getValue("LeagueID");
    }

    @Override
    public void print(){
        System.out.println("Name: " +getName());
        System.out.println("\tHome Field: " + getStadiumName());
        System.out.println("\tTrophy Count: " + getTrophyCount());
    }

}
