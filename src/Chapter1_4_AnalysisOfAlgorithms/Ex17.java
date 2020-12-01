package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;

/*
*   Farthest pair(in one dimension). Write a program that, given an array a[] of N double values, finds a farthest pair:
*   two values whose difference is no smaller than the difference of any other pair(in absolute value).
*   The running time of your program should be linear in the worst case.
*
* */
public class Ex17 {
    public static double[] farthestPair(double[] array){
        double[] farthestPair = new double[2];
        double minVal = array[0];
        double MaxVal = array[0];
        farthestPair[0] = array[0];
        farthestPair[1] = array[0];
        for (int i = 0; i < array.length-1; i++){
            if (array[i] < minVal){
                minVal = array[i];
                farthestPair[0] = array[i];
            }
            if (array[i] > MaxVal){
                MaxVal = array[i];
                farthestPair[1] = array[i];
            }
        }
        return farthestPair;
    }

    public static void main(String[] args) {
        double[] array1 = {-5.2, 9.4, 20.0, -10.0, 21.1, 40.0, 50.0, -20.0};
        double[] array2 = {-10, -3, 0.0, 2.0, 4.0, 20.0};
        double[] farthestPair1 = farthestPair(array1);
        double[] farthestPair2 = farthestPair(array2);
        StdOut.println("Farthest Pair: " + farthestPair1[0] + " " + farthestPair1[1]);
        StdOut.println("Farthest Pair: " + farthestPair2[0] + " " + farthestPair2[1]);
    }
}
