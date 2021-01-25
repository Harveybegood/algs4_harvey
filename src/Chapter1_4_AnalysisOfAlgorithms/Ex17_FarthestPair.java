package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Farthest pair(in one dimension). Write a program that, given an array a[] of N double values, finds a farthest pair:
*   Two values whose difference is no smaller than the difference of any other pair(in absolution value). The running time
*   of your program should be linear in the worst case.
*
* */
public class Ex17_FarthestPair {
    // generate an array a[] of N double value
    public static double[] doubleArray(int N){
        double[] array = new double[N];
        for (int i = 0; i < N; i++){
            array[i] = StdRandom.uniform(-100, 100);
        }
        return array;
    }
    // To find a farthest pair in the given array, with which we will compare each double value and then print it out.
    public static void printFarthestValue(double[] array){
        int currentIndex = 0;
        int nextIndex = 1;
        double currentPair = Math.abs(array[nextIndex] - array[currentIndex]);
        double newPair = 0.0;
        for (int i = 2; i < array.length; i++){
            newPair = Math.abs(array[i] - array[i-1]);
            if (newPair > currentPair){
                currentPair = newPair;
                currentIndex = i-1;
                nextIndex = i;
            }
        }
        StdOut.printf("%.2f %.2f %.2f", array[currentIndex], array[nextIndex], currentPair);
    }

    public static void main(String[] args) {
        printFarthestValue(doubleArray(20));
    }
}
