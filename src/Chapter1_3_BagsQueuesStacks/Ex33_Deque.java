package Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/*
*   Deque. A double-ended queue or deque(pronounced"deck")is like a stack or a queue but supports adding and removing items
*   at both ends. A deque stores a collection of items and supports the following API:
*
*   Write a class ...
*
* */
public class Ex33_Deque<Item> implements Iterable<Item> {
    private class Node{
        Item item;
        Node next;
        Node previous;
    }
    private Node first, last;
    public int n;
    public boolean isEmpty(){return  n == 0;}
    public int size(){return n;}
    void pushLeft(Item item){
        Node oldLeft = first;
        first = new Node();
        first.item = item;
        if (isEmpty()){
            last = first;
        }
        else {
            first.next = oldLeft;
        }
        n++;
    }
    void pushRight(Item item){
        Node oldRight = last;
        last = new Node();
        last.item = item;
        if (isEmpty()){
            first = last;
        }
        else {
            oldRight.next = last;
        }
        n++;
    }
    Item popLeft(){
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }
    Item popRight(){
        Item item = last.item;
        last = last.previous;
        if (last != null){
            last.next = null;
        }
        else {
            first = null;
        }
        n--;
        return item;
    }
    public Iterator<Item> iterator(){
        return new dequeIterator();
    }
    private class dequeIterator implements Iterator<Item>{
        Node current = first;
        Item item;
        public boolean hasNext(){return current != null;}
        public Item next(){
            item = current.item;
            current = current.next;
            return item;
        }
    }
    public static void main(String[] args) {
        Ex33_Deque<String> deque = new Ex33_Deque<>();
        deque.pushLeft("A");
        deque.pushLeft("B");
        deque.pushRight("A");
        deque.pushRight("B");
        for (String s : deque){
            StdOut.print(s + " ");
        }
        StdOut.print("\n" + deque.popLeft() + " ");
        StdOut.print(deque.popRight());
        StdOut.println("\nThe size of Deque: 2 " + deque.size());
    }
}
