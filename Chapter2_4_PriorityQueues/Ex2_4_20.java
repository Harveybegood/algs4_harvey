package Chapter2_4_PriorityQueues;

import edu.princeton.cs.algs4.StdOut;

/*
*   Prove that sink-based heap construction uses fewer than 2N compares and fewer than N exchanges.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_4_20 {
    public static class sinkBasedHeap<Key extends Comparable<Key>>{
        private int n;
        private Key[] PQ;
        private int timesOfCompares = 0;
        private int timesOfExchange = 0;
        public sinkBasedHeap(Key[] array){
            n = array.length;
            PQ = (Key[]) new Comparable[n + 1];
            for (int i = 0; i < n; i++){
                PQ[i + 1] = array[i];
            }
            for (int k = n / 2; k >= 1; k--){
                sink(k);
            }
        }
        public boolean less(int v, int w){
            timesOfCompares++;
            return PQ[v].compareTo(PQ[w]) < 0;
        }
        public void exchange(int i, int j){
            timesOfExchange++;
            Key temp = PQ[i];
            PQ[i] = PQ[j];
            PQ[j] = temp;
        }
        public void sink(int k){
            while (2 * k <= n){
                int j = 2 * k;
                if (j < n && less(j, j + 1)){
                    j++;
                }
                if (!less(k, j)){
                    break;
                }
                exchange(k, j);
                k = j;
            }
        }

        public static void main(String[] args) {
            StdOut.printf("The number of sink-based heap | Time of Compares | Time of exchanges\n");
            int n = 1000000;
            Integer[] array = new Integer[n];
            for (int i = 0; i < n; i++){
                array[i] = i;
            }
            sinkBasedHeap compares = new sinkBasedHeap(array);

            StdOut.printf("%20d %18d %15d\n", n, compares.timesOfCompares, compares.timesOfExchange);
        }
    }
}
