package com.Study_6.proj;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class web_API {
    /*messyMethod
     *TODO
     * 1)Delay method to use while pooling website "delay"
     * 2)Querry for humidity
     * 3)Querry for preasure
     * 4)Threads pooling
     */
    public static String messyMethod() {
        //Instantiating the URL class
        URL url = null;
        try {
            url = new URL("http://meteo.kdwd.webd.pl/index.php");
        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        }

        //String dat = "";
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //LocalDateTime nowdate = LocalDateTime.now();
        //dat = LocalDateTime.now().toString();
        ///////
        //System.out.println("Hello: "+ dat);
        //////

        while (true) {
            //if (dat != LocalDateTime.now().toString()) {
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
            //Removing the HTML tags
            result = result.replaceAll("<[^>]*>", "");

            //Searching for temp value
            result = result.substring(result.indexOf("Aktualizacja:&nbsp;") + 50, result.indexOf("hrWilgotno") - 7);
            result = result.substring(result.indexOf(":") + 1, result.indexOf("deg;C") - 1);
            return result;
            //}
        }
    }

    public static String current_temperature() {
        /*
        *TODO
        * 1)Shouldn't be close or try with resources?(sc i URL)
         */
        URL url = null;
        try {
            url = new URL("http://meteo.kdwd.webd.pl/index.php");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
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
        }
        //Retrieving the String from the String Buffer object
        String result = sb.toString();
        //Removing the HTML tags
        result = result.replaceAll("<[^>]*>", "");
        //Searching for temp value
        result = result.substring(result.indexOf("Aktualizacja:&nbsp;") + 50, result.indexOf("hrWilgotno") - 7);
        result = result.substring(result.indexOf(":") + 1, result.indexOf("deg;C") - 1);
        return result;
    }

    private static boolean delay(int delTime) {
        ///Periodic issue
        for (int i = 0; i < delTime; i++) {
            //do Something
        }
        return true;
    }

}
