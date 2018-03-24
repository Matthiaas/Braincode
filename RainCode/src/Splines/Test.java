package Splines;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Point2D;

public class Test extends JPanel {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Test");
        frame.setSize(1000, 500);

        frame.setLocationRelativeTo(null);

        JPanel panel = new Test();
        frame.add(panel);

        frame.setVisible(true);
        panel.setVisible(true);

        panel.repaint();

    }

    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g.clearRect(0, 0, 1000, 500);

        int n = 4;
        double x[] = new double[n];
        double y[] = new double[n];

        for (int i = 0; i < n; i++) {
            x[i] = i*200;
            y[i] = Math.random()*500;
            g.setColor(Color.black);
            g.fillOval((int)x[i],(int)y[i],10,10);
        }

        Casteljau c = new Casteljau(x,y,4);

        g.setColor(Color.red);

        double timestep = 0.005;
        for(double t = 0; t < 500; t+=timestep){
            double xy[] = c.getXYvalues(t);
            g.fillOval((int)xy[0],(int)xy[1],3,3);
        }



    }
}