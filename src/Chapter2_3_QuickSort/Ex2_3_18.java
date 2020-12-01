package Chapter2_3_QuickSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Median-of-3 partitioning. Add median-of-3 partitioning to quicksort, as described in the text(see page 296). Run
*   doubling tests to determine the effectiveness of the change.
*
* */
public class Ex2_3_18 {
    private static Comparable[] generateArray(int n){
        Comparable[] array = new Comparable[n];
        for (int i = 0; i < n; i++){
            array[i] = StdRandom.uniform(1, n);
        }
        return array;
    }
   /* private static Comparable[] generateSample(Comparable[] array){
        Comparable[] samples = new Comparable[3];
        for (int i = 0; i < 3; i++){
            samples[i] = array[StdRandom.uniform(0,array.length - 1)];
        }
        return samples;
    }*/
    @SuppressWarnings("unchecked")
    private static Comparable generateMedian(Comparable[] array, int low){
        //Comparable[] array = generateArray(n);
        //Comparable[] samples = generateSample(array);
        Comparable median;
        //int i = low;

            if (array[low].compareTo(array[low + 1]) > 0){
                if (array[low].compareTo(array[low + 2]) < 0){
                    median = array[low];
                }else if (array[low + 1].compareTo(array[low + 2]) > 0){
                    median = array[low + 1];
                }
                else {
                    median = array[low + 2];
                }
            }
            else{
                if (array[low].compareTo(array[low + 2]) > 0){
                    median = array[low];
                }else if (array[low + 1].compareTo(array[low + 2]) < 0){
                    median = array[low + 1];
                }
                else
                    median = array[low + 2];
            }
        //StdOut.println(median);
        return median;
    }
    @SuppressWarnings("unchecked")
    private static int medianOf3Partition(Comparable[] array, int low, int high){
        int i = low, j = high + 1;
        Comparable v = generateMedian(array, low);
        while (true){
            while (array[++i].compareTo(v) < 0){
                if (i == high)
                    break;
            }
            while (array[--j].compareTo(v) > 0){
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
    @SuppressWarnings("unchecked")
    public static int partition(Comparable[] array, int low, int high){
        int i = low, j =  high + 1;
        Comparable v = array[low];
        while (true){
            while (array[++i].compareTo(v) < 0){
                if (i == high)
                    break;
            }
            while (array[--j].compareTo(v) > 0){
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
    public static void sort(Comparable[] array){
        StdRandom.shuffle(array);
        sort(array, 0, array.length - 1);
    }
    public static void sort(Comparable[] array, int low, int high){
        if (high <= low)
            return;
        int j = partition(array, low, high);
        sort(array, low, j - 1);
        sort(array, j + 1, high);
    }
    public static void sortWithMedianOf3(Comparable[] array){
        StdRandom.shuffle(array);
        sortWithMedianOf3(array, 0, array.length - 1);
    }
    private static void sortWithMedianOf3(Comparable[] array, int low, int high){
        if (high <= low)
            return;
        if ((high - low) < 3){
            int j = partition(array, low, high);
            sortWithMedianOf3(array, low, j - 1);
            sortWithMedianOf3(array, j + 1, high);
        }else {
            int median = medianOf3Partition(array, low, high);
            sortWithMedianOf3(array, low, median - 1);
            sortWithMedianOf3(array, median + 1, high);
        }
    }
    private static double timeTrial(String alg, Comparable[] array){
        Stopwatch timer = new Stopwatch();
        if (alg.equals("sort"))
            sort(array);
        if (alg.equals("sortWithMediaOf3"))
            sortWithMedianOf3(array);
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        StdOut.printf("%7s %8s %18s\n", "N", "sort", "sortWithMediaOf3");
        for (int N = 1000; N <= 5120000; N *= 2){
            Comparable[] array= generateArray(N);
            double time1 = timeTrial("sort", array);
            double time2 = timeTrial("sortWithMediaOf3", array);
            StdOut.printf("%7d %8s %14s\n", N, time1, time2);
        }
    }
}




