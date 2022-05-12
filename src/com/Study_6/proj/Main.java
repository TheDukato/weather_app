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
        //Instantiating the URL class
        URL url = null;
        try {
            url = new URL("http://meteo.kdwd.webd.pl/index.php");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
////////Connection to DB
        Random rand = new Random();
        DB_API.conn2DB();
        DB_API.createQuerry("temperature_1");
        DB_API.insertQuerry("temperature_1",String.valueOf(rand.nextInt(30)));
        for(Measurement c: DB_API.selectQuerry("temperature_1"))
            System.out.println("Value: " + c.getValue());


////////Current temprature
/*      String dat = "";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        dat = dtf.format(now);
        System.out.println("Hello: "+ dat);*/

        /*while (true) {
            if(dat!=LocalDateTime.now().toString()) {
                //Retrieving the contents of the specified page
                Scanner sc = null;
                try {
                    sc = new Scanner(url.openStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Instantiating the StringBuffer class to hold the result
                StringBuffer sb = new StringBuffer();
                while (sc.hasNext()) {
                    sb.append(sc.next());
                    //System.out.println(sc.next());
                }
                //Retrieving the String from the String Buffer object
                String result = sb.toString();
                //System.out.println(result);
                //Removing the HTML tags
                result = result.replaceAll("<[^>]*>", "");
                //System.out.println("Contents of the web page: "+result);
                result = result.substring(result.indexOf("Aktualizacja:&nbsp;") + 50, result.indexOf("hrWilgotno") - 7);
                //Format of return data: Sucho,BezchmurnieTemperatura:22.6&deg;C
                result = result.substring(result.indexOf(":") + 1, result.indexOf("deg;C") - 1);
                //Format of return data: 22.6
                System.out.println("Contents of the web page: " + result);

            }

        }*/

    }


}
