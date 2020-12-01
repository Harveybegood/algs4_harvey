package Chapter2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdOut;

import static Tool.ArrayGenerator.Doubles;

/*
*   Instrument shellsort to print the number of compares divided by the array size for each increment. Write a test
*   client that tests the hypothesis that this number is a small constant, by sorting arrays of random Double values,
*   using array sizes that are increasing powers of 10, starting at 100.
*
* */
public class Ex2_1_12 {
    static long numberOfCompares;
    public static void shellSort(Comparable[] a){
        int N = a.length;
        int h = 1;
        while (h < N / 3){
            h = h * 3 +1;
        }
        while (h >= 1){
            numberOfCompares = 0;
            for (int i = h; i < N; i++){

                //totalNumberOfComparesEachIncrement = 0;

                for (int j = i; j >= h && less(a[j], a[j-1]); j -= h){

                    Comparable temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;
                }
            }
            StdOut.printf("h = %6d \t %d\n", h, numberOfCompares / N);
            h = h / 3;
        }
    }
    @SuppressWarnings("unchecked")
    public static boolean less(Comparable v, Comparable w){
        numberOfCompares++;
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        /*Double[] doubleArray = new Double[1000000000];
        for (int i = 2; i < 3; i++){
            doubleArray[i] = Math.pow(10, i);
            shellSort(doubleArray);
        }*/
        for (int i = 2; i < 6; i++){
            Double[] d = Doubles((int) Math.pow(10, i));
            shellSort(d);
        }
    }
}
