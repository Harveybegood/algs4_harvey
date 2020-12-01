package test;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class testReadAllInts {
    public static void main(String[] args) {
        int[] array = StdIn.readAllInts();
        for (int i = 0; i < array.length; i++){
            StdOut.println(array[i]);
        }
    }
}
