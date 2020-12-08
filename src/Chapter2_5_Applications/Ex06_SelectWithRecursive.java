package Chapter2_5_Applications;

import edu.princeton.cs.algs4.StdOut;

/*
*   Implement a recursive version of select().
*   find the kth smallest of a collection of numbers
*
* */
@SuppressWarnings("unchecked")
public class Ex06_SelectWithRecursive {
    private static int partition(Comparable[] a, int lo, int hi){
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true){
            while (less(v, a[++i])) if (i == hi) break;
            while (less(a[--j], v)) if (j == lo) break;
            if (i >= j) break;
            exchange(a, i, j);
        }
        exchange(a, lo, j);
        return j;
    }
    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }
    private static void exchange(Comparable[] a, int i, int j){
        Comparable temp = a[i]; a[i] = a[j]; a[j] = temp;
    }
    private static Comparable selectWithRecursive(Comparable[] a, int k, int lo, int hi){
        //if (hi <= lo) return a[k];
        if (hi <= lo) {
            int j = partition(a, lo, hi);
            if (j == k) return a[k];
            selectWithRecursive(a, k, lo, j - 1);
            selectWithRecursive(a, k, j + 1, hi);
            //return a[k];
        }
        return a[k];
        /*if (k > a.length || k < 0) throw new RuntimeException("under flow");
        int j = partition(a, lo, hi);
        if (hi <= lo) return a[lo];
        if (k > j){
            return selectWithRecursive(a, j + 1, hi, k);
        }
        else if (k < j){
            return selectWithRecursive(a, lo, j - 1, k);
        }
        else {
            return a[k];
        }*/
    }

    public static void main(String[] args) {
        int k = 2;
        Comparable[] a = {"w", "i", "t", "h", "r", "e", "c"};
        int n = a.length - 1;
        Comparable v = selectWithRecursive(a, k, 0, n);
        StdOut.print(v);
    }
}
