package Tool;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;

@SuppressWarnings("unchecked")
public class Insertion {
    // This class should not be initialized
    private Insertion(){}
    // Rearranges the array in ascending order, using the natural order
    public static void sort(Comparable[] a){
        for (int i = 1; i < a.length; i++){
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--){
                exch(a, j, j-1);
            }
            assert isSorted(a);
        }
        assert isSorted(a);
    }
    // Rearranges the subarray a[lo..hi] in ascending order, using the natural order
    public static void sort(Comparable[] a, int low, int high){
        for (int i = low + 1; i < high; i++){
            for (int j = i; j > low && less(a[j], a[j-1]); j--){
                exch(a, j, j-1);
            }
            assert isSorted(a, low, high);
        }
        assert isSorted(a, low, high);
    }
    // Rearranges the array in ascending order, using a comparator
    // the comparator specifying the order
    public static void sort(Object[] a, Comparator comparator){
        for (int i = 1; i < a.length; i++){
            for (int j = i; j > 0 && less(a[j], a[j-1], comparator); j--){
                exch(a, j, j-1);
            }
            assert isSorted(a, comparator);
        }
        assert isSorted(a, comparator);
    }
    // Rearranges the subarray a[lo..hi] in ascending order, using a comparator
    // the comparator specifying the order
    public static void sort(Object[] a, int low, int high, Comparator comparator){
        for (int i = low + 1; i < high; i++){
            for (int j = i; j > low && less(a[j], a[j-1], comparator); j--){
                exch(a, j, j-1);
            }
            assert isSorted(a, low, high, comparator);
        }
        assert isSorted(a, low, high, comparator);
    }
    // return a permutation that gives the elements in a[] in ascending order, do not change the original array a[]
    public static int[] indexSort(Comparable[] a){
        int[] index = new int[a.length];
        for (int i = 0; i < a.length; i++){
            index[i] =  i;
        }
        for (int i = 1; i < a.length; i++){
            for (int j = i; j > 0 && less(a[index[j]], a[index[j-1]]); j--){
                exch(index, j, j-1);
            }
        }
        return index;
    }


    // helper sorting functions
    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }
    private static boolean less(Object v, Object w, Comparator comparator){
        return comparator.compare(v, w) < 0;
    }
    private static void exch(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    private static void exch(Object[] a, int i, int j){
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // check if array is sorted - useful for debugging
    private static boolean isSorted(Comparable[] a){
        return isSorted(a, 0, a.length - 1);
    }
    private static boolean isSorted(Comparable[] a, int low, int high){
        for (int i = low + 1; i < high; i++){
            if (less(a[i], a[i-1]))
                return false;
        }
        return true;
    }
    private static boolean isSorted(Object[] a, Comparator comparator){
        return isSorted(a, 0, a.length - 1, comparator);
    }
    private static boolean isSorted(Object[] a, int low, int high, Comparator comparator){
        for (int i = low + 1; i < high; i++){
            if (less(a[i], a[i-1], comparator))
                return false;
        }
        return true;
    }
    private static void show(Comparable[] a){
        for (int i = 0; i < a.length; i++){
            StdOut.print(a[i] + " ");
        }
    }

    public static void main(String[] args) {
        Comparable[] a = new Comparable[10];
        int[] index;
        for (int i = 0; i < 10; i++){
            a[i] = StdRandom.uniform(1, 20);
        }
        for (Comparable n : a){
            StdOut.print(n + " ");
        }
        StdOut.println();
        index = indexSort(a);
        for (Comparable n : a){
            StdOut.print(n + " ");
        }
        StdOut.println();
        show(a);
        StdOut.println();
        for (int n : index){
            StdOut.print(n + " ");
        }
    }
}
