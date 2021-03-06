package Chapter2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdOut;

public class ShellSort {
    public static void sort(Comparable[] a){
        int N = a.length;
        int h = 1;
        while (h < N/3) h = 3*h + 1;
        while (h >= 1){
            for (int i = h; i < N; i++){
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h){
                    exch(a, j, j-h);
                }
            }
            h = h/3;
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
    public static boolean isSorted(Comparable[] a){
        for (int i = 1; i < a.length; i++){
            if (less(a[i], a[i-1]))return false;
        }
        return true;
    }
    public static void show(Comparable[] a){
        for (int i = 0; i < a.length; i++){
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
        /*int i = 0;
        StdOut.print(String.format("%s ", a[++i]));*/
    }
 /*   public String toString(){
        StdOut.println("First h-Sort:");
        int i = 0;
        StdOut.printf();
    }*/
    public static void main(String[] args) {
        String[] a = {"S", "H", "E", "L", "L", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        sort(a);
        isSorted(a);
        show(a);
    }
}
