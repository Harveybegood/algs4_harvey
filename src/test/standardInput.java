package test;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

public class standardInput {
    public static void main(String[] args) {
        Queue<Transaction> queue = new Queue<>();
        while (StdIn.hasNextLine()){
            queue.enqueue(new Transaction(StdIn.readLine()));
        }
        while (!queue.isEmpty()){
            StdOut.println(queue.dequeue());
        }
        int[] p = StdIn.readAllInts();
    }
}
