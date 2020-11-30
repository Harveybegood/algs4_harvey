package Chapter3_1_SymbolTables;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class testClientST {
    public static void main(String[] args) {
        ST<String, Integer> st = new ST<>();
        for (int i = 0; !StdIn.isEmpty(); i++){
            st.put(StdIn.readString(), i);
        }
        for (String s : st.keys()){
            StdOut.println(s + " " + st.get(s));
        }
    }
}
