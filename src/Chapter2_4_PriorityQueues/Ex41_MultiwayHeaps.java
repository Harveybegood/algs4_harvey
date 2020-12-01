package Chapter2_4_PriorityQueues;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Multiway heaps. Implement a version of heapsort based on complete heap-ordered 3-ary and 4-ary trees, as described in
*   the text. Count the number of compares used by each and the number of compares used by the standard implementation,
*   for randomly ordered distinct keys with N = 10^3, 10^6 and 10^9.
*
* */
@SuppressWarnings("unchecked")
public class Ex41_MultiwayHeaps {
    private static int ternaryOfCount = 0;
    private static int quaternaryOfCount = 0;
    private static int standardOfCount = 0;
    private static void ternarySortHeap(Comparable[] array){
        int n = array.length - 1;
        for (int k = n / 2; k >= 1; k--){
            ternarySink(array, k, n);
        }
        while (n > 1){
            exchange(array, 1, n--);
            ternarySink(array, 1, n);
        }
    }
    private static void ternarySink(Comparable[] array, int k, int n){
        while (k * 3 - 1 <= n){
            int j = k * 3;
            if (j < n && ternaryLess(array, j - 1, j)){
                j++;
            }
            if (!ternaryLess(array, k, j - 1)){
                break;
            }
            exchange(array, k, j - 1);
            k = j - 1;
        }
    }
    private static boolean ternaryLess(Comparable[] array, int i, int j){
        ternaryOfCount++;
        return array[i].compareTo(array[j]) < 0;
    }
    private static void exchange(Comparable[] array, int i, int j){
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    private static void quaternarySortHeap(Comparable[] array){
        int n = array.length - 1;
        for (int k = n / 2; k >= 1; k--){
            quaternarySink(array, k, n);
        }
        while (n > 1){
            exchange(array, 1, n--);
            quaternarySink(array, 1, n);
        }
    }
    private static void quaternarySink(Comparable[] array, int k, int n){
        while (k * 4 - 1 <= n){
            int j = k * 4 - 1;
            if (j < n && quaternaryLess(array, j, j + 1)){
                j++;
            }
            if (!quaternaryLess(array, k, j)){
                break;
            }
            exchange(array, k, j);
            k = j;
        }
    }
    private static boolean quaternaryLess(Comparable[] array, int i, int j){
        quaternaryOfCount++;
        return array[i].compareTo(array[j]) < 0;
    }
    private static void standardSortHeap(Comparable[] array){
        int n = array.length - 1;
        for (int k = n / 2; k >= 1; k--){
            standardSink(array, k, n);
        }
        while (n > 1){
            exchange(array, 1, n--);
            standardSink(array, 1, n);
        }
    }
    private static void standardSink(Comparable[] array, int k, int n){
        while (k * 2 <= n){
            int j = k * 2;
            if (j < n && standardLess(array, j, j + 1)){
                j++;
            }
            if (!standardLess(array, k, j)){
                break;
            }
            exchange(array, k, j);
            k = j;
        }
    }
    private static boolean standardLess(Comparable[] array, int i, int j){
        standardOfCount++;
        return array[i].compareTo(array[j]) < 0;
    }
    private static Comparable[] generateArray(int n){
        Comparable[] array = new Comparable[n + 1];
        for (int i = 1; i <= n; i++){
            array[i] = StdRandom.uniform(1, n);
        }
        return array;
    }

    public static void main(String[] args) {
        int[] n = {10, 1000, 1000000, 10000000};
        StdOut.println("Random data     Standard    Ternary     Quaternary");
       /* StdOut.println(n[0]);
        StdOut.println(n[1]);
        StdOut.println(n[2]);*/
        for (int i = 0; i < n.length; i++){
            standardSortHeap(generateArray(n[i]));
            ternarySortHeap(generateArray(n[i]));
            quaternarySortHeap(generateArray(n[i]));
            StdOut.printf("%8d %12d %12d %12d\n", n[i], standardOfCount, ternaryOfCount, quaternaryOfCount);
        }
        Comparable[] array = generateArray(20);
        standardSortHeap(array);
        for (int i = 1; i <= 20; i++){
            StdOut.print(array[i] + " ");
        }
    }
}
