package com.Study_6.proj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GUI {

    static private int width=800;
    static private int height=300;

    private static Logger log = LogManager.getLogger("myLogger");

    public static void mainMenu() {
        myThread myMyThread1 = new myThread();
        myThread myMyThread2 = new myThread();

        log.debug("Run insertion myThread for table " + "temperature_1");
        myMyThread1.insert2DB_from_web_temperaure_Thread("temperature_1",10000);

        log.debug("Run insertion myThread for table " + "humidity_1");
        myMyThread2.insert2DB_from_web_humidity_Thread("humidity_1",10000);

        JFrame jf = new JFrame("Weather window");
        jf.setLayout(new GridLayout(2,1));
        jf.setSize(width,height);
        jf.setLocationRelativeTo(null);

        JPanel plots = new JPanel(new CardLayout());
        JPanel conteriner4temp = new JPanel();
        conteriner4temp.setLayout(new BorderLayout());
        JPanel conteriner4humi = new JPanel();
        conteriner4humi.setLayout(new BorderLayout());

        JPanel topMenu = new JPanel();
        topMenu.setLayout(new BorderLayout());

        JPanel botMenu = new JPanel();
        botMenu.setLayout(new BorderLayout());

        JPanel bottomMenu = new JPanel();
        bottomMenu.setLayout(new BorderLayout());
        bottomMenu.setSize((int) (width*0.25),(int) (height*0.25));

        JLabel CurrPeriod = new JLabel("Pooling time: 10 seconds");

        String[] timeRef = {"1min","5min","15min"};
        JList jlist = new JList(timeRef);
        jlist.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    switch ((String) jlist.getSelectedValue()){
                        case "1min":
                            CurrPeriod.setText("Pooling time: " + (String) jlist.getSelectedValue());
                            myThread.enable=1;
                            log.debug("Run insertion myThread for table " + "temperature_1");
                            myMyThread1.insert2DB_from_web_temperaure_Thread("temperature_1",60000);
                            log.debug("Run insertion myThread for table " + "humidity_1");
                            myMyThread2.insert2DB_from_web_humidity_Thread("humidity_1",60000);

                            log.info("Setting pooling period on 1 min");
                            break;
                        case "5min":
                            CurrPeriod.setText("Pooling time: " + (String) jlist.getSelectedValue());

                            myThread.enable=1;
                            log.debug("Run insertion myThread for table " + "temperature_1");
                            myMyThread1.insert2DB_from_web_temperaure_Thread("temperature_1",300000);
                            log.debug("Run insertion myThread for table " + "humidity_1");
                            myMyThread2.insert2DB_from_web_humidity_Thread("humidity_1",300000);

                            log.info("Setting pooling period on 5 min");
                            break;
                        case"15min":
                            CurrPeriod.setText("Pooling time: " + (String) jlist.getSelectedValue());

                            myThread.enable=1;
                            log.debug("Run insertion myThread for table " + "temperature_1");
                            myMyThread1.insert2DB_from_web_temperaure_Thread("temperature_1",900000);
                            log.debug("Run insertion myThread for table " + "humidity_1");
                            myMyThread2.insert2DB_from_web_humidity_Thread("humidity_1",900000);

                            log.info("Setting pooling period on 15 min");
                            break;
                        default:
                            break;
                    }
                }
            }
        });
        plots_2D humidity = new plots_2D();
        plots_2D temperature = new plots_2D();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        JLabel tempy = new JLabel("Temperatura(1-29)[C]");
        JLabel tempx = new JLabel("Czas od\t 2022/05/20 18:13:02                   do \t\t\t\t"+dtf.format(now));

        JLabel humiy = new JLabel("Wilgotność(45-68)[%]");
        JLabel humix = new JLabel("Czas od\t 2022/05/20 18:13:02                   do \t\t\t\t"+dtf.format(now));

        DB_API.conn2DB();
        temperature.mes = DB_API.selectQuerry("temperature_1");
        conteriner4temp.add(tempy,BorderLayout.NORTH);
        conteriner4temp.add(tempx,BorderLayout.SOUTH);
        conteriner4temp.add(temperature);
        plots.add(conteriner4temp);
        log.debug("Plot temperature successful added");

        humidity.mes = DB_API.selectQuerry("humidity_1");
        conteriner4humi.add(humiy,BorderLayout.NORTH);
        conteriner4humi.add(humix,BorderLayout.SOUTH);
        conteriner4humi.add(humidity);
        plots.add(conteriner4humi);
        log.debug("Plot humidity successful added");

        JButton prev = new JButton("Previous plot");
        prev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                CardLayout cl = (CardLayout) plots.getLayout();
                if (e.getActionCommand().equals("Previous plot")) cl.previous(plots);
                log.debug("Prev button clicked");
            }
        });

        JButton next = new JButton("Next plot");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                CardLayout cl = (CardLayout) plots.getLayout();
                if (e.getActionCommand().equals("Next plot")) cl.next(plots);
                log.debug("Next button clicked");
            }
        });

        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DB_API.conn2DB();
                temperature.mes = DB_API.selectQuerry("temperature_1");
                conteriner4temp.add(tempy,BorderLayout.NORTH);
                conteriner4temp.add(temperature);
                plots.add(conteriner4temp);
                log.debug("Plot temperature successful added");

                humidity.mes = DB_API.selectQuerry("humidity_1");
                conteriner4humi.add(humiy,BorderLayout.NORTH);
                conteriner4humi.add(humidity);
                plots.add(conteriner4humi);
                log.debug("Plot humidity successful added");

            }
        });

        topMenu.add(jlist,BorderLayout.WEST);
        topMenu.add(CurrPeriod,BorderLayout.EAST);
        botMenu.add(prev,BorderLayout.WEST);
        botMenu.add(next,BorderLayout.EAST);
        botMenu.add(refresh,BorderLayout.CENTER);
        bottomMenu.add(botMenu,BorderLayout.SOUTH);
        bottomMenu.add(topMenu,BorderLayout.NORTH);
        jf.add(plots,BorderLayout.NORTH);
        jf.add(bottomMenu,BorderLayout.SOUTH);

        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
