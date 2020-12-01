package Chapter2_3_QuickSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Give a code fragment that sorts an array that is known to consist of items having just two distinct keys.
*
* */
public class Ex2_3_5_withTwoDistinctKeys {
    private static Comparable[] generateArrayWithTwoDistinctKeys(int n){
        Comparable[] newArrayWithTwoDistinctKeys = new Comparable[n];
        for (int i = 0; i < n; i++){
            newArrayWithTwoDistinctKeys[i] = StdRandom.uniform(1, 3);
        }
        return newArrayWithTwoDistinctKeys;
    }

    public static void main(String[] args) {
        Comparable[] newArray = generateArrayWithTwoDistinctKeys(20);
        StdOut.print("original array: ");
        for (Comparable x : newArray){
            StdOut.print(x + " ");
        }
        quickSortExample.sort(newArray);
        StdOut.print("\n  sorted array: ");
        for (Comparable x : newArray){
            StdOut.print(x + " ");
        }
    }
}
