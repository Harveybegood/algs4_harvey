package test;

import edu.princeton.cs.algs4.StdOut;

public class Standardinput {
    public static void main(String[] args) {
        int[] w = {100000, 2, 3, 400000, 4, 1};
        //Standardinput s = new Standardinput();
        for (int i = 0; i < w.length; i++){
            if (w[i] > 10000){
                StdOut.println(w[i]);
            }
        }
    }
}
