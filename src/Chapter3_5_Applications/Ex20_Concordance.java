package Chapter3_5_Applications;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.FileOutputStream;

/*
*   Concordance. Write an ST client Concordance that puts on standard output a concordance of the strings in the standard
*   input stream(see page 498).
*   Concordance:
*   Associate each word in a text with the set of position in the text where that word occurs.
*
* */
public class Ex20_Concordance {
    public ST<String, Queue<Integer>> readAndBuildConcordance(){
        ST<String, Queue<Integer>> st = new ST<>();
        int pageIndex = 0;
        while (StdIn.hasNextLine()){
            String[] words = StdIn.readLine().split(" ");
            for (String word : words){
                if (!st.contains(word)){
                    st.put(word, new Queue<>());
                }
                st.get(word).enqueue(pageIndex);
                pageIndex++;
            }
        }
        return st;
    }
    public void outputConcordance(ST<String, Queue<Integer>> st){
        for (String word : st.keys()){
            StdOut.print(word + "  " + st.get(word));
            StdOut.println();
        }
    }

    public static void main(String[] args) {
        Ex20_Concordance concordance = new Ex20_Concordance();
        ST<String, Queue<Integer>> st = concordance.readAndBuildConcordance();
        concordance.outputConcordance(st);
    }
}

/*
*   this is a concordance of the strings in the standard input stream
    client concordance that puts on standard output
    a  2
    client  12
    concordance  3 13
    in  7
    input  10
    is  1
    of  4
    on  16
    output  18
    puts  15
    standard  9 17
    stream  11
    strings  6
    that  14
    the  5 8
    this  0
*
*
*
* */
