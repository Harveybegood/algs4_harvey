package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/*
*   Steque with two stacks. Implement a steque with two stacks so that each steque operation(see ex1.3.32)takes a constant amortized
*   number of stack operations.
*
*   Steque. A stack-ended queue or steque is a data type that supports push, pop, and
    enqueue. Articulate an API for this ADT. Develop a linked-list-based implementation.
*
*   note: Push and pop operate on the same end of queue. enqueue and push are alike, but operate on the each end of the queue.
*
*
* */
public class Ex29_StequeWithTwoStacks<Item> {
    Stack<Item> stack1;
    Stack<Item> stack2;
    public Ex29_StequeWithTwoStacks(){
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }
    //public int n;
    //public boolean isEmpty(){return stack1.isEmpty();}
    //public int size(){return stack1.size();}
    // pop items from the top of a stack not from the bottom
    public Item pop(){
        if (stack1.isEmpty()){
            throw new RuntimeException("Stack under flow");
        }
        return stack1.pop();
    }
    // push item to the top of a stack
    public void push(Item item){
        stack1.push(item);
    }
    // enqueue item to the bottom of a stack
    public void enqueue(Item item){
        if (item == null){throw new IllegalStateException("Argument cannot be null");}
        while (!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        stack1.push(item);
        while (!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
    }

    public static void main(String[] args) {
        Ex29_StequeWithTwoStacks<Integer> stequeWithTwoStacks = new Ex29_StequeWithTwoStacks<>();
        stequeWithTwoStacks.push(1);
        stequeWithTwoStacks.push(2);
        stequeWithTwoStacks.push(3);
        stequeWithTwoStacks.enqueue(11);
        stequeWithTwoStacks.enqueue(22);
        stequeWithTwoStacks.enqueue(33);
        StdOut.println("Expecting 3: " + stequeWithTwoStacks.pop());
        StdOut.println("Expecting 2: " + stequeWithTwoStacks.pop());
        StdOut.println("Expecting 1: " + stequeWithTwoStacks.pop());
        StdOut.println("Expecting 11: " + stequeWithTwoStacks.pop());
        StdOut.println("Expecting 22: " + stequeWithTwoStacks.pop());
        StdOut.println("Expecting 33: " + stequeWithTwoStacks.pop());
    }
}
