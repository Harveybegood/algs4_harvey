package Chapter3_4_HashTables;

import Chapter3_1_SymbolTables.SequentialSearchST;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/*
*   Double probing. Modify SeparateChainingHashST to use a second hash function and pick the shorter of the two lists. Give
*   a trace of the process of inserting the keys E A S Y Q U T I O N in that order into an initially empty table of size M = 3
*   using the function 11 K % M (for the kth letter) as the first hash function and the function 17 K % M (for the kth letter)
*   as the second hash function. Give the average number of probes for random search hit and search miss in this table.
*
* */
@SuppressWarnings("unchecked")
public class Ex27_DoubleProbing<Key, Value> {
    /*
    *   Key   Value   hash (11 K % M(3))      hash (17 K % M(3))            st[]
    *
    *   E       0       11 69 % 3 = 0            17 69 % 3 = 0
        X       1       11 88 % 3 = 2            17 88 % 3 = 2
        Two hash functions which come to same index value, which means it is not able to pick the shorter lists
    *
    * */
    private int M;
    private int n;
    public SequentialSearchST<Key, Value>[] st;
    public Ex27_DoubleProbing(){
        this(3);
    }
    public Ex27_DoubleProbing(int m){
        this.M = m;
        st = (SequentialSearchST<Key, Value>[])new SequentialSearchST[M];
        for (int i = 0; i < m; i++){
            st[i] = new SequentialSearchST<>();
        }
    }
    public int hash1(Key key){
        return (11 * key.hashCode()) % M;
    }
    public int hash2(Key key){
        return (17 * key.hashCode()) % M;
    }
    public boolean isEmpty(){return n == 0;}
    public int size(){return n;}
    public boolean isContains(Key key){
        if (key == null){throw new IllegalArgumentException("Argument key cannot be null");}
        return get(key) != null;
    }
    public Value get(Key key){
        if (key == null){throw new IllegalArgumentException("Argument key cannot be null");}
        int i = hash1(key);
        //if (!isContains(key)){return null;}
        return st[i].get(key);
    }
    public void put(Key key, Value value){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        if (value == null){throw new IllegalArgumentException("Argument cannot be null");}
        if (n > M / 2){resize(2 * M);}
        int firstHash = hash1(key);
        int secondHash = hash2(key);
        int lengthOfLink1 = st[firstHash].size();
        int lengthOfLink2 = st[secondHash].size();
        int i;
        if (!isContains(key)){n++;}
        // To determine which link is longer
        i = lengthOfLink1 < lengthOfLink2 ? firstHash : secondHash;
        st[i].put(key, value);
    }
    public void resize(int newSize){
        Ex27_DoubleProbing<Key, Value> temp = new Ex27_DoubleProbing<>(newSize);
        for (int i = 0; i < M; i++){
            for (Key key : st[i].keys()){
                temp.put(key, st[i].get(key));
            }
        }
        this.st = temp.st;
        this.M = temp.M;
        this.n = temp.n;
    }
    public Iterable<Key> keys(){
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < M; i++){
            for (Key key : st[i].keys()){
                queue.enqueue(key);
            }
        }
        return queue;
    }
    // Method delete() will be written on Ex29
    public void delete(Key key){}
    public static void main(String[] args) {
        Ex27_DoubleProbing<String, Integer> doubleProbing = new Ex27_DoubleProbing<>();
        doubleProbing.put("E", 0);
        doubleProbing.put("X", 1);
        doubleProbing.put("A", 2);
        doubleProbing.put("M", 3);
        doubleProbing.put("P", 4);
        doubleProbing.put("L", 5);
        doubleProbing.put("Q", 6);
        doubleProbing.put("U", 7);
        doubleProbing.put("I", 8);
        doubleProbing.put("O", 9);
        doubleProbing.put("N", 10);
        doubleProbing.put("S", 11);
        for (String s : doubleProbing.keys()){
            StdOut.println(s + "   " + doubleProbing.get(s));
        }
    }
}
