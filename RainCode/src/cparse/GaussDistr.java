package cparse;

import java.util.Random;

public class GaussDistr {

    Random random;

    public GaussDistr(int seed) {
        random = new Random(seed);
    }

    public double[] distribute(double x, double y, long count) {
        double[] ret = new double[3];
        ret[0] = x + random.nextGaussian() * count / 10;
        ret[1] = y + random.nextGaussian() * count / 10;

        return ret;
    }

}
