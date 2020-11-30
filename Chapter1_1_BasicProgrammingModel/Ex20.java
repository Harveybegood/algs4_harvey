package Chapter1_1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

/*
*   Write a recursive static method that computes the value of ln(N!)
*
* */
public class Ex20 {
    public static void main(String[] args) {
        int N = 10;
        for (int i = 0; i < N; i++){
            StdOut.println("Integer " + i + ": " + computeLnN(i));
        }
    }
    private static int computeLnN(int n){
        if (n == 0)
            return 0;
        if (n == 1){
            return 1;
        }
        return computeLnN(n - 1) * n;
    }
}
