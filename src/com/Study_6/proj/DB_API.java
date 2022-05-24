package com.Study_6.proj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class DB_API {
    private static final String DRIVER = "org.sqlite.JDBC";
    public static String DB_URL = "jdbc:sqlite:historian.db";
    private static Connection conn;
    private static Statement stat;

    private static Logger log = LogManager.getLogger("myLogger");

    /*conn2DB
    * TODO:
    *  1) Passing argument DB_URL that is path to DB
    *  2)Reading querry from file and passing value to querry
     */

    public static boolean conn2DB() {
        try {
            Class.forName(DB_API.DRIVER);
        } catch (ClassNotFoundException e) {
            log.error("Failure when loading JDBC driver");
            log.fatal(e);
            //e.printStackTrace();
            return false;
        }
        log.info("JDBC driver load succesfull");
        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
        } catch (SQLException e) {
            log.warn("Problem with connection with DB " + DB_URL);
            log.fatal(e);
            //e.printStackTrace();
            return false;
        }
        log.info("Connected to DB " + DB_URL);
        return true;
    }

    /*createQuerry
    * Querry that initial new historian structures
     */
    static boolean createQuerry(String target) {
        String querry =
                "CREATE TABLE IF NOT EXISTS "+ target +
                        "(id_mes INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        "timestampOf VARCHAR(15),"+
                        "valueOf VARCHAR(5),"+
                        "unitOf VARCHAR(5)"+
                        ")";
        log.trace("Create new table "+target+" with that querry ///" + querry);
        try {
            stat.execute(querry);
        } catch (SQLException e) {
            log.error("Failure when created new table");
            log.fatal(e);
            //e.printStackTrace();
            return false;
        }
        log.debug("Create querry succesful excecuted");
        return true;
    }
    /*insertQuerry
    *
     */
    static boolean insertQuerry(String target,String value){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        /************
         * HANDLING ERROR
         **/
        if(value != null) {
            try {
                PreparedStatement prepStmt = conn.prepareStatement("insert into " + target + "(valueOf,timestampOf) values (?,?);");
                log.debug("Insert into querry: " + prepStmt);
                prepStmt.setString(1, value);
                prepStmt.setString(2, dtf.format(now));
                prepStmt.execute();
            } catch (SQLException e) {
                log.warn("Unable to insert value to DB: " + target);
                //e.printStackTrace();
                return false;
            }
            log.info("Successful insertion");
        }
        /**
         * HANDLING ERROR
         ************/
        return true;
    }
    /*selectQuerry
     *
     */
    static List<Measurement> selectQuerry(String target){

        List<Measurement> mes = new LinkedList<Measurement>();
        int i = 0;
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM "+ target);
            log.debug("Starting select querry for table " + target);
            String time,value;
            while(result.next()) {
                //time = result.getString("timestampOf");
                value = result.getString("valueOf");
                mes.add(new Measurement(value));
                i++;
            }
        } catch (SQLException e) {
            log.warn("Unable to processed select querry");
            log.fatal(e);
            //e.printStackTrace();
            return null;
        }
        log.info("Select querry processed");
        //log.trace("Returned value" + mes);
        return mes;
    }
}
