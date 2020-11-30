package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/*
*   Closest pair(in one dimension). Write a program that, given an array a[] of N double values, finds a closest pair:
*   two values whose difference is no greater than the difference of any other pair(in absolute value).
*   The running time of your program should be linear in the worst case.
*
* */
public class Ex16 {
    private static double[] closestPair(double[] array){
        double[] closestPair = new double[2];
        double currentMinimumDifference = Double.MAX_VALUE;
        Arrays.sort(array);
        for (int i = 0; i < array.length - 1; i++){
            if (Math.abs(array[i] - array[i+1]) < currentMinimumDifference){
                currentMinimumDifference = Math.abs(array[i] - array[i+1]);
                closestPair[0] = array[i];
                closestPair[1] = array[i+1];
            }
        }
        return closestPair;
    }

    public static void main(String[] args) {
       /* double[] array = new double[1];
        for (int i = 0; i < 10; i++){
            array[i] = StdRandom.uniform(-10, 10);
        }
        StdOut.println(closestPair(array));*/
       double[] array1 = {-5.2, 9.4, 20.0, -10.0, 21.1, 40.0, 50.0, -20.0};
       double[] array2 = {-4.0, -3.0, 0.0, 10.0, 20.0};
       double[] closePair1 = closestPair(array1);
       double[] closePair2 = closestPair(array2);
       StdOut.println("Closest Pair: " + closePair1[0] + " " + closePair1[1]);
       StdOut.println("Closest Pair: " + closePair2[0] + " " + closePair2[1]);
    }
}
