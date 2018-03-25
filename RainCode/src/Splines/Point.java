package Splines;

import cparse.GaussDistr;

public class Point {

    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Point deviate(GaussDistr g, int count) {
        double[] xy = g.distribute(x, y, count);
        return new Point(xy[0], xy[1]);
    }
}
