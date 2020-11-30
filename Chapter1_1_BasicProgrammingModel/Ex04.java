package Chapter1_1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

/*
*   What(if anything) is wrong with each of the following statements?
*
*       if(a > b) then c = 0;
*       if a > b {c = 0;}
*       if (a > b) c = 0;
*       if (a > b) c = 0 else b = 0;
*
* */
public class Ex04 {
    public static void main(String[] args) {
        int a = 2, b = 1;
        int c = 1;
        StdOut.println("can't resolve this symbol");
        if (a > b) {c = 0;}
        StdOut.println(c + " expected with 0");
        StdOut.println("Correct statement.");
        if (a > b) c = 0;
        StdOut.println(c + " expected with 0");
        StdOut.println("Correct statement.");
        if (a > b) c = 0;
        else b = 0;
        StdOut.println(";" + " missing");
    }
}
