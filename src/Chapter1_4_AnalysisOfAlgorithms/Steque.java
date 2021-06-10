package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
*   Steque. A stack-ended queue or steque is a data type that supports push, pop, and
*   enqueue. Articulate an API for this ADT. Develop a linked-list-based implementation.
*
* */
public class Steque<Item> {
    public class Node{
        Item item;
        Node next;
    }
    public Node first;
    private Node last;
    private int n;
    public boolean isEmpty(){return n == 0;}
    public int size(){return n;}
    public void push(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        if (isEmpty()){
            last = first;
        }
        else {
            first.next = oldFirst;
        }
        n++;
    }
    public Item pop(){
        if (isEmpty()){throw new NoSuchElementException("Steque under flow");}
        Item item = first.item;
        first = first.next;
        return item;
    }
    public void enqueue(Item item){
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()){
            first = last;
        }
        else {
            oldLast.next = last;
        }
        n++;
    }
    public Iterator<Item> iterator(){
        return new StequeIterator();
    }
    private class StequeIterator implements Iterator<Item>{
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

    public static void main(String[] args) {
        Steque<String> steque = new Steque<>();
        steque.push("a");
        steque.push("b");
        steque.enqueue("c");
        steque.enqueue("d");
        StdOut.println(steque.pop() + " " + "b");
        StdOut.println(steque.pop() + " " + "a");
        StdOut.println(steque.pop() + " " + "c");
        StdOut.println(steque.pop() + " " + "d");
    }
}

