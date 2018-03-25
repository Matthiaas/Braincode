package Splines;

import cparse.GaussDistr;

public class Point {

    double x, y, z;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Point deviate(GaussDistr g, int count) {
        double[] xyz = g.distribute(x, y, z, count);
        return new Point(xyz[0], xyz[1], xyz[2]);
    }
}
