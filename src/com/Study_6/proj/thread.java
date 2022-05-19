package com.Study_6.proj;

public class thread extends Thread{
    public void insert2DB_from_web(String nameOfTable) {
        Runnable r = () -> {
            while (true) {
                DB_API.conn2DB();
                DB_API.insertQuerry(nameOfTable, String.valueOf(web_API.messyMethod()));
                try {
                    System.out.println("Be quiet, I try sleep");
                    this.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
                System.out.println("Poll successfull");
            }
        };
        Thread t = new Thread(r);
        t.start();
    }
    public void runGUI() {
        Runnable r = () -> {
            while (true) {
                System.out.println("Starting GUI thread");
                GUI.mainMenu();
                return;
            }
        };
        Thread t = new Thread(r);
        t.start();

    }
}
