package Chapter2_3_QuickSort;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;

/*
*   Subarray sizes. Write a program that plots a histogram of the subarray sizes left for insertion sort when you run
*   quicksort for an array of size N with cutoff for subarrays of size less than M. Run your program for M = 10, 20
*   and 50 and N = 10^5.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_3_26 {
    private static Comparable[] generateArray(int n){
        Comparable[] array = new Comparable[n];
        for (int i = 0; i < n; i++){
            array[i] = StdRandom.uniform(1, 200);
        }
        return array;
    }

    public static void main(String[] args) {
        Comparable[] array = generateArray((int)Math.pow(10, 5));
        for (int m = 10; m <= 50; m += m){
            StdDraw.clear();
            sort(array, m);
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    // write a snipe of codes for insertion
    private static void insertion(Comparable[] array){
        for (int i = 1; i < array.length; i++){
            for (int j = i; j > 0 && array[j].compareTo(array[j - 1]) < 0; j--){
                Comparable temp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = temp;
            }
        }
    }
    // write a snipe of codes for quick sort
    private static int quickSort(Comparable[] array, int low, int high){
        int i = low, j = high + 1;
        Comparable v = array[low];
        while (true){
            while (array[++i].compareTo(v) < 0){
                if (i >= high)
                    break;
            }
            while (array[--j].compareTo(v) > 0){
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
    private static void sort(Comparable[] array, int cutoff){
        StdRandom.shuffle(array);
        sort(array, 0, array.length - 1, cutoff);
    }
    private static void sort(Comparable[] array, int low, int high, int cutoff){
        if (high <= low)
            return;
        if ((high - low + 1) <= cutoff){
            insertion(array);
            int subArraySize = high - low + 1;
            StdDraw.setXscale(.0, 20.0);
            StdDraw.setYscale(.0, 60.0);
            StdDraw.setPenRadius();
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.filledRectangle(subArraySize, subArraySize, 0.2, subArraySize / 2.0);

            return;
        }
        int j = quickSort(array, low, high);
        sort(array, low, j - 1, cutoff);
        sort(array, j + 1, high, cutoff);
    }
}
