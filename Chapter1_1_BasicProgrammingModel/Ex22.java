package Chapter1_1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/*
*  Write a version of BinarySearch that uses the recursive rank() given on page 25 and traces the method calls.
*  Each time the recursive method is called, print the argument values lo and hi, indented by the depth of the
*  recursive. Hint: Add an argument to the recursive method that keeps track of the depth.
*
* */
public class Ex22 {
    static int depth = 0;
    public static void main(String[] args) {
        int n = 20;
        int[] array = generateArray(n);
        Arrays.sort(array);
        StdOut.println(recursiveRank(5, array));
    }

    private static int[] generateArray(int n){
        int[] array = new int[n];
        for (int i = 0; i < n; i++){
            array[i] = StdRandom.uniform(1, 50);
        }
        return array;
    }

    private static int recursiveRank(int key, int[] array){
        return recursiveRank(0, array.length - 1, key, array);
    }
    private static int recursiveRank(int lo, int hi, int key, int[] array){
        if (lo > hi) {
            return -1;
        }
        for (int i = 0; i < depth; i++){
            StdOut.print("-");
        }
        StdOut.printf("%1d %1d\n", lo, hi);
        int mid = lo + (hi - lo) / 2;
        if (key > array[mid]){
            depth++;
            return recursiveRank(mid + 1, hi, key, array);
        }
        else if (key < array[mid]){
            depth++;
            return recursiveRank(lo, mid - 1, key, array);
        }
        else {
            return mid;
        }
    }
}
