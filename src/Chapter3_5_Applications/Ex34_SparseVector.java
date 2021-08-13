package Chapter3_5_Applications;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Sparse vector. Run experiments to compare the performance of matrix-vector multiplication using SparseVector to the standard
*   implementation using arrays.
*
* */
public class Ex34_SparseVector {
    private SparseVector[] sparseVectors;
    double[] x;
    double[][] a;
    public Ex34_SparseVector(int n){
        sparseVectors = new SparseVector[n];
        x = new double[n];
        a = new double[n][n];
        for (int i = 0; i < n; i++){
            x[i] = StdRandom.uniform(-1.0, 1.0);
        }
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                a[i][j] = StdRandom.uniform(-1.0, 1.0);
            }
        }
        for (int i = 0; i < n; i++){
            sparseVectors[i] = new SparseVector();
        }
        //boolean inputZero = true;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                // the value will be 0.0, if random value is 2
                if ((StdRandom.uniform(1, 3)) == 2){
                    //sparseVectors[i].put(j, 0.0);
                }
                else {
                    sparseVectors[i].put(j, StdRandom.uniform(-1.0, 1.0));
                }
            }
        }
    }

    // sparse vector implementation of matrix-vector multiplication
    public void matrixVectorUsingSparseVector(SparseVector[] sparseVector, double[] x){
        double[] b = new double[x.length];
        for (int i = 0; i < x.length; i++){
            b[i] = sparseVector[i].dot(x);
        }
    }

    //  standard implementation of matrix-vector multiplication
    public void matrixVectorUsingArrays(double[][] a, double[] x){
        double[] b = new double[x.length];
        for (int i = 0; i < x.length; i++){
            double sum = 0.0;
            for (int j = 0; j < x.length; j++){
                sum += a[i][j] * x[j];
            }
            b[i] = sum;
        }
    }
    public void runExperiment(Ex34_SparseVector sparseVector){
        StdOut.printf("%9s %17s %11s\n", "dimension", "usingSparseVector", "usingArrays");
        StdOut.printf("%9d", sparseVectors.length);
        SparseVector[] sv = sparseVector.sparseVectors;
        Stopwatch timer1 = new Stopwatch();
        matrixVectorUsingSparseVector(sv, sparseVector.x);
        StdOut.printf("%11.2f", timer1.elapsedTime());
        double[][] array = sparseVector.a;
        Stopwatch timer2 = new Stopwatch();
        matrixVectorUsingArrays(array, sparseVector.x);
        StdOut.printf("%17.2f", timer2.elapsedTime());
    }

    public static void main(String[] args) {
        Ex34_SparseVector sparseVector = new Ex34_SparseVector(1000);
        sparseVector.runExperiment(sparseVector);
    }
}

/*
                dimension usingSparseVector usingArrays
                     1000       0.27             0.02


* */