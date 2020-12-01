package Chapter2_5_Applications;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.StdOut;


/*
*   Stable priority queue. Develop a stable priority-queue implementation(which returns duplicate keys in the same order
*   in which they were inserted).
*
* */
public class Ex24_StablePriorityQueue {
    public class Wrapper implements Comparable<Wrapper>{
        private Comparable keyValue;
        private int originalIndex;
        public Wrapper(Comparable keyValue, int originalIndex){
            this.keyValue = keyValue;
            this.originalIndex = originalIndex;
        }
        public int compareTo(Wrapper that){
            @SuppressWarnings("unchecked")
            int compare = this.keyValue.compareTo(that.keyValue);
            // different keys
            if (compare != 0){
                return compare;
            }
            // equal keys
            if (this.originalIndex < that.originalIndex){
                return -1;
            }
            else if (this.originalIndex > that.originalIndex){
                return 1;
            }
            else {
                return 0;
            }
        }

    }
    @SuppressWarnings("unchecked")
    private void sortStablePriorityQueue(Comparable[] array){
        Wrapper[] wrappersKeys = new Wrapper[array.length];
        int wrappedIndex = 0;
        for (int i = 0; i < array.length; i++){
            Wrapper wrapper = new Wrapper(array[i], i);
            wrappersKeys[wrappedIndex++] = wrapper;
        }
        MaxPQ maxPQ = new MaxPQ();
        for (int i = 0; i < array.length; i++){
            maxPQ.insert(wrappersKeys[i]);
        }
        Wrapper[] aux = new Wrapper[array.length];
        for (int i = 0; i < array.length; i++){
            aux[i] = (Wrapper) maxPQ.delMax();
        }
        for (int i = 0; i < array.length; i++){
            StdOut.print(aux[i].keyValue + " ");
        }
    }

    public static void main(String[] args) {
        Comparable[] array = {2, 20, -1, -30, 30, 5, 6, 8, -99, -3, 0, 4, 4, 4};
        Ex24_StablePriorityQueue stablePriorityQueue = new Ex24_StablePriorityQueue();
        stablePriorityQueue.sortStablePriorityQueue(array);
    }
}
