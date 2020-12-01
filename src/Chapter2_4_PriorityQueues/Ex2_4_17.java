package Chapter2_4_PriorityQueues;


import edu.princeton.cs.algs4.StdOut;

/*
*   Prove that building a minimum-oriented priority queue of size k then doing N-k replace the minimum
*   (insert followed by remove the minimum) operations leaves the k largest of the N items in the
*   priority queue.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_4_17 {
    public boolean less(Comparable[] array, int v, int w){
        return array[v].compareTo(array[w]) < 0;
    }
    public void exch(Comparable v, Comparable w){
        Comparable temp = v;
        v = w;
        w = temp;
    }
    public void sink(Comparable[] array, int k, int N){
        while (k < N){
            int j = 2*k;
            while (j < N && less(array, j, j + 1)){
                j++;
            }
            if (!(less(array, k, j))){
                break;
            }
            exch(array[j], array[k]);
            k = j;
        }
    }
    public void swim(Comparable[] array, int k){
        while (k > 1 && less(array, k, k / 2)){
            exch(array[k / 2], array[k]);
            k = k / 2;
        }
    }
    public void insert(Comparable v, Comparable[] array){
        int N = array.length;
        array[++N] = v;
        swim(array, N);
    }
    public void delMin(Comparable[] array){
        int N = array.length;
        //Comparable min = array[1];
        exch(1, N);
        array[1] = null;
        N--;
        swim(array, N);
    }
    public Comparable[] buildingMiniOrientedPQ(Comparable[] array){
        int N = array.length;
        for (int k = 0; k < N; k++){
            sink(array, k, N);
        }
        while (N > 1){
            exch(1, N--);
            sink(array,1, N);
        }
        return array;
    }
    public static void main(String[] args) {
        Ex2_4_17 prove = new Ex2_4_17();
        Comparable[] array = {"a", "n", "u", "q", "i", "t", "p", "s", "j", "b"};
        Comparable[] heapArray = prove.buildingMiniOrientedPQ(array);
        StdOut.println(heapArray.length);
        for (int i = 0; i < heapArray.length; i++){
            StdOut.print(heapArray[i] + " ");
        }
        prove.delMin(heapArray);
        prove.insert("r", heapArray);
        prove.delMin(heapArray);
        prove.insert("e", heapArray);
        prove.delMin(heapArray);
        prove.insert("t", heapArray);
        prove.delMin(heapArray);
        prove.insert("p", heapArray);
        prove.delMin(heapArray);
        prove.insert("c", heapArray);
        prove.delMin(heapArray);
        prove.insert("i", heapArray);
        for (int i = 0; i < heapArray.length; i++){
            StdOut.print(heapArray[i] + "");
        }
        StdOut.println(heapArray.length);
    }
}
