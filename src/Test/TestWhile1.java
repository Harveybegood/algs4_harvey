package Test;

import edu.princeton.cs.algs4.StdOut;

public class TestWhile1 {

    public static void main(String[] args) {
        boolean cmp;
        int[] test = {1, 2, 4, 5, 10, 30, 34, 41, 67, 98};
        int i = 70;
        int j = 0;
        cmp = i > test[j];
        if (cmp){
            j = j + 1;
            StdOut.print("True" + " ");
        }
        int[] arrayA = new int[]{5, 10, 15};
    }
}
