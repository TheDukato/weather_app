package com.Study_6.proj;

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

////////Connection to DB
        Random rand = new Random();
        DB_API.conn2DB();
        DB_API.createQuerry("temperature_1");
        DB_API.insertQuerry("temperature_1",String.valueOf(rand.nextInt(30)));
        for(Measurement c: DB_API.selectQuerry("temperature_1"))
            System.out.println("Value: " + c.getValue());

////////Current temprature
        //web_API.messyMethod();

    }
}
