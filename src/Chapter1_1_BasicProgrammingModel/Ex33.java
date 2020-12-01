package Chapter1_1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

/*
*   Matrix library. Write a library Matrix that implements the following API:
*   Develop a test client that reads values from standard input and tests all the methods.
*
* */
public class Ex33 {
    public static double dot(double[] x, double[] y){
        // vector dot product
        // Two ways to calculate vector dot product. 1, x0 * y0 + x1 * y1. 2, |x|*|y|*cos(angle)
        double ans = 0.0;
        for (int i = 0; i < x.length; i++){
            for (int j = i; j < i + 1; j++){
                ans += x[i] * y[j];
            }
        }
        return ans;
    }

    public static double[][] mult(double[][] a, double[][] b){
        // matrix-matrix product
        // first row of first array multiply first column of second array by which will produce a value
        // that we shall put it to be as first item of a new array... and so forth
        int row = a.length;
        int col = b[0].length;
        double[][] matrixProduct = new double[row][col];
        for (int i = 0; i < row; i++){
            int k = 0;
            while (k < col){
                for (int j = 0; j < col; j++){
                    matrixProduct[i][k] += a[i][j] * b[j][k];
                }
                k++;
            }
        }
        return matrixProduct;
    }

    public static double[][] transpose(double[][] a){
        // transpose
        // move row to column,  verse vice.
        // observation, the index of both row and column add 1 after completion of each exchange of row and column.
        int n = a[0].length;
        double[][] array = new double[n][n];
        //double[][] temp = new double[n][n];
        for (int i = 0; i < n; i++){
            for (int j = i; j < n; j++){
                array[j][i] = a[i][j];
                array[i][j] = a[j][i];
            }
        }

        return array;
    }

    public static double[] mult(double[][] a, double[] x){
        // matrix-vector product
        int r = a[0].length;
        int col = a.length;
        double[] matrixProduct = new double[r];
        for (int i = 0; i < col; i++){
            for (int j = 0; j < r; j++){
                matrixProduct[i] += a[i][j]*x[j];
            }
        }
        return matrixProduct;
    }

    public static double[] mult(double[] y, double[][] a){
        // vector-matrix product
        int col = a[0].length;
        double[] array = new double[col];
        for (int i = 0; i < col; i++){
            for (int j = 0; j < col; j++){
                array[i] += y[j]*a[j][i];
            }
        }
        return array;
    }

    public static void main(String[] args) {
        double[] x = {1, 2, 3};
        double[] y = {4, 2, 1};
        double[][] a = {
                {1, 2},
                {3, 4}
        };
        double[][] b = {
                {3, 1},
                {2, 5}
        };

        int row = a.length;
        int col = b[0].length;
        StdOut.println("Vector dot product: ");
        StdOut.println(dot(x, y));

        StdOut.println("Matrix-matrix product: ");
        double[][] Matrix;
        Matrix = mult(a, b);
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                StdOut.print(Matrix[i][j] + " ");
            }
            StdOut.println();
        }

        StdOut.println("Transpose: ");
        double[][] transArray;
        transArray = transpose(a);
        int transCol = transArray.length;
        int transRow = transArray[0].length;
        for (int i = 0; i < transRow; i++){
            for (int j = 0; j < transCol; j++){
                StdOut.print(transArray[i][j] + " ");
            }
            StdOut.println();
        }
        StdOut.println();

        StdOut.println("Matrix-vector product: ");
        int l = a.length;
        double[] MatrixVector;
        MatrixVector = mult(a, x);
        for (int i = 0; i < l; i++){
            StdOut.println(MatrixVector[i]);
        }

        StdOut.println("Vector-Matrix product: ");
        int w = a[0].length;
        double[] vectorMatrix;
        vectorMatrix = mult(y, a);
        for (int i = 0; i < w; i++){
            StdOut.print(vectorMatrix[i] + " ");
        }
    }
}
