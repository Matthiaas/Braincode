package Splines;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Interpolator {

    protected Line l;
    protected int maxX ,  maxY;

    public Interpolator(Line l , int maxX , int maxY) {
        this.l = l;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public abstract double[] evaluate(double t);

    public BufferedImage paint(BufferedImage bufferedImage, double resolution, Color c) {
        Graphics g = bufferedImage.getGraphics();
        g.setColor(c);

        int x_old = (int) l.getX()[0];
        int y_old = (int) l.getY()[0];
        int x_new = 0;
        int y_new = 0;

        for (double t = 0; t <= 1; t += resolution) {
            double xy[] = this.evaluate(t);
            x_new = (int) xy[0];
            y_new = (int) xy[1];
            g.drawLine(x_old, y_old, x_new, y_new);
        }

        for (int i = 0; i < l.length(); i++) {
            g.fillRect((int) l.getX()[i], (int) l.getY()[i], 5, 5);
        }

        return bufferedImage;
    }

}
