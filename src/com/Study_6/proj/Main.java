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



/*        //String str1 = new String("no��:44%&nbsp;Humidex123456");
        String str1 =new String(":44%Humidex");

        //System.out.println( str1.charAt(str1.indexOf("Humidex") - 3));
        //System.out.println(str1.charAt(11));
        if(String.valueOf(str1.charAt(str1.indexOf("Humidex") - 3)).equals("s")){
            str1 = str1.substring(str1.indexOf("Humidex") - 9, str1.indexOf("Humidex")-7);
        }else{
            str1 = str1.substring(str1.indexOf("Humidex") - 3, str1.indexOf("Humidex")-1);
        }
        System.out.println(str1);*/

/////////Log4j2

        //System.setProperty("log4j-core","D:\\Kamil\\.Studia\\3rok\\6semestr\\Java\\Project_for_subject_JAVA\\bin\\log4j2.properties");
        //Logger log = LogManager.getRootLogger();

/*        Logger log = LogManager.getLogger();
        Configurator.initialize(new DefaultConfiguration());
        Configurator.setRootLevel(Level.TRACE);

        log.fatal("Hello");
        log.fatal("fatal Message");
        log.error("errror message");
        log.warn("Warning Message");
        log.info("info Message");
        log.trace("Trace messega");*/

/////////myThread
        myThread myMyThread1 = new myThread();
        myThread myMyThread2 = new myThread();
        myThread myMyThread3 = new myThread();
        myThread myMyThreadGUI = new myThread();

        log.debug("Run insertion myThread for table " + "temperature_1");
        myMyThread1.insert2DB_from_web_temperaure_Thread("temperature_1",10000);

        log.debug("Run insertion myThread for table " + "humidity_1");
        myMyThread2.insert2DB_from_web_humidity_Thread("humidity_1",10000);

        log.debug("Run insertion myThread for table " + "preasure_1");
        myMyThread3.insert2DB_from_web_preasure_Thread("preasure_1",10000);

        log.debug("Run GUI myThread");
        myMyThreadGUI.runGUIThread();
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



    }
}
