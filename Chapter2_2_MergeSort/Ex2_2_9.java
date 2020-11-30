package Chapter2_2_MergeSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Use of a static array like aux[] is inadvisable in library software because multiple clients might use the class
*   concurrently. Give an implementation of Merge that does not use a static array. Do not make aux[] local to merge()
*   (see the Q&A for this section). Hint: Pass the auxiliary array as argument to the recursive sort().
*
* */
public class Ex2_2_9 {
    private static void sort(Comparable[] a, Comparable[] aux){
        sort(a, aux, 0, a.length - 1);
    }
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi){
        if (hi <= lo)return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }
    @SuppressWarnings("unchecked")
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
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

    private static Comparable[] generateArray(int n){
        Comparable[] arrayElements = new Comparable[n];
        for (int i = 0; i < n; i++){
            arrayElements[i] = StdRandom.uniform(1, 100);
        }
        return arrayElements;
    }
    public static void main(String[] args) {
        int n = 20;
        Comparable[] aux = new Comparable[n];
        Comparable[] array = generateArray(n);
        sort(array, aux);
        for (int i = 0; i < n; i++){
            StdOut.print(array[i] + " ");
        }
        StdOut.println("\n" + array.length);
    }
}
