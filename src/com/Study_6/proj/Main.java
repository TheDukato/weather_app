package com.Study_6.proj;

import javax.swing.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

////////GUI
        GUI.mainMenu();

////////Connection to DB
        Random rand = new Random();//rand.nextInt(30)
        DB_API.conn2DB();
        DB_API.createQuerry("temperature_1");
        DB_API.insertQuerry("temperature_1",String.valueOf(web_API.messyMethod()));
        //for(Measurement c: DB_API.selectQuerry("temperature_1"))
        //    System.out.println("Value: " + c.getValue());

////////Current temprature
        //web_API.messyMethod();

////////plots_2D

/*        //create an instance of JFrame class
        JFrame frame = new JFrame();
        // set size, layout and location for frame.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new plots_2D());
        frame.setSize(400, 400);
        frame.setLocation(200, 200);
        frame.setVisible(true);*/
    }
}
