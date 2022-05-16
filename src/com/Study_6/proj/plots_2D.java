package com.Study_6.proj;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.List;

public class plots_2D extends JPanel {
    //initialize coordinates
    public List<Measurement> mes = new LinkedList<Measurement>();
    private int marg = 10;

    protected void paintComponent(Graphics grf){
        //create instance of the Graphics to use its methods
        super.paintComponent(grf);
        Graphics2D graph = (Graphics2D)grf;

        //Sets the value of a single preference for the rendering algorithms.
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // get width and height
        int width = getWidth();
        int height = getHeight();

        // draw graph
        graph.draw(new Line2D.Double(marg, marg, marg, height-marg));
        graph.draw(new Line2D.Double(marg, height-marg, width-marg, height-marg));

        //find value of x and scale to plot points
        double x = (double)(width-2*marg)/(mes.size()-1);
        double scale = (double)(height-marg)/getMax();

        //set color for points
        graph.setPaint(Color.RED);

        // set points to the graph
        for(int i=0; i<mes.size(); i++){
            double x1 = marg+i*x;
            double y1 = height-marg-scale*Double.parseDouble(mes.get(i).getValue());
            graph.fill(new Ellipse2D.Double(x1-2, y1-2, 4, 4));
        }
    }

    //create getMax() method to find maximum value
    private double getMax(){
        double max = -Double.MAX_VALUE;
        for(int i=0; i<mes.size(); i++){
            if(Double.parseDouble(mes.get(i).getValue())>max)
                max = Double.parseDouble(mes.get(i).getValue());

        }
        return max;
    }
}
