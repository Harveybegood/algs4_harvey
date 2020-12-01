package Chapter2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Example {
    public static void sort(Comparable[] a){

    }
    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }
    private static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    private static void show(Comparable[] a){
        // print the array, on a single line
        for (int i = 0; i < a.length; i++){
            StdOut.print(a[i] + " ");
        }
        /*for (Comparable i: a
             ) {
            StdOut.print(i + " ");
        }*/
        StdOut.println();
    }
    private static boolean isSorted(Comparable[] a){
        // Test whether the array entries are in order
        for (int i = 1; i < a.length; i++){
            if (less(a[i], a[i-1])){
                return false;
            }
           // return true;
        }
        return true;
    }

    public static void main(String[] args) {
        // Read strings from standard input, sort them, and print.
        String[] a = StdIn.readAllStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
