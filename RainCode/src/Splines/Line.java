package Splines;

import cparse.GaussDistr;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Line {

    private List<Point> points;

    private static Random rand = new Random(24);
    private double minX = Double.MAX_VALUE, minY = Double.MAX_VALUE, maxX = 0, maxY = 0;

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
            minX = Double.min(r[i], minX);
            maxX = Double.max(r[i], maxX);
            r[i] = points.get(i).x;

        }
        return r;
    }

    public double[] getY() {
        double[] r = new double[points.size()];
        for (int i = 0; i < points.size(); i++) {
            r[i] = points.get(i).y;
            minY = Double.min(r[i], minY);
            maxY = Double.max(r[i], maxY);
            r[i] = points.get(i).y;
        }
        return r;
    }

    public void add(double x, double y) {
        points.add(new Point(x, y));
    }

    public int length() {
        return points.size();
    }

    public String toString() {
        return "Line: \n" + points.stream().map(p -> "(" + p.x + "," + p.y + ")").collect(Collectors.joining(","));
    }

    public static List<Line> betterHack(Line line, int width, int height, int numPoints, int numConnections, int count, GaussDistr g) {
        List<Line> r = new ArrayList<>();

        List<Point> randomPoints = new ArrayList<>(numPoints);
        for (int i = 0; i < numPoints; i++) {
            randomPoints.add(new Point(rand.nextDouble() * width, rand.nextDouble() * height));
        }

        int randomIndex = 0;
        for (int i = 0; i < line.points.size(); i++) {
            for (int j = i + 1; j < line.points.size(); j++) {
                List<Point> connection = new ArrayList<>(numConnections);
                connection.add(line.points.get(i).deviate(g, count * 15));
                for (int k = 0; k < numConnections - 2; k++) {
                    connection.add(randomPoints.get(randomIndex++ % numPoints).deviate(g, count * 5));
                }
                connection.add(line.points.get(j).deviate(g, count * 15));
                r.add(new Line(connection));
            }
        }
        return r;
    }

    public static List<Line> evenBetterHack(Line line, List<Point> constructs, int numConnections, GaussDistr g, int count, int offset) {
        List<Line> r = new ArrayList<>();

        for (int i = 0; i < line.points.size(); i++) {
            for (int j = i + 1; j < line.points.size(); j++) {
                List<Point> connection = new ArrayList<>(numConnections);
                connection.add(line.points.get(i).deviate(g, count * 30));
                for (int k = 0; k < numConnections - 2; k++) {
                    connection.add(constructs.get(offset++ % constructs.size()).deviate(g, count * 30));
                }
                connection.add(line.points.get(j).deviate(g, count * 30));
                r.add(new Line(connection));
            }
        }
        return r;
    }


    public static void scale(List<Line> lines, int width, int height) {

        for (Line l : lines) {
            for (Point p : l.points) {
                p.x = 0.1 * width + p.x * 0.8;
                p.y = 0.15 * height + p.y * 0.7;
            }
        }
    }


    public double getMinX() {
        return minX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMaxY() {
        return maxY;
    }

    public void sort() {
        points.sort((l, r) -> ((Double.compare(l.x, r.x))));
    }
}
