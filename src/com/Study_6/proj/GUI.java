package com.Study_6.proj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class GUI {
    public static void mainMenu() {
        JFrame jf = new JFrame("My Calculator");
        jf.setLayout(new GridLayout(2,1));
        jf.setSize(400,300);

        JPanel topMenu = new JPanel();
        jf.add(topMenu);

        JButton refresh = new JButton("Refresh");
        topMenu.add(refresh);

        String[] timeRef = {"1min","5min","15min"};
        JList jlist = new JList(timeRef);
        topMenu.add(jlist);

        JPanel plots = new JPanel();
        plots.setLayout(new GridLayout(1,3));
        plots.setLayout(new CardLayout());
        jf.add(plots);

        JPanel temperature = new JPanel();
        plots.add(temperature);
        temperature.setBackground(Color.blue);

        JPanel humidity = new JPanel();
        plots.add(humidity);
        humidity.setBackground(Color.red);

        JPanel preasure = new JPanel();
        plots.add(preasure);
        preasure.setBackground(Color.yellow);

        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}