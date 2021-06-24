package Chapter3_4_HashTables;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Cuckoo hashing. Develop a symbol-table implementation that maintains two hash tables and two hash functions. Any given
*   key is in one of the tables, but not both. When inserting a new key, hash to one of the tables; if the table position
*   is occupied, replace that key with new key and hash the old key into the other table(again kicking out a key that might
*   reside there). If this process cycles, restart. Keep the tables less than half full. This method uses a constant number
*   of equality tests in the worst case for search(trivial) and amortized constant time for insert.
*
* */
@SuppressWarnings("unchecked")
public class Ex31_CuckooHashing<Key, Value> {
    private Key[] keys1;
    private Value[] values1;
    private Key[] keys2;
    private Value[] values2;
    private int MTable1;
    private int MTable2;
    private int NTable1;
    private int NTable2;

    public int hash1(Key key){
        return (key.hashCode() & 0x7fffffff) % MTable1;
    }
    public int hash2(Key key){
        return ((key.hashCode() * 3) & 0x7fffffff) % MTable2;
    }
    public boolean isEmpty(){return (NTable1 + NTable2) == 0;}
    public int size(){return NTable1 + NTable2;}
    public boolean isContains(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        return get(key) != null;
    }
    public Ex31_CuckooHashing(){
        this(5, 5);
    }
    public Ex31_CuckooHashing(int MTable1, int MTable2){
        this.MTable1 = MTable1;
        this.MTable2 = MTable2;
        keys1 = (Key[]) new Object[MTable1];
        keys2 = (Key[]) new Object[MTable2];
        values1 = (Value[]) new Object[MTable1];
        values2 = (Value[]) new Object[MTable2];
    }
    public void put(Key key, Value value){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        if (value == null){throw new IllegalArgumentException("Argument cannot be null");}

        // what about the key to be inserted that is already placed at one of the tables

        if (keys1[hash1(key)] != null && keys1[hash1(key)].equals(key)){
            values1[hash1(key)] = value;
            StdOut.println(keys1[hash1(key)] + " is duplicated.");
            return;
        }
        if (keys2[hash2(key)] != null &&keys2[hash2(key)].equals(key)){
            values2[hash2(key)] = value;
            StdOut.println(keys1[hash2(key)] + " is duplicated.");
            return;
        }

        if (NTable1 >= MTable1 / 2){
            resize(2 * MTable1);
        }
        if (NTable2 >= MTable2 / 2){
            resize(2 * MTable2);
        }
        // suppose that first hash table and its hash function would be primary choice.

        int i = hash1(key);
        if (keys1[i] == null){
            keys1[i] = key;
            values1[i] = value;
            NTable1++;
        }
        else {
            // the table position is occupied
            Key keyToOtherTable = keys1[i];
            Value valueToOtherTable = values1[i];
            keys1[i] = key;
            values1[i] = value;
            // the other table is also occupied
            int j = hash2(keyToOtherTable);
            if (keys2[j] == null){
                keys2[j] = keyToOtherTable;
                values2[j] = valueToOtherTable;
                NTable2++;
            }
            else {
                Key keyToTheTable = keys2[j];
                Value valueToTheTable = values2[j];
                int k = hash1(keyToTheTable);
                if (keys1[k] == null){
                    keys1[k] = keyToTheTable;
                    values1[k] = valueToTheTable;
                }
                else {
                    put(keyToTheTable, valueToTheTable);
                }
            }
        }
    }
    // I use a condition that will determine which one table to be executed
    public void resize(int newSize){
        Ex31_CuckooHashing<Key, Value> temp = new Ex31_CuckooHashing<>(newSize, newSize);
        if (NTable1 >= (MTable1 / 2) ){
            for (int i = 0; i < MTable1; i++){
                if (keys1[i] != null){
                    temp.put(keys1[i], values1[i]);
                }
            }
            this.MTable1 = temp.MTable1;
            this.NTable1 = temp.NTable1;
            this.keys1 = temp.keys1;
            this.values1 = temp.values1;
        }
        if (NTable2 >= (MTable2 / 2)){
            for (int i = 0; i < MTable2; i++){
                if (keys2[i] != null){
                    temp.put(keys2[i], values2[i]);
                }
            }
            this.MTable2 = temp.MTable2;
            this.NTable2 = temp.NTable2;
            this.keys2 = temp.keys2;
            this.values2 = temp.values2;
        }
    }
    public Value get(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        int i = hash1(key);
        int j = hash2(key);
        if (keys1[i] != null){
            return values1[i];
        }
        else if (keys2[j] != null){
            return values2[j];
        }
        return null;
    }
    public void delete(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        if (!isContains(key)){
            return;
        }
        int i = hash1(key);
        int j = hash2(key);
        if (keys1[i].equals(key)){
            keys1[i] = null;
            values1[i] = null;
            NTable1--;
            if (NTable1 <= MTable1 / 4){
                resize(MTable1 / 2);
            }
        }
        else {
            keys2[j] = null;
            keys2[j] = null;
            NTable2--;
            if (NTable2 <= MTable2 / 4){
                resize(MTable2 / 2);
            }
        }
    }
    public Iterable<Key> keys(){
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < MTable1; i++){
            if (keys1[i] != null){
                queue.enqueue(keys1[i]);
            }
        }
        for (int j = 0; j < MTable2; j++){
            if (keys2[j] != null){
                queue.enqueue(keys2[j]);
            }
        }
        return queue;
    }

    public static void main(String[] args) {
        Ex31_CuckooHashing<String, Integer> cuckooHashing = new Ex31_CuckooHashing<>();
        for (int i = 0; i < 30; i++){
            cuckooHashing.put(StdRandom.uniform(1, 20) + "E", StdRandom.uniform(1, 50));
        }
       /* cuckooHashing.put("E", 0);
        cuckooHashing.put("X", 1);
        cuckooHashing.put("A", 2);
        cuckooHashing.put("M", 3);
        cuckooHashing.put("P", 4);
        cuckooHashing.put("L", 5);
        cuckooHashing.put("Q", 6);
        cuckooHashing.put("U", 7);
        cuckooHashing.put("I", 8);
        cuckooHashing.put("O", 9);
        cuckooHashing.put("N", 10);*/
        StdOut.println("All items in the table");
        for (String s : cuckooHashing.keys()){
            StdOut.println(s + " " + cuckooHashing.get(s));
        }
        StdOut.println("Implementation of deleting items");
        for (String s : cuckooHashing.keys()){
            Integer item = Integer.parseInt(s.substring(0, 1));
            if ((item % 2) == 0){
                StdOut.println("Deleting " + s);
                cuckooHashing.delete(s);
            }
        }
        for (String s : cuckooHashing.keys()){
            StdOut.println(s + " " + cuckooHashing.get(s));
        }
    }
}
