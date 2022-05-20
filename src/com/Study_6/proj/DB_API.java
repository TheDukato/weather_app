package com.Study_6.proj;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DB_API {
    private static final String DRIVER = "org.sqlite.JDBC";
    public static String DB_URL = "jdbc:sqlite:historian.db";
    private static Connection conn;
    private static Statement stat;

    private static Logger log = LogManager.getLogger();

    /*conn2DB
    * TODO:
    *  1) Passing argument DB_URL that is path to DB
    *  2)Reading querry from file and passing value to querry
     */

    public static boolean conn2DB() {
        Configurator.initialize(new DefaultConfiguration());
        Configurator.setRootLevel(Level.DEBUG);

        try {
            Class.forName(DB_API.DRIVER);
        } catch (ClassNotFoundException e) {
            log.error("Failure when loading JDBC driver");
            //e.printStackTrace();
            return false;
        }
        log.debug("JDBC driver load succesfull");
        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
        } catch (SQLException e) {
            log.warn("Problem with connection with DB " + DB_URL);
            //e.printStackTrace();
            return false;
        }
        log.debug("Connected to DB " + DB_URL);
        return true;
    }

    /*createQuerry
    * Querry that initial new historian structures
     */
    static boolean createQuerry(String target) {
        Configurator.initialize(new DefaultConfiguration());
        Configurator.setRootLevel(Level.DEBUG);

        String querry =
                "CREATE TABLE IF NOT EXISTS "+ target +
                        "(id_mes INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        "timestampOf VARCHAR(15),"+
                        "valueOf VARCHAR(5),"+
                        "unitOf VARCHAR(5)"+
                        ")";
        log.debug("Create new table "+target+" with that querry ///" + querry);
        try {
            log.debug("Before exec");
            stat.execute(querry);
            log.debug("After exec");
        } catch (SQLException e) {
            log.error("Failure when created new table");
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
        Configurator.initialize(new DefaultConfiguration());
        Configurator.setRootLevel(Level.DEBUG);

        try {
            PreparedStatement prepStmt = conn.prepareStatement("insert into "+ target +"(valueOf) values (?);");
            log.debug("Insert into querry: " + prepStmt);
            prepStmt.setString(1, value);
            prepStmt.execute();
        } catch (SQLException e){
            log.warn("Unable to insert value to DB: " + target);
            //e.printStackTrace();
            return false;
        }
        log.debug("Successful insertion");
        return true;
    }
    /*selectQuerry
     *
     */
    static List<Measurement> selectQuerry(String target){
        Configurator.initialize(new DefaultConfiguration());
        Configurator.setRootLevel(Level.DEBUG);

        List<Measurement> mes = new LinkedList<Measurement>();
        int i = 0;
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM "+ target);
            log.debug("Starting select querry for table " + target);
            String time,value;
            while(result.next()) {
                time = result.getString("timestampOf");
                value = result.getString("valueOf");
                mes.add(new Measurement(value));
                //System.out.println(mes.get(i).getValue());
                i++;
            }
        } catch (SQLException e) {
            log.warn("Unable to processed select querry");
            //e.printStackTrace();
            return null;
        }
        log.debug("Select querry processed");
        log.trace("Returned value" + mes);
        return mes;
    }
}
