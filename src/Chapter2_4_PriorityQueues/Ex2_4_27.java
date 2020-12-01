package Chapter2_4_PriorityQueues;


import edu.princeton.cs.algs4.StdOut;

/*
*   Find the minimum. Add a min() method to MaxPQ. Your implementation should use constant time and constant extra space
*
* */
@SuppressWarnings("unchecked")
public class Ex2_4_27 {
    public class MaxPQ<Key extends Comparable<Key>>{
        private int n;
        private Key[] pq;
        private Key min;
        public MaxPQ(int maxN){
           pq = (Key[]) new Comparable[maxN + 1];
        }
        public boolean isEmpty(){return n == 0;}
        public int size(){return n;}
        public void insert(Key v){
            if (min == null || v.compareTo(min) < 0){
                min = v;
            }
            pq[++n] = v;
            swim(n);
        }
        public Key delMax(){
            Key max = pq[1];
            Key temp = pq[1];
            pq[1] = pq[n];
            pq[n] = temp;
            if (max == min){
                min = null;
            }
            n--;
            pq[n + 1] = null;
            sink(1);
            return max;
        }
        public void swim(int k){
            while (k > 1){
                if (pq[k/2].compareTo(pq[k]) < 0){
                    Key temp = pq[k];
                    pq[k] = pq[k/2];
                    pq[k/2] = temp;
                }
                k = k/2;
            }
        }
        public void sink(int k){
            while (2 * k <= n){
                int j = 2 * k;
                if (j < n && pq[j].compareTo(pq[j+1]) < 0){
                    j++;
                }
                if (!(pq[k].compareTo(pq[j]) < 0)){
                    break;
                }
                Key temp = pq[k];
                pq[k] = pq[j];
                pq[j] = temp;
                k = j;
            }
        }
        public Key min(){
            return min;
        }
    }

    public static void main(String[] args) {
        Ex2_4_27.MaxPQ maxPQ = new Ex2_4_27().new MaxPQ(6);
        StdOut.println("Min expected null : " + maxPQ.min());
        maxPQ.insert(10);
        maxPQ.insert(2);
        StdOut.println("Min expected 2 : " + maxPQ.min());
        maxPQ.insert(9);
        maxPQ.insert(90);
        maxPQ.insert(1);
        maxPQ.insert(36);
        StdOut.println("Min expected 1 : " + maxPQ.min());
        maxPQ.delMax();
        maxPQ.delMax();
        StdOut.println("Min expected 1 : " + maxPQ.min());
        maxPQ.delMax();
        maxPQ.delMax();
        maxPQ.delMax();
        maxPQ.insert(20);
        StdOut.println("Min expected 1 : " + maxPQ.min());
    }
}
