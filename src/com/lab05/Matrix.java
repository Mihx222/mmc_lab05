package com.lab05;

final public class Matrix {
    private final int M;             // number of rows
    private final int N;             // number of columns
    private final double[][] data;   // M-by-N array

    // create M-by-N matrix of 0's
    public Matrix(int M, int N) {
        this.M = M;
        this.N = N;
        data = new double[M][N];
    }

    // create matrix based on 2d array
    public Matrix(double[][] data) {
        M = data.length;
        N = data[0].length;
        this.data = new double[M][N];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                this.data[i][j] = data[i][j];
    }

    // copy constructor
    public Matrix(Matrix A) {
        this(A.data);
    }

    //  returns element at M and N positions
    public double getElement(int M, int N) {
        return data[M][N];
    }

    // modifies a element at i and j positions
    public void setElement(int M, int N, double value) {
        try { data[M][N] = value; }
        catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int getM() {
        return M;
    }

    public int getN() {
        return N;
    }

    // return A = -A
    public Matrix reverse() {
        Matrix temp = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                temp.setElement(i, j, 0D - data[i][j]);
        return temp;
    }

    // create and return the transpose of the invoking matrix
    public Matrix transpose() {
        Matrix A = new Matrix(N, M);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[j][i] = this.data[i][j];
        return A;
    }

    // return C = A + B
    public Matrix plus(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] + B.data[i][j];
        return C;
    }

    // return C = A - B
    public Matrix minus(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] - B.data[i][j];
        return C;
    }

    // return C = A / B
    public Matrix divide(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++) {
                try { C.data[i][j] = A.data[i][j] / B.data[i][j]; }
                catch (ArithmeticException ex) {
                    System.out.println("Division by 0!");
                }
        }
        return C;
    }

    // return C = A * B
    public Matrix times(Matrix B) {
        Matrix A = this;
        if (A.N != B.M) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(A.M, B.N);
        for (int i = 0; i < C.M; i++)
            for (int j = 0; j < C.N; j++)
                for (int k = 0; k < A.N; k++)
                    C.data[i][j] += (A.data[i][k] * B.data[k][j]);
        return C;
    }

    // return C = A * b(scalar)
    public Matrix timesScalar(Matrix B) {
        Matrix C = new Matrix(B.M, B.N);
        for (int i = 0; i < B.M; i++)
            for (int j = 0; j < B.N; j++)
                C.setElement(i, j, B.data[i][j] * data[0][0]);
        return C;
    }

    // return C = A * b(constant)
    public Matrix timesConstant(double value) {
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.setElement(i, j, data[i][j] * value);
        return C;
    }

    //  Verifies if 2 scalar matrices are less or equal
    public boolean lessOrEqual(Matrix x) {
        return data[0][0] <= x.data[0][0];
    }

    // print matrix to standard output
    public void show() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++)
                System.out.printf("%9.4f ", data[i][j]);
            System.out.println();
        }
    }
}