package com.Study_6.proj;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DB_API {

    private static final String DRIVER = "org.sqlite.JDBC";
    public static String DB_URL = "jdbc:sqlite:historian.db";

    private static Connection conn;
    private static Statement stat;

    /*conn2DB
    * TODO:
    *  1) Passing argument DB_URL that is path to DB
    *  2)Reading querry from file and passing value to querry
     */
    public static boolean conn2DB() {

        try {
            Class.forName(DB_API.DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Couldn't find JDBC driver");
            //e.printStackTrace();
            return false;
        }
        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem with open DB");
            //e.printStackTrace();
            return false;
        }
        return true;
    }
    /****SQLQuerry
     *
     *
     *
     */

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
        try {
            stat.execute(querry);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /*insertQuerry
    *
     */
    static boolean insertQuerry(String target,String value){
        try {
            PreparedStatement prepStmt = conn.prepareStatement("insert into "+ target +"(valueOf) values (?);");
            prepStmt.setString(1, value);
            prepStmt.execute();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
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
            String time,value;
            while(result.next()) {
                time = result.getString("timestampOf");
                value = result.getString("valueOf");
                mes.add(new Measurement(value));
                //System.out.println(mes.get(i).getValue());
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return mes;
    }
}
