package Chapter2_2_MergeSort;

import edu.princeton.cs.algs4.StdOut;

/*
*   Answer Exercise 2.2.2 for bottom-up mergeSort.  E   A   S   Y   Q   U   E   S   T   I   O   N
*
* */
public class Ex2_2_3 {
    /*                          0   1   2   3   4   5   6   7   8   9   10  11
    *                           e   a   s   y   q   u   e   s   t   i   o   n
    *   merge(a, 0, 0, 1)       a   e
    *   merge(a, 2, 2, 3)               s   y
    *   merge(a, 4, 4, 5)                       q   u
    *   merge(a, 6, 6, 7)                               e   s
    *   merge(a, 8, 8, 9)                                       i   t
    *   merge(a, 10, 10, 11)                                             n   o
    *                           a   e   s   y   q   u   e   s   i   t    n   o
    *   merge(a, 0, 1, 3)       a   e   s   y
    *   merge(a, 4, 5, 7)                       e   q   s   u
    *   merge(a, 8, 9, 11)                                      i   n   o   t
    *                           a   e   s   y   e   q   s   u   i   n   o   t
    *   merge(a, 0, 3, 7)       a   e   e   q   s   s   u   y
    *   merge(a, 8, 11, 11)                                     i   n   o   t
    *
    *   merge(a, 0, 7, 11)      a   e   e   i   n   o   q   s   s   t   u   y
    *
    *
    *
    *
    * */
    private static Comparable[] aux;
    private static void sort(Comparable[] a){
        int N = a.length;
        aux = new Comparable[N];
        for (int size = 1; size < N; size *= 2){
            for (int index = 0; index < N - size; index += 2 * size){
                merge(a, index, index + size - 1, Math.min(index +  size + size - 1, N - 1));
            }
        }
    }
    @SuppressWarnings("unchecked")
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
        Comparable[] a = {"E", "A", "S", "Y", "Q", "U", "E", "S", "T", "I", "O", "N"} ;
        sort(a);
        for (int i = 0; i < a.length - 1; i++){
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }
}
