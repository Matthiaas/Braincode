package Splines;

import java.util.ArrayList;

public class Line {

    private ArrayList<Double> x;
    private ArrayList<Double> y;
    private ArrayList<Double> z;

    public Line() {
        x = new ArrayList<Double>();
        y = new ArrayList<Double>();
        z = new ArrayList<Double>();
    }

    public Double[] getX() {
        return (Double[]) x.toArray();
    }

    public Double[] getY() {
        return (Double[]) y.toArray();
    }

    public Double[] getZ() {
        return (Double[]) z.toArray();
    }

    public void add(double x, double y, double z) {
        this.x.add(x);
        this.y.add(y);
        this.z.add(z);
    }
}
