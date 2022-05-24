package com.Study_6.proj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;


public class Main {
    private static Logger log = LogManager.getLogger("myLogger");

    public static void main(String[] args) {
/*        //String(double)
        List<Measurement> mes = new LinkedList<Measurement>();
        Measurement meas = new Measurement("12345");
        mes.add(meas);
        //System.out.println(Character.codePointAt(mes.get(0).getValue().substring(0,1)));
        System.out.println(Character.isDigit(mes.get(0).getValue().charAt(1)));

        //mes.get(0).getValue().substring(0,0);
        //System.out.println(Character.isDigit(Integer.parseInt(mes.get(0).getValue().substring(0,1))));*/

        myThread myMyThreadGUI = new myThread();
        log.debug("Run GUI myThread");
        myMyThreadGUI.runGUIThread();
    }
}
