package Splines;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Line {

    private List<Point> points;
    private double scalerX = 1;



    private double scalerY = 1;

    public void sort(){
        points.stream().map(r -> r.x).sorted().collect(Collectors.toList());
    }

    public Line() {
        points = new ArrayList<>();
    }

    public Line(List<Point> points) {
        this.points = points;
    }

    public double[] getX() {
        double[] r = new double[points.size()];
        for (int i = 0; i < points.size(); i++) {
            r[i] = points.get(i).x *scalerX;
        }
        return r;
    }

    public double[] getY() {
        double[] r = new double[points.size()];
        for (int i = 0; i < points.size(); i++) {
            r[i] = points.get(i).y  * scalerY;
        }
        return r;
    }

    public double[] getZ() {
        double[] r = new double[points.size()];
        for (int i = 0; i < points.size(); i++) {
            r[i] = points.get(i).z  * scalerX;
        }
        return r;
    }

    public void add(double x, double y, double z) {
        points.add(new Point(x, y, z));
    }

    public int length() {
        return points.size();
    }

    public void setScalerX(double scalerX) {
        this.scalerX = scalerX;
    }

    public void setScalerY(double scalerY) {
        this.scalerY = scalerY;
    }

    public String toString() {
        return "Line: \n" + points.stream().map(p -> p.x * scalerX + "," + p.y * scalerY + "," + p.z * scalerY).collect(Collectors.joining(","));
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
