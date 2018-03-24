package Splines;

import org.apache.commons.math3.analysis.interpolation.LoessInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

public class Regression extends  Interpolator {

    private static final double smooth = 0.1;
    private static final int robust = 1;
    private static final double acc = 0.01;

    LoessInterpolator interpolator;
    PolynomialSplineFunction fx, fy;
    public Regression(Line l ) {
        super(l);

        double min = Double.MAX_VALUE, max = 0;
        for(double i : l.getX()){
            if(i < min)
                min = i;
            if(max < i){
                max = i;
            }
        }
        interpolator = new LoessInterpolator(smooth , robust , acc );
        fx = interpolator.interpolate(l.getX() ,l.getY());
        fy = interpolator.interpolate(l.getX() ,l.getZ());
    }


    @Override
    public double[] evaluate(double t) {
        double[] res = new double[2];
        res[0] = fx.value(t);
        res[1] = fy.value(t);

        return res ;

    }
}
