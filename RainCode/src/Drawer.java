import com.sun.org.apache.xpath.internal.operations.String;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentListener;
import java.awt.geom.Point2D;

public class Drawer extends JFrame {


    private final int STEPS = 12;


    public static void main(String args[]){
        System.out.println("test");
    }

    public Drawer() {
        super("Class Paint");
        JButton button = new JButton("Click for more");
        setSize(800, 600);
        add(button);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button.setLayout(null);
        button.setLocation(100,100);
        button.setSize(200,100);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        drawLine(g);
    }

    private void drawLine(Graphics g) {
        Polygon pol = new Polygon();
        BSpline model = new BSpline();


        double[] xp ={100,200,300,400,500,600};
        double[] yp = {100,700,400,200,100,400};
        int x, y;
        Point2D.Double[] points = model.curvePoints(xp, yp, xp.length, STEPS);
        if (points.length > 0) {
            for (Point2D.Double point : points) {
                if (point != null) {
                    x = Math.round((float) point.getX());
                    y = Math.round((float) point.getY());
                    pol.addPoint(x, y);
                }
            }
            g.drawPolyline(pol.xpoints, pol.ypoints, pol.npoints);
        }

    }


}
