package Chapter3_1_SymbolTables;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
*   What is the most frequently used word of ten letters or more in TaleOfTwo Cities?
*
* */
public class Ex08_equalOrGreater10Letters {
    public static void main(String[] args) {
        int N = 10;
        ST<String, Integer> st = new ST<>();
        while (!StdIn.isEmpty()){
            String word = StdIn.readString();
            if (word.length() < N) continue;
            if (!st.contains(word))
                st.put(word, 1);
            else
                st.put(word, st.get(word) + 1);
        }
        String max = " ";
        st.put(max, 0);
        for (String word : st.keys()){
            if (st.get(word) > st.get(max)){
                max = word;
            }
        }
        StdOut.println(max + " " + st.get(max));
    }
}
