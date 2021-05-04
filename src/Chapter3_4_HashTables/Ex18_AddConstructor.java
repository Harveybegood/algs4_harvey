package Chapter3_4_HashTables;

import edu.princeton.cs.algs4.StdOut;

/*
*   Add a constructor to SeparateChainingHashST that gives the client the ability to specify the average number of probes
*   to be tolerated for searches. Use array resizing to keep the average list size less than the specified value, and use
*   the technique described on page 478 to ensure that the modules for hash() is prime.
*
* */
public class Ex18_AddConstructor<Key, Value> extends SeparateChainingHashST<Key, Value> {
    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> separateChainingHashST = new SeparateChainingHashST<>(20, 5);
        separateChainingHashST.put("E", 1);
        separateChainingHashST.put("A", 2);
        separateChainingHashST.put("S", 3);
        separateChainingHashST.put("Y", 4);
        separateChainingHashST.put("Q", 5);
        separateChainingHashST.put("U", 6);
        separateChainingHashST.put("T", 7);
        separateChainingHashST.put("I", 8);
        separateChainingHashST.put("O", 9);
        separateChainingHashST.put("N", 10);
        StdOut.println("The initial sequence of key-value pairs");
        for (String s : separateChainingHashST.keys()){
            StdOut.println(s + " : " + separateChainingHashST.get(s));
        }
        StdOut.println("The sequence of key-value pairs after deleting a key");
        separateChainingHashST.delete("A");
        for (String s : separateChainingHashST.keys()){
            StdOut.println(s + " : " + separateChainingHashST.get(s));
        }
    }

}
