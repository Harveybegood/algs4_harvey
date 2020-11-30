package Chapter3_1_SymbolTables;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
*   Add code to FrequencyCounter to keep track of the last call to put(). Print the last word inserted and the number of
*   words that were processed in the input stream prior to this insertion. Run your program for tale.txt with length
*   cutoffs 1, 8 and 10.
*
* */
public class Ex09_LastCallToPut {
    public static void main(String[] args) {
        int number = 0;
        ST<String, Integer> st = new ST<>();
        int N = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()){
            String word = StdIn.readString();
            if (word.length() < N) continue;
            if (!st.contains(word)){
                st.put(word, 1);
                if (StdIn.isEmpty()){
                    StdOut.println("Last word inserted: " + word);
                    break;
                }
            }
            else {
                st.put(word, st.get(word) + 1);
                if (StdIn.isEmpty()){
                    StdOut.println("Last word inserted: " + word);
                    break;
                }
            }
        }
        for (String s : st.keys()){
            number += st.get(s);
        }
        StdOut.println("The number of words: " + (number - 1));
    }
}
