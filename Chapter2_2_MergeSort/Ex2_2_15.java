package Chapter2_2_MergeSort;


import java.util.Queue;

/*
*   Bottom-up queue mergesort. Develop a bottom-up mergesort implementation based on the following approach:
*   Given N items, create N queues, each containing one of the items. Create a queue of the N queues. Then repeatedly
*   apply the merging operation of Exercise 2.2.14 to the first two queues and reinsert the merged queue at the end.
*   Repeat until the queue of queues contains only one queue.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_2_15 {
    private static Comparable[] aux;

    private static Queue<Comparable>[] createNQueues(Comparable[] items){
        //Comparable[] items = {"E", "A", "S", "Y", "Q", "U", "E", "S", "T", "I", "O", "N"};
        int index = 0;
        int n = items.length;
        //String[] eachQueue = new String[n];
        Queue[] eachQueue = new Queue[n];
        for (Comparable s : items){
            eachQueue[index++].add(s);
        }
        return eachQueue;
    }

    private static void bottomUpSort(Comparable[] a, int low, int mid, int high){

    }
    private static void sort(Comparable[] a){
        int N = a.length;
        aux = new Comparable[N];
        for (int size = 1; size < N; size *= 2){
            for (int index = 0; index < N - size; index += size){
                bottomUpSort(a, index, index + size - 1, Math.max(index + size + size - 1, N - size));
            }
        }
    }


    public static void main(String[] args) {
        Comparable[] items = {"E", "A", "S", "Y", "Q", "U", "E", "S", "T", "I", "O", "N"};
        sort(items);
        //StdOut.print();
    }
}
