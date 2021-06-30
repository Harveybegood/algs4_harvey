package Chapter3_4_HashTables;

import Chapter3_1_SymbolTables.SequentialSearchST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

@SuppressWarnings("unchecked")
public class SeparateChainingHashST<Key, Value> {
    public int M;                                // hash table size
    public int n;                                // number of key-value pairs
    private int lgM;                              //
    private int averageListSize;                  // specify the length of each list
    public SequentialSearchST<Key, Value>[] st;   // array of linked-list symbol tables
    // Modules for hash
    private int[] primes = {1, 1, 3, 7, 13, 31, 61, 127, 251, 509, 1021, 2039, 4093, 8191, 16381, 32749, 65521, 131071, 262139, 524287,
            1048573, 2097143, 4194301, 8388593, 16777213, 33554393, 67108859, 134217689, 268435399, 536870909, 1073741789, 2147483647};

    public SeparateChainingHashST() {
        this(10, 5);
    }
    public SeparateChainingHashST(int M){
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++){
            st[i] = new SequentialSearchST<>();
        }
    }

    // A constructor to give the client the ability to specify the average number of probes
    public SeparateChainingHashST(int M, int averageListSize) {
        this.M = M;
        this.averageListSize = averageListSize;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++){
            st[i] = new SequentialSearchST<>();
        }
        lgM = (int) (Math.log(M) / Math.log(2));
    }

    // resize the hash table to have the given number of chains,
    // rehashing all of the keys
    public void resize(int chains) {
        SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<>(chains);
        for (int i = 0; i < M; i++) {
            for (Key key : st[i].keys()) {
                if (key != null){
                    temp.put(key, st[i].get(key));
                }
            }
        }
        this.M  = temp.M;
        this.n  = temp.n;
        this.st = temp.st;
    }

    // hash function for keys
    public int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % M;
    }

    // hash function for keys based on primes for hash table size
    public int hashWithDesignatedModulus(Key key){
        int t = key.hashCode() & 0x7ffffff;
        if (lgM < 26){
            t = t % primes[lgM + 5];
        }
        return t % M;
    }

    //Returns the number of key-value pairs in this symbol table.
    public int size() {
        return n;
    }

    //Returns true if this symbol table is empty.
    public boolean isEmpty() {
        return size() == 0;
    }
    //Returns true if this symbol table contains the specified key.
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    //Returns the value associated with the specified key in this symbol table.
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        int i = hashWithDesignatedModulus(key);
        return st[i].get(key);
    }

    /*Inserts the specified key-value pair into the symbol table, overwriting the old
     value with the new value if the symbol table already contains the specified key.
     Deletes the specified key (and its associated value) from this symbol table
     if the specified value is {@code null}.*/
     
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }
        // double table size if average length of list >= averageListSize
        if ((n / M) >= averageListSize) {
            resize(2 * M);
            lgM++;
        }
        int i = hashWithDesignatedModulus(key);
        if (!st[i].contains(key)) {
            n++;
        }
        st[i].put(key, val);
    }
     //Removes the specified key and its associated value from this symbol table
     //(if the key is in this symbol table).
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");

        int i = hashWithDesignatedModulus(key);
        if (st[i].contains(key)) n--;
        st[i].delete(key);
        // halve table size if average length of list averageListSize / 4
        if (M > 1 && (n / M) <= averageListSize / 4){
            resize(M/2);
            lgM--;
        }
    }

    // return keys in symbol table as an iterable
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < M; i++) {
            for (Key key : st[i].keys())
                queue.enqueue(key);
        }
        return queue;
    }
     //Unit tests the {@code SeparateChainingHashST} data type.
     //@param args the command-line arguments
    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        // print keys
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));

    }
}