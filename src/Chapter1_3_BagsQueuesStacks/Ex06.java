package Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/*
*   What does the following code fragment do to the queue q?
*
* */
public class Ex06 {
    public static void main(String[] args) {
        Queue<String> q = new Queue<>();
        q.enqueue("a");
        q.enqueue("b");
        q.enqueue("c");
        for (String s : q){
            StdOut.print(s + " ");
        }
        Stack<String> stack = new Stack<>();
        while (!q.isEmpty()){
            stack.push(q.dequeue());
        }
        while (!stack.isEmpty()){
            q.enqueue(stack.pop());
        }
        StdOut.print(" Expected opposite ");
        for (String s : q){
            StdOut.print(s + " ");
        }
    }
}
