package Chapter2_3_QuickSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Jsve system sort. Add to your implementation from Ex2.3.22 code to use the Tukey ninther to compute the partitioning
*   item - choose three sets of three items, take the median of each, then use the median of the three medians as the
*   partitioning item. Also, add a cutoff to insertion sort for small subarrays.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_3_23 {
    private static final int defaultCutoff = 5;
    private static void insert(Comparable[] array){
        for (int i = 1; i < array.length; i++){
            for (int j = i; j > 0 && array[j].compareTo(array[j - 1]) < 0; j--){
                Comparable temp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = temp;
            }
        }
    }
    private static int median3(Comparable[] array, int i, int j, int k){
        return array[i].compareTo(array[j]) < 0 ?

                (array[j].compareTo(array[k]) < 0 ? j : array[i].compareTo(array[k]) < 0 ? k : i) :

                (array[k].compareTo(array[j]) < 0 ? j : array[k].compareTo(array[i]) < 0 ? k : i);
    }
 /* private static int median3(Comparable[] a, int i, int j, int k) {
        return (less(a[i], a[j]) ?
                (less(a[j], a[k]) ? j : less(a[i], a[k]) ? k : i) :
                (less(a[k], a[j]) ? j : less(a[k], a[i]) ? k : i));
    }
    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }
    */
 //@SuppressWarnings("unchecked")
    private static void javaSystemSort(Comparable[] array, int low, int high){
        int N =  high - low + 1;
        if (N < defaultCutoff){
            insert(array);
            return;
        }
        if (low >= high){
            return;
        }
        int mid = N / 2;
        int delta = N / 8;
        int mean1 = median3(array, low, low + delta, low + 2 * delta);
        int mean2 = median3(array, mid - delta, mid, mid + delta);
        int mean3 = median3(array, high - 2 * delta, high - delta, high);
        int TN = median3(array, mean1, mean2, mean3);
        int leftEqual = low, left = low;
        int rightEqual = high, right = high;
        Comparable temp;
        while (true){
            while (array[left].compareTo(TN) <= 0 && left <= high){
                while (array[left].compareTo(TN) == 0){
                    temp = array[left];
                    array[left] = array[leftEqual];
                    array[leftEqual] = temp;
                    leftEqual++;
                }
                left++;
            }
            while (array[right].compareTo(TN) >= 0 && right >= low){
                while (array[right].compareTo(TN) == 0){
                    temp = array[right];
                    array[right] = array[rightEqual];
                    array[rightEqual] = temp;
                    rightEqual--;
                }
                right--;
            }
            if (left >= right)
                break;
            temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
        left--;
        leftEqual--;
        while (leftEqual >= low){
            temp = array[left];
            array[left] = array[leftEqual];
            array[leftEqual] = temp;
            left--;
            leftEqual--;
        }
        right++;
        rightEqual++;
        while (rightEqual <= high){
            temp = array[right];
            array[right] = array[rightEqual];
            array[rightEqual] = temp;
            right++;
            rightEqual++;
        }
        javaSystemSort(array, low, left);
        javaSystemSort(array, right, high);
    }

    private static Comparable[] generateArray(int n){
         Comparable[] array = new Comparable[n];
         for (int i = 0; i < n; i++){
             array[i] = StdRandom.uniform(1, 10);
     }
     return array;
    }
    public static void main(String[] args) {
        Comparable[] array = generateArray(15);
        javaSystemSort(array, 0, array.length - 1);
        for (Comparable x : array){
            StdOut.println(x);
        }
    }
}
