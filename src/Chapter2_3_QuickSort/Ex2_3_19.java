package Chapter2_3_QuickSort;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Median-of-5 partitioning. Implement a quicksort based on partitioning on the median of a random sample of five items
*   from the subarray. Put the items of the sample at the appropriate ends of the array so that only the median
*   participates in partitioning. Run doubling tests to determine the effectiveness of the change, in comparision both
*   to the standard algorithm and to median-of-3 partitioning(see the previous exercise). Extra credit: Devise a median
*   -of-5 algorithm that uses fewer than seven compares on any input.
*
* */
public class Ex2_3_19 {
    private static Comparable[] generateArray(int n){
        Comparable[] array = new Comparable[n];
        for (int i = 0; i < n; i++){
            array[i] = StdRandom.uniform(1, n);
        }
        return array;
    }
    private static Comparable[] generateSamples(Comparable[] array, int low, int high){
        Comparable[] samples = new Comparable[5];
        for (int i = 0; i < 5; i++){
            samples[i] = array[StdRandom.uniform(low, high)];
        }
        return samples;
    }
    @SuppressWarnings("unchecked")
    private static Comparable generateMedian(Comparable[] array, int low, int high){
        Comparable[] samples = generateSamples(array, low, high);
        int median = 0;
        //int flag;
        for (int i = low + 1; i < low + samples.length; i++){
         /*   flag = 0;
            for (int j = low + 1; j < low + samples.length; j++){
                //StdOut.printf("%2d %2d %2d\n", j, i, low + samples.length);
                if (i == j);
               else if (samples[j].compareTo(samples[i]) < 0){
                   flag++;
               }else {
                   flag--;
               }
           }
           if (flag == 0){
              median = i;
              break;
           }*/
           for (int j = i; j > low && array[j].compareTo(array[j - 1]) < 0; j--){
               Comparable temp = array[j];
               array[j] = array[j - 1];
               array[j - 1] = temp;
           }
           median = low + (high - low) / 2;
        }
        return samples[median];
    }
    @SuppressWarnings("unchecked")
    private static int medianOf5Partition(Comparable[] array, int low, int high){
        int i = low, j = high + 1;
        Comparable v = generateMedian(array, low, high);
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

    private static void sortWithMedianOf5(Comparable[] array){
        StdRandom.shuffle(array);
        sortWithMedianOf5(array, 0, array.length - 1);
    }
    private static void sortWithMedianOf5(Comparable[] array, int low, int high){
        if (high <= low)
            return;
        if ((high - low + 1) < 5){
            int j = Ex2_3_18.partition(array, low, high);
            Ex2_3_18.sort(array, low, j - 1);
            Ex2_3_18.sort(array, j + 1, high);
        }else{
            int median = medianOf5Partition(array, low, high);
            sortWithMedianOf5(array, low, median - 1);
            sortWithMedianOf5(array, median + 1, high);
        }
    }
    private static double timeTrial(String alg, Comparable[] array){
        Stopwatch timer = new Stopwatch();
        if (alg.equals("sort"))
            Ex2_3_18.sort(array);
        if (alg.equals("sortWithMedianOf3"))
            Ex2_3_18.sortWithMedianOf3(array);
        if (alg.equals("sortWithMedianOf5"))
            sortWithMedianOf5(array);
        return timer.elapsedTime();
    }
    public static void main(String[] args) {
        StdOut.println();
        StdOut.printf("%8s %8s %18s %18s\n", "N", "Sort", "SortWithMedianOf3", "SortWithMedianOf5");
        int c = 0;
        double total1 = 0.0, total2 = 0.0, total3 = 0.0;
        for (int n = 1000; n < 5000000; n += n){
            c++;
            Comparable[] array = generateArray(n);
            double t1 = timeTrial("sort", array);
            double t2 = timeTrial("sortWithMedianOf3", array);
            double t3 = timeTrial("sortWithMedianOf5", array);
            total1 += t1;
            total2 += t2;
            total3 += t3;
            StdOut.printf("%8d %8.2f %18.2f %18.2f\n", n, t1, t2, t3);
        }
        StdOut.printf("%16.1f %18.1f %18.1f\n", total1 / c, total2 / c, total3 / c);
    }
}
