package Splines;

public class Casteljau {

    private double[] x;
    private double[] y;
    private int n;

    private double[][] b;

    public Casteljau(double[] x, double[] y, int n) {
        this.x = x;
        this.y = y;
        this.n = n;
        this.b = new double[n][n];
    }

    private void init(double[] initialValues) {
        for (int i = 0; i < n; i++) {
            b[0][i] = initialValues[i];
        }
    }

    private double evaluate(double t, double[] initialValues) {
        init(initialValues);
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                b[j][i] = b[j - 1][i] * (1 - t) + b[j - 1][i + 1] * t;
            }
        }
        return (b[n - 1][0]);
    }

    public double[] getXYvalues(double t) {
        double xVal = evaluate(t, x);
        double yVal = evaluate(t, y);
        return new double[]{xVal, yVal};
    }

}