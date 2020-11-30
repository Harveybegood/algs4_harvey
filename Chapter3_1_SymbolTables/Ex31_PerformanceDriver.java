package Chapter3_1_SymbolTables;

import edu.princeton.cs.algs4.StdRandom;


/*
*   Performance driver. Write a performance driver program that uses put() to fill a symbol table, then uses get() such
*   that each key in the table is hit an average of ten times and there is about the same number of misses, doing so
*   multiple times on random sequences of string keys of various lengths ranging from 2 to 50 characters; measures the
*   time taken for each run; and prints out or plots the average running times.
*
* */
public class Ex31_PerformanceDriver {
    // generate random characters with length ranging from 2 to 50
    String t = "f2s1w3f5klj4rf5jf7d4so7u5fi6a9f29g3hs0l890afsklfm23r1f29nfdk3d0d1f9h";
    public String generateRandomChars(int minLen, int maxLen){
        int n = StdRandom.uniform(minLen, maxLen);
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(t, n, 50).toString();
    }
    // generate array containing these random characters
    public String[] generateArray(){
        String[] s = new String[1000];
        for (int i = 0; i < 100; i++){
            s[i] = generateRandomChars(2, 49);
        }
        return s;
    }
    // build up a symbol table
    binarySearchST<String, Integer> binarySearchST = new binarySearchST<>(10);
    public void buildST(){
        String[] s = generateArray();
        for (int i = 0; i < s.length; i++){
            binarySearchST.put(s[i], i);
        }
    }
}
