package Chapter3_4_HashTables;

import edu.princeton.cs.algs4.StdOut;

/*
*   Give the contents of a linear-probing hash table that results when you insert the keys E A S Y Q U T I O N in that order
*   into an initially empty table of initial size M = 4 that is expanded with doubling whenever half full. Use the hash function
*   11 k % M to transform the kth letter of the alphabet into a table index.
*
* */
public class Ex11_GiveResultsHashTableM4 extends Ex10_InsertKeysWithLinearProbing {
    public static void main(String[] args) {
        Ex10_InsertKeysWithLinearProbing<String, Integer> linearProbing = new Ex10_InsertKeysWithLinearProbing<>(4);
        linearProbing.put("E", 0);
        linearProbing.put("A", 1);
        linearProbing.put("S", 2);
        linearProbing.put("Y", 3);
        linearProbing.put("Q", 4);
        linearProbing.put("U", 5);
        linearProbing.put("T", 6);
        linearProbing.put("I", 7);
        linearProbing.put("O", 8);
        linearProbing.put("N", 9);
        int i = 0;
        for (String s : linearProbing.keys()){
            StdOut.println(i + " " + s);
            i++;
        }
    }

    /*
    *   0 null
        1 null
        2 null
        3 I
        4 null
        5 O
        6 null
        7 U
        8 null
        9 null
        10 null
        11 A
        12 null
        13 null
        14 null
        15 null
        16 null
        17 S
        18 null
        19 Y
        20 null
        21 null
        22 null
        23 E
        24 null
        25 null
        26 N
        27 Q
        28 T
        29 null
        30 null
        31 null
    */

}
