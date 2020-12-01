package Chapter2_2_MergeSort;

import edu.princeton.cs.algs4.StdOut;

public class topDownMergeSort {
    private static Comparable[] aux;
    private static void sort(Comparable[] a){
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }
    @SuppressWarnings("unchecked")
    private static void merge(Comparable[] a, int lo, int mid, int hi){
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++){
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++){
            if (i > mid)
            {
                a[k] = aux[j++];
            }
            else if (j > hi)

            {
                a[k] = aux[i++];
            }
            else if (aux[j].compareTo(aux[i]) < 0)
            {
                a[k] = aux[j++];
            }
            else
            {
                a[k] = aux[i++];
            }
        }
    }
    private static void sort(Comparable[] a, int lo, int hi){
        if (hi <= lo)return;
        StdOut.printf("%2d %11d\n", lo, hi);
        int mid = lo + (hi - lo) / 2;
        //sort(a);
        StdOut.printf("%2d %5d %5d\n\n", lo, mid, hi);
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);

    }

    public static void main(String[] args) {
        Comparable[] a = {"M", "E", "R", "G", "E", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        StdOut.println(a.length);
        //int lo = 0;
        StdOut.printf("%2s %5s %5s\n", "low", "mid", "high");
        sort(a);
        //sort(a, lo, a.length - 1);
        for (int i = 0; i < a.length - 1; i++){
            StdOut.print(a[i] + "  ");
        }
    }
}
