package Chapter1_3_BagsQueuesStacks;


import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/*
*   Josephus problem. In the Josephus problem from antiquity, N people are in dire straits and agree to the following strategy
*   to reduce the population. They arrange themselves in a circle(at positions numbered from 0 to N-1)and proceed around the
*   circle, eliminating every Mth person until only one person is left. Legend has it that Josephus figured out where to sit to
*   avoid being eliminated. Write a Queue client Josephus that takes N and M from the command line and prints out the order in
*   which people are eliminated(and thus would show Josephus where to site in the circle).
*
* */
public class Ex37_JosephusProblem {
    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < n - 1; i++){
            queue.enqueue(i);
        }
        while (!queue.isEmpty()){
            for (int i = 0; i < m - 1; i++){
                queue.enqueue(queue.dequeue());
            }
            StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println();
    }
}
