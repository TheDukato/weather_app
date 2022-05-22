package com.Study_6.proj;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;

public class myThread extends Thread{
    private static Logger log = LogManager.getLogger();
    public static int currDelayTime;
    public static int enable =0;

    public void insert2DB_from_web_temperaure_Thread(String nameOfTable,int delayTime) {
        Configurator.initialize(new DefaultConfiguration());
        Configurator.setRootLevel(Level.DEBUG);

        currDelayTime = delayTime;
        Runnable r = () -> {
            while (true) {
                DB_API.conn2DB();
                DB_API.insertQuerry(nameOfTable, String.valueOf(web_API.current_temperature()));
                try {
                    log.debug("Waiting " + delayTime/1000 + " seconds");
                    this.sleep(currDelayTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
                if (enable == 1){
                    enable = 0;
                    return;
                }
                //System.out.println("Poll successfull");
            }
        };
        Thread t = new Thread(r);
        t.start();
    }
    public void insert2DB_from_web_preasure_Thread(String nameOfTable,int delayTime) {

        Configurator.initialize(new DefaultConfiguration());
        Configurator.setRootLevel(Level.DEBUG);

        currDelayTime = delayTime;
        Runnable r = () -> {
            while (true) {
                DB_API.conn2DB();
                DB_API.createQuerry("preasure_1");
                DB_API.insertQuerry(nameOfTable, String.valueOf(web_API.current_preasure()));
                try {
                    log.debug("Waiting " + delayTime/1000 + " seconds");
                    this.sleep(currDelayTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
                //System.out.println("Poll successfull");
                if (enable == 1){
                    enable = 0;
                    return;
                }
            }
        };
        Thread t = new Thread(r);
        t.start();

    }
    public void insert2DB_from_web_humidity_Thread(String nameOfTable,int delayTime) {
        Configurator.initialize(new DefaultConfiguration());
        Configurator.setRootLevel(Level.DEBUG);

        currDelayTime = delayTime;
        Runnable r = () -> {
            while (true) {
                DB_API.conn2DB();
                //log.debug("Executed: DB_API.conn2DB()");
                DB_API.createQuerry("humidity_1");
                DB_API.insertQuerry(nameOfTable, String.valueOf(web_API.current_humidity()));
                try {
                    log.debug("Waiting " + delayTime/1000 + " seconds");
                    this.sleep(currDelayTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
                //System.out.println("Poll successfull");
                if (enable == 1){
                    enable = 0;
                    return;
                }
            }
        };
        Thread t = new Thread(r);
        t.start();
    }
    public void runGUIThread() {
        Configurator.initialize(new DefaultConfiguration());
        Configurator.setRootLevel(Level.DEBUG);

        Runnable r = () -> {
                GUI.mainMenu();
        };
        Thread t = new Thread(r);
        t.start();
        log.debug("GUI myThread started");
    }
}
