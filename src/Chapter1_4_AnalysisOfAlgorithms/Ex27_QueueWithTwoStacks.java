package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/*
*   Queue with two stacks. Implement a queue with two stacks so that each queue operation takes a constant amortized number of
*   stack operations. Hint: if you push elements onto a stack and then pop them all, they appear in reverse order. If you repeat
*   this process, they're now back in order.
*
* */
public class Ex27_QueueWithTwoStacks<Item> {
    Stack<Item> stack1 = new Stack<>();
    Stack<Item> stack2 = new Stack<>();
    private int n;
    private Ex27_QueueWithTwoStacks(){}
    public boolean isEmpty(){return n == 0;}
    public int size(){return n;}
    public void enqueue(Item item){
        if (item == null){throw new IllegalStateException("Argument Item cannot be null");}
        if (stack1.isEmpty()){
            stack1.push(item);
        }
        else {
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
            stack1.push(item);
            while (!stack2.isEmpty()){
                stack1.push(stack2.pop());
            }
        }
        n++;
    }
    public Item dequeue(){
        if (stack1.isEmpty()){return null;}
        Item item;
        item = stack1.pop();
        n--;
        return item;
    }

    public static void main(String[] args) {
        Ex27_QueueWithTwoStacks<Integer> queueWithTwoStacks = new Ex27_QueueWithTwoStacks<>();
        queueWithTwoStacks.enqueue(1);
        queueWithTwoStacks.enqueue(2);
        queueWithTwoStacks.enqueue(3);
        StdOut.println("Expecting 1 - " + queueWithTwoStacks.dequeue());
        StdOut.println("Expecting 2 - " + queueWithTwoStacks.dequeue());
        StdOut.println("Expecting 3 - " + queueWithTwoStacks.dequeue());
    }
}

/*
*       stack1      stack2
*
*   1
*   2
*         1
*         2
*   3                   2
*                       1
*         1
*         2
*         3
*
* */