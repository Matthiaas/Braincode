package Splines;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Interpolator {

    protected Line l;

    public Interpolator(Line l) {
        this.l = l;
    }

    public abstract double[] evaluate(double t);

    public BufferedImage paint(BufferedImage bufferedImage, double resolution) {
        Graphics g = bufferedImage.getGraphics();

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

        return bufferedImage;
    }

}
