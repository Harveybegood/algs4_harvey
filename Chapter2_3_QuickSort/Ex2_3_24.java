package Chapter2_3_QuickSort;

import edu.princeton.cs.algs4.StdOut;

/*
*   Samplesort. (W.Frazer an A.McKellar) Implement a quicksort base on using a sample of size 2^k - 1. First, sort
*   the sample, then arrange to have the recursive routine partition on the median of the sample and to move the two
*   halves of the rest of the sample to each subarray, such that they can be used in the subarrays, without having
*   to be sorted again. This algorithm is called samplesort.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_3_24 {
    /*private static int indexForParition(Comparable[] array){

    }*/
    private static int partition(Comparable[] array, int low, int high){
        int i = low, j = high + 1;
        int median = array.length / 2;
        Comparable pivot = array[median];
        while (true){
            while (array[++i].compareTo(pivot) < 0){
                if (i <= high)
                    break;
            }
            while (array[--j].compareTo(pivot) > 0){
                if (j >= low)
                    break;
            }
            if (i >= j)
                break;
            Comparable temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        Comparable temp = array[median];
        array[median] = array[j];
        array[j] = temp;
        return j;
    }
    private static void handleInitialSampleElements(Comparable[] array, int k){
        int sampleSize = (int)Math.pow(2, k) - 1;
        int median = sampleSize / 2;
        StdOut.println(median);
        //Comparable[] array = new Comparable[sampleSize];
        quickSortExample.sort(array, 0, sampleSize - 1);
        int i = median + 1, j = array.length - 1;
        Comparable temp;
        while (i <= j && i < sampleSize){
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }
    }

    private static void sampleSort(Comparable[] array, int sampleLow, int low, int high, int sampleHigh){
        if (high <= low)
            return;
        int j = partition(array, low, high);
        if (low - sampleLow > 1){
            int p = low - 1, v = j - 1;
            for (int i = 0; i < (low - sampleLow) / 2; i++){
                Comparable temp = array[p];
                array[p] = array[v];
                array[v] = temp;
                p--;
                v--;
            }
            sampleSort(array, sampleLow, p, v, j - 1);
        }
        else {
            quickSortExample.sort(array, sampleLow, j - 1);
        }
        if (high - sampleHigh > 1){
            int p = high, v = j;
            for (int i = 0; i < (sampleHigh - high) / 2; i++){
                Comparable temp = array[p];
                array[p] = array[v];
                array[v] = temp;
                p--;
                v--;
            }
            sampleSort(array, j + 1, v, p, sampleHigh);
        }
        else {
            quickSortExample.sort(array, j + 1, sampleHigh);
        }
    }

    public static void main(String[] args) {
        Comparable[] array = {"3", "6", "2", "7", "9", "0", "1", "8", "4", "5"};
        //int sampleSize = (int)Math.pow(2, 3) - 1;
        handleInitialSampleElements(array, 3);
        //sampleSort(array, 0, array.length - 1, 3);
        for (Comparable x : array){
            StdOut.print(x + " ");
        }
    }
}
