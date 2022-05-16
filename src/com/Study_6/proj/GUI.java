package com.Study_6.proj;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI {

    static private int period;

    public static void mainMenu() {



        JFrame jf = new JFrame("Weather window");
        jf.setLayout(new GridLayout(2,1));
        jf.setSize(400,300);
        jf.setLocationRelativeTo(null);

        /*Top menu
        *
         */
        JPanel topMenu = new JPanel();

        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

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
        /*Main menu
         *
         */

        JPanel control = new JPanel();
        control.setLayout(new BorderLayout());

        JPanel plots = new JPanel(new CardLayout());

        /*
        * TODO*********************************
        *  1) one action listener
         */
/*        ActionListener myActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e){
                CardLayout cl = (CardLayout) plots.getLayout();
                if (e.getActionCommand().equals("Previous plot")) cl.previous(plots);
                else if (e.getActionCommand().equals("Previous plot")) cl.next(plots);
            }
        };*/

        JButton prev = new JButton("Previous plot");
        prev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                CardLayout cl = (CardLayout) plots.getLayout();
                if (e.getActionCommand().equals("Previous plot")) cl.previous(plots);
            }
        });
        JButton next = new JButton("Next plot");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                CardLayout cl = (CardLayout) plots.getLayout();
                if (e.getActionCommand().equals("Next plot")) cl.next(plots);
            }
        });

        JPanel temp = new JPanel();
        temp.setBackground(Color.blue);

        JPanel humidity = new JPanel();
        humidity.setBackground(Color.red);

        JPanel preasure = new JPanel();
        preasure.setBackground(Color.yellow);

        plots_2D temperature = new plots_2D();
        DB_API.conn2DB();
        temperature.mes = DB_API.selectQuerry("temperature_1");

        jf.add(topMenu);
        topMenu.add(refresh);
        topMenu.add(jlist);
        topMenu.add(CurrPeriod);
        jf.add(control);
        control.add(prev,BorderLayout.WEST);
        control.add(plots, BorderLayout.CENTER);
        control.add(next,BorderLayout.EAST);
        plots.add(temperature);
        plots.add(humidity);
        plots.add(preasure);
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
