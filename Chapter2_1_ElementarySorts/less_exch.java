package Chapter2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdOut;

public class less_exch {
    @SuppressWarnings("unchecked")
    public static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }
    public static void exch(Comparable[] a, int i, int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    public static void show(Comparable[] a){
        for (int i = 0; i < a.length; i++){
            StdOut.print(a + " ");
        }
        StdOut.println();
    }
    public static boolean isSorted(Comparable[] a){
        int N = a.length;
        for (int i = 1; i < N; i++){
            if (less(a[i] , a[i-1]))
                return false;
        }
        return true;
    }
}
