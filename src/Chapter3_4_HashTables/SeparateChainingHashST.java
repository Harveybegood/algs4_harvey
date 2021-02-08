package Chapter3_4_HashTables;
/*
*   Assumption J: (uniform hashing assumption). The hash functions that we use uniformly and independently distribute keys
*   among the integer values between 0 and M-1.
*
* */
import Chapter3_1_SymbolTables.SequentialSearchST;
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
        return (Value) st[hash(key)].get(key);
    }
    public void put(Key key, Value value){
        st[hash(key)].put(key, value);
    }

}
