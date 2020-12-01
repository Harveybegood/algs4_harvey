package test;

import edu.princeton.cs.algs4.StdOut;


public class testReturn {
    private static int total;
    private static int sum(){
        for (int i = 0; i < 100; i++){
            total += i;
        }
        return total;
    }

    public static void main(String[] args) {
        StdOut.print(sum());
    }
}
