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
import java.util.Vector;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Main {
    public static void main(String[] args) {

////////GUI
        //GUI.mainMenu();

////////Connection to DB
        //Random rand = new Random();//rand.nextInt(30)

        //DB_API.conn2DB();
        //DB_API.createQuerry("temperature_1");
        //DB_API.insertQuerry("temperature_1",String.valueOf(web_API.messyMethod()));

        //for(Measurement c: DB_API.selectQuerry("temperature_1"))
        //    System.out.println("Value: " + c.getValue());

////////Current temprature
        //web_API.messyMethod();
        //System.out.println(web_API.messyMethod());
        //System.out.println(web_API.current_temperature());

////////plots_2D

/////////thread
/*        Program obj = new Program();
        Thread thred = new Thread();*/

        thread myThread1 = new thread();
        thread myThread2 = new thread();

        myThread1.insert2DB_from_web("temperature_1");
        myThread2.runGUI();
    }
}
