package Test;

import edu.princeton.cs.algs4.StdOut;

public class testFloor {
    public static void main(String[] args) {
        double i =  Math.log(4);
        StdOut.println(Math.floor(i));
        int j = (int) Math.log(8);
        StdOut.println(j);
        double k = Math.ceil(Math.log(4));
        StdOut.println(k);
        double a = Math.log1p(4);
        StdOut.println(a);
    }
}
