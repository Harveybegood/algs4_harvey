package Chapter3_1_SymbolTables;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**/
public class frequencyCounter {
    // counts the frequency of occurrence of the strings
    // prints out one that occurs with highest frequency
    public static void main(String[] args) {
        int minLen = Integer.parseInt(args[0]);
        ST<String, Integer> st = new ST<>();
        // Build the symbol table and count frequencies.
        while (!StdIn.isEmpty()){
            String word = StdIn.readString();
            StdOut.println(word);
            if (word.length() < minLen) continue;
            if (!st.contains(word)) st.put(word, 1);
            else st.put(word, st.get(word) + 1);
        }
        StdOut.println();

        // Find a key with the highest frequency count.
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()){
            if (st.get(word) > st.get(max)){
                max = word;
            }
        }
        StdOut.println(max + " " + st.get(max));
    }
}
