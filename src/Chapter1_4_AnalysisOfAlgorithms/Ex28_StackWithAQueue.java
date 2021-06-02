package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/*
*   Stack with a queue. Implement a stack with a single queue so that each stack operations takes a linear number of queue operations.
*   Hint: To delete an item, get all of the elements on the queue one at a time, and put them at the end, except for the last one which
*   you should delete and return. (This solution is admittedly very inefficient.)
*
* */
public class Ex28_StackWithAQueue<Item> {
    private int n;
    public Ex28_StackWithAQueue(){}
    Queue<Item> queue = new Queue<>();
    public boolean isEmpty(){return queue.size() == 0;}
    public int size(){return queue.size();}
    public void push(Item item){
        queue.enqueue(item);
    }
    public Item pop(){
        //int size = queue.size();
        if (queue.isEmpty()){throw new RuntimeException("Stack under flow");}
        for (int i = 0; i < queue.size() - 1; i++){
            queue.enqueue(queue.dequeue());
        }
        return queue.dequeue();
    }

    public static void main(String[] args) {
        Ex28_StackWithAQueue<Integer> stackWithAQueue = new Ex28_StackWithAQueue<>();
        stackWithAQueue.push(1);
        stackWithAQueue.push(2);
        stackWithAQueue.push(3);
        StdOut.println(stackWithAQueue.pop());
        StdOut.println(stackWithAQueue.pop());
    }
}
