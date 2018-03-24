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

    public double[] getX() {
        return toPrimitive(x);
    }

    public double[] getY() {
        return toPrimitive(y);
    }

    public double[] getZ() {
        return toPrimitive(z);
    }

    public static double[] toPrimitive(ArrayList<Double> array) {
        if (array == null) {
            return null;
        } else if (array.size() == 0) {
            return new double[0];
        }
        final double[] result = new double[array.size()];
        for (int i = 0; i < array.size(); i++) {
            result[i] = array.get(i).doubleValue();
        }
        return result;
    }

    public void add(double x, double y, double z) {
        this.x.add(x);
        this.y.add(y);
        this.z.add(z);
    }
}
