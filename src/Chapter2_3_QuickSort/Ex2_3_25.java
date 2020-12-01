package Chapter2_3_QuickSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Cutoff to insertion sort. Implement quicksort with a cutoff to insertion sort for subarrays with less than M elements
*   and empirically determine the value of M for which quicksort runs fastest in your computing environment to sort
*   random arrays of N doubles, for N = 10^3, 10^5, and 10^6. Plot average running times for M from 0 to 30 for each
*   value of M, Note: You need to add a three-argument sort() method to Algorithm2.2 for sorting subarrays such that the
*   call Insertion.sort(a, lo, hi) sorts the subarray a[lo..hi].
*
* */
@SuppressWarnings("unchecked")
public class Ex2_3_25 {

    private static Comparable[] generateArray(int n){
        Comparable[] array = new Comparable[n];
        for (int i = 0; i < n; i++){
            array[i] = StdRandom.uniform(1, 100);
        }
        return array;
    }
    // double ratio test
    private static void doubleTest(Comparable[] array, int cutoff){
        Stopwatch timer = new Stopwatch();
        sort(array, cutoff);
        StdOut.printf("\ncutOff: " + cutoff+ " runningTime: " + timer.elapsedTime());
    }
    // cutoff to insertion sort
    private static void cutoffToInsertionSort(Comparable[] array, int low, int high){
        for (int i = low + 1; i < high; i++){
            for (int j = i; j > 0 && array[j].compareTo(array[j - 1]) < 0; j--){
                Comparable temp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = temp;
            }
        }
        //doubleTest(array, low, high);
    }
    // implement quick sort when the size of subarray is bigger than M
    private static int quickSortWithCutoff(Comparable[] array, int low, int high){

        /*if (cutOff < 30){
            //Stopwatch timer = new Stopwatch();
            cutoffToInsertionSort(array, low, high);
        }*/

        int i = low, j = high + 1;
        Comparable v = array[i];
        while (true){
            while (array[++i].compareTo(v) < 0){
                if (i >= high)
                    break;
            }
            while (array[--j].compareTo(v) > 0){
                if (j <= low)
                    break;
            }
            if (i >= j)
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
    private static void sort(Comparable[] array, int cutoff){
        StdRandom.shuffle(array);
        sort(array, 0, array.length - 1, cutoff);
    }
    private static void sort(Comparable[] array, int low, int high, int cutoff){

        if (high <= low)
            return;

        int cutOffSize = high - low + 1;
        if (cutOffSize <= cutoff){
            cutoffToInsertionSort(array, low, high);
            return;
        }

        int j = quickSortWithCutoff(array, low, high);
        sort(array, low, j - 1, cutoff);
        sort(array, j + 1, high, cutoff);
    }
    public static void main(String[] args) {
        // determine the value of M
        Comparable[] array = generateArray((int)Math.pow(10, 5));
        //sort(array);
        for (int cutoff = 30; cutoff >= 0; cutoff--){
            doubleTest(array, cutoff);
        }
    }
}
