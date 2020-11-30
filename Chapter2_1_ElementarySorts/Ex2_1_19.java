package Chapter2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Shellsort worst case: Construct an array of 100 elements containing the numbers 1 through 100 for which shellsort
*   , with the increments 1 4 13 40, uses as large a number of compares as you can find.
*
* */
public class Ex2_1_19 {
    private static int compares = 0;
    public static void main(String[] args) {
        int arraySize = 100;
        Comparable[] array = new Comparable[arraySize];
        for (int i = 0; i < arraySize; i++){
            double value = StdRandom.uniform(1, 100);
            array[i] = value;
        }
        shellSort(array);
        StdOut.print(compares);
    }
    @SuppressWarnings("unchecked")
    public static void shellSort(Comparable[] a){
        int N = a.length;
        int h = 1;
        while (h < N / 2){
            h = h * 3 + 1;
        }
        while (h >= 1){
            for (int i = h; i < N; i++){
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h){
                    Comparable temp = a[j];
                    a[j] = a[j-h];
                    a[j-h] = temp;
                }
            }
            h = h / 3;
        }
    }
    @SuppressWarnings("unchecked")
    public static boolean less(Comparable v, Comparable w){
        compares++;
        return v.compareTo(w) < 0;
    }
}
