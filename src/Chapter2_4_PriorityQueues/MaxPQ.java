package Chapter2_4_PriorityQueues;

@SuppressWarnings("unchecked")
public class MaxPQ<Key extends Comparable<Key>> {
    // Use a generic implementation with a parameterized type key that implements the Comparable interface
    private Key[] pq; // heap-ordered complete binary tree
    private int N = 0;
    public MaxPQ(int maxN){
        pq = (Key[]) new Comparable[maxN + 1];
    }
    // If the heap is empty or not
    public boolean isEmpty(){
        return  N == 0;
    }
    // the size of current heap
    public int size(){
        return N;
    }
    public void insert(Key v){
        if (N >= pq.length / 2){
            resize(2 * N);
        }
        pq[++N] = v;
        sink(N);
    }
    //take off the top item; move the item from the end of heap to the top, reduce the size of heap, finally, sink().
    public Key delMax(){
        if (N > 0 && N <= size() / 4){
            resize(N / 2);
        }
        Key max = pq[1];
        exch(1, N); // exchange the end of heap with the top of heap
        N--;
        pq[N + 1] = null; // avoid loitering
        sink(1);
        return max;
    }
    public void exch(int v, int w){
        Key temp = pq[v];
        pq[v] = pq[w];
        pq[w] = temp;
    }
    public boolean less(int v, int w){
        return pq[v].compareTo(pq[w]) < 0 ;
    }
    public void swim(int k){
        while (k > 1 && less(k/2, k)){
            exch(k/2, k);
            k = k / 2;
        }
    }
    public void sink(int k){
        while (2*k <= N){
            int j = 2*k;
            if (j < N && less(j, j + 1)){
                j++;
            }
            if (!less(k, j))
                break;
            exch(k, j);
            k = j;
        }
    }
    public void resize(int newSize){
        Key[] newPQ = (Key[]) new Comparable[newSize];
        for (int i = 0; i < N; i++){
            newPQ[i] = pq[i];
        }
        pq = newPQ;
    }

    public static void main(String[] args) {


    }
}
