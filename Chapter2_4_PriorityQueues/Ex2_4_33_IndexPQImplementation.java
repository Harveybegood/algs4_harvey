package Chapter2_4_PriorityQueues;

import edu.princeton.cs.algs4.StdOut;

/*
*   Index priority-queue implementation. Implement the basic operations in the index priority-queue API on page 320 by
*   modifying Algorithm 2.6 as follows: Change pq[] to hold indices, add an array keys[] to hold the key values, and add
*   an array qp[] that is the inverse of pq[] -- qp[i] gives the position of i in pq[] (the index j such that pq[j] is i)
*   . Then modify the code in Algorithm 2.6 to maintain these data structures. Use the convention that qp[i] = -1 if i is
*   not on the queue, and include a method contains() that test this condition. You need to modify the helper methods
*   exch() and less() but not sink() or swim().
*
* */
@SuppressWarnings("unchecked")
public class Ex2_4_33_IndexPQImplementation<Key extends Comparable<Key>> {
    private int n;
    private int[] pq;   // chanage pq[] to hold indices
    private Key[] keys; // hold the key values and key values with priorities
    private int[] qp;   // hold the inverse of pq[], qp[pq[i]] = pq[qp[i]] = i, qp[i] = -1 if i is not on the queue.

    public Ex2_4_33_IndexPQImplementation(int MaxN){
        keys = (Key[]) new Comparable[MaxN + 1];
        pq = new int[MaxN + 1];
        qp = new int[MaxN + 1];
        for (int i = 0; i <= MaxN; i++){
            qp[i] = -1;
        }
    }
    public boolean contains(int k){return qp[k] != -1;}
    public boolean isEmpty(){return n == 0;}
    public int size(){return n;}
    private void exchange(int v, int w){
        Key temp = keys[pq[v]];
        int tempIndex = pq[v];
        keys[pq[v]] = keys[pq[w]];
        pq[v] = pq[w];
        keys[pq[w]] = temp;
        pq[w] = tempIndex;
    }
    private boolean less(int i, int j){
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }
    public void insert(int k ,Key key){
        if (n == keys.length / 2){resize( 2 * keys.length);}
        keys[++n] = key;
        qp[n] = k;
        pq[k] = n;
        swim(n);
    }
    public void swim(int k){
        while (k > 1){
            if (less(k, k / 2)){
                // index minimum priority queue
                exchange(k, k /2);
            }
            k = k / 2;
        }
    }
    public Key min(){
        return keys[pq[1]];
    }
    public int delMin(){
        // This is minimum-oriented PQ, according to which we delete the top element of heap to complete the delete action
        if (keys.length == 0){throw new RuntimeException("PQ is underflow. ");}
        if (n > 0 && n == keys.length / 4){resize(keys.length / 2);}
        int indexOfMin = pq[1];
        exchange(1, n);
        n--;
        keys[pq[n+1]] = null;
        qp[pq[n+1]] = -1;
        sink(1);
        return indexOfMin;
    }
    public void sink(int k){
        while (2 * k <= n){
            // each parent node needs to compare its two children which are its left child and right child
            int j = 2 * k;
            if (j < n && less(j, j + 1)){
                j++;
            }
            if (less(k, j)){
                break;
            }
            exchange(k, j);
            k = j;
        }
    }
    public void resize(int newSize){
        Key[] auxArray = (Key[]) new Comparable[newSize];
        int[] auxPQ = new int[newSize];
        int[] auxQP = new int[newSize];
        for (int i = 1; i <= n; i++){
            auxArray[pq[i]] = keys[pq[i]];
            auxPQ[i] = pq[i];
            auxQP[i] = qp[i];
        }
        keys = auxArray;
        pq = auxPQ;
        qp = auxQP;
    }

    public static void main(String[] args) {
        Ex2_4_33_IndexPQImplementation indexPQImplementation = new Ex2_4_33_IndexPQImplementation(1);
        indexPQImplementation.insert(1,4);
        indexPQImplementation.insert(2,7);
        indexPQImplementation.insert(3,1);
        indexPQImplementation.insert(4,10);
        indexPQImplementation.insert(5,6);
        for (int i = 1; i <= indexPQImplementation.size(); i++){
            StdOut.print(indexPQImplementation.keys[i] + " ");
        }
        StdOut.println();
        StdOut.println("Expected value: 1 -> " + indexPQImplementation.delMin());
        StdOut.println("Expected value: 6 -> " + indexPQImplementation.delMin());
        StdOut.println("Expected PQ size: 3 -> " + indexPQImplementation.size());
        for (int i = 1; i <= indexPQImplementation.size(); i++){
            StdOut.print((indexPQImplementation.keys[i] + " "));
        }
    }
}
