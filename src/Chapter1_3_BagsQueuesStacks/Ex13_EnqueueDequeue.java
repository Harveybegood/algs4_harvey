package Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/*
*   Suppose that a client performs an intermixed sequence of (queue) enqueue and dequeue operations. The enqueue operations
*   put the integer 0 through 9 in order onto the queue; the dequeue operations print out the return value. Which of the 
*   following sequence(s) could not occur?
* 
* */
public class Ex13_EnqueueDequeue {
    /*
    * 
    *       a. 0 1 2 3 4 5 6 7 8 9 --> FIFO Okay
    * 
    *       b. 4 6 8 7 5 3 2 9 0 1 --> not occur
    * 
    *       c. 2 5 6 7 4 8 9 3 1 0 --> not occur
    * 
    *       d. 4 3 2 1 0 5 6 7 8 9 --> not occur
    * 
    * 
    * 
    * 
    * 
    * */

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        for (int i = 0; i < 10; i++){
            StdOut.print(queue.dequeue());
        }
    }
}
