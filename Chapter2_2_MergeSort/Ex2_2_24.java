package Chapter2_2_MergeSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Sort-test improvement. Run empirical studies for large randomly ordered arrays to study the effectiveness of the
*   modification described in exercise 2.2.8 for random data. In particular, develop a hypothesis about the average
*   number of times the test(whether an array is sorted)succeeds, as a function of N(the original array size for the sort
*
* */
@SuppressWarnings("unchecked")
public class Ex2_2_24 {
    private static int count;
    //private static int nonCount;
    //private static int countTotal;
    //private static int nonCountTotal;
    private static Comparable[] aux;
    private static Comparable[] generateRandomArray(int n){
        Comparable[] randomArray = new Comparable[n];
        for (int i = 0; i < n; i++){
            randomArray[i] = StdRandom.uniform(1,100);
        }
        return randomArray;
    }

    public static void main(String[] args) {
        Comparable[] randomArray1 = generateRandomArray(1000000);
        Stopwatch timer1 = new Stopwatch();
        nonSkipCallToMergeSort(randomArray1);
        double timeCost1 = timer1.elapsedTime();
        Comparable[] randomArray2 = generateRandomArray(1000000);
        Stopwatch timer2 = new Stopwatch();
        skipCallToMergeSort(randomArray2);
        double timeCost2 = timer2.elapsedTime();
        StdOut.println(count);
        StdOut.printf("%.2f\n", timeCost2 / timeCost1);
    }
    private static void nonSkipCallToMergeSort(Comparable[] array){
        aux = new Comparable[array.length];
        nonSkipCallToMergeSort(array, 0, array.length - 1);
    }
    private static void nonSkipCallToMergeSort(Comparable[] array, int low, int high){
        if (high <= low) return;
        int mid = low + (high - low) / 2;
        nonSkipCallToMergeSort(array, low, mid);
        nonSkipCallToMergeSort(array, mid + 1, high);
        mergeMethodForBothCondition(array, low, mid, high);
    }
    private static void skipCallToMergeSort(Comparable[] array){
        aux = new Comparable[array.length];
        skipCallToMergeSort(array, 0, array.length - 1);
    }
    private static void skipCallToMergeSort(Comparable[] array, int low, int high){
        if (high <= low) return;
        int mid = low + (high - low) / 2;
        skipCallToMergeSort(array, low, mid);
        skipCallToMergeSort(array, mid + 1, high);
        if (array[mid].compareTo(array[mid+1]) > 0){
            //return nonCountTotal;
            mergeMethodForBothCondition(array, low, mid, high);
        }
        count++;
        //countTotal += count;
        //StdOut.println(countTotal);
        //return countTotal;
    }
    private static void mergeMethodForBothCondition(Comparable[] array, int low, int mid, int high){
        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++){
            aux[k] = array[k];
        }
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
}
