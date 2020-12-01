package Chapter2_3_QuickSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Sentinels: Modify the code in Algorithm 2.5 to remove both bounds checks in the inner while loops. The test against
*   the left end of the subarray is redundant since the partitioning item acts as a sentinel(v is never less than a[lo]).
*   To enable removal of the other test, put an item whose key is the largest in the whole array into a[length - 1] just
*   after the shuffle. This item will never move (except possibly to be swapped with an item having the same key) and
*   will serve as a sentinel in all subarrays involving the end of the array. Note: When sorting interior subarrays, the
*   leftmost entry in the subarray to the right serves as a sentinel for the right end of the subarrays.
*
* */
public class Ex2_3_17 {
    @SuppressWarnings("unchecked")
    private static int partition(Comparable[] array, int low, int high){
        int i = low, j = high + 1;
        Comparable v = array[low];
        while (true) {
            while (array[++i].compareTo(v) < 0){}
            while (array[--j].compareTo(v) > 0){}
            if (i >= j)
                break;
            Comparable temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        Comparable temp = array[j];
        array[j] = array[low];
        array[low] = temp;
        return j;
    }
    @SuppressWarnings("unchecked")
    private static void modifyArray(Comparable[] array){
        int max = 0;
        for (int i = 0; i < array.length - 1; i++){
            int j = i + 1;
            if (array[j].compareTo(array[max]) > 0){
                max = j;
            }
        }
        Comparable temp = array[array.length - 1];
        array[array.length - 1] = array[max];
        array[max] = temp;
    }
    private static void sort(Comparable[] array){
        //Comparable[] array1 = StdRandom.shuffle(array);
        StdRandom.shuffle(array);
      /*  StdOut.print(array[array.length - 1] + "\n");
        for (Comparable x : array){
            StdOut.print(x + " ");
        }
        StdOut.println();*/
        modifyArray(array);

    /*    StdOut.print("\n" + array[array.length - 1] + "\n");
        for (Comparable x : array){
            StdOut.print(x + " ");
        }
        StdOut.println();
        StdOut.println();*/
        sort(array, 0, array.length - 1);
    }
    private static void sort(Comparable[] array, int low, int high){
        if (high <= low)
            return;
        int j = partition(array, low, high);
        sort(array, low, j - 1);
        sort(array, j + 1, high);
    }
    private static Comparable[] generateArray(int n){
        Comparable[] array = new Comparable[n];
        for (int i = 0; i < n; i++){
            array[i] = StdRandom.uniform(1, 100);
        }
        return array;
    }

    public static void main(String[] args) {
        Comparable[] array = generateArray(10);
        sort(array);
        for (Comparable x : array){
            StdOut.print(x + " ");
        }
    }
}
