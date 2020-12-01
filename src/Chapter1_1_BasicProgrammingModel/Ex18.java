package Chapter1_1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

/*
*   Consider the following recursive function:
*   What are the values of mystery(2,25) and mystery(3,11)? Given positive integers a and b, describe what value
*   mystery(a,b) computes. Answer the same question, but replace + with* and replace return 0 with return 1.
* */
public class Ex18 {
    public static void main(String[] args) {
        StdOut.println(mystery(2, 25));
        StdOut.println(mystery(3, 11));
        StdOut.println(mystery1(2, 25));
        StdOut.println(mystery1(3, 11));
    }
    public static int mystery(int a, int b){
        if (b == 0) return 0;
        if (b % 2 == 0)
            return mystery(a+a, b/2);
        else
            return mystery(a+a, b/2) + a;
    }
    public static int mystery1(int a, int b){
        if (b == 0) return 1;
        if (b % 2 == 0)
            return mystery1(a*a, b/2);
        else
            return mystery1(a*a, b/2) * a;
    }
}

/*
*   multiplications
*
*   Power * power
*
*   50
    33
    33554432
    177147
*
* */