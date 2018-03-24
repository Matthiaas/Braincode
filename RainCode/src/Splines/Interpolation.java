package Splines;

import java.awt.*;

public abstract class Interpolation {

    protected double[] x, y;

    public Interpolation(double[] x, double[] y) {
        this.x = x;
        this.y = y;
    }

    public abstract double[] evaluate(double t);

    public void paint(Graphics g, double resolution) {
        int x_old = (int) this.x[0];
        int y_old = (int) this.y[0];
        int x_new = 0;
        int y_new = 0;

        for (double t = 0; t <= 1; t += resolution) {
            double xy[] = this.evaluate(t);
            x_new = (int) xy[0];
            y_new = (int) xy[1];
            g.drawLine(x_old, y_old, x_new, y_new);
        }
    }
}
