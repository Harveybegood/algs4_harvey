package Chapter1_2_DataAbstraction;

import edu.princeton.cs.algs4.StdOut;

/*
*   What does the following code fragment print?
*   String string1 = "hello";
*
* */
public class Ex04 {
    public static void main(String[] args) {
        String string1 = "hello";  // an object of String with identity as string1 and assign the value of "hello"
        String string2 = string1;  // object string2 points to object string1
        string1 = "world";         // change the value of string1 to world, then string2 also changed accordingly.
        StdOut.println(string1 + " expected: " + "world");  // print value is world
        StdOut.println(string2 + " expected: " + "hello");  // a reference to object string1 not value.

        // primitive type
        int value1 = 1;
        int value2 = value1;
        value1 = 2;
        StdOut.println(value1 + " expected: " + 2);
        StdOut.println(value2 + " expected: " + 1);
    }
}
