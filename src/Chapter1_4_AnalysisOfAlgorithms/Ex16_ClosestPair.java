package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/*
*   Closest pair(in one dimension). Write a program that, given an array a[] of N double values, finds a closest pair:
*   two values whose difference is no greater than the difference of any other pair(in absolute value). The running time
*   of your program should be linearithmic in the worst case.
*
* */
public class Ex16_ClosestPair {
    public static double[] arrayWithDouble(int n){
        double[] array = new double[n];
        for (int i = 0; i < n; i++){
            array[i] = StdRandom.uniform(-10.0, 20.0);
        }
        return array;
    }
    public static void closestPair(double[] array){
        double difference = Math.abs(array[1] - array[0]);
        int m = 0;
        int n = 1;
        Arrays.sort(array);
        for (double x : array){
            StdOut.printf("%.2f ", x);
        }
        StdOut.println();
        for (int i = 1; i < array.length; i++){
            int nextNumber = i - 1;
            double newDifference = Math.abs(array[i] - array[nextNumber]);
            if (newDifference < difference){
                difference = newDifference;
                m = nextNumber;
                n = i;
            }
        }
        StdOut.printf("THe pairs: %.2f %.2f the value of difference: %.2f\n", array[m], array[n], difference);
    }
    public static void main(String[] args) {
        StdOut.println("n number of double values");

        closestPair(arrayWithDouble(15));
    }
}
