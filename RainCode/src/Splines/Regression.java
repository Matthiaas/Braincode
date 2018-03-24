package Splines;

import org.apache.commons.math3.analysis.interpolation.LoessInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

public class Regression extends  Interpolator {

    private static final double smooth = 0.5;
    private static final int robust = 1;
    private static final double acc = 0.01;


    LoessInterpolator interpolator;
    PolynomialSplineFunction fx, fy;
    public Regression(Line l ) {

        super(l);
        l.sort();
        System.out.println(l);
        System.out.println("HELP!!!");

        interpolator = new LoessInterpolator(smooth , robust , acc );
        fx = interpolator.interpolate(l.getX() ,l.getY());
        fy = interpolator.interpolate(l.getX() ,l.getZ());
    }


    @Override
    public double[] evaluate(double t) {
        double[] res = new double[2];
        res[0] = t*(l.getMaxX()-l.getMinX()) +l.getMinX();// fx.value(t*(l.getMaxX()-l.getMinX()) +l.getMinX() );
        res[1] =  fx.value(t*(l.getMaxX()-l.getMinX()) +l.getMinX());

        return res ;

    }
}
