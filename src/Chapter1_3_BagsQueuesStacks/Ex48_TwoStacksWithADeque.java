package Chapter1_3_BagsQueuesStacks;

/*
*   Deque. A double-ended queue or deque (pronounced “deck”) is like a stack or
    a queue but supports adding and removing items at both ends
*
*   Two stacks with a deque. Implement two stacks with a single deque so that each operation takes a constant number of
*   deque operations.
*
* */


import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Ex48_TwoStacksWithADeque<Item> implements Iterable<Item> {
    private Ex33_Deque<Item> deque;
    private int stackLeftSize;
    private int stackRightSize;
    private void pushStackLeft(Item item){
        deque.pushLeft(item);
        stackLeftSize++;
    }
    private void pushStackRight(Item item){
        deque.pushRight(item);
        stackRightSize++;
    }
    private Item popStackLeft(){
        if (stackLeftSize == 0){
            throw new NoSuchElementException("Stack left underflow");
        }
        stackLeftSize--;
        return deque.popLeft();
    }
    private Item popStackRight(){
        Item item = deque.popRight();
        if (stackRightSize == 0){
            throw new NoSuchElementException("Stack right underflow");
        }
        stackRightSize--;
        return item;
    }
    public Ex48_TwoStacksWithADeque(){
        deque = new Ex33_Deque<>();
    }
    /*public int stackLeft(){return stackLeftSize;}
    public int stackRight(){return stackRightSize;}*/

    public Iterator<Item> iterator(){
        return new TwoStacksWithADeque();
    }
    @SuppressWarnings("unchecked")
    private class TwoStacksWithADeque implements Iterator<Item>{
        private int index;
        private Item[] items;
        private boolean hasNext;
        public TwoStacksWithADeque(){
            items = (Item[]) new Object[deque.size()];
            int currentIndex = 0;
            hasNext = true;
            for(Item item : deque){
                items[currentIndex++] = item;
            }
            if (stackLeftSize == 0 && stackRightSize != 0){
                index = deque.size() - 1;
            }
        }

        public boolean hasNext(){
            return hasNext;
        }

        public Item next(){
            if (index == 0 && stackLeftSize > 0){
                StdOut.println("Stack left");
            }
            if (index == deque.size() - 1 && stackRightSize > 0){
                StdOut.println("Stack right");
            }
            Item item = items[index];
            if (isIteratingStackLeft(index)){
                if (index != stackLeftSize - 1){
                    index++;
                }
                else if (stackRightSize != 0){
                    index = deque.size() - 1;
                }
                else {
                    hasNext = false;
                }
            }
            else {
                if (index != stackLeftSize){
                    index--;
                }
                else {
                    hasNext = false;
                }
            }
            return item;
        }

        private boolean isIteratingStackLeft(int index){
            return index < stackLeftSize;    
        }
    }
    private void testPushStackLeft(){
        Ex48_TwoStacksWithADeque<String> deque = new Ex48_TwoStacksWithADeque<>();
        deque.pushStackLeft("a");
        deque.pushStackLeft("b");
        deque.pushStackLeft("c");
        for(String s : deque){
            StdOut.print(s + " ");
        }
        StdOut.println();
    }
    private void testPushStackRight(){
        Ex48_TwoStacksWithADeque<String> deque = new Ex48_TwoStacksWithADeque<>();
        deque.pushStackRight("a");
        deque.pushStackRight("b");
        deque.pushStackRight("c");
        for (String s : deque){
            StdOut.print(s + " ");
        }
        StdOut.println();
    }
    private void testPopStackLeft(){
        Ex48_TwoStacksWithADeque<String> deque = new Ex48_TwoStacksWithADeque<>();
        deque.pushStackLeft("a");
        deque.pushStackLeft("b");
        deque.pushStackLeft("c");
        deque.popStackLeft();
        for(String s : deque){
            StdOut.print(s + " ");
        }
        StdOut.println();
    }
    private void testPopStackRight(){
        Ex48_TwoStacksWithADeque<String> deque = new Ex48_TwoStacksWithADeque<>();
        deque.pushStackRight("a");
        deque.pushStackRight("b");
        deque.pushStackRight("c");
        deque.popStackRight();
        //deque.popStackRight();
        for(String s : deque){
            StdOut.print(s + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        Ex48_TwoStacksWithADeque<String> deque = new Ex48_TwoStacksWithADeque<>();
        deque.testPushStackLeft();
        deque.testPushStackRight();
        deque.testPopStackLeft();
        deque.testPopStackRight();
    }
}
