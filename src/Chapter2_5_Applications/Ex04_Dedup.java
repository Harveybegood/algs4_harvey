package Chapter2_5_Applications;

import edu.princeton.cs.algs4.StdOut;

/*
* Implement a method String[] dedup(String[] a) that returns the objects in a[] in sorted order, with duplicates removed.
*
*   Analysis:
*   The original String[] a is a unordered array of string.
*   Firstly, use quick sort to sort it as in an ordered array.
*
*
* */
@SuppressWarnings("unchecked")
public class Ex04_Dedup {
    //private static String[] a;
    private static String[] a = {"i","t","d","i","c","a","t","i","o","n","i","e","o","d"};
    private static String[] a1 = {"i","i","i","i","i","i","i","i"};
    private static int n = a.length;
    public static void main(String[] args) {
        StdOut.println("at starting n = " + n);
        quickSort(a);
        //int n = a.length;
        for (int i = 0; i < a.length; i++){
            StdOut.print(a[i] + " ");
        }
        Dedup(a);
        for (int i = 0; i < a.length; i++){
            StdOut.print(a[i] + " ");
        }
        StdOut.println("n = " + n);
    }
    public static void quickSort(String[] a){
        int n = a.length - 1;
        quickSort(a, 0, n);
    }
    public static void quickSort(String[] a, int lo, int hi){
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        quickSort(a, lo, j - 1);
        quickSort(a, j + 1, hi);
    }
    public static int partition(String[] a, int lo, int hi){
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true){
            while (less(a[++i], v)) if (i == hi){break;}
            while (less(v, a[--j])) if (j == lo){break;}
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
    public static void Dedup(String[] a){
        //int n = a.length;
        for (int i = 1; i < n; i++){
            // how to control
           if (a[i].equals(a[i-1])){
               for (int j = i; j < n; j++){
                   a[j - 1] = a[j];
               }
               --i;
               a[--n] = "  ";
           }
        }
        StdOut.println();
        StdOut.println("After removing duplicated items: n =  " + n);
    }
}

