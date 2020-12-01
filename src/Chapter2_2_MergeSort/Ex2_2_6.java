package Chapter2_2_MergeSort;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;

/*
*   Write a program to compute the exact value of the number of array accesses used by top-down mergesort and by
*   bottom-up mergesort. Use your program to plot the values for N from 1 to 512, and and to compare the exact values
*   with the upper bound of 6NlgN.
*
* */
public class Ex2_2_6 {
    private static Comparable[] aux;
    //private static Comparable[] array;
    private static int countArrayAccess;
    @SuppressWarnings("unchecked")
    private static boolean less(Comparable v, Comparable w){
        countArrayAccess += 2;
        return v.compareTo(w) < 0;
    }
    private static int merge(Comparable[] a, int lo, int mid, int hi){
        countArrayAccess = 0;
        int totalCount = 0;
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++){
            aux[k] = a[k];      // copy data to aux array
            countArrayAccess += 2;
        }
        for (int k = lo; k <= hi; k++){  // move data back to original array
            if (i > mid){
                countArrayAccess += 2;
                a[k] = aux[j++];
            }
            else if (j > hi){
                countArrayAccess += 2;
                a[k] = aux[i++];
            }
            else if (less(aux[j], aux[i])){ // compare
                a[k] = aux[j++];
                countArrayAccess += 2;
            }
            else {
                countArrayAccess += 2;
                a[k] = aux[i++];
            }
        }
        totalCount += countArrayAccess;
        return totalCount;
    }
    private static int topDownSort(Comparable[] a){
        aux = new Comparable[a.length];
        return topDownSort(a, 0, a.length - 1);
    }
    private static int topDownSort(Comparable[] a, int lo, int hi){
        if (hi <= lo) return 0;
        int mid = lo + (hi - lo) / 2;
        topDownSort(a, lo, mid);
        topDownSort(a, mid + 1, hi);
        return merge(a, lo, mid, hi);
    }

    /*private static void bottomUpSort(Comparable[] a){
        for (int size = 1; size < a.length; size *= 2){
            for (int index = 0; index < a.length - size; index += size * 2){
                merge(a, index, index + size - 1, Math.min(index + size + size - 1, a.length - 1));
            }
        }
    }*/
    private static Comparable[] generateArray(int N){
        Comparable[] array = new Comparable[N];
        for (int i = 0; i < N; i++){
            array[i] = StdRandom.uniform(1, 100);
        }
        return array;
    }
    public static void main(String[] args) {
        //Comparable[] array = new Comparable[512];
        StdDraw.setXscale(0, 512);
        StdDraw.setYscale(-100, 30000);
        for (int N = 1; N <= 512; N++){
            int totalCountPerN = topDownSort(generateArray(N));
            StdDraw.setPenColor(Color.red);
            StdDraw.point(N, 6 * N * Math.log(N));
            StdDraw.setPenColor(Color.blue);
            StdDraw.point(N, totalCountPerN);
        }
    }
}
