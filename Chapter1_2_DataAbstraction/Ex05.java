package Chapter1_2_DataAbstraction;

import edu.princeton.cs.algs4.StdOut;

/*
*   What does the following code fragment print?
*
* */
public class Ex05 {
    public static void main(String[] args) {
        String s = "Hello World";
        StdOut.println(s.toUpperCase()+" expected: " + "HELLO WORLD");
        StdOut.println(s.substring(6,11) + " expected: " + "World");
        StdOut.println(s + " expected: Hello World");
    }
    // String objects are immutable, string methods return a new String object
}
