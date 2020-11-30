package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.math.BigInteger;

/*
*   Modify ThreeSum to work properly even when the int values are so large that adding
*   two of them might cause overflow.
*
* */
public class Ex2 {
    public static int count(int[] array){
        int N = array.length;
        int cnt = 0;
        BigInteger bigInteger;

        for (int i = 0; i < N; i++){
            for (int j = i + 1; j < N; j++){
                for (int k = j + 1; k < N; k++){
                    bigInteger = BigInteger.valueOf(array[i]);
                    bigInteger = bigInteger.add(BigInteger.valueOf(array[j]));
                    bigInteger = bigInteger.add(BigInteger.valueOf(array[k]));
                    if (bigInteger.intValue() == 0){
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] array = in.readAllInts();
        StdOut.println(count(array));
    }
}
