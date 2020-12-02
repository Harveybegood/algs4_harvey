package Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/*
*   Steque. A stack-ended queue or steque is a data type that supports push, pop, and enqueue. Articulate an API for this
*   ADT. Develop a linked-list-based implementation.
*
* */
@SuppressWarnings("unchecked")
public class Ex32_Steque<Item> implements Iterable<Item>{
    private class Node{
        Node next;
        Item item;
    }
    private int n;
    private Node first;
    private Node last;
    public boolean isEmpty(){return n == 0;}
    public int size(){return n;}
    public void push(Item item){
        Node oldNode = first;
        first = new Node();
        first.item = item;
        if (isEmpty()){
            last = first;
        }
        else {
            first.next = oldNode;
        }
        n++;
    }
    public Item pop(){
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }
    public void enqueue(Item item){
        Node oldNode = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()){
            first = last;
        }
        else {
            oldNode.next = last;
        }
        n++;
    }
    public Iterator iterator(){
        return new stequeIterator();
    }
    private class stequeIterator implements Iterator<Item>{
        Item item;
        Node current = first;
        public boolean hasNext(){
            return current != null;
        }
        public Item next(){
            item = current.item;
            current = current.next;
            return item;
        }
    }
    public static void main(String[] args) {
        Ex32_Steque<String> steque = new Ex32_Steque<>();
        steque.push("a");
        steque.push("b");
        steque.push("c");
        steque.enqueue("a");
        steque.enqueue("b");
        steque.enqueue("c");
        for (String s : steque){
            StdOut.print(s + " ");
        }
    }
}
