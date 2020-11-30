package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.StringJoiner;

public class Ex48_1<Item> implements Iterable<Item> {
    private Stack<Item> leftStack;
    private Stack<Item> rightStack;

    public Ex48_1(){
        leftStack = new Stack<>();
        rightStack = new Stack<>();
    }
    public boolean isLeftStackEmpty(){return leftStack.size() == 0;}
    public boolean isRightStackEmpty(){return rightStack.size() == 0;}

    public int leftStackSize(){return leftStack.size();}
    public int rightStackSize(){return rightStack.size();}

    public void pushLeft(Item item){
        leftStack.push(item);
    }
    public void pushRight(Item item){
        rightStack.push(item);
    }
    public Item popLeft(){
        if (isLeftStackEmpty()){
            throw new RuntimeException("Left Chapter1_3_Bags_Queues_Stacks.stack underflow");
        }
        return leftStack.pop();
    }
    public Item popRight(){
        if (isRightStackEmpty()){
            throw new RuntimeException("Right Chapter1_3_Bags_Queues_Stacks.stack underflow");
        }
        return rightStack.pop();
    }

    @Override
    public Iterator<Item> iterator() {
        return new TwoStackDequeIterator();
    }
    public class TwoStackDequeIterator implements Iterator<Item>{
        private int index = 0;
        private Iterator<Item> leftStackIterator = leftStack.iterator();
        private Iterator<Item> rightStackIterator = rightStack.iterator();

        public boolean hasNext(){
            return index < leftStack.size() + rightStack.size();
        }

        public Item next(){
            if (index ==0 && leftStackSize() > 0){
                StdOut.println("Left Chapter1_3_Bags_Queues_Stacks.stack");
            }
            Item item;
            if (leftStackIterator.hasNext()){
                item = leftStackIterator.next();
            }else {
                if (index == leftStackSize()){
                    StdOut.println("Right Chapter1_3_Bags_Queues_Stacks.stack");
                }
                item = rightStackIterator.next();
            }
            index++;
            return item;
        }
    }

    private void testPushLeft(){
        Ex48_1<String> deque = new Ex48_1<>();
        deque.pushLeft("a");
        deque.pushLeft("b");
        deque.pushLeft("c");
        StringJoiner dequeItems = new StringJoiner(" ");
        for (String item : deque){
            dequeItems.add(item);
        }
        StdOut.println("Test Push Left: " + dequeItems.toString());
        StdOut.println();
    }
    private void testPushRight(){
        Ex48_1<String> deque = new Ex48_1<>();
        deque.pushRight("a");
        deque.pushRight("b");
        deque.pushRight("c");
        StringJoiner dequeItems = new StringJoiner(" ");
        for (String item : deque){
            dequeItems.add(item);
        }
        StdOut.println("Test Push Right: " + dequeItems.toString());
        StdOut.println();
    }
    private void testPopLeft(){
        Ex48_1<String> deque = new Ex48_1<>();
        deque.pushLeft("a");
        deque.pushLeft("b");
        deque.pushLeft("c");
        deque.popLeft();
        deque.popLeft();
        StringJoiner dequeItems = new StringJoiner(" ");
        for (String item : deque){
            dequeItems.add(item);
        }
        StdOut.println("Test Pop Left: " + dequeItems.toString());
        StdOut.println();
    }
    private void testPopRight(){
        Ex48_1<String> deque = new Ex48_1<>();
        deque.pushRight("a");
        deque.pushRight("b");
        deque.pushRight("c");
        deque.popRight();
        deque.popRight();
        StringJoiner dequeItems = new StringJoiner(" ");
        for (String item : deque){
            dequeItems.add(item);
        }
        StdOut.println("Test Pop Right: " + dequeItems.toString());
    }

    public static void main(String[] args) {
        Ex48_1<String> deque = new Ex48_1<>();
        deque.testPushLeft();
        deque.testPushRight();
        deque.testPopLeft();
        deque.testPopRight();
    }
}
