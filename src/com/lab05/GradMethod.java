package com.lab05;

public class GradMethod {
    public static void optimizeF1(Matrix x0) {
        Matrix alpha = new Matrix(new double[][] { { 1D } }),
                alphaK = new Matrix(alpha),
                gamma = new Matrix(new double[][] { { 0.5D } }),
                fi = new Matrix(new double[][] { { 0.1D } }),
                z = new Matrix(x0),
                xK = new Matrix(x0);
        int k = 0;

        while(!(MatrixUtils.normalize(MatrixUtils.gradF1(xK)) < 0.0001D)) {
            k++;
            z = xK.minus(alpha.timesScalar(MatrixUtils.gradF1(xK)));
            if (MatrixUtils.F1(z).minus(MatrixUtils.F1(xK)).lessOrEqual(
                    alpha.reverse().timesScalar(fi).
                            timesConstant(Math.pow(MatrixUtils.normalize(MatrixUtils.gradF1(xK)), 2D)))) {
                alphaK = alpha;
                xK = z;
            }
            else {
                alpha = alpha.timesScalar(gamma);
            }
        }

        System.out.println("Solutiile:");
        xK.show();
        System.out.println("Iteratii: " + k);
    }

    public static void optimizeF2(Matrix x0) {
        Matrix alpha = new Matrix(new double[][] { { 1D } }),
                alphaK = new Matrix(alpha),
                gamma = new Matrix(new double[][] { { 0.5D } }),
                fi = new Matrix(new double[][] { { 0.1D } }),
                z = new Matrix(x0),
                xK = new Matrix(x0);
        int k = 0;

        while(!(MatrixUtils.normalize(MatrixUtils.gradF2(xK)) < 0.0001D)) {
            k++;
            z = xK.minus(alpha.timesScalar(MatrixUtils.gradF2(xK)));
            if (MatrixUtils.F2(z).minus(MatrixUtils.F2(xK)).lessOrEqual(
                    alpha.reverse().timesScalar(fi).
                            timesConstant(Math.pow(MatrixUtils.normalize(MatrixUtils.gradF2(xK)), 2D)))) {
                alphaK = alpha;
                xK = z;
            }
            else {
                alpha = alpha.timesScalar(gamma);
            }
        }

        System.out.println("Solutiile:");
        xK.show();
        System.out.println("Nr. de iteratii: " + k);
    }
}
