package Splines;

public abstract class Interpolation {

    protected double[] x, y;

    public Interpolation(double[] x, double[] y) {
        this.x = x;
        this.y = y;
    }

    public abstract double[] evaluate(double t);

}
