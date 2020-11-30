package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/*
*   Binary search for a fraction. Devise a method that uses a logarithmic number of queries of the form Is the number
*   less_exch than x? to find a rational number p/q such that 0<p<q<N. Hint: Two fractions with denominators less_exch than N
*   cannot differ by more than 1/N^2
*
* */
public class Ex23 {
    public static int binarySearch(double[] a, double key){
        return binarySearchRur(a, key, 0, a.length-1);
    }
    public static int binarySearchRur(double[] a, double key, int low, int high){
        int mid = low + (high - low) / 2;
        int N = a.length;
        double threshold = 1.0 / (N*N);
        while (low <= high){
            double differ = Math.abs(a[mid] - key);
            if (differ <= threshold) return mid;
            else if (a[mid] > key) return binarySearchRur(a, key, low, high-1);
            else return binarySearchRur(a, key, low + 1, high);
        }
        return -1;
    }
    // generate an array of random rational
    private static double[] sourceArray(int N){
        double[] array = new double[N];
        for (int i = 0; i < N; i++){
            int p = StdRandom.uniform(1,10);
            int q = StdRandom.uniform(11,20);
            array[i] = p * 1.0 / q;
        }
        Arrays.sort(array);
        return array;
    }
    private static void printArray(double[] array){
        for (int i = 0; i < array.length; i++){
            if ((i + 1) % 10 == 0) StdOut.printf("%2.3f\n", array[i]);
            else StdOut.printf("%2.3f\n", array[i]);
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        double[] array = sourceArray(10);
        printArray(array);
        StdOut.println("Result is: " + binarySearch(array, 4.0 / 7) + " " );
    }
}
