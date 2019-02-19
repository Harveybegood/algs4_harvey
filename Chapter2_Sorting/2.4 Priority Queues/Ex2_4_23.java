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
