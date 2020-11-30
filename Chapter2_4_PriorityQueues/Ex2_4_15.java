package Chapter2_4_PriorityQueues;

import edu.princeton.cs.algs4.StdOut;

/*
*   Design a linear-time certification algorithm to check whether an array pq[] is a min-oriented heap.
*
* */

@SuppressWarnings("unchecked")
public class Ex2_4_15 {
    public static boolean linearTimeCert(Comparable[] pq){
        int i = 1;
        while (i < pq.length) {
            int j = 2 * i;
            if (j < pq.length && pq[j].compareTo(pq[j + 1]) < 0) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Comparable[] pq1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Comparable[] pq2 = {10, 11, 9, -11, 25, 18, 88, 76, 20};
        StdOut.println("linearTimeCert for array pq1[]: " + linearTimeCert(pq1) + " is True.");
        StdOut.println("linearTimeCert for array pq2[]: " + linearTimeCert(pq2) + " is False.");
    }
}