package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;

/*
*   Local minimum of an array. Write a program that, given an array a[] of N distinct integers, finds a local minimum:
*   an index i such that a[i] < a[i-1],  a[]i < a[i+1]. Your program should use ~2lgN compares in the worst case.
*
* */
public class Ex18_LocalMinimumOfArray {

    // find local minimum of an array
    public static int findLocalMinimum(int[] array){
        int low = 0;
        int high = array.length - 1;
        while (low <= high){
            int middle = low + (high - low) / 2;
            if (middle == 0 || middle == array.length - 1){
                return -1;
            }
            // the number for comparing is twice
            if (array[middle] < array[middle - 1] && array[middle] < array[middle + 1]){
                return middle;
            }
            else if (array[middle] > array[middle - 1]){
                high = middle - 1;
            }
            else if (array[middle] > array[middle + 1]){
                low = middle + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = {-5, 6, 7, 4, 8, 12, 3, 10, 9, -2, 1};
        StdOut.println(findLocalMinimum(array));
    }
}
