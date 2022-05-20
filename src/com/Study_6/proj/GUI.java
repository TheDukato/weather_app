package com.Study_6.proj;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    static private int period;

    private static Logger log = LogManager.getLogger();

    public static void mainMenu() {
        Configurator.initialize(new DefaultConfiguration());
        Configurator.setRootLevel(Level.DEBUG);

        JFrame jf = new JFrame("Weather window");
        jf.setLayout(new GridLayout(2,1));
        jf.setSize(400,300);
        jf.setLocationRelativeTo(null);

        /*Top menu
        *
         */
        JPanel topMenu = new JPanel();

        JLabel CurrPeriod = new JLabel("0min");

        String[] timeRef = {"1min","5min","15min"};
        JList jlist = new JList(timeRef);
        jlist.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    switch ((String) jlist.getSelectedValue()){
                        case "1min":
                            CurrPeriod.setText((String) jlist.getSelectedValue());
                            period = 1;
                            break;
                        case "5min":
                            CurrPeriod.setText((String) jlist.getSelectedValue());
                            period = 5;
                            break;
                        case"15min":
                            CurrPeriod.setText((String) jlist.getSelectedValue());
                            period = 15;
                            break;
                        default:
                            break;
                    }
                }
            }
        });

        JPanel control = new JPanel();
        control.setLayout(new BorderLayout());

        JPanel plots = new JPanel(new CardLayout());

        /*
        * TODO*********************************
        *  1) one action listener
        *  2) Add logging
         */
/*        ActionListener myActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e){
                CardLayout cl = (CardLayout) plots.getLayout();
                if (e.getActionCommand().equals("Previous plot")) cl.previous(plots);
                else if (e.getActionCommand().equals("Previous plot")) cl.next(plots);
            }
        };*/

        JPanel temp = new JPanel();
        temp.setBackground(Color.blue);

        plots_2D humidity = new plots_2D();

        plots_2D preasure = new plots_2D();

        plots_2D temperature = new plots_2D();
        //temperature.mes = DB_API.selectQuerry("temperature_1");

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
                plots.add(temperature);
                log.debug("Plot temperature successful added");

                humidity.mes = DB_API.selectQuerry("humidity_1");
                plots.add(humidity);
                log.debug("Plot humidity successful added");
                plots.add(humidity);

                preasure.mes = DB_API.selectQuerry("preasure_1");
                plots.add(preasure);
                log.debug("Plot preasure successful added");
                plots.add(preasure);
            }
        });

        jf.add(topMenu);
        topMenu.add(jlist);
        topMenu.add(CurrPeriod);
        jf.add(control);
        control.add(prev,BorderLayout.WEST);
        control.add(plots, BorderLayout.CENTER);
        control.add(next,BorderLayout.EAST);
        control.add(refresh,BorderLayout.SOUTH);
        plots.add(temp);

        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

/*class MyActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        CardLayout cl = (CardLayout) plots.getLayout();
        if (e.getActionCommand().equals("Previous plot")) cl.previous(plots);
        else if (e.getActionCommand().equals("Previous plot")) cl.next(plots);
    }
}*/
