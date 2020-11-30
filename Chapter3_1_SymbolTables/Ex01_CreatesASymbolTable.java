package Chapter3_1_SymbolTables;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


/*
*   Write a client that creates a symbol table mapping letter grades to numerical scores, as in the table below, then
*   reads from standard input a list of letter grades and computes and prints the GPA
*   (the average of the numbers corresponding to the grades)
*   A+      A       A-      B+       B        B-      C+      C       C-      D       F
*   4.33    4.00    3.67    3.33     3.00     2.67    2.33    2.00    1.67    1.00    0.00
*
* */
public class Ex01_CreatesASymbolTable{
    public static void main(String[] args) {
        ST<String, Double> st = new ST<>();
        while (!StdIn.isEmpty()){
            st.put(StdIn.readString(), StdIn.readDouble());
        }
        for (String s : st.keys()){
            StdOut.printf("%3s %3s", s, "|");
        }
        StdOut.println();
        for (String s : st.keys()){
            StdOut.printf("%1.2f %2s", st.get(s), "|");
        }
        StdOut.println();
    }
}
