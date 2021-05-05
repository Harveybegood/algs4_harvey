package Chapter3_4_HashTables;

import edu.princeton.cs.algs4.StdOut;

/*
*   Implement keys() for SeparateChainingHashST and LinearProbingHashST.
*
* */
// keys() method that was already implemented in another exercise, so here this exercise is only adding keys() for Linear..
public class Ex19_ImplementKeysMethod<Key, Value> extends LinearProbingHashST<Key, Value>{
    public static void main(String[] args) {
        LinearProbingHashST<String, Integer> linearProbingHashST = new LinearProbingHashST<>(16);
        linearProbingHashST.put("S", 0);
        linearProbingHashST.put("E", 1);
        linearProbingHashST.put("A", 2);
        linearProbingHashST.put("R", 3);
        linearProbingHashST.put("C", 4);
        linearProbingHashST.put("H", 5);
        linearProbingHashST.put("E", 6);
        linearProbingHashST.put("X", 7);
        linearProbingHashST.put("A", 8);
        linearProbingHashST.put("M", 9);
        linearProbingHashST.put("P", 10);
        linearProbingHashST.put("L", 11);
        linearProbingHashST.put("E", 12);
        linearProbingHashST.put("S", 13);
        for (String s : linearProbingHashST.keys()){
            StdOut.println(s + " - " +linearProbingHashST.get(s));
        }
    }
}
