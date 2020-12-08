package Test;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class zeroOneTwoForth {
    public static void main(String[] args) {
        int n = 10;
        int[] array = new int[n];
        int k = 0, s = 0;
        while (true){
            int i;
            for (i = s; i < (n - s + 1) / 2 + s; i++){
                array[i] = StdRandom.uniform(k, k + 1);
            }
            s = i;
            k++;
            //l = l / 2;
            if (i == n)
                break;
        }
        for (int x : array){
            StdOut.print(x + " ");
        }
    }
}
