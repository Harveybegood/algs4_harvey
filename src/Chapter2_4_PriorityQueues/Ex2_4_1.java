package Chapter2_4_PriorityQueues;


import edu.princeton.cs.algs4.StdOut;

/*
*   Suppose that the sequence P R I O * R * * I * T * Y * * * Q U E * * * U * E (where a letter means insert and
*   an asterisk means remove the maximum) is applied to an initially empty priority queue. Give the sequence of letters
*   returned by the remove the maximum operations.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_4_1 {
    public static void main(String[] args) {
        Ex2_4_1 ex2_4_1 = new Ex2_4_1();
        PQMax<String> pqMax = ex2_4_1.new PQMax<>();
        pqMax.insert("P");
        pqMax.insert("R");
        pqMax.insert("I");
        pqMax.insert("O");
        StdOut.print(pqMax.delMax() + " ");
        pqMax.insert("R");
        StdOut.print(pqMax.delMax() + " ");
        StdOut.print(pqMax.delMax() + " ");
        pqMax.insert("I");
        StdOut.print(pqMax.delMax() + " ");
        pqMax.insert("T");
        StdOut.print(pqMax.delMax() + " ");
        pqMax.insert("Y");
        StdOut.print(pqMax.delMax() + " ");
        StdOut.print(pqMax.delMax() + " ");
        pqMax.insert("Q");
        pqMax.insert("U");
        pqMax.insert("E");
        StdOut.print(pqMax.delMax() + " ");
        StdOut.print(pqMax.delMax() + " ");
        StdOut.print(pqMax.delMax() + " ");
        pqMax.insert("U");
        StdOut.print(pqMax.delMax() + " ");
        pqMax.insert("E");
    }
    public class PQMax<Key extends Comparable<Key>>{
        // delete maximum elements once a symbol of * met
        private int N = 0;
        private Key[] pq;
        public PQMax(){
            pq = (Key[]) new Comparable[1];
        }
        public boolean isEmpty(){
            return N == 0;
        }
        public int size(){
            return N;
        }
        public void insert(Key k){
            if (N == pq.length / 2){
                resize( 2 * pq.length);
            }
            pq[++N] = k;
            swim(N);
        }
        public void swim(int key){
            while (key > 1 && less(key / 2, key)){
                exch(key / 2, key);
                key = key / 2;
            }
        }
        public void sink(int key){
            while (2 * key < N){
                int j = 2 * key;
                if (j < N && less(j, j + 1)){
                    j++;
                }
                if (!less(key, j)){
                    break;
                }
                exch(key, j);
                key = j;
            }
        }
        private boolean less(int v, int w){
            return pq[v].compareTo(pq[w]) < 0;
        }
        private void exch(int k1, int k2){
            Key temp = pq[k1];
            pq[k1] = pq[k2];
            pq[k2] = temp;
        }
        public void resize(int newSize){
            Key[] newKey = (Key[]) new Comparable[newSize];
            for (int i = 1; i <= N; i++){
                newKey[i] = pq[i];
            }
            pq = newKey;
        }
        private Key delMax(){
            if (N > 0 && N == pq.length / 4){
                resize(pq.length / 2);
            }
            Key max = pq[1];
            exch(1, N);
            N--;
            pq[N + 1] = null;
            sink(1);
            return max;
        }
    }
}
