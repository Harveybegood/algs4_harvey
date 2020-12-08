package Chapter2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Insertion sort with sentinel. Develop an implementation of insertion sort that eliminates the j > 0 Test in the
*   inner loop by first putting the smallest item into position. Use SortCompare to evaluate the effectiveness of doing
*   so. Note: It is often possible to avoid an index-out-of-bounds Test in this way - the element that enables the Test
*   to be eliminated is known as a sentinel.
*
* */
public class Ex2_1_24 {
    public enum  InsertionType{
        Default, Sentinel
    }
    @SuppressWarnings("unchecked")
    public static void insertion(Comparable[] a){
        int N = a.length;
        for (int i = 1; i < N; i++){
            for (int j = i; j > 0 && a[j].compareTo(a[j-1]) < 0; j--){
                Comparable temp = a[j];
                a[j] = a[j-1];
                a[j-1] = temp;
            }
        }
    }
    @SuppressWarnings("unchecked")
    public static void insertionWithSentinel(Comparable[] a){
        int N = a.length;
        Comparable minimumElement = Double.MAX_VALUE;
        int minimumIndex = 0;
        for (int i = 0; i < N; i++){
            if (a[i].compareTo(minimumElement) < 0){
                minimumElement = a[i];
                minimumIndex = i;
            }
        }
        Comparable temp = a[0];
        a[0] = a[minimumIndex];
        a[minimumIndex] = temp;
        for (int i = 1; i < N; i++){
            for (int j = i; a[j].compareTo(a[j-1]) < 0; j--){
                Comparable temp2 = a[j];
                a[j] = a[j-1];
                a[j-1] = temp2;
            }
        }
    }

    public static double time(Comparable[] a, InsertionType insertionType){
        Stopwatch timer = new Stopwatch();
        if (insertionType == InsertionType.Default){
            insertion(a);
        }else if (insertionType == InsertionType.Sentinel){
            insertionWithSentinel(a);
        }
        return timer.elapsedTime();
    }
    public static void sortCompare(InsertionType insertionType){
        double total1 = 0.0;
        double total2 = 0.0;
        int arrayLength = 10000;
        Comparable[] array = new Comparable[arrayLength];
        int numberOfCompare = 10;
            for (int experiment = 0; experiment < numberOfCompare; experiment++){
                if (insertionType == InsertionType.Default){
                    for (int i = 0; i < arrayLength; i++){
                        array[i] = StdRandom.uniform();
                    }
                    //StdOut.printf();
                    total1 += time(array, InsertionType.Default);
                }else if (insertionType == InsertionType.Sentinel){
                    for (int i = 0; i < arrayLength; i ++){
                        array[i] = StdRandom.uniform();
                    }
                    total2 += time(array, InsertionType.Sentinel);
                }
            }
            StdOut.printf("For %d random doubles, Insertion sort default is", arrayLength);
            StdOut.printf("%.1f times faster than Insertion sort with Sentinel", total1/total2);
    }

    public static void main(String[] args) {
        sortCompare(InsertionType.Default);
        sortCompare(InsertionType.Sentinel);
    }
}
