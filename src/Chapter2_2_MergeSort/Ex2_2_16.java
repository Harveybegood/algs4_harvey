package Chapter2_2_MergeSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Natural mergesort. Write a version of bottom-up mergesort that takes advantage of order in the array by proceeding
*   as follows each time it needs to find two arrays to merge: find a sorted subarray (by incrementing a pointer until
*   finding an entry that is smaller than its predecessor in the array), then find the next, then merge them. Analyze
*   the running time of this algorithm in terms of the array size and the number of maximal increasing sequences
*   in the array.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_2_16 {
    private static Comparable[] newArray;
    /*private static void merge(Comparable[] subArray1, Comparable[] subArray2){

    }*/
    private static void findSubArray(Comparable[] array){
        int n = array.length;
        Comparable[] newSubArray = new Comparable[n];
        for (int i = 0; i < n; i++){
            newSubArray[i] = array[i];
        }
        int low = 0;
        int mid = 0;
        int high = 0;
        for (int i = 0; i < array.length; i++){
            //int j = i;
            if (array[i].compareTo(array[i+1]) < 0){
                //newSubArray[i] = array[i];
                i++;
                high = i;
                mid = low + (high - low) / 2;

            }else{
                bottomUpMergeSort_1.merge(array, newSubArray, low, mid, high);
            }
        }
    }
    private static Comparable[] generateArray(int n){
        newArray = new Comparable[n];
        for (int i = 0; i < n; i++){
            newArray[i] = StdRandom.uniform(1, 100);
        }
        return newArray;
    }
    public static void main(String[] args) {
        Comparable[] array = generateArray(20);
        findSubArray(array);
        for (Comparable x : array){
            StdOut.print(x + " ");
        }
        StdOut.println();
    }
/*    private static int[] aux;
    private static int[] generateArray(int n){
        int[] newArray = new int[n];
        for (int i = 0; i < n; i++){
            newArray[i] = StdRandom.uniform(1, 100);
        }
        return newArray;
    }
    public static void merge(int[] a) {
        int N = a.length;
        aux = new int[N];
        for (int sz = 1; sz < N; sz += sz)
            for (int lo = 0; lo < N - sz; lo += 2 * sz)
                merge(a, lo, lo + sz - 1, Math.min(lo + 2 * sz - 1, N - 1));
    }
    private static void merge(int[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        for (int k = lo; k <= hi; k++)
            if      (i > mid)           a[k] = aux[j++];
            else if (j > hi)            a[k] = aux[i++];
            else if (aux[j] < aux[i])   a[k] = aux[j++];
            else                        a[k] = aux[i++];
    }
    private static void mergeNatural(int[] a) {
        if (a.length == 1) return;
        int lo = 0, mid = 0, hi = 0, N = a.length;
        aux = new int[N];
        while (true) {
            mid = 0;
            while (mid < N - 1 && a[mid] < a[mid + 1]) mid++;
            hi = mid + 1;
            while (hi < N - 1 && a[hi] < a[hi + 1]) hi++;
            if (hi == N) return;
            merge(a);
        }
    }

    public static void main(String[] args) {
        int[] array = generateArray(20);
        mergeNatural(array);
        for (int i = 0; i < 20; i++){
            StdOut.print(array[i] + " ");
        }
        StdOut.println();
    }*/
}
