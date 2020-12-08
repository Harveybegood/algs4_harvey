package Chapter2_3_QuickSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Corner cases. Test quick sort on large nonrandom arrays of the kind described in Exercise2.1.35 and 2.1.36 both with
*   and without the initial random shuffle. How does shuffling affect its performance for these arrays?
*       Test data generated as below described
*
*       Gaussian, Poisson, Geometric and Discrete
*       Half the data is 0s, half 1s.
*       Half the data is 0s, half the remainders is 1s, half the remainder is 2s, and so forth.
*       Half the data is 0s, half random int values.
*
*       Brief idea:
*           Firstly, comes to mind that how to generate such these data as required.
*           secondly, prepare quicksort both with and without the initial random shuffle.
*           finally, take each of prepared data, each of which will be put to the quicksort with and without the initial
*           random shuffle respectively, and inside which embedded with a method to count the time cost.
* */
@SuppressWarnings("unchecked")
public class Ex2_3_30 {
    // To avoid something repeated for 7 different arrays generated, take one more parameter to identity its
    // corresponding data as required
    private static void generateArray(int n, String dataType){
        int[] array = new int[n];
        if (dataType.equals("Gaussian")){
            for (int i = 0; i < n; i++){
                array[i] = (int) StdRandom.gaussian();
            }
            double time1 = withShuffleSort(array);
            double time2 = withOutShuffleSort(array);
            StdOut.printf("%8S : %8.1f\n", "Gaussian", time1 / time2);
        }
        if (dataType.equals("Poisson")){
            for (int i = 0; i < n; i++){
                array[i] = StdRandom.poisson(2.0);
            }
            double time1 = withShuffleSort(array);
            double time2 = withOutShuffleSort(array);
            StdOut.printf("%7S : %8.1f\n", "Poisson", time1 / time2);
        }
        if (dataType.equals("Geometric")){
            for (int i = 0; i < n; i++){
                array[i] = StdRandom.geometric(.6);
            }
            double time1 = withShuffleSort(array);
            double time2 = withOutShuffleSort(array);
            StdOut.printf("%9S : %8.1f\n", "Geometric", time1 / time2);
        }
        if (dataType.equals("Discrete")){
            int[] array1 = new int[10];
            for (int k = 0; k < 10; k++){
                array1[k] = StdRandom.uniform(1, 10);
            }
            for (int i = 0; i < n; i++){
                array[i] = StdRandom.discrete(array1);
            }
            double time1 = withShuffleSort(array);
            double time2 = withOutShuffleSort(array);
            StdOut.printf("%8S : %8.1f\n", "Discrete", time1 / time2);
        }
        if (dataType.equals("zeroOne")){
            for (int i = 0; i < n; i++){
                if (i < n / 2){
                    array[i] = StdRandom.uniform(0, 1);
                }else {
                    array[i] = StdRandom.uniform(1, 2);
                }
            }
            double time1 = withShuffleSort(array);
            double time2 = withOutShuffleSort(array);
            StdOut.printf("%7S : %8.1f\n", "ZeroOne", time1 / time2);
        }
        if (dataType.equals("zeroForth")){
            int k = 0, s = 0;
            while (true){
                int i;
                for (i = s; i < (n - s + 1)/2 + s; i++){
                    array[i] = StdRandom.uniform(k, k + 1);
                }
                s = i;
                k++;
                if (i == n)
                    break;
            }
            double time1 = withShuffleSort(array);
            double time2 = withOutShuffleSort(array);
            StdOut.printf("%7S : %8.1f\n", "ZeroForth", time1 / time2);
        }
        if (dataType.equals("zeroRandom")){
            for (int i = 0; i < n; i++){
                if (i < n / 2){
                    array[i] = StdRandom.uniform(0, 1);
                }else {
                    array[i] = StdRandom.uniform(1, 1000);
                }
            }
            double time1 = withShuffleSort(array);
            double time2 = withOutShuffleSort(array);
            StdOut.printf("%10S : %8.1f\n", "ZeroRandom", time1 / time2);
        }
        //return array;
    }
    // Then, Write method of quicksort both with and without the initial shuffle array.
    private static int partition(int[] array, int low, int high){
        int i = low, j = high + 1;
        int pivot = array[low];
        while (true){
            while (array[++i] < pivot){
                if (i == high)
                    break;
            }
            while (array[--j] > pivot){
                if (j == low)
                    break;
            }
            if (i >= j)
                break;
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        int temp = array[low];
        array[low] = array[j];
        array[j] = temp;
        return j;
    }
    private static double withShuffleSort(int[] array){
        Stopwatch timer = new Stopwatch();
        StdRandom.shuffle(array);
        withShuffleSort(array, 0, array.length - 1);
        return timer.elapsedTime();
    }
    private static void withShuffleSort(int[] array, int low, int high){
        if (high <= low)
            return;
        int j = partition(array, low, high);
        withShuffleSort(array, low, j - 1);
        withShuffleSort(array, j + 1, high);
    }
    // Write method of quiksort without shuffle involved
    private static double withOutShuffleSort(int[] array){
        Stopwatch timer = new Stopwatch();
        withoutShuffleSort(array, 0, array.length - 1);
        return timer.elapsedTime();
    }
    private static void withoutShuffleSort(int[] array, int low, int high){
        if (high <= low)
            return;
        int j = partition(array, low, high);
        withoutShuffleSort(array, low, j - 1);
        withoutShuffleSort(array, j + 1, high);
    }

    public static void main(String[] args) {
        StdOut.printf("%8s %10s", "DataType", "Ratio");
        StdOut.println();
    /*    StdOut.printf("%8s %7s %9s %8s %7s %9s %10s\n", "Gaussion", "Poissom", "Geometric", "Discrete", "zeroOne",
                "zeroForth", "zeroRandom");*/
        String[] nonRandomString = {"Gaussian", "Poisson", "Geometric", "Discrete", "zeroOne",
                "zeroForth", "zeroRandom"};
        for (int i = 0; i < 7; i++){
            generateArray(100000, nonRandomString[i]);
        }
    }
}
