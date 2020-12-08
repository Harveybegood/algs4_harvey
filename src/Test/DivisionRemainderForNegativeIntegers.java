package Test;

import edu.princeton.cs.algs4.StdOut;

/*
*   What is the result of division and remainder for negative integers?
*
* */
public class DivisionRemainderForNegativeIntegers {
    // Firstly, to get those results by running a program to test
    public static void main(String[] args) {
        int a = -14;
        int b = 14;
        int c = -3;
        int d = 3;
        StdOut.println("The result for division: " + a / d);
        StdOut.println("The result for division: " + b / c);
        StdOut.println("The result for remainder: " + a % d);
        StdOut.println("The result for remainder: " + b % c);
    }
    /*
    *
    *
    *       The result for division: -4
            The result for division: -4
            The result for remainder: -2
            The result for remainder: 2
    *
    *
    *       why in the book says that the quotient a/b rounds toward 0;
    *
    *
    *
    *
    * */
}
