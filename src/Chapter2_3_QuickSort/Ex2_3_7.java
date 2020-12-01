package Chapter2_3_QuickSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Find the expected number of subarrays of size 0, 1, and 2 when quicksort is used to sort an array of N items with
*   distinct keys. If you are mathematically inclined, do the math; if not, runs some experiments to develop hypothesis.
*
* */
public class Ex2_3_7 {
    private static int numberOfSize0;
    private static int numberOfSize1;
    private static int numberOfSize2;
    private static void sort(Comparable[] array){
        StdRandom.shuffle(array);
        sort(array, 0, array.length - 1);
    }
    private static void sort(Comparable[] array, int low, int high){
        if (high <= low)
            return;
        int j = partition.partitioning(array, low, high);
        if ((j - 1 - low) == 0){
            numberOfSize0++;
        }
        if ((j - 1 - low) == 1){
            numberOfSize1++;
        }
        if ((j - 1 - low) == 2){
            numberOfSize2++;
        }
        sort(array, low, j - 1);
        sort(array, j + 1, high);
    }

    public static void main(String[] args) {
        Comparable[] array = generateArray.generateNewArray(1000000);
        sort(array);
        StdOut.println(numberOfSize0 + " : " + numberOfSize1 + " : " + numberOfSize2);
    }
}
