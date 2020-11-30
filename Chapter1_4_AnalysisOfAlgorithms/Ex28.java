package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/*
*   Stack with a queue. Implement a Chapter1_3_Bags_Queues_Stacks.stack with a single queue so that each Chapter1_3_Bags_Queues_Stacks.stack operations takes a linear number of
*   queue operations.
*       Hint: To delete an item, get all of the elements on the queue one at a time, and put them at the end, except
*       for the last one which you should delete and return. (This solution is admittedly very inefficient.)
*
* */
public class Ex28<Item> {
    private Queue<Item> queue;
    public Ex28(){
        queue = new Queue<>();
    }
    public boolean isEmpty(){return queue.isEmpty();}
    public int size(){return queue.size();}
    public void push(Item item){queue.enqueue(item);}
    public Item pop(){
        if (queue.isEmpty()){
            throw new RuntimeException("Stack Underflow");
        }
        int currentSize = size();
        for (int i = 0; i < currentSize; i++){
            queue.enqueue(queue.dequeue());
        }
        return queue.dequeue();
    }

   /* public String toString(){
        if (isEmpty())return "Empty queue";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" ");
        for (int i = 0; i < queue.size(); i++){
            stringBuilder.append(String.format(" %3s ",queue));
        }
        return stringBuilder.toString();
    }*/

    public static void main(String[] args) {
        Ex28<String> ex28 = new Ex28<>();
        StdOut.println("IsEmpty: " + ex28.isEmpty() + "? - Yes");
        ex28.push("A");
        ex28.push("B");
        ex28.push("C");
        StdOut.println(ex28.pop());
        StdOut.println(ex28.pop());
        ex28.push("E");
        ex28.push("F");
        StdOut.println("Size: " + ex28.size() + "? =3");
        StdOut.println("IsEmpty: " + ex28.isEmpty() + "? - No");
        StdOut.println("The sequence in Chapter1_3_Bags_Queues_Stacks.stack: F E A");
        StdOut.println(ex28.pop());
        ex28.pop();
        StdOut.println("Output elements from pop(): C B F E");
        StdOut.println("Size: " + ex28.size() + "? = 1");
    }
}
