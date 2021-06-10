
package Chapter1_4_AnalysisOfAlgorithms;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;
/*
*   Deque with a stack and a steque. Implement a deque with a stack and a steque(see ex1.3.32)so that each deque operation
*   takes a constant amortized number of stack and steque operations.
*   Deque is an abstract date type and an adaptation of queue. such that the elements can be inserted and removed from both side.
*
* */

public class Ex30_DequeWithaStackAndaSteque<Item> implements Iterable<Item>{
    private Stack<Item> stack;
    private Steque<Item> steque;
    public Ex30_DequeWithaStackAndaSteque(){
        stack = new Stack<>();
        steque = new Steque<>();
    }
    private int n;
    public boolean isEmpty(){return n == 0;}
    public int size(){return n;}
    public void pushLeft(Item item){
        steque.push(item);
        n++;
    }
    public Item popLeft(){
        if (isEmpty()){throw new NoSuchElementException("Deque under flow");}
        Item item = steque.pop();
        n--;
        return item;
    }
    public void pushRight(Item item){
        steque.enqueue(item);
        n++;
    }
    public Item popRight(){
        if (isEmpty()){throw new NoSuchElementException("Deque under flow");}
        for (int i = 0; i < n - 1; i++){
            stack.push(steque.pop());
        }
        Item item = steque.pop();
        while (!stack.isEmpty()){
            steque.push(stack.pop());
        }
        n--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
    private class DequeIterator implements Iterator<Item>{
        Chapter1_4_AnalysisOfAlgorithms.Steque.Node current = steque.first;
        public boolean hasNext(){return current != null;}
        public Item next(){
            Item item = (Item) current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Ex30_DequeWithaStackAndaSteque<String> dequeWithaStackAndaSteque = new Ex30_DequeWithaStackAndaSteque<>();
        dequeWithaStackAndaSteque.pushLeft("b");
        dequeWithaStackAndaSteque.pushLeft("a");
        dequeWithaStackAndaSteque.pushRight("c");
        dequeWithaStackAndaSteque.pushRight("d");
        dequeWithaStackAndaSteque.pushRight("e");
        StdOut.println(dequeWithaStackAndaSteque.popLeft() + " " + "a");
        StdOut.println(dequeWithaStackAndaSteque.popLeft() + " " + "b");
        StdOut.println(dequeWithaStackAndaSteque.popLeft() + " " + "c");
        StdOut.println(dequeWithaStackAndaSteque.popRight() + " " + "e");
        StdOut.println(dequeWithaStackAndaSteque.popRight() + " " + "d");
    }
}
