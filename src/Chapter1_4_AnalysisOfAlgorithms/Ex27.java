package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/*
*   Queue with two stacks. Implement a queue with two stacks so that each queue operation takes a constant amortized
*   number of Chapter1_3_Bags_Queues_Stacks.stack operations.
*       Hint: If you push elements onto a Chapter1_3_Bags_Queues_Stacks.stack and then pop them all, they appear in reverse order. If you repeat
*       this process, they're now back in order.
*
* */
public class Ex27<Item> {
    private Stack<Item> tailStack;
    private Stack<Item> headStack;
    public Ex27(){
        tailStack = new Stack<>();
        headStack = new Stack<>();
    }
    public int size(){
        return headStack.size() + tailStack.size();
    }
    public boolean isEmpty(){
        return headStack.isEmpty() && tailStack.isEmpty();
    }
    public void enqueue(Item item){
        tailStack.push(item);
    }
    public Item dequeue(){
        if (headStack.isEmpty()){
            moveAllItemsFromTailToHead();
        }
        return headStack.pop();
    }
    private void moveAllItemsFromTailToHead(){
        while (!tailStack.isEmpty()){
            headStack.push(tailStack.pop());
        }
    }

    public static void main(String[] args) {
        Ex27<String> ex27 = new Ex27<>();
        StdOut.println("IsEmpty: " + ex27.isEmpty() + " Expected: true");
        StdOut.println("Size: " + ex27.size() + " Expected: 0");
        ex27.enqueue("A");
        ex27.enqueue("B");
        StdOut.println(ex27.dequeue());
        StdOut.println(ex27.dequeue());
        ex27.enqueue("C");
        ex27.enqueue("D");
        ex27.enqueue("E");
        ex27.enqueue("F");
        StdOut.println("Size: " + ex27.size() + " Expected: 4");
        StdOut.println(ex27.dequeue());
        StdOut.println(ex27.dequeue());
        StdOut.println("Expected output from dequeue(): A B C D");
        StdOut.println("IsEmpty: " + ex27.isEmpty() + " Expected: false");
        StdOut.println("Size: " + ex27.size() + " Expected: 2");
    }
}
