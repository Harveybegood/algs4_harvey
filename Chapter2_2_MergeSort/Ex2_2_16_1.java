package Chapter2_2_MergeSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Ex2_2_16_1 {
    private static Comparable[] generateArray(int n){
        Comparable[] newArray = new Comparable[n];
        for (int i = 0; i < n; i++){
            newArray[i] = StdRandom.uniform(1, 100);
        }
        return newArray;
    }
    @SuppressWarnings("unchecked")
    private static void naturalMergeSort(Comparable[] array){
        if (array == null || array.length == 1) return;
        Comparable[] aux = new Comparable[array.length];
        int low = 0;
        int mid = 0;
        int high = 0;
        boolean secondSubArray = false;
        for (int i = 1; i < array.length; i++){
            if (array[i].compareTo(array[i-1]) < 0){
                if (!secondSubArray){
                    mid = i - 1;
                    secondSubArray = true;
                }
                else {
                    high = i - 1;
                    bottomUpMergeSort_1.merge(array, aux, low, mid, high);
                    mid = high;
                }
            }
        }
        if (high != array.length - 1){
            bottomUpMergeSort_1.merge(array, aux, low, mid, array.length - 1);
        }
    }

    public static void main(String[] args) {
        Comparable[] array = generateArray(20);
        naturalMergeSort(array);
        for (Comparable x : array){
            StdOut.print(x + " ");
        }
        StdOut.println();
    }
}
