package Chapter2_4_PriorityQueues;


/*
*   Fast insert. Develop a compare-based implementation of the MinPQ API such the insert uses ~log logN compares and delete the
*   minimum uses ~2logN compares. Hint: Use binary search on parent pointers to find the ancestor in swim().
*
* */
@SuppressWarnings("unchecked")
public class Ex2_4_31_FastInsert<Key extends Comparable<Key>> {
    private int n;
    private Key[] pq;
    public Ex2_4_31_FastInsert(Comparable[] array){
        pq = (Key[]) new Comparable[array.length + 1];
        for (int i = 0; i < array.length; i++){
            pq[i + 1] = (Key) array[i];
        }
    }
    public boolean isEmpty(){return n == 0;}
    public int size(){return n;}
    public void resize(int newSize){
        Key[] newArray = (Key[]) new Comparable[newSize];
        for (int i = 0; i < n; i++){
            newArray[i] = pq[i];
        }
        pq = newArray;
    }
    public void exchange(int v, int w){
        Key temp = pq[v];
        pq[v] = pq[w];
        pq[w] = temp;
    }
    public void swim(int k){
        // use binary search to find the ancestor node, how many node between k and 1 there are.
        // which depends on how many levels in this binary heap. go upward from the current node of k to node 1.
        // we see the result k -> k/2 -> k/4 -> ... -> k/n == 1; n that we can calculate is logN.

        int lowK = 0;
        int highK = (int) Math.log(pq.length);
        while (lowK < highK){
            int middle = (highK - lowK) / 2;
            int middleIndex = (int) (k / Math.pow(2, middle));
            if (pq[k].compareTo(pq[middleIndex]) < 0){
                highK = middle - 1;
            }else if (pq[k].compareTo(pq[middleIndex]) > 0){
                lowK = middle + 1;
            }else {
                k = middle;
            }
        }
        exchange(k, 1);
    }
    public void insert(Key key){
        pq[++n] = key;
        swim(n);
    }
    public void sink(int k){
        while (2 * k <= n){
            int j = 2 * k;
            int lowNode = j;
            int highNode = pq.length;
            while (lowNode < highNode){
                int middle = lowNode + (highNode - lowNode) / 2;
                if (pq[j].compareTo(pq[middle]) < 0){
                    highNode = middle - 1;
                }else if (pq[j].compareTo(pq[middle]) > 0){
                    lowNode = middle + 1;
                }else {

                }
            }
            if (j < n && pq[j].compareTo(pq[j + 1]) < 0){
                j++;
            }
            if (!(pq[k].compareTo(pq[j]) < 0)){
                break;
            }
            exchange(k, j);
            k = j;
        }
    }
    public Key delMin(){
        Key min = pq[1];
        exchange(1, n);
        pq[n] = null;
        n--;
        return min;
    }

    public static void main(String[] args) {

    }
}
