package com.Study_6.proj;

public class thread extends Thread{
    public void insert2DB_from_web(String nameOfTable) {
        while (true) {
            DB_API.conn2DB();
            DB_API.insertQuerry(nameOfTable,String.valueOf(web_API.messyMethod()));
            try {
                System.out.println("Be quiet, I try sleep");
                this.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Poll successfull");
        }
    }
    public void runGUI() {
        System.out.println("Starting GUI thread");
        GUI.mainMenu();
    }
}
