package Chapter2_3_QuickSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Randomization. Run empirical studies to compare the effectiveness of the strategy of choosing a random partitioning
*   item with the strategy of initially randomizing the array(as in the text). Use a cutoff for arrays for arrays of size
*   M, and sort arrays of N distinct elements, for M = 10, 20 and 50 and N = 10^3, 10^4, 10^5 AND 10^6.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_3_29 {
    // generate arrays of N distinct elements, the way i do below is with bad efficiency, i just like it.
    // idea: each of elements resides in arrayA that will be compared for its distinct requirement before copy to arrayB
    private static Comparable[] generateArraysWithNDistinctElements(int n){
        Comparable[] arrayA = new Comparable[n];
        //Comparable[] arrayB = new Comparable[n];
        for (int i = 0; i < n; i++){
           /* // generate element for arrayA one by one
            arrayA[i] = StdRandom.uniform(0, n);
            // obviously for the first element does not need to be compared with none of its subsequent elements
            if (i == 0 ){
                System.arraycopy(arrayA, i, arrayB, i, 1);
            }else {
                int j = i - 1;
                while (j >= 0){
                    if (arrayA[i].compareTo(arrayB[j]) != 0){
                        j--;
                    }else {
                        i--;
                        break;
                    }
                    //there is a flaw which is a copy reoccurs again for previous element while duplications happens
                }
                System.arraycopy(arrayA, i, arrayB, i, 1);
            }*/

           arrayA[i] = i;
        }
        return arrayA;
    }

    public static void main(String[] args) {
        StdOut.printf("%4s %8s %8s %8s %8s %15s\n", "M", "N", "randomP", "shuffle", "ratio", "timeCreatingArray");
        int[] cutoff = {10, 20, 50};
        int[] orderOfArraySize = {3, 4, 5, 6};
        for (int m = 0; m < 3; m++){
            double timeCostPartition;
            double timeCostShuffle;
            for (int n = 0; n < 4; n++){
                Stopwatch timer = new Stopwatch();
                int arraySize = (int) Math.pow(10, orderOfArraySize[n]);
                Comparable[] array = generateArraysWithNDistinctElements(arraySize);
                double timeCreatingArray = timer.elapsedTime();
                timeCostPartition = sortWithRandomPartition(array, cutoff[m]);
                timeCostShuffle = sortWithRandomShuffle(array, cutoff[m]);
                StdOut.printf("%4d %8d %8.2f %8.2f %8.2f %15.1f\n", cutoff[m], arraySize, timeCostPartition, timeCostShuffle,
                        timeCostPartition / timeCostShuffle, timeCreatingArray);
            }
        }
    }
    // To small size array, switch to insertion sort
    private static void insertion(Comparable[] array, int low, int high){
        for (int i = low + 1; i <= high; i++){
            for (int j = i; j > low && array[j].compareTo(array[j - 1]) < 0; j--){
                Comparable temp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = temp;
            }
        }
    }
    // To have a random partitioning item.
    // Idea: use inner method StdRandom.uniform() to retrieve a random item which stationed between low and high.
    // and do one more thing, move the partitioning item to the position of starting index.
    private static void exchange(Comparable[] array, int v, int w){
        Comparable temp = array[v];
        array[v] = array[w];
        array[w] = temp;
    }
    private static int randomPartition(Comparable[] array, int low, int high){
        int i = low, j = high + 1;
        int pivot = StdRandom.uniform(low, high + 1);
        // exchange the pivot with array[low]
        /*Comparable temp = pivot;
        pivot = array[low];
        array[low] = temp;*/
        exchange(array, low, pivot);
        while (true){
            while (array[++i].compareTo(array[pivot]) < 0){
                if (i >= high)
                    break;
            }
            while (array[--j].compareTo(array[pivot]) > 0){
                if (j <= low)
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
    private static double sortWithRandomPartition(Comparable[] array, int cutoff){
        // do not shuffle the original array
        Stopwatch timer = new Stopwatch();
        sortWithRandomPartition(array, 0, array.length - 1, cutoff);
        return timer.elapsedTime();
    }
    private static void sortWithRandomPartition(Comparable[] array, int low, int high, int cutoff){
        if (high <= low)
            return;
        int subArraySize = high - low + 1;
        if (subArraySize < cutoff){
            insertion(array, low, high);
            return;
        }
        int p = randomPartition(array, low, high);
        sortWithRandomPartition(array, low, p - 1, cutoff);
        sortWithRandomPartition(array, p + 1, high, cutoff);
    }
    // To the one as in the text
    private static int partition(Comparable[] array, int low, int high){
        int i = low, j = high + 1;
        Comparable pivot = array[low];
        while (true){
            while (array[++i].compareTo(pivot) < 0){
                if (i >= high)
                    break;
            }
            while (array[--j].compareTo(pivot) > 0){
                if (j <= low)
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
    private static double sortWithRandomShuffle(Comparable[] array, int cutoff){
        Stopwatch timer = new Stopwatch();
        StdRandom.shuffle(array);
        sortWithRandomShuffle(array, 0, array.length - 1, cutoff);
        return timer.elapsedTime();
    }
    private static void sortWithRandomShuffle(Comparable[] array, int low, int high, int cutoff){
        if (high <= low)
            return;
        int subArraySize = high - low + 1;
        if (subArraySize < cutoff){
            insertion(array, low, high);
            return;
        }
        int p = partition(array, low, high);
        sortWithRandomShuffle(array, low, p - 1, cutoff);
        sortWithRandomShuffle(array, p + 1, high, cutoff);
    }
}
// running results
   M        N  randomP  shuffle    ratio timeCreatingArray
  10     1000     0.01     0.00     7.00             0.0
  10    10000     0.05     0.00    15.00             0.0
  10   100000     0.28     0.15     1.93             0.0
  10  1000000     4.79     0.37    12.84             0.2
  20     1000     0.00     0.00      NaN             0.0
  20    10000     0.00     0.00     0.67             0.0
  20   100000     0.07     0.02     3.82             0.0
  20  1000000     2.79     0.34     8.16             0.1
  50     1000     0.00     0.00      NaN             0.0
  50    10000     0.00     0.00     2.00             0.0
  50   100000     0.06     0.02     3.39             0.0
  50  1000000     3.02     0.37     8.14             0.0

