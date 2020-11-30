package Chapter2_2_MergeSort;

import edu.princeton.cs.algs4.StdOut;

/*
*   Give traces, in the style of the trace given with Algorithm 2.4, showing how the keys
*
*       E   A   S   Y   Q   U   E   S   T   I   O   N
*
*   are sorted with top-down mergesort.
* */
@SuppressWarnings("unchecked")
public class Ex2_2_2 {
    /*
    *
    *                   0   1   2   3   4   5   6   7   8   9   10  11
    *                   E   A   S   Y   Q   U   E   S   T   I   O   N
    *   a[] sort(0, 5)  E                   U
    *       sort(0, 2)  E       S
    *       sort(0, 1)  E   A
    *       sort(0, 0)
    *merge(a, 0, 0, 1)  A   E                                               Compares: 1
    *merge(a, 0, 1, 2)  A   E   S
    *       sort(3, 5)  A   E   S   Y       U
    *       sort(3, 4)  A   E   S   Y   Q
    *       sort(3, 3)
    *merge(a, 3, 3, 4)  A   E   S   Q   Y
    *merge(a, 3, 4, 5)  A   E   S   Q   U   Y
    *merge(s, 0, 2, 5)  A   E   Q   S   U   Y
    *       sort(6, 11)                         E                     N
    *       sort(6, 8)                          E         T
    *       sort(6, 7)                          E   S
    *       sore(6, 6)
    *merge(a, 6, 6, 7)
    *
    * */

    private static Comparable[] aux;
    private static void sort(Comparable[] a){
        aux = new Comparable[a.length];
        sort(a, 0, a.length-1);
    }
    private static void sort(Comparable[] a, int lo, int hi){
        if (hi <= lo) return;
        int mid = lo + (hi - lo) /2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }
    private static void merge(Comparable[] a, int lo, int mid, int hi){
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++){
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++){
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (aux[j].compareTo(aux[i]) < 0)
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }

    public static void main(String[] args) {
        Comparable[] a = {"E", "A", "S", "Y", "Q", "U", "E", "S", "T", "I", "O", "N"};
        sort(a);
        for (int i = 0; i < a.length - 1; i++){
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }
}
