package Chapter3_4_HashTables;
/*
*   Assumption J: (uniform hashing assumption). The hash functions that we use uniformly and independently distribute keys
*   among the integer values between 0 and M-1.
*
* */
import Chapter3_1_SymbolTables.SequentialSearchST;
import edu.princeton.cs.algs4.StdOut;

@SuppressWarnings("unchecked")
public class SeparateChainingHashST<Key, Value> {
    private int N; // number of key-value pairs
    private int M; // hash table size
    private SequentialSearchST<Key, Value>[] st; // array of ST objects
    public SeparateChainingHashST(){
        this(997);
        //this();
    }
    public SeparateChainingHashST(int M){
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++){
            st[i] = new SequentialSearchST<>();
        }
    }
    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % M;
    }
    public Value get(Key key){
        return st[hash(key)].get(key);
    }
    public void put(Key key, Value value){
        st[hash(key)].put(key, value);
    }

    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> stringIntegerSeparateChainingHashST = new SeparateChainingHashST<>();
        StdOut.println(stringIntegerSeparateChainingHashST.hash("S"));
        StdOut.println(stringIntegerSeparateChainingHashST.hash("S"));
        StdOut.println(stringIntegerSeparateChainingHashST.hash("E"));
        StdOut.println(stringIntegerSeparateChainingHashST.hash("A"));
        StdOut.println(stringIntegerSeparateChainingHashST.hash("R"));
        StdOut.println(stringIntegerSeparateChainingHashST.hash("C"));
        StdOut.println(stringIntegerSeparateChainingHashST.hash("H"));
        StdOut.println(stringIntegerSeparateChainingHashST.hash("E"));
    }
}
