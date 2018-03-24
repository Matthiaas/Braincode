package cparse;

import java.util.Random;

public class GaussDistr {

    Random random;

    public GaussDistr(int seed) {
        random = new Random(seed);
    }

    public double[] distribute(int x, int y, int z, int count) {
        double[] ret = new double[3];
        ret [0] = x + random.nextGaussian()*count;
        ret [1] = y + random.nextGaussian()*count;
        ret [2] = z + random.nextGaussian()*count;

        return ret;
    }

}
