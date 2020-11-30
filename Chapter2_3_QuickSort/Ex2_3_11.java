package Chapter2_3_QuickSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Suppose that we scan over items with keys equal to the partitioning item's key instead of stopping the scans when
*   we encounter them, Show that the running time of this version of quicksort is quadratic for all arrays with just a
*   constant number of distinct keys.
*
* */
public class Ex2_3_11 {
    @SuppressWarnings("unchecked")
    private static int partition(Comparable[] array, int low, int high){
        int i = low, j = high + 1;
        Comparable v = array[low];
        while (true){
            while (array[++i].compareTo(v) <= 0){
                if (i == high)
                    break;
            }
            while (array[--j].compareTo(v) >= 0){
                if (j == low)
                    break;
            }
            if (i >= j)
                break;
            Comparable temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        Comparable temp = array[low];
        array[low] = array[j];
        array[j] = temp;
        return j;
    }
    private static void sort(Comparable[] array){
        StdRandom.shuffle(array);
        sort(array, 0, array.length - 1);
    }
    private static void sort(Comparable[] array, int low, int high){
        if (high <= low)
            return;
        int j = partition(array, low, high);
        sort(array, low, j - 1);
        sort(array, j + 1, high);
    }
    private static Comparable[] generateArray(int n){
        Comparable[] newArray = new Comparable[n];
        for (int i = 0; i < n; i++){
            newArray[i] = StdRandom.uniform(1, 1000000);
        }
        return newArray;
    }
    private static double runExperiment(int n){
            Comparable[] array = generateArray(n);
            Stopwatch timer = new Stopwatch();
            sort(array);
            return timer.elapsedTime();
    }

    public static void main(String[] args) {
        StdOut.printf("%5s %5s %3s\n","input", "time", "ratio");
        double prev = runExperiment(5000);
        for (int n = 10000; n <= 1000000; n += 10000){
            double current = runExperiment(n);
            StdOut.printf("%5d %5.2f", n, current);
            StdOut.printf("%5.2f\n", current / prev);
            prev = current;
        }
    }
}
