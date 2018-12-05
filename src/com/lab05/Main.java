package com.lab05;

public class Main {
    public static void main(String[] args) {
        Matrix x0 = new Matrix(new double[][] { { 0D }, { 0D } });
        Matrix hesse = new Matrix(new double[][] { { 2D, 2D }, { 2D, 6D } });
        Matrix nablaFx0 = gradF1(x0);
//        nablaFx0.show();
//        System.out.println(normalize(nablaFx0));

        Matrix nablaFxKPlus1 = new Matrix(nablaFx0);
        Matrix xK = new Matrix(x0);
        Matrix dxKPlus1 = new Matrix(gradF1(xK).reverse()), dxK, upperFactor, lowerFactor, alphaK, xKplus1 = null, betaK,
            upperBetaFactor, lowerBetaFactor;
        int k = 0;
        while(!Matrix.loopCondition(nablaFxKPlus1)) {
            dxK = gradF1(xK).reverse();
            upperFactor = new Matrix((nablaFxKPlus1.transpose().times(dxKPlus1)));
            lowerFactor = new Matrix((dxKPlus1.transpose().times(hesse).times(dxKPlus1)));
            alphaK = new Matrix(upperFactor.divide(lowerFactor)).reverse();
            xKplus1 = new Matrix(alphaK.timesScalar(dxKPlus1)).plus(xK);
            nablaFxKPlus1 = gradF1(xKplus1);
            if (Matrix.loopCondition(nablaFxKPlus1)) break;
            else {
                upperBetaFactor = new Matrix(new double[][] { { Math.pow(normalize(nablaFxKPlus1), 2D) } });
                lowerBetaFactor = new Matrix(new double[][] { { Math.pow(normalize(gradF1(xK)), 2D) } });
                betaK = new Matrix(upperBetaFactor.divide(lowerBetaFactor));
                dxKPlus1 = nablaFxKPlus1.reverse().plus(betaK.timesScalar(dxK));
            }
            k++;
            xK = xKplus1;
        }

        xKplus1.show();
    }

    public static Matrix gradF1(Matrix x) {
        return new Matrix(new double[][] {
                { 2D * x.getElement(0, 0) + 2D * x.getElement(1, 0) - 2D },
                { 2D * x.getElement(0, 0) + 6D * x.getElement(1, 0) - 3D }
        });
    }

    public static double normalize(Matrix x) {
        return Math.sqrt(Math.pow(x.getElement(0, 0), 2D) +
                Math.pow(x.getElement(1, 0), 2D));
    }
}
