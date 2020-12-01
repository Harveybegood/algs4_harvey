package Chapter2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Selection {
    public static void sort(Comparable[] a){
        for (int i = 0; i < a.length; i++){
            for (int j = i + 1; j < a.length; j++){
                if (less(a[j], a[i])){
                    exch(a, j, i);
                }
            }
        }
    }

    public static void sort_back(Comparable[] a){
        int N = a.length;
        for (int i = 0; i < N; i++){
            int min = i;
            for (int j = i + 1; j < N; j++){
                if (less(a[j], a[min])){
                    min = j;
                }
            }
            exch(a, i, min);
        }
    }

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
        StdOut.println(" - -  - - - - - -  - - - ");
        for (int i = 0; i < a.length; i++){
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }
    public static boolean isSorted(Comparable[] a){
        for (int i = 0; i < a.length; i++){
            if (less(a[i],a[i+1])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        sort(a);
        isSorted(a);
        show(a);
    }
}
