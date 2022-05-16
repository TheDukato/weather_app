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

        String dat = "";
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //LocalDateTime nowdate = LocalDateTime.now();
        dat = LocalDateTime.now().toString();
        ///////
        //System.out.println("Hello: "+ dat);
        //////

        while (true) {
            if (dat != LocalDateTime.now().toString()) {
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
                //System.out.println("Contents of the web page: "+result);
                result = result.substring(result.indexOf("Aktualizacja:&nbsp;") + 50, result.indexOf("hrWilgotno") - 7);
                //Format of return data: Sucho,BezchmurnieTemperatura:22.6&deg;C
                result = result.substring(result.indexOf(":") + 1, result.indexOf("deg;C") - 1);
                //Format of return data: 22.6
                ///////
                //System.out.println("Contents of the web page: " + result+"\nAt time: "+ LocalDateTime.now().toString());
                //////
                web_API.delay(10000);
                return result;
            }
        }
    }
    private static boolean delay(int delTime){
        ///Periodic issue
        for(int i = 0; i< delTime;i++){
            //do Something
        }
        return true;
    }

}
