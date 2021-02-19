
package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;

/*
*   Binary search with duplicates. Modify binary search so that it always returns the smallest (largest) index of
*   a key of an item matching the search key.
 *
* */
public class Ex23_BinarySearchWithDuplicates {
    public static void main(String[] args) {
        int[] duplicates1 = {-3, -2, -2, 0, 1, 2, 2, 2, 2, 5};
        int[] duplicates2 = {-2, -2, -2, 0, 1, 2, 2, 2, 2, 5};
        StdOut.println("Search key is 2 " + smallestIndex(duplicates1, 2) + " expecting 5");
        StdOut.println("Search key is -2 " + smallestIndex(duplicates2, -2) + " expecting 0");
    }
    public static int smallestIndex(int[] array, int key){
        int low = 0;
        int high = array.length - 1;
        while (low <= high){
            int mid = low + (high - low)/2;
            if (key < array[mid]){
                high = mid - 1;
            }
            else if (key > array[mid]){
                low = mid + 1;
            }
            else {
                while (mid > 0){
                    if (array[mid] == array[mid - 1]){
                        mid--;
                    }
                    else {
                        return mid;
                    }
                }
                return mid;
            }
        }
        return -1;
    }
}
