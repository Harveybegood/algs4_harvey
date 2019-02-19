package Chapter2_4_PriorityQueues;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Multiway heaps. Considering the cost of compares only, and assuming that it takes t compares to find the largest of
*   t items, find the value of t that minimizes the coefficient of NlgN in the compare count when a t-ary heap is used
*   in heapsort. First, assume a straightforward generalization of sink(); then assume that Floyd's method can save
*   one compare in the inner loop.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_4_23<Key extends Comparable<Key>> {
    private static int numberOfCompare = 0;
    public static void exchange(Comparable[] array, int k, int N){
        Comparable temp = array[k];
        array[k] = array[N];
        array[N] = temp;
    }
    public static boolean less(Comparable[] array, int i, int j){
        numberOfCompare++;
        return array[i].compareTo(array[j]) < 0;
    }
    public static void sink(Comparable[] array, int k, int N, int TAry){
        while (TAry * k <= N){
            int j = TAry * k;
            if (j < N && less(array, j, j + 1)){
                j++;
            }
            if (!less(array, k, j)){
                break;
            }
            exchange(array, k, j);
            k = j;
        }
    }
    public static void heapSort(Comparable[] array, int TAry){
        int N = array.length;
        Comparable[] newArray = new Comparable[N + 1];
        for (int i = 0; i < N; i++){
            newArray[i + 1] = array[i];
        }
        for (int k = N / 2; k >= 1; k--){
            sink(newArray, k, N, TAry);
        }
        while (N > 1){
            exchange(newArray, 1, N--);
            sink(newArray, 1, N, TAry);
        }
    }

    public static void main(String[] args) {
        StdOut.println("Straightforward generalization");
        StdOut.println();
        StdOut.println("TAry  numberOfCompare");
        Comparable[] array = new Comparable[100];
        for (int i = 0; i < 100; i++){
            array[i] = StdRandom.uniform(1, 1000);
        }
        for (int TAry = 2 ; TAry < 10; TAry++){
            heapSort(array, TAry);
            StdOut.printf("%2d %7d\n", TAry, numberOfCompare);
            numberOfCompare = 0;
        }
    }
}
// copy from https://github.com/reneargento/algorithms-sedgewick-wayne/blob/master/src/chapter2/section4/
// Exercise23_MultiwayHeaps.txt

/*
* t-ary heaps make O(log t n) comparisons in a swim() operation and O(t log t n) comparisons in a sink() operation.
* This allows t-ary heaps to be more efficient in algorithms where decrease priority operations are more common than
* delete max operations. Examples: Dijkstra's algorithm for a single source shortest path and Prim's algorithm
* for minimum spanning tree.

Heapsort uses N/2 sink() operations to build the heap and N sink() operations in the sortdown phase.
This means that heapsort makes 1.5 N * (t log t n) comparisons in a straightforward generalization of sink()
and 1.5 N * ((t log t n) - 1) comparisons with Floyd's method.

Therefore, the number of comparisons for different t while sorting an array of size 100 is:

Straightforward generalization of sink():
Binary heap: 150 * 2 * log 2 100 ~ 1992 compares
3-ary heap: 150 * 3 * log 3 100 ~ 1885 compares
4-ary heap: 150 * 4 * log 4 100 ~ 1992 compares
5-ary heap: 150 * 5 * log 5 100 ~ 2145 compares
6-ary heap: 150 * 6 * log 6 100 ~ 2313 compares
7-ary heap: 150 * 7 * log 7 100 ~ 2478 compares

Using Floyd's method:
Binary heap: 150 * (2 * log 2 100 - 1) ~ 1842 compares
3-ary heap: 150 * (3 * log 3 100 - 1) ~ 1735 compares
4-ary heap: 150 * (4 * log 4 100 - 1) ~ 1842 compares
5-ary heap: 150 * (5 * log 5 100 - 1) ~ 1995 compares
6-ary heap: 150 * (6 * log 6 100 - 1) ~ 2163 compares
7-ary heap: 150 * (7 * log 7 100 - 1) ~ 2328 compares

For both a straightforward generalization of sink() and for Floyd's method the value of t that minimizes
the coefficient of N lg N in the compare count when a t-ary heap is used in heapsort is 3.

*
* */
