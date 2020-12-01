package Chapter1_1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/*
*   Random matches. Write a BinarySearch client that takes an int value T as command-line argument and runs T trials
*   of the following experiment for N = 10^3, 10^4, 10^5, 10^6: generate two arrays of N randomly generated positive
*   six-digit int values, and find the number of values that appear in both arrays. Print a table giving the average
*   value of this quantity over the T trails for each value of N.
*
* */
public class Ex39 {
    public static void main(String[] args) {
        //int T = Integer.parseInt(args[0]);
        int T = 5;
        StdOut.printf("%-6s %12s\n", "array Size", "average Value");
        double[] averageValue = binarySearch(T);
        for (int i = 0; i <= averageValue.length; i++){
            StdOut.printf("%8d %12.2f\n",(int)Math.pow(10, i + 3), averageValue[0]);
        }
    }

    public static int[] generateArray(int N){
        int[] trialArray = new int[N];
        for (int i = 0; i < N; i++){
            trialArray[i] = StdRandom.uniform((int)Math.pow(10, 6), (int)Math.pow(10, 7));
        }
        return trialArray;
    }

    public static int rank(int[] array, int key){
        return rank(0, array.length - 1, array, key);
    }

    public static int rank(int low, int high, int[] array, int key){
        int mid = low + (high - low) / 2;
        while (low <= high){
            if (key < array[mid])
                return rank(low, mid - 1, array, key);
            else if (key > array[mid])
                return rank(mid + 1, high, array, key);
            else
                return mid;
        }
        return -1;
    }

    public static double[] binarySearch(int T){
        double[] averageValue = new double[4];
        for (int i = 0; i <= 3; i++){
            int amountOfTrial = 0;
            for (int k = 0; k < T; k++){
                int cnt = 0;
                int[] array1 = generateArray((int)Math.pow(10, i + 3));
                int[] array2 = generateArray((int)Math.pow(10, i + 3));
                Arrays.sort(array1);
                for (int j = 0; j < array2.length; j++){
                    if (array1[j] != -1){
                        cnt++;
                    }
                }
                amountOfTrial += cnt;
            }
            averageValue[i] = amountOfTrial / T;
        }
        return averageValue;
    }
}
