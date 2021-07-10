package Chapter3_4_HashTables;

import Chapter3_3_BalancedSearchTrees.RedBlackBST;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/*
*   Hybrid. Run experimental studies to determine the effect of using RedBlackBST instead of SequentialSearchST to handle
*   collisions in SeparateChainingHashST. This solution carries the advantage of guaranteeing logarithmic performance even
*   for a bad hash function and the disadvantage of necessitating maintenance of two different symbol-table implementations.
*   What are the practical effects?
*   1> ..
*
* */
@SuppressWarnings("unchecked")
public class Ex37_Hybrid<Key extends Comparable<Key>, Value>{
    private int M; // initial symbol table size
    private int N; // key-value pair
    //private int lgM; // distribute the key-value pair using a prime larger than M
    private RedBlackBST<Key, Value>[] redBlackBST;
    public Ex37_Hybrid(){
        this(10);
    }
    private Ex37_Hybrid(int M){
        this.M = M;
        this.N = 0;
        redBlackBST = (RedBlackBST<Key, Value>[]) new RedBlackBST[M];
        for (int i = 0; i < M; i++){
            redBlackBST[i] = new RedBlackBST<>();
        }
        //lgM = (int) (Math.log(M) / Math.log(2));
    }
    /*private int[] primes = {1, 1, 3, 7, 13, 31, 61, 127, 251, 509, 1021, 2039, 4093, 8191, 16381, 32749, 65521, 131071, 262139, 524287,
            1048573, 2097143, 4194301, 8388593, 16777213, 33554393, 67108859, 134217689, 268435399, 536870909, 1073741789, 2147483647};
*/
    public boolean isEmpty(){return N == 0;}
    public int size(){return N;}
    public boolean isContains(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        return get(key) != null;
    }
    // a bad hash function
    public int badHashFunction(Key key){
        return ((M * key.hashCode()) & 0x7fffffff) % M;
    }
    //
    /*public int hash(Key key){
        int t = key.hashCode() & 0x7fffffff;
        if (lgM < 26){
            t = t % primes[lgM + 5];
        }
        return t % M;
    }*/
    public Value get(Key key){
        if (key == null){throw new IllegalArgumentException("Key argument cannot be null");}
        int i = badHashFunction(key);
        return redBlackBST[i].get(key);
    }
    public void put(Key key, Value value){
        if (key == null){throw new IllegalArgumentException("Argument key cannot be null");}
        if (value == null){throw new IllegalArgumentException("Argument value cannot be null");}
        if (N >= M / 2){
            resize(2 * M);
            //lgM++;
        }
        int i = badHashFunction(key);
        if (!isContains(key)){
            N++;
        }
        redBlackBST[i].put(key, value);
    }
    public void delete(Key key){
        if (key == null){throw new IllegalArgumentException("key argument cannot be null");}
        int i = badHashFunction(key);
        if (isContains(key)){
            N--;
        }
        redBlackBST[i].delete(key);
        if (N > 0 && N <= M / 4){
            resize(M / 2);
            //lgM--;
        }
    }
    public void resize(int cap){
        Ex37_Hybrid<Key, Value> temp = new Ex37_Hybrid<>(cap);
        for (int i = 0; i < M; i++){
            for (Key key : redBlackBST[i].keys()){
                if (redBlackBST[i] != null){
                    temp.put(key, redBlackBST[i].get(key));
                }
            }
        }
        this.M = temp.M;
        this.N = temp.N;
        this.redBlackBST = temp.redBlackBST;
    }
    public Iterable<Key> keys(){
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < M; i++){
            if (redBlackBST[i].root != null){
                for (Key key : redBlackBST[i].keys()){
                    queue.enqueue(key);
                }
            }
        }
        return queue;
    }

    public static void main(String[] args) {
        Ex37_Hybrid<Integer, Integer> hybrid = new Ex37_Hybrid<>();
        for (int i = 0; i < 5; i++){
            hybrid.put(i, i);
        }
        for (Integer number : hybrid.keys()){
            StdOut.println(number + " " + hybrid.get(number));
        }
        hybrid.delete(4);
        StdOut.println("The size of symbol table is 4 : " + hybrid.N);
        for (Integer number : hybrid.keys()){
            StdOut.println(number + " " + hybrid.get(number));
        }
    }
}
