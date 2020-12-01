package test;

import edu.princeton.cs.algs4.StdOut;

public class arcsin {
    public static void main(String[] args) {
       StdOut.println(Math.asin(-1) + " " + "-" + Math.PI / 2);
       StdOut.println(Math.sin(Math.asin(-1)) + " " + "-PI/2");
       StdOut.println(Math.toRadians(Math.asin(-1)));
       StdOut.println(Math.toDegrees(Math.asin(-1)));
    }
}
