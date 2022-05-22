package com.Study_6.proj;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;


public class Main {
    //private static final Logger log = LogManager.getLogger(Main.class);
    //private static final Logger log = LogManager.getLogger("HelloWorld");
    //private static final Logger log = LogManager.getLogger();

    private static Logger log = LogManager.getLogger();

    public static void main(String[] args) {
        Configurator.initialize(new DefaultConfiguration());
        Configurator.setRootLevel(Level.DEBUG);


        myThread myMyThreadGUI = new myThread();

        myThread myMyThread1 = new myThread();
        myThread myMyThread2 = new myThread();
        myThread myMyThread3 = new myThread();

        log.debug("Run insertion myThread for table " + "temperature_1");
        myMyThread1.insert2DB_from_web_temperaure_Thread("temperature_1",10000);

        log.debug("Run insertion myThread for table " + "humidity_1");
        myMyThread2.insert2DB_from_web_humidity_Thread("humidity_1",10000);

        log.debug("Run insertion myThread for table " + "preasure_1");
        myMyThread3.insert2DB_from_web_preasure_Thread("preasure_1",10000);

        log.debug("Run GUI myThread");
        myMyThreadGUI.runGUIThread();
    }
}
