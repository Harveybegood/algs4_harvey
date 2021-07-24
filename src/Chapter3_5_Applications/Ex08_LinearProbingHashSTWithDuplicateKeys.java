package Chapter3_5_Applications;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


/*
*   Modify LinearProbingHashST to keep duplicate keys in the table. Return any value associated with the given key for
*   get(), and remove all items in the table that have keys equal to the given key for delete();
*
* */
@SuppressWarnings("unchecked")
public class Ex08_LinearProbingHashSTWithDuplicateKeys<Key, Value> {
    private int M;
    private int N;
    private Key[] keys;
    private Value[] values;
    private final static int INIT_TABLE = 5;
    public Ex08_LinearProbingHashSTWithDuplicateKeys(){
        this(INIT_TABLE);
    }
    public Ex08_LinearProbingHashSTWithDuplicateKeys(int M){
        this.M = M;
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }
    public boolean isEmpty(){return N == 0;}
    public int size(){return N;}
    public boolean contains(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        return get(key) != null;
    }
    public int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % M;
    }
    public Value get(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        StringBuilder stringBuilder = new StringBuilder();
        //Value[] value = (Value[]) new Object();
        int i = hash(key);
        while (keys[i] != null){
            if (key.equals(keys[i])){
                stringBuilder.append(values[i]).append(" ");
            }
            i = (i + 1) % M;
            if (keys[i] == null){
                return (Value) stringBuilder;
            }
            //i = (i + 1) % M;
        }
        return null;
    }
    public void put(Key key, Value value){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        if (value == null){throw new IllegalArgumentException("Argument cannot be null");}
        if (N == M / 2){
            resize(2 * M);
        }
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M){
            if (key.equals(keys[i])){
                if (values[i] == value){
                    return;
                }
            }
        }
        keys[i] = key;
        values[i] = value;
        N++;
    }
    public void resize(int cap){
        Ex08_LinearProbingHashSTWithDuplicateKeys<Key, Value> temp = new Ex08_LinearProbingHashSTWithDuplicateKeys<>(cap);
        for (int i = 0; i < M; i++){
            if (keys[i] != null){
                temp.put(keys[i], values[i]);
            }
        }
        this.keys = temp.keys;
        this.values = temp.values;
        this.M = temp.M;
    }
    public void delete(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        if (isEmpty() || !contains(key)){throw new RuntimeException("Symbol table underflow");}
        int i = hash(key);
        while (keys[i] != key){
            i = (i + 1) % M;
        }
        while (keys[i] == key){
            keys[i] = null;
            values[i] = null;
            N--;
            i = (i + 1) % M;
        }
        i = (i + 1) % M;
        while (keys[i] != null){
            Key keyToRedo = keys[i];
            Value valueToRedo = values[i];
            put(keyToRedo, valueToRedo);
            i = (i + 1) % M;
        }
        if (N > 0 && N <= M / 4){
            resize(M / 2);
        }
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
        Ex08_LinearProbingHashSTWithDuplicateKeys<Integer, Integer> linearProbingHashSTWithDuplicateKeys
                = new Ex08_LinearProbingHashSTWithDuplicateKeys<>();
        linearProbingHashSTWithDuplicateKeys.put(1, 1);
        linearProbingHashSTWithDuplicateKeys.put(2, 2);
        linearProbingHashSTWithDuplicateKeys.put(3, 4);
        linearProbingHashSTWithDuplicateKeys.put(3, 5);
        linearProbingHashSTWithDuplicateKeys.put(4, 6);
        linearProbingHashSTWithDuplicateKeys.put(5, 7);
        StdOut.println("All keys in table");
        for (Integer number : linearProbingHashSTWithDuplicateKeys.keys()){
            StdOut.println(number + " " + linearProbingHashSTWithDuplicateKeys.get(number));
        }
        StdOut.println("The size of keys before deleting " + linearProbingHashSTWithDuplicateKeys.size() + " expected 6");
        linearProbingHashSTWithDuplicateKeys.delete(3);
        StdOut.println("All keys in table after deleting 3");
        for (Integer number : linearProbingHashSTWithDuplicateKeys.keys()){
            StdOut.println(number + " " + linearProbingHashSTWithDuplicateKeys.get(number));
        }
    }
}

/*
*               All keys in table
                1 1
                2 2
                3 4 5
                3 4 5
                4 6
                5 7
                The size of keys before deleting 6 expected 6
                All keys in table after deleting 3
                1 1
                2 2
                4 6
                5 7
*
* */

