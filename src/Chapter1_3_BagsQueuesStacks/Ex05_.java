package Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Ex05_ {
    public static void main(String[] args) {
        int N = 50;
        Stack<Integer> stack = new Stack<>();

        while (N > 0){
            stack.push(N % 2);
            N = N / 2;
        }
        for (int d : stack){
            StdOut.print(d);
        }
        StdOut.println();
    }
}

// Convert decimal numbers to their binary representation