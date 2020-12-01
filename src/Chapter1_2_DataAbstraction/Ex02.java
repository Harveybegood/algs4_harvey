package Chapter1_2_DataAbstraction;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Write an Interval1d client that takes an int value N as command-line argument, reads N intervals(each defined by
*   a pair of double values)from standard input, and prints all pairs that intersect.
*
* */
public class Ex02 {
    public static void main(String[] args) {
        //int N = Integer.parseInt(args[0]);
        int N = 10;
        Interval1D[] intervals = new Interval1D[N];
        for (int i = 0; i < N; i++){
            intervals[i] = new Interval1D(StdRandom.uniform(1.0, 20.0), StdRandom.uniform(20.0, 100.0));
        }
        for (int i = 0; i < N; i++){
            for (int j = i + 1; j < N; j++){
                if (intervals[i].intersects(intervals[j])){
                    StdOut.println(intervals[i] + " pairs " + intervals[j]);
                }
            }
        }
    }
}
