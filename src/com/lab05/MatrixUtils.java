package com.lab05;

public interface MatrixUtils {
    static double normalize(Matrix x) {
        return Math.sqrt(Math.pow(x.getElement(0, 0), 2D) +
                Math.pow(x.getElement(1, 0), 2D));
    }

    static Matrix gradF1(Matrix x) {
        return new Matrix(new double[][] {
                { 2D * x.getElement(0, 0) + 2D * x.getElement(1, 0) - 2D },
                { 2D * x.getElement(0, 0) + 6D * x.getElement(1, 0) - 3D }
        });
    }

    static Matrix gradF2(Matrix x) {
        return new Matrix(new double[][] {
                { Math.abs(-(14D * Math.pow(x.getElement(0, 0), 3D) +
                        (6D * Math.pow(x.getElement(1, 0), 2D) - 14D) * x.getElement(0, 0)) *
                        Math.exp((-Math.pow(x.getElement(0, 0), 2D) -
                                Math.pow(x.getElement(1, 0), 2D))))},
                { Math.abs(-(6D * Math.pow(x.getElement(1, 0), 3D) +
                        (14D * Math.pow(x.getElement(0, 0), 2D) - 6D) * x.getElement(1, 0)) *
                        Math.exp((-Math.pow(x.getElement(1, 0), 2D) -
                                Math.pow(x.getElement(0, 0), 2D))))}
        });
    }

    static Matrix F1(Matrix x) {
        return new Matrix(new double[][] {
                { Math.pow(x.getElement(0, 0), 2D) +
                        2D * x.getElement(0, 0) * x.getElement(1, 0) +
                        3 * Math.pow(x.getElement(1, 0), 2D) -
                        2 * x.getElement(0, 0) - 3 * x.getElement(1, 0) }
        });
    }

    static Matrix F2(Matrix x) {
        return new Matrix(new double[][] {
                { Math.exp(-(Math.pow(x.getElement(0, 0), 2D) +
                        Math.pow(x.getElement(1, 0), 2D))) *
                        (7 * Math.pow(x.getElement(0, 0), 2D) +
                                3 * Math.pow(x.getElement(1, 0), 2D)) }
        });
    }
}
