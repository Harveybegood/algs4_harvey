package Chapter1_1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

/*
*   Criticize the following recursive function:
*
* */
public class Ex17 {
    public static void main(String[] args) {
        StdOut.print(exR2(6));
    }
    public static String exR2(int n){
        // The base case will never be reached, a call to exr1(n) will result in n = 3, 0, -3, -6, -9, ... until
        // StackOverflowError.
        String s = exR2(n-3) + n + exR2(n-2) + n;
        if (n <= 0)
            return "";
        return s;
    }
}
