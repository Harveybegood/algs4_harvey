package Chapter2_2_MergeSort;

import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Top-down versus bottom-up. Use SortCompare to compare top-down and bottom-up mergesort for N = 10^3, 10^4 and 10^6.
*
* */
public class Ex2_2_28 {
    private static Comparable[] aux;
    private static void bottomUpSort(Comparable[] array){
        int n = array.length;
        aux = new Comparable[n];
        for (int size = 1; size < array.length - 1; size *= 2){
            for (int index = 0; index < n - size; index += 2 * size){
                bottomUpMerge(array, index, index + size - 1, Math.min(index + size + size - 1, n - 1));
            }
        }
    }
    private static void topDownSOrt(Comparable[] array){
        aux = new Comparable[array.length];
        topDownSort(array, 0, array.length - 1);
    }
    private static void topDownSort(Comparable[] array, int low, int high) {
        if (high <= low)
            return;
        int mid = low + (high - low) / 2;
        topDownSort(array, low, mid);
        topDownSort(array, mid + 1, high);
        bottomUpMerge(array, low, mid, high);
    }
    @SuppressWarnings("unchecked")
    private static void bottomUpMerge(Comparable[] array, int low, int mid, int high){
        int leftIndex = low, rightIndex = mid + 1;
        for (int k = low; k <= high; k++)
            aux[k] = array[k];
        for (int k = low; k <= high; k++){
            if (leftIndex > mid)
                array[k] = aux[rightIndex++];
            else if (rightIndex > high)
                array[k] = aux[leftIndex++];
            else if (aux[rightIndex].compareTo(aux[leftIndex]) < 0)
                array[k] = aux[rightIndex++];
            else
                array[k] = aux[leftIndex++];
        }
    }
    private static Comparable[] generateRandomArrays(int n){
        Comparable[] array = new Comparable[n];
        for (int i = 0; i < n; i++){
            array[i] = StdRandom.uniform(1, 100);
        }
        return array;
    }
    private static double sortCompare(String alg, Comparable[] array){
        Stopwatch timer = new Stopwatch();
        if (alg.equals("topDownMerge")){
            topDownSOrt(array);
        }
        if (alg.equals("bottomUpMerge")){
            bottomUpSort(array);
        }
        return timer.elapsedTime();
    }
  /*  private static void runExperiment(String alg){
        for (int n = 1000000; n <= 10000000; n *= 2){
            Comparable[] array = generateRandomArrays(n);
            double topTime = sortCompare(alg, array);
            double bottomTime = sortCompare(alg, array);
            printResults(n, topTime / bottomTime);
        }
    }*/
    private static void printResults(int n, double ratio){
        StdOut.printf("%4d %18.2f\n", n, ratio);
    }

    public static void main(String[] args) {
        StdOut.printf("%3s %10s\n", "Experiment Array", "Ratio");
        for (int n = 1000; n <= 1000000; n *= 10){
            Comparable[] array = generateRandomArrays(n);
            double topTime = sortCompare("topDownMerge", array);
            double bottomTime = sortCompare("bottomUpMerge", array);
            printResults(n, topTime / bottomTime);
        }
    }
}
