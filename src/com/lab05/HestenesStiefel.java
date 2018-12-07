package com.lab05;

public class HestenesStiefel {
    public static void optimizeF1(Matrix x0, Matrix hesse) {
        Matrix nablaFx0 = MatrixUtils.gradF1(x0);   //  Gradientul f(x(k))
        Matrix nablaFxKPlus1 = new Matrix(nablaFx0);    //  Gradientul f(x(k+1))
        Matrix xK = new Matrix(x0);
        //  Declararea matricilor necesare
        Matrix dxKPlus1 = new Matrix(MatrixUtils.gradF1(xK).reverse()),
                dxK, upperFactor, lowerFactor, alphaK, xKplus1 = null, betaK,
                upperBetaFactor, lowerBetaFactor;
        //  Contor
        int k = 0;

        //  Verificarea conditiei de stopare a algoritmului
        while(!loopCondition(nablaFxKPlus1)) {
            dxK = MatrixUtils.gradF1(xK).reverse(); //  Calcularea directiei dxK
            upperFactor = new Matrix((nablaFxKPlus1.transpose().times(dxKPlus1)));  // Calcularea numaratorului pentru calculul alfaK
            lowerFactor = new Matrix((dxKPlus1.transpose().times(hesse).times(dxKPlus1)));  // Calcularea numitorului pentru calculul alfaK
            alphaK = new Matrix(upperFactor.divide(lowerFactor)).reverse(); //  Calcularea alfaK
            xKplus1 = new Matrix(alphaK.timesScalar(dxKPlus1)).plus(xK);    // Calcularea xk+1
            nablaFxKPlus1 = MatrixUtils.gradF1(xKplus1);    // Calcularea gradientului in xk+1 pentru verificarea solutiilor
            k++;    // Incrementarea nr. de iteratii
            if (loopCondition(nablaFxKPlus1)) break; // Conditia de stopare
            else {
                // Calculul numaratorului pentru formula BetaK
                upperBetaFactor = new Matrix(new double[][] { { Math.pow(MatrixUtils.normalize(nablaFxKPlus1), 2D) } });
                // Calculul numitorului pentru formula BetaK
                lowerBetaFactor = new Matrix(new double[][] { { Math.pow(MatrixUtils.normalize(MatrixUtils.gradF1(xK)), 2D) } });
                betaK = new Matrix(upperBetaFactor.divide(lowerBetaFactor));    // Calcularea betaK
                dxKPlus1 = nablaFxKPlus1.reverse().plus(betaK.timesScalar(dxK));    // Calcularea directiei dxK+1
            }
            xK = xKplus1;   // xK devine xK+1 pentru urmatoarea iteratie
        }

        // Afisarea rezultatelor
        System.out.println("Solutiile:");
        xKplus1.show();
        System.out.println("Nr de iteratii: " + k);
    }

    public static void optimizeF2(Matrix x0, Matrix hesse) {
        Matrix nablaFx0 = MatrixUtils.gradF2(x0);   //  Gradientul f(x(k))
        //  Daca gradientul este 0, solutia este 0
        if (loopCondition(nablaFx0)) {
            System.out.println("Solutiile: \n0,0000\n0,0000\nNr. de iteratii: 0");
            return;
        }
        Matrix nablaFxKPlus1 = new Matrix(nablaFx0);    //  Gradientul f(x(k+1))
        Matrix xK = new Matrix(x0);
        //  Declararea matricilor necesare
        Matrix dxKPlus1 = new Matrix(MatrixUtils.gradF2(xK).reverse()),
                dxK, upperFactor, lowerFactor, alphaK, xKplus1 = null, betaK,
                upperBetaFactor, lowerBetaFactor;
        //  Contor
        int k = 0;

        //  Verificarea conditiei de stopare a algoritmului
        while(!loopCondition(nablaFxKPlus1)) {
            dxK = MatrixUtils.gradF2(xK).reverse(); //  Calcularea directiei dxK
            upperFactor = new Matrix((nablaFxKPlus1.transpose().times(dxKPlus1)));  // Calcularea numaratorului pentru calculul alfaK
            lowerFactor = new Matrix((dxKPlus1.transpose().times(hesse).times(dxKPlus1)));  // Calcularea numitorului pentru calculul alfaK
            alphaK = new Matrix(upperFactor.divide(lowerFactor)).reverse(); //  Calcularea alfaK
            xKplus1 = new Matrix(alphaK.timesScalar(dxKPlus1)).plus(xK);    // Calcularea xk+1
            nablaFxKPlus1 = MatrixUtils.gradF2(xKplus1);    // Calcularea gradientului in xk+1 pentru verificarea solutiilor
            k++;    // Incrementarea nr. de iteratii
            if (loopCondition(nablaFxKPlus1)) break; // Conditia de stopare
            else {
                // Calculul numaratorului pentru formula BetaK
                upperBetaFactor = new Matrix(new double[][] { { Math.pow(MatrixUtils.normalize(nablaFxKPlus1), 2D) } });
                // Calculul numitorului pentru formula BetaK
                lowerBetaFactor = new Matrix(new double[][] { { Math.pow(MatrixUtils.normalize(MatrixUtils.gradF2(xK)), 2D) } });
                betaK = new Matrix(upperBetaFactor.divide(lowerBetaFactor));    // Calcularea betaK
                dxKPlus1 = nablaFxKPlus1.reverse().plus(betaK.timesScalar(dxK));    // Calcularea directiei dxK+1
            }
            xK = xKplus1;   // xK devine xK+1 pentru urmatoarea iteratie
        }

        // Afisarea rezultatelor
        System.out.println("Solutiile:");
        xKplus1.show();
        System.out.println("Nr de iteratii: " + k);
    }

    // Loop condition for when the algorithm stops, with error = 0.0001
    public static boolean loopCondition(Matrix x) {
        int validCounter = 0;
        for (int i = 0; i < x.getM(); i++)
            for (int j = 0; j < x.getN(); j++)
                if (Math.abs(x.getElement(i, j)) < 0.0001D)
                    validCounter++;
        return validCounter == 2;
    }
}
