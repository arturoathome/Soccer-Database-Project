package com370.sjc.ambNet.core.SQLdata;

import java.sql.ResultSet;

public class League extends SQLRecord {

    /**
     * Calls super with the result set and a list of properties to put into the parent class's data HashMap
     * @param results The ResultSet containing set to the row with the data that this object will store
     */
    public League(ResultSet results) {
        super(new String[]{"ID", "Name", "Sport", "HomeNation"},
                results);
    }
    /**
     * @return the value of the ID column from the ResultSet used to initiate this object as an int
     */
    public int getLeagueID() {
        return (int) this.getValue("ID");
    }

    /**
     * @return the value of the Name column from the ResultSet used to initiate this object as a String
     */
    public String getLeagueName() {
        return (String) this.getValue("Name");
    }

    /**
     * @return the value of the StadiumName column from the ResultSet used to initiate this object as a String
     */
    public String getSportName() {
        return (String) this.getValue("Sport");
    }

    /**
     * @return the value of the StadiumName column from the ResultSet used to initiate this object as a String
     */
    public String getHomeNation() {
        return (String) this.getValue("HomeNation");
    }


    @Override
    public void print(){
        System.out.println("\tLeague Name: " + getLeagueName());
        System.out.println("\tSport Name: " + getSportName());
        System.out.println("\tHome Nation:  " + getHomeNation());
    }

}
