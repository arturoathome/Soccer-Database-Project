package com370.sjc.ambNet.core.SQLdata;

import com370.sjc.ambNet.core.SQLdata.SQLRecord;

import java.sql.ResultSet;

public class SQLRecordFactory {
    public static SQLRecord createSQLRecord(ResultSet results, SQLDataType dataType) {
        return switch (dataType) {
            case Players -> new Player(results);
            case Teams -> new Team(results);
            case Leagues -> new League(results);
            default -> null;
        };

    }
}
