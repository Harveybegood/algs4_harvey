package Chapter3_4_HashTables;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/*
*   Double hashing. Modify LinearProbingHashST to use a second hash function to define the probe sequence. Specifically,
*   replace(i + 1) % M(both occurrences)by (i + k) % M where k is a nonzero key-dependent integer that is relatively prime
*   to M. Note: You may meet the last condition by assuming that M is prime. Give a trace of the process of inserting the
*   keys E A S Y Q U T I O N in that order into an initially empty table of size M = 11, using the hash functions described
*   in the previous exercise. Give the average number of probes for random search hit and search miss in this table.
*
*       So, we have h1 = 11 k % M and h2 = 17 k % M in Exercise 27
* */
@SuppressWarnings("unchecked")
public class Ex28_DoubleHashing<Key, Value> {
    private int M;
    private int n;
    private Key[] keys;
    private Value[] values;
    private int increment = 0;
    public Ex28_DoubleHashing(){
        this(11);
    }
    public Ex28_DoubleHashing(int M){
        this.M = M;
        //this.n = n;
        for (int i = 0; i < M; i++){
            keys = (Key[]) new Object[M];
            values = (Value[]) new Object[M];
        }
    }
    public int hash1(Key key){
        return (11 * key.hashCode() & 0x7fffffff) % M;
    }
    // if a collision encounters then a second hash function hash2 will be taken effective to avoid the collision and
    // find a proper position for the new key
    public int hash2(Key key){
        return (17 * key.hashCode() & 0x7fffffff) % M;
    }
    public int hash(Key key){
        increment++;
        return (hash2(key) + increment * hash1(key)) % M;
    }
    public boolean isEmpty(){return n == 0;}
    public int size(){return n;}
    public Value get(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        for (int i = hash1(key); keys[i] != null; i = (i + 1) % M){
            if (key.equals(keys[i])){
                return values[i];
            }
        }
        return null;
    }
    public boolean isContains(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        return get(key) != null;
    }
    public void put(Key key, Value value){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        // some codes written as followings:
        // if(value == null){
        //      delete(key);
        //        return;
        // }
        // in my view, argument is null, which would be using to tell user or programming the parameter that cannot be left out.
        if (value == null){throw new IllegalArgumentException("Argument cannot be null");}
        int i;
        for (i = hash1(key); keys[i] != null; i = hash(key)){
            StdOut.println("Hash value for: " + i);
            if (keys[i].equals(key)){
                values[i] = value;
            }
        }
        keys[i] = key;
        values[i] = value;
        n++;
    }
    public void delete(Key key){
        // delete function left to in the next exercise 29
    }
    public Iterable<Key> keys(){
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < M; i++){
            if (keys[i] != null){
                queue.enqueue(keys[i]);
            }
        }
        return queue;
    }

    public static void main(String[] args) {
        Ex28_DoubleHashing<String, Integer> doubleHashing = new Ex28_DoubleHashing<>();
        doubleHashing.put("E", 0);
        doubleHashing.put("X", 1);
        doubleHashing.put("A", 2);
        doubleHashing.put("M", 3);
        doubleHashing.put("P", 4);
        doubleHashing.put("L", 5);
        doubleHashing.put("Q", 6);
        doubleHashing.put("U", 7);
        doubleHashing.put("I", 8);
        doubleHashing.put("O", 9);
        doubleHashing.put("N", 10);

    }
}

