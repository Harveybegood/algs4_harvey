package Chapter3_4_HashTables;

import Chapter3_1_SymbolTables.SequentialSearchST;
import edu.princeton.cs.algs4.StdOut;

/*
*   Insert the keys E A S Y Q U T I O N in that order into an initially empty table of M = 5 lists, using separate chaining.
*   Use the hash function 11 k % M to transform the kth letter of the alphabet into a table index.
*
* */
@SuppressWarnings("unchecked")
public class Ex01_InsertKeys<Key, Value> {
    private int N;
    private int M = 5;
    private SequentialSearchST<Key, Value>[] st;
    public Ex01_InsertKeys(){
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++){
            st[i] = new SequentialSearchST<>();
        }
    }
    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public static void main(String[] args) {
        Ex01_InsertKeys<String, Integer> insertKeys = new Ex01_InsertKeys<>();
        StdOut.println(insertKeys.hash("E"));   // 4
        StdOut.println(insertKeys.hash("A"));   // 0
        StdOut.println(insertKeys.hash("S"));   // 3
        StdOut.println(insertKeys.hash("Y"));   // 4
        StdOut.println(insertKeys.hash("Q"));   // 1
        StdOut.println(insertKeys.hash("U"));   // 0
        StdOut.println(insertKeys.hash("T"));   // 4
        StdOut.println(insertKeys.hash("I"));   // 3
        StdOut.println(insertKeys.hash("O"));   // 4
        StdOut.println(insertKeys.hash("N"));   // 3
    }
    /*
    *
    *                   key     hash    value
    *
    *                   E       4       0
    *                   A       0       1
    *                   S       3       2       st
    *                   Y       4       3       0           A|1     U|5
    *                   Q       1       4       1           Q|4
    *                   U       0       5       2           null
    *                   T       4       6       3           S|2     I|7     N|9
    *                   I       3       7       4           E|0     Y|3     T|6     O|8
    *                   O       4       8
    *                   N       3       9
    *
    *
    *
    *
    *
    * */
}
