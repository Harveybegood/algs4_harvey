package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;

/*
*   Modify binary search so that it always returns the element with the smallest index that matches the search element
*   (and still guarantees logarithmic running time).
*
* */
public class Ex10_BinarySearchWithSmallestIndex {
    private static int binarySearch(int[] array, int key){
        int lo = 0, hi = array.length - 1;
        while (lo <= hi){
            int mid = lo + (hi - lo)/2;
            if (key < array[mid]){
                hi = mid - 1;
            }
            else if (key > array[mid]){
                lo = mid + 1;
            }
            else {
                return mid;
            }
        }
        return lo;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int index5 = binarySearch(array, 5);
        StdOut.println(index5);
        int index1 = binarySearch(array, 1);
        StdOut.println(index1);
    }
}
