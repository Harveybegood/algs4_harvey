package Chapter3_5_Applications;

import edu.princeton.cs.algs4.*;

/*
*   Inverted concordance. Write a program InvertedConcordance that takes a concordance on standard input and puts the original string
*   on standard output stream. Note: This computation is associated with a famous story having to do with the Dead Sea Scrolls. The team
*   that discovered the original tablets enforced a secrecy rule that essentially resulted in their making public only a concordance.
*   After a while, other researchers figured out how to invert the concordance, and the full text was eventually made public.
*
* */
public class Ex21_InvertedConcordance {
    int numberOfWords = 0;
    public ST<String, Queue<Integer>> readConcordanceStandardInput(){
        ST<String, Queue<Integer>> st = new ST<>();
        while (StdIn.hasNextLine()){
            String[] concordance = StdIn.readLine().split(" ");
            String key = concordance[0];
            for (int i = 1; i < concordance.length; i++){
                int val = Integer.parseInt(concordance[i]);
                if (!st.contains(key)){
                    st.put(key, new Queue<>());
                }
                st.get(key).enqueue(val);
                if (val > numberOfWords){
                    numberOfWords = val;
                }
            }
        }
        return st;
    }
    public void outputOriginalString(ST<String, Queue<Integer>> st){
        String[] words = new String[numberOfWords + 1];
        for (String s : st.keys()){
            for (Integer i : st.get(s)){
                words[i] = s;
            }
        }
        for (String s : words){
            StdOut.print(s + " ");
        }
    }

    public static void main(String[] args) {
        Ex21_InvertedConcordance invertedConcordance = new Ex21_InvertedConcordance();
        ST<String, Queue<Integer>> st = invertedConcordance.readConcordanceStandardInput();
        invertedConcordance.outputOriginalString(st);
    }
}

/*
*           a 2
            client 12
            concordance 3 13
            in 7
            input 10
            is 1
            of 4
            on 16
            output 18
            puts 15
            standard 9 17
            stream 11
            strings 6
            that 14
            the 5 8
            this 0
            this is a concordance of the strings in the standard input stream client concordance that puts on standard output
*
* */