package Chapter3_1_SymbolTables;
/*
*   Ordered insertion. Modify BinarySearchST so that inserting a key that is larger than all keys in the table takes
*   constant time(so that building a table by calling put() for keys that are in order takes linear time).
*
* */
public class Ex28_OrderedInsertion {
    Integer[] keys = new Integer[2];
    String[] values = new String[2];
    int n;
    binarySearchST<Integer, String> binarySearchST = new binarySearchST<>();
    public void put(Integer key, String value){
        if (key == null) throw new IllegalArgumentException("Argument for put() is null");
        if (value == null){
            binarySearchST.delete(key);
            return;
        }
        if (n == keys.length / 2){
            binarySearchST.resize(keys.length * 2);
        }
        // if the key inserted is larger than the last key in the table
        if (key.compareTo(keys[n-1]) > 0){
            keys[n] = key;
            values[n] = value;
        }
        int i = binarySearchST.rank(key);
        if (keys[i].compareTo(key) == 0){
            values[i] = value;
            return;
        }
        for (int j = binarySearchST.size(); j > i; j--){
            keys[j] = keys[j-1];
            values[j] = values[j-1];
        }
        keys[i] = key;
        values[i] = value;
        n++;
    }
}
