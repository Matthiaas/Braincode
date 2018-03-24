package Splines;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Line {

    private ArrayList<Point> points;

    public Line() {
        points = new ArrayList<>();
    }

    public double[] getX() {
        double[] r = new double[points.size()];
        for (int i = 0; i < points.size(); i++) {
            r[i] = points.get(i).x;
        }
        return r;
    }

    public double[] getY() {
        double[] r = new double[points.size()];
        for (int i = 0; i < points.size(); i++) {
            r[i] = points.get(i).y;
        }
        return r;
    }

    public double[] getZ() {
        double[] r = new double[points.size()];
        for (int i = 0; i < points.size(); i++) {
            r[i] = points.get(i).z;
        }
        return r;
    }

    public void add(double x, double y, double z) {
        points.add(new Point(x, y, z));
    }

    public int length() {
        return points.size();
    }

    public String toString() {
        return "Line: \n" + points.stream().map(p -> p.x + "," + p.y + "," + p.z).collect(Collectors.joining(","));
    }
}
