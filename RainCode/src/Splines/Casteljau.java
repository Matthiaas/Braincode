package Splines;

public class Casteljau extends Interpolator {

    private int n;
    private double[][] b;

    public Casteljau(Line l) {
        super(l);
        this.n = l.getX().length;
        this.b = new double[n][n];
    }

    public double[] evaluate(double t) {
        double xVal = evaluate(t, l.getX());
        double yVal = evaluate(t, l.getY());
        return new double[]{xVal, yVal};
    }

    private double evaluate(double t, Double[] initialValues) {
        for (int i = 0; i < n; i++) {
            b[0][i] = initialValues[i];
        }
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                b[j][i] = b[j - 1][i] * (1 - t) + b[j - 1][i + 1] * t;
            }
        }
        return (b[n - 1][0]);
    }


}