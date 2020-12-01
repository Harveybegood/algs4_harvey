package Chapter2_2_MergeSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Array creation. Use sortCompare to get rough idea of the effect on performance on your machine of
*   creating aux[] in merge() rather than in sort().
*
* */
public class Ex2_2_26 {
    private static Comparable[] aux;
    private static Comparable[] generateSourceArray(int n){
        Comparable[] newArray = new Comparable[n];
        for (int i = 0; i < n; i++){
            newArray[i] = StdRandom.uniform(1, 100);
        }
        return newArray;
    }
    private static void sortWithCreatingAux(Comparable[] array){
        aux = new Comparable[array.length];
        sortWithCreatingAux(array, 0, array.length - 1);
    }
    private static void sortWithCreatingAux(Comparable[] array, int low, int high){
        if (high <= low)
            return;
        int mid = low + (high - low) / 2;
        sortWithCreatingAux(array, low, mid);
        sortWithCreatingAux(array, mid + 1, high);
        merge(array, low, mid, high);
    }
    @SuppressWarnings("unchecked")
    private static void merge(Comparable[] array, int low, int mid, int high){
        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++){
            aux[k] = array[k];
        }
        for (int k = low; k <= high; k++){
            if (i > mid)
                array[k] = aux[j++];
            else if (j > high)
                array[k] = aux[i++];
            else  if (aux[j].compareTo(aux[i]) < 0)
                array[k] = aux[j++];
            else
                array[k] = aux[i++];
        }
    }
    private static void sort(Comparable[] array){
        sort(array, 0, array.length - 1);
    }
    private static void sort(Comparable[] array, int low, int high){
        if (high <= low)
            return;
        int mid = low + (high - low) / 2;
        sort(array, low, mid);
        sort(array, mid + 1, high);
        mergeWithCreatingAux(array, low, mid, high);
    }
    @SuppressWarnings("unchecked")
    private static void mergeWithCreatingAux(Comparable[] array, int low, int mid, int high){
        aux = new Comparable[array.length];
        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++)
            aux[k] = array[k];
        for (int k = low; k <= high; k++){
            if (i > mid)
                array[k] = aux[j++];
            else if (j > high)
                array[k] = aux[i++];
            else if (aux[j].compareTo(aux[i]) < 0)
                array[k] = aux[j++];
            else
                array[k] = aux[i++];
        }
    }
    private static double sortCompare(String alg, Comparable[] array){
        Stopwatch timer = new Stopwatch();
        if (alg.equals("mergeWithCreatingAux")){
            sort(array);
        }
        else if (alg.equals("sortWithCreatingAux")){
            sortWithCreatingAux(array);
        }
        return timer.elapsedTime();
    }
    private static double runExperiments(String alg, int n, int T){
        double total = 0.0;
        Comparable[] a = generateSourceArray(n);
        for (int i = 0; i < T; i++){
            total += sortCompare(alg, a);
        }
        StdOut.println(total + " - " + alg);
        return total;
    }
    public static void main(String[] args) {
        int n = 10000;
        int T = 100;
        double timeForSortWithAux = runExperiments("sortWithCreatingAux", n, T);
        double timeForMergeWithAux = runExperiments("mergeWithCreatingAux", n, T);
        StdOut.printf("With array Size %d and %d times experiments \n %.1f faster than Merge with Aux Array creation: \n",
                n, T, timeForSortWithAux * 1.0 / timeForMergeWithAux);

    }
}

