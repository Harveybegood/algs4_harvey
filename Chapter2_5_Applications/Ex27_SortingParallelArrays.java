package Chapter2_5_Applications;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;

/*
*   Sorting parallel arrays. When sorting parallel arrays, it is useful to have a version of a sorting routine that returns
*   a permutation, say index[], of the indices in sorted order. Add a method indirectSort() to Insertion that takes an
*   array of Comparable objects a[] as argument, but instead of rearranging the entries of a[] returns an integer array
*   index[] so that a[index[0]] through a[index[N-1]] are the items in ascending order.
*
* */
@SuppressWarnings("unchecked")
public class Ex27_SortingParallelArrays {
    //
    public static class Insertion{
        public void sort(Comparable[] a){
            for (int i = 1; i < a.length; i++){
                for (int j = i; j > 0 && less(a[j], a[j-1]); j--){
                    exchange(a, j, j-1);
                }
                assert isSorted(a);
            }
            assert isSorted(a);
        }

        public int[] indirectSort(Comparable[] a){
            int[] index = new int[a.length];
            for (int i = 0; i < a.length; i++){
                index[i] = i;
            }
            for (int i = 1; i < a.length; i++){
                for (int j = i; j > 0 && less(a[index[j]], a[index[j-1]]); j--){
                    exchange(index, j, j-1);
                }
            }
            return index;
        }
        // helper functions
        public static boolean less(Comparable v, Comparable w){
            return v.compareTo(w) < 0;
        }
        public static boolean less(Object v, Object w, Comparator comparator){
            return comparator.compare(v, w) < 0;
        }
        public void exchange(int[] a, int i, int j){
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        public void exchange(Object[] a, int i, int j){
            Object temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        // check if the array is sorted
        public boolean isSorted(Comparable[] a){
            for (int i = 1; i < a.length; i++){
                if (less(a[i], a[i-1]))
                    return false;
            }
            return true;
        }
        public boolean isSorted(Object[] a, Comparator comparator){
            for (int i = 1; i < a.length; i++){
                if (less(a[i], a[i-1], comparator))
                    return false;
            }
            return true;
        }
        public void show(Comparable[] a){
            for (Comparable m : a){
                StdOut.print(m + " ");
            }
        }

        public static void main(String[] args) {
            Comparable[] a = new Comparable[10];
            for (int i = 0; i < 10; i++){
                a[i] = StdRandom.uniform(1, 20);
            }
            Insertion insertion = new Insertion();
            int[] index = insertion.indirectSort(a);
            for (Comparable n : a){
                StdOut.print(n + " ");
            }
            StdOut.println();
            for (int i : index){
                StdOut.print(i + " ");
            }
            insertion.sort(a);
            int[] index1 = insertion.indirectSort(a);
            StdOut.println();
            for (Comparable n : a){
                StdOut.print(n + " ");
            }
            StdOut.println();
            for (int i : index1){
                StdOut.print(i + " ");
            }
        }
    }
}
