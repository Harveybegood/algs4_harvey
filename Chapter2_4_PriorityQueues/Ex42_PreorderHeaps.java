package Chapter2_4_PriorityQueues;
/*
*   Preorder heaps. Implement a version of heapsort based on the idea of representing the heap-ordered tree in preorder
*   rather than in level order. Count the number of compares used by your program and the number of compares used by the
*   standard implementation, for randomly ordered keys with N = 10^3, 10^6 and 10^9.
*
*   https://en.wikipedia.org/wiki/Tree_traversal#Pre-order_(NLR)
*
*
* */
@SuppressWarnings("unchecked")
public class Ex42_PreorderHeaps {
    private static int standardCount = 0;
    private static int preorderCount = 0;
    private static void standardHeapSort(Comparable[] array){
        int n = array.length;
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
            if (j < n && standardLess(array, j, j+1)){
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
        standardCount++;
        return array[i].compareTo(array[j]) < 0;
    }
    private static void exchange(Comparable[] array, int i, int j){
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void preorderHeapSort(Comparable[] array){

    }
}

