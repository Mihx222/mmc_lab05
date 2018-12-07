package com.lab05;

public class Main {
    public static void main(String[] args) {
        Matrix x0 = new Matrix(new double[][] { { 0D }, { 0D } });
        Matrix hesseF1 = new Matrix(new double[][] { { 2D, 2D }, { 2D, 6D } });
        Matrix hesseF2 = new Matrix(new double[][] { { 14D, 0D }, { 0D, 6D } });

        System.out.println("\nMetoda gradientului cu fractionarea pasului:");
        System.out.println("Ecuatia: f(x) = x1^2 + 2*x1*x2 + 3*x2^2 - 2*x1 - 3*x2");
        GradMethod.optimizeF1(x0);
        System.out.println("\nEcuatia: f(x) = exp(-(x1^2 + x2^2)) * (7*x1^2 + 3*x2^2)");
        GradMethod.optimizeF2(x0);

        System.out.println("\nMetoda Hestenes-Stiefel:");
        System.out.println("Ecuatia: f(x) = x1^2 + 2*x1*x2 + 3*x2^2 - 2*x1 - 3*x2");
        HestenesStiefel.optimizeF1(x0, hesseF1);
        System.out.println("\nEcuatia: f(x) = exp(-(x1^2 + x2^2)) * (7*x1^2 + 3*x2^2)");
        HestenesStiefel.optimizeF2(x0, hesseF2);
    }
}
