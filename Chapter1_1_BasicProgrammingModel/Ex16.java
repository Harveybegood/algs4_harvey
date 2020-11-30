package Chapter1_1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

/*
*   Give the value  of exR1(6):
*
* */
public class Ex16 {
    public static void main(String[] args) {
        StdOut.println(exR1(6));
        //exR1(6);
    }
    public static String exR1(int n){
        if (n <= 0){
            return " - ";
        }
        //StdOut.println(exR1(n-3) + n + exR1(n-2) + n);
        //StdOut.println(n);
        //StdOut.println();
        return exR1(n-3) + n + exR1(n-2) + n;
    }
}
