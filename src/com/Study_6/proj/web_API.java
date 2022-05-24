package com.Study_6.proj;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class web_API {

    private static Logger log = LogManager.getLogger("myLogger");

    /*messyMethod
     *TODO
     * 1)Delay method to use while pooling website "delay" +++
     * 2)Querry for humidity
     * 3)Querry for preasure
     * 4)Threads pooling +++
     * 5)Shouldn't be close or try with resources?(sc i URL)
     * 6)42 linie object class Scanner "sc" is null when didn't have internet
     */

    public static String current_temperature() {
        URL url = null;
        try {
            url = new URL("http://meteo.kdwd.webd.pl/index.php");
        } catch (MalformedURLException e) {
            //e.printStackTrace();
            log.error("Failure while connecting to web site");
            log.fatal(e);
        }
        log.debug("Successful while connecting to web site");
        /************
         * HANDLING ERROR
         **/
        Scanner sc = null;
        while(sc==null){
            try {
                sc = new Scanner(url.openStream());
            } catch (IOException e) {
                log.error("Failure while retrieving content of website \nTrying to reconnect");
                log.fatal(e);
                //e.printStackTrace();
            }
        }
        /**
         * HANDLING ERROR
         ************/
        log.debug("Successful retrieving content of website");
        //Instantiating the StringBuffer class to hold the result
        StringBuffer sb = new StringBuffer();
        while (sc.hasNext()) {
            sb.append(sc.next());
        }
        log.trace("Successful append content of web site to string buffer");
        String result = sb.toString();
        log.trace("Successful save from string buffer to string");
        result = result.replaceAll("<[^>]*>", "");
        log.trace("Successful removed HTML tags");
        //Searching for temp value
        result = result.substring(result.indexOf("Aktualizacja:&nbsp;") + 50, result.indexOf("hrWilgotno") - 7);
        result = result.substring(result.indexOf(":") + 1, result.indexOf("deg;C") - 1);
        log.debug("Finded this value -> \""+result+ "\" of temperature");
        return result;
    }

    public static String current_humidity() {
        URL url = null;
        try {
            url = new URL("http://meteo.kdwd.webd.pl/index.php");
        } catch (MalformedURLException e) {
            //e.printStackTrace();
            log.error("Failure while connecting to web site");
        }
        log.debug("Successful while connecting to web site");
        /************
         * HANDLING ERROR
         **/
        Scanner sc = null;
        while(sc==null){
            try {
                sc = new Scanner(url.openStream());
            } catch (IOException e) {
                log.error("Failure while retrieving content of website \nTrying to reconnect");
                log.fatal(e);
                //e.printStackTrace();
            }
        }
        /**
         * HANDLING ERROR
         ************/
        log.debug("Successful retrieving content of website");
        //Instantiating the StringBuffer class to hold the result
        StringBuffer sb = new StringBuffer();
        while (sc.hasNext()) {
            sb.append(sc.next());
        }
        log.trace("Successful append content of web site to string buffer");
        String result = sb.toString();
        log.trace("Successful save from string buffer to string");
        result = result.replaceAll("<[^>]*>", "");
        log.trace("Successful removed HTML tags");
        //Searching for temp value
        //log.fatal(result);

        if(String.valueOf(result.charAt(result.indexOf("Humidex") - 3)).equals("s")){
            result = result.substring(result.indexOf("Humidex") - 9, result.indexOf("Humidex")-7);
        }else{
            result = result.substring(result.indexOf("Humidex") - 3, result.indexOf("Humidex")-1);
        }
        //result = result.substring(result.indexOf(":") + 1, result.indexOf("deg;C") - 1);
        //log.fatal(result);
        log.debug("Finded this value -> \""+result+ "\" of humidity");
        return result;
    }

    public static String current_preasure() {
        URL url = null;
        try {
            url = new URL("http://meteo.kdwd.webd.pl/index.php");
        } catch (MalformedURLException e) {
            //e.printStackTrace();
            log.error("Failure while connecting to web site");
        }
        log.debug("Successful while connecting to web site");
        /************
         * HANDLING ERROR
         **/
        Scanner sc = null;
        while(sc==null){
            try {
                sc = new Scanner(url.openStream());
            } catch (IOException e) {
                log.error("Failure while retrieving content of website \nTrying to reconnect");
                log.fatal(e);
                //e.printStackTrace();
            }
        }
        /**
         * HANDLING ERROR
         ************/
        log.debug("Successful retrieving content of website");
        //Instantiating the StringBuffer class to hold the result
        StringBuffer sb = new StringBuffer();
        while (sc.hasNext()) {
            sb.append(sc.next());
        }
        log.trace("Successful append content of web site to string buffer");
        String result = sb.toString();
        log.trace("Successful save from string buffer to string");
        result = result.replaceAll("<[^>]*>", "");
        log.trace("Successful removed HTML tags");
        //Searching for temp value
        result = result.substring(result.indexOf("hPa") - 6, result.indexOf("hPa"));
        //result = result.substring(result.indexOf(":") + 1, result.indexOf("deg;C") - 1);
        log.debug("Finded this value -> \""+result+ "\" of preasure");
        return result;
    }
}
