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

        g2d.clearRect(0, 0, 1000, 500);

        CubicCurve2D curve = new CubicCurve2D.Double();

        int n = 10;
        Point2D[] points = new Point2D[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point2D.Double(i * 100, Math.random() * 500);
            System.out.println(points[i]);
        }

        g2d.setColor(Color.red);

        curve.setCurve(points, 0);
        g2d.draw(curve);

        curve.setCurve(points, 2);
        g2d.draw(curve);

    }
}
