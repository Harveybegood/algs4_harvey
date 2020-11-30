package Chapter1_2_DataAbstraction;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/*
*   File input. Develop a possible implementation of the static readInts() method from In(which we use for various
*   test clients, such as binary search on page47) that is based on the split() method in String.
*
* */
public class Ex15 {
    private static final String FilePath = Tool.Constants.FILE_PATH + "number1.txt";
    public static int[] readInts(String name){
        In in = new In(name);
        //in.readInts();
        String input = in.readAll();
        String[] words = input.split("\\s+");
        int[] ints = new int[words.length];
        for (int i = 0; i < words.length; i++){
            ints[i] = Integer.parseInt(words[i]);
        }
        return ints;
    }

    public static void main(String[] args) {
        //String name = ../algs4-data/ + "ex01.txt";
        int[] a = readInts("/usr/local/algs4/src/algs4-data/number1.txt");
        for (int i = 0; i < a.length; i++){
            StdOut.print(a[i] + " ");
        }
    }
}
