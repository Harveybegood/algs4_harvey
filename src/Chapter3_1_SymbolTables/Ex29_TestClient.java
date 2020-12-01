package Chapter3_1_SymbolTables;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
*   Test client. Write a test client TestBinarySearch.java for use in testing the implementation of min(), max(), floor()
*   , ceiling(), select(), rank(), deleteMin(), deleteMax(), and keys() that are given in the text. Start with the
*   standard indexing client given on page 370. Add code to take additional command-line arguments, as appropriate.
*
* */
public class Ex29_TestClient {
    public static void main(String[] args) {
        ST<String, Integer> st = new ST<>();
        for (int i = 0; !StdIn.isEmpty(); i++){
            // build symbol table
            st.put(StdIn.readString(), i);
        }
        StdOut.println("print out all key-value pairs with method get()");
        for (String s : st.keys()){
            StdOut.println(s + "  " + st.get(s));
        }
        StdOut.println("testing min()");
        StdOut.println(st.min());
        StdOut.println("testing max()");
        StdOut.println(st.max());
        StdOut.println("testing floor(0");
        StdOut.println(st.floor("E"));
        StdOut.println("testing ceiling()");
        StdOut.println(st.ceiling("E"));
        StdOut.println("testing keys()");
        StdOut.println(st.keys());
    }
}
