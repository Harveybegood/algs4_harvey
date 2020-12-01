package Chapter2_2_MergeSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Subarray lengths. Run mergesort for large random arrays, and make an empirical determination of the average length
*   of the other subarray when the first subarray exhausts, as a function of N (the sum of the two subarray sizes for
*   a given merge).
*
* */
public class Ex2_2_27 {
    private static int subArrayLength;
    private static Comparable[] aux;
    private static Comparable[] generateRandomArrays(int n){
        Comparable[] array = new Comparable[n];
        for (int i = 0; i < n; i++){
            array[i] = StdRandom.uniform(1, 100);
        }
        return array;
    }
    private static void sort(Comparable[] array){
        aux = new Comparable[array.length];
        sort(array, 0, array.length - 1);
    }
    private static void sort(Comparable[] array, int low, int high){
        if (high <= low)
            return;
        int mid = low + (high - low) / 2;
        sort(array, low, mid);
        sort(array, mid + 1, high);
        merge(array, low, mid, high);
    }
    @SuppressWarnings("unchecked")
    private static void merge(Comparable[] array, int low, int mid, int high){
        int leftIndex = low, rightIndex = mid + 1;
        for (int k = low; k <= high; k++){
            aux[k] = array[k];
        }
        for (int k = low; k <= high; k++){
            if (leftIndex > mid){
                array[k] = aux[rightIndex++];
                subArrayLength++;
            }
            else if (rightIndex > high){
                array[k] = aux[leftIndex++];
                subArrayLength++;
            }
            else if (aux[rightIndex].compareTo(aux[leftIndex]) < 0)
                array[k] = aux[rightIndex++];
            else
                array[k] = aux[leftIndex++];
        }
    }

    public static void main(String[] args) {
        int n = 1000000;
        Comparable[] array = generateRandomArrays(n);
        sort(array);
        StdOut.printf("Average length: %.2f\n", subArrayLength * 1.0 / n);
    }
}
