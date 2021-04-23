package Chapter3_4_HashTables;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/*
*   Insert the keys E A S Y Q U T I O N in that order into an initially empty table of size M = 16 using linear probing.
*   Use the hash function 11 k % M to transform the kth letter of the alphabet into a table index. Redo this exercise for M = 10.
* 
* */
@SuppressWarnings("unchecked")
public class Ex10_InsertKeysWithLinearProbing<Key, Value> {
    private Key[] keys;
    private Value[] values;
    private int M;
    private int n;
    public Ex10_InsertKeysWithLinearProbing(int M){
        this.M = M;
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }
    public Ex10_InsertKeysWithLinearProbing(){}
    // M that will be initialized in the phase of constructing ADT;
    public int hash(Key key){
        return 11 * key.hashCode() % M;
    }
    // while M = 10
    /*public int hashWithM10(Key key){
        return 11 * key.hashCode() % M;
    }*/
    public boolean isEmpty(){return n == 0;}
    public int size(){return n;}
    public boolean contains(Key key){
        return get(key) != null;
    }
    public void put(Key key, Value value){
        if (key == null || value == null){throw new IllegalArgumentException("Argument key cannot be null");}
        if (n > 0.75 * M){resize(2 * M);}
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M){
            if (keys[i].equals(key)){
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;
        n++;
    }
    public void resize(int cap){
        Ex10_InsertKeysWithLinearProbing<Key, Value> insertKeysWithLinearProbing = new Ex10_InsertKeysWithLinearProbing<>(cap);
        for (int i = 0; i < M; i++){
            if (keys[i] != null){
                insertKeysWithLinearProbing.put(keys[i], values[i]);
            }
        }
        keys = insertKeysWithLinearProbing.keys;
        values = insertKeysWithLinearProbing.values;
        M = insertKeysWithLinearProbing.M;
    }
    public Value get(Key key){
        if (key == null){throw new IllegalArgumentException("Argument key cannot be null");}
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M){
            if (keys[i].equals(key)){
                return values[i];
            }
        }
        return null;
    }
    public Iterable<Key> keys(){
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < M; i++){
            //if (keys[i] != null){
                queue.enqueue(keys[i]);
            //}
        }
        return queue;
    }
    public static void main(String[] args) {
        Ex10_InsertKeysWithLinearProbing<String, Integer> insertKeysWithLinearProbing1 = new Ex10_InsertKeysWithLinearProbing<>(16);
        Ex10_InsertKeysWithLinearProbing<String, Integer> insertKeysWithLinearProbing2 = new Ex10_InsertKeysWithLinearProbing<>(10);
        insertKeysWithLinearProbing1.put("E", 0);
        insertKeysWithLinearProbing1.put("A", 1);
        insertKeysWithLinearProbing1.put("S", 2);
        insertKeysWithLinearProbing1.put("Y", 3);
        insertKeysWithLinearProbing1.put("Q", 4);
        insertKeysWithLinearProbing1.put("U", 5);
        insertKeysWithLinearProbing1.put("T", 6);
        insertKeysWithLinearProbing1.put("I", 7);
        insertKeysWithLinearProbing1.put("O", 9);
        insertKeysWithLinearProbing1.put("N", 10);
        insertKeysWithLinearProbing2.put("E", 0);
        insertKeysWithLinearProbing2.put("A", 1);
        insertKeysWithLinearProbing2.put("S", 2);
        insertKeysWithLinearProbing2.put("Y", 3);
        insertKeysWithLinearProbing2.put("Q", 4);
        insertKeysWithLinearProbing2.put("U", 5);
        insertKeysWithLinearProbing2.put("T", 6);
        insertKeysWithLinearProbing2.put("I", 7);
        insertKeysWithLinearProbing2.put("O", 9);
        insertKeysWithLinearProbing2.put("N", 10);
        StdOut.println("Sequence with M 16");
        for (String s : insertKeysWithLinearProbing1.keys()){
            StdOut.print(s + " ");
        }
        StdOut.println("\nSequence with M 10");
        for (String s : insertKeysWithLinearProbing2.keys()){
            StdOut.print(s + " ");
        }
    }
}
