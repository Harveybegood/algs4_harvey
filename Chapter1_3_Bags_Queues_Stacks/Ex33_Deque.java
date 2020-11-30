package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/*
*   Deque. A double-ended queue or deque(pronounce "deck") is like a stack or a queue but supports adding and removing
*   items at both ends. A deque stores a collection of items and supports the following API:
*
*   Write a class Deque that uses a doubly-linked list to implement this API and a class ResizingArrayDeque that uses
*   a resizing array.
*
* */
public class Ex33_Deque<Item> implements Iterable<Item> {
    private class Node{
        Item item;
        Node prev;
        Node next;
    }
    private Node first;
    private Node last;
    private int N;
    public Ex33_Deque(){
        first = null;
        last = null;
        N = 0;
    }
    public boolean isEmpty(){return first == null;}
    public int size(){return N;}
    public void pushLeft(Item item){
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()){
            first = last;
            //first.next = null;
            first.prev = null;
        }
        else {
            oldLast.next = last;
            last.prev = oldLast;
        }
        N++;
    }
    public void pushRight(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.prev = null;
        if (isEmpty()){
            last = first;
            //last.prev = null;
            last.next = null;
        }
        else {
            oldFirst.prev = first;
            first.next = oldFirst;
        }
        N++;
    }
    public Item popLeft(){
        Item item = last.item;
        last = last.prev;
        last.next = null;
        if (isEmpty()){
            last = null;
        }
        N--;
        return item;
    }
    public Item popRight(){
        Item item = first.item;
        first = first.next;
        first.prev = null;
        if (isEmpty()){
            first = null;
        }
        return item;
    }
    public Iterator<Item> iterator(){
        return new listIterator();
    }
    private class listIterator implements Iterator<Item>{
        Node current = first;
        public boolean hasNext(){
            return current != null;
        }
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for (Item item : this){
            s.append(item);
            s.append(" ");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        Ex33_Deque<Integer> deque = new Ex33_Deque<>();
        deque.pushLeft(3);
        deque.pushLeft(4);
        deque.pushRight(5);
        deque.pushRight(6);
        StdOut.println(deque.toString());
        deque.popLeft();
        StdOut.println(deque.toString());
        deque.popRight();
        StdOut.println(deque.toString());
    }
}
