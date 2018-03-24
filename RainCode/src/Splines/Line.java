package Splines;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Line {

    private List<Point> points;

    public Line() {
        points = new ArrayList<>();
    }

    public Line(List<Point> points) {
        this.points = points;
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

    public static void hack(List<Line> lines) {
        for (int i = 0; i < lines.size(); i++) {
            Line l = lines.get(i);
            if (l.length() >= 8) {
                List<Point> points = l.points;
                lines.remove(i);
                lines.add(new Line(l.points.subList(0, 4)));
                lines.add(new Line(l.points.subList(4, l.points.size())));
            }
        }
    }
}
