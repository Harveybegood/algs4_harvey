package Chapter2_3_QuickSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Ignore small subarrays. Run experiments to compare the following strategy for dealing with small subarrays with the
*   approach described in Exercise 2.3.25: Simply ignore the small subarrays in quicksort, then run a single insertion
*   sort after the quick sort completes. Note: You may be able to estimate the size of your computer's cache memory with
*   these experiments, as the performance of this method is likely to degrade when the array does not fit in the cache.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_3_27 {
    private static final int cutOffValue = 5;
    private static Comparable[] generateArray(int n){
        Comparable[] array = new Comparable[n];
        for (int i = 0; i < n; i++){
            array[i] = StdRandom.uniform(1, 20);
        }
        return array;
    }

    public static void main(String[] args) {
        Comparable[] array = generateArray(20);
        for (Comparable x : array){
            StdOut.print(x + " ");
        }
        StdOut.println();
        sort(array);
        for (Comparable x : array){
            StdOut.print(x + " ");
        }
        insertion(array);
        StdOut.println();
        for (Comparable x : array){
            StdOut.print(x + " ");
        }
    }
    private static void insertion(Comparable[] array){
        for (int i = 1; i < array.length - 1; i++){
            for (int j = i; j > 0 && array[j].compareTo(array[j - 1]) < 0; j--){
                Comparable temp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = temp;
            }
        }
    }
    private static int quickSort(Comparable[] array, int low, int high){
        int i = low, j = high + 1;
        Comparable v = array[low];
        while (true){
            while (array[++i].compareTo(v) < 0){
                if (i >= high)
                    break;
            }
            while (array[--j].compareTo(v) > 0){
                if (j <= low)
                    break;
            }
            if (i > j)
                break;
            Comparable temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        Comparable temp = array[low];
        array[low] = array[j];
        array[j] = temp;
        return j;
    }
    private static void sort(Comparable[] array){
        StdRandom.shuffle(array);
        sort(array, 0, array.length - 1);
    }
    private static void sort(Comparable[] array, int low, int high){
        if (high <= low)
            return;
        int subArraySize = high -low + 1;
        if (subArraySize < cutOffValue){
            return;
        }
        int j = quickSort(array, low, high);
        sort(array, low, j - 1);
        sort(array, j + 1, high);
    }
}
