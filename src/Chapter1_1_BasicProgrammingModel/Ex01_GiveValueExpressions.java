package Chapter1_1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

/*
*   Give the value of each of the following expressions:
*
* */
public class Ex01_GiveValueExpressions {
    public static void main(String[] args) {
        int a = (0 + 15) / 2;
        // "/" denotes divide, in this expression which will retrieve not the remainder but quotient.
        double b = 2.0E-6 * 100000000.1;
        // E or e denotes a kind of scientific notation which become more simpler to handle either very large or very small numbers.
        boolean c = true && false || true && true;
        // The point for this is to consider the precedence of logic operations. ! denotes NOT && denotes AND, || denotes OR,
        StdOut.println(a + " expected 7");
        StdOut.println(b + " expected 200.0000001");
        StdOut.println(c + " expected true");
    }
}
