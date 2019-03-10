package Chapter2_4_PriorityQueues;
/*
*   Index priority-queue implementation(additional operations). Add minIndex(), change(), and delete() to your implementation
*   of Exercise 2.4.33.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_4_34_IndexPQWithminIndexChange<Key extends Comparable<Key>> {
    private int n;
    private int[] pq;   // chanage pq[] to hold indices
    private Key[] keys; // hold the key values and key values with priorities
    private int[] qp;   // hold the inverse of pq[], qp[pq[i]] = pq[qp[i]] = i, qp[i] = -1 if i is not on the queue.

    public Ex2_4_34_IndexPQWithminIndexChange(int MaxN){
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
        keys[pq[v]] = keys[pq[w]];
        keys[pq[w]] = temp;
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
    public int minIndex(){return pq[1];}
    public void change(int k, Key key){
        keys[k] = key;
        swim(qp[k]);
        sink(qp[k]);
    }
    public void delete(int k){
        exchange(k, n--);
        swim(qp[k]);
        sink(qp[k]);
        keys[pq[n+1]] = null;
        qp[pq[n+1]] = -1;
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

    }
}
