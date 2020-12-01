package Chapter2_2_MergeSort;

import edu.princeton.cs.algs4.StdOut;

public class bottomUPMergeSort {
    private static Comparable[] aux;
    public static void sort(Comparable[] a){
        int N = a.length;
        aux = new Comparable[N];
        for (int arraySize = 1; arraySize < N; arraySize = 2 * arraySize){
            for (int lo = 0; lo < N - arraySize; lo += arraySize * 2){
                merge(a, lo, lo + arraySize - 1, Math.min(lo + arraySize + arraySize - 1, N - 1));
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
        Comparable[] a = {"M", "E", "R", "G", "E", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        StdOut.print(a.length + "\n");
        sort(a);
        for (int i = 0; i <= a.length - 1; i++){
            StdOut.print(i + ": " +a[i] + " \n");
        }
    }
}