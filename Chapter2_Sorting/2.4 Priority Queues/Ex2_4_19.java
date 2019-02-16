package Chapter2_4_PriorityQueues;

import edu.princeton.cs.algs4.StdOut;

/*
*   Implement the constructor for MaxPQ that takes an array of items as argument, using the bottom-up heap
*   construction method described on page 323 in the text.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_4_19 {
    public class MaxPQ<Key extends Comparable<Key>>{
        private Key[] pq;
        private int n;
        public MaxPQ(Key[] array){
            int n = array.length;
            pq = (Key[]) new Comparable[n + 1];
            for (int i = 0; i < n; i++){
                pq[i + 1] = array[i];
            }
            for (int k = n / 2; k >= 1; k--){
                sink(k);
            }
        }
        public void sink(int k){
            //int j = 2 * k;
            while (2 * k <= n){
                int j = 2 * k;
                if (j < n && pq[j].compareTo(pq[j+1]) < 0){
                    j++;
                }
                if (!(pq[k].compareTo(pq[j]) < 0)){
                    break;
                }
                Key temp = pq[j];
                pq[j] = pq[k];
                pq[k] = temp;
                k = j;
            }
        }
        public Comparable[] getPriorityQueue(){
            return pq;
        }
    }

    public static void main(String[] args) {
        Comparable[] array = {"h", "q", "u", "a", "c", "o", "w", "i", "v", "s", "b", "t", "n", "y", "d", "e"};
        MaxPQ maxPQ = new Ex2_4_19().new MaxPQ(array);
        Comparable[] PQArray = maxPQ.getPriorityQueue();
        for (int i = 1; i < array.length; i++){
            StdOut.print(PQArray[i] + " ");
        }
    }
}
