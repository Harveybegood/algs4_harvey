package Chapter2_2_MergeSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   3-way mergesort. Suppose instead of dividing in half at each step, you divide into thirds, sort each third, and combine
*   using a 3-way merge. what is the order of growth of the overall running time of this algorithm?
*
* */
@SuppressWarnings("unchecked")
public class Ex2_2_22 {
    private static Comparable[] aux;
    private static void sortMerge3Way(Comparable[] a, int low, int high){
        if (high <= low) return;
        int thirds = low + (high - low) / 3;
        sortMerge3Way(a, low, thirds);
        sortMerge3Way(a, thirds + 1, high);
        mergeSOrt3Way(a, low, thirds, high);
    }
    private static void mergeSOrt3Way(Comparable[] a, int low, int thirds, int high){
        aux = new Comparable[a.length];
        int i = low, j = thirds + 1;
        for (int k = low; k <= high; k++){
            aux[k] = a[k];
        }
        for (int k = low; k <= high; k++){
            if (i > thirds)
                a[k] = aux[j++];
            else if (j > high)
                a[k] = aux[i++];
            else if (aux[j].compareTo(aux[i]) < 0)
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }

    private static void sort2Way(Comparable[] a, int low, int high){
        if (high <= low)return;
        int mid = low + (high - low) / 2;
        sort2Way(a, low, mid);
        sort2Way(a, mid + 1, high);
        mergeSort2Way(a, low, mid, high);
    }
    private static void mergeSort2Way(Comparable[] a, int low, int mid, int high){
        aux = new Comparable[a.length];
        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++){
            aux[k] = a[k];
        }
        for (int k = low; k <= high; k++){
            if (i > mid)
                a[k] = aux[j++];
            else if (j > high)
                a[k] = aux[i++];
            else if (aux[j].compareTo(aux[i]) < 0)
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }
    private static Comparable[] generateArray(int n){
        Comparable[] newArray = new Comparable[n];
        for (int i = 0; i < n; i++){
            newArray[i] = StdRandom.uniform(1, 100);
        }
        return newArray;
    }

    public static void main(String[] args) {
        Comparable[] array = generateArray(200000);
        Stopwatch timer1 = new Stopwatch();
        sortMerge3Way(array, 0, array.length - 1);
        timer1.elapsedTime();
        StdOut.print("Test ==== Test\n");
        /*for (int i = 0; i < array.length; i++){
            StdOut.print(array[i] + " ");
        }*/
        StdOut.println();
        StdOut.printf("%.2f\n", timer1.elapsedTime());

        Comparable[] array1 = generateArray(200000);
        Stopwatch timer2 = new Stopwatch();
        sort2Way(array1, 0, array1.length - 1);
        timer2.elapsedTime();
       /* for (int i = 0; i < array1.length; i++){
            StdOut.print(array1[i] + " ");
        }*/
        StdOut.println();
        StdOut.printf("%.2f\n", timer2.elapsedTime());
    }
}
