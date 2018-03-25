package Splines;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Interpolator {

    protected Line l;



    public Interpolator(Line l) {

        this.l = l;
    }

    public abstract double[] evaluate(double t);

    public BufferedImage paint(BufferedImage bufferedImage, double resolution, Color c) {
        Graphics g = bufferedImage.getGraphics();
        g.setColor(c);

        int x_old;
        int y_old;
        int x_new = (int) l.getX()[0];
        int y_new = (int) l.getY()[0];

        for (double t = 0; t <= 1; t += resolution) {
            double xy[] = this.evaluate(t);
            x_old = x_new;
            y_old = y_new;
            x_new = (int) xy[0];
            y_new = (int) xy[1];
            g.drawLine(x_old, y_old, x_new, y_new);
        }

        for (int i = 0; i < l.length(); i++) {
          //  g.setColor(Color.cyan);
          // g.fillRect((int) l.getX()[i], (int) l.getY()[i], 50, 50);
        }

        return bufferedImage;
    }

}
