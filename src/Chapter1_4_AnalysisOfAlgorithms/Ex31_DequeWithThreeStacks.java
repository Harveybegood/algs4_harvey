package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.Stack;

import java.util.NoSuchElementException;

/*
*   Deque with three stacks. Implement a deque with three stacks so that each deque operation takes a constant amortized
*   number of stack operations.
*
* */
public class Ex31_DequeWithThreeStacks<Item> {
    Stack<Item> leftStack;
    Stack<Item> middleStack;
    Stack<Item> rightStack;
    private int n;
    public Ex31_DequeWithThreeStacks(){
        leftStack = new Stack<>();
        middleStack = new Stack<>();
        rightStack = new Stack<>();
    }
    public boolean isEmpty(){return n == 0;}
    public int size(){return n;}
    public void pushLeft(Item item){
        leftStack.push(item);
        n++;
    }
    public Item popLeft(){
        if (leftStack.isEmpty()){
            throw new NoSuchElementException("Left stack under flow");
        }
        for (int i = 0; i < leftStack.size() - 1; i++){
            middleStack.push(leftStack.pop());
        }
        Item item = leftStack.pop();
        n--;
        while (!middleStack.isEmpty()){
            leftStack.push(middleStack.pop());
        }
        return item;
    }
    public void pushRight(Item item){
        rightStack.push(item);
        n++;
    }
    public Item popRight(){
        if (rightStack.isEmpty()){
            throw new NoSuchElementException("Left stack under flow");
        }
        for (int i = 0; i < rightStack.size() - 1; i++){
            middleStack.push(rightStack.pop());
        }
        Item item = rightStack.pop();
        n--;
        while (!rightStack.isEmpty()){
            rightStack.push(rightStack.pop());
        }
        return item;
    }

    public static void main(String[] args) {

    }
}
