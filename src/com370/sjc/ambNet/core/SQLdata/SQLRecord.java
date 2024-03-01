package com370.sjc.ambNet.core.SQLdata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public abstract class SQLRecord {
    private HashMap<String, Object> data = new HashMap<>();

    /**
     * Attempts to retrieve the data from a ResultSet and place it into the data HashMap
     * Because the ResultSet is already obtained, errors related to whether or not there was an error retrieving the data
     * should already be handled
     *
     * If a value cannot be found, place null instead
     * @param keys list of columns to get data from
     * @param results The ResultSet containing set to the row with the data that this object will store
     */
    public SQLRecord(String[] keys, ResultSet results) {
        for (String key : keys)
            try {
                data.put(key, results.getObject(key));
            } catch (SQLException e) {
                data.put(key, null);
            }
    }

    /**
     *
     * @param key the "key" of the object to be returned
     * @return the "value" of the object
     */
    Object getValue(String key) {
        return data.get(key);
    }

    public abstract void print();

}
