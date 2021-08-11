package Chapter3_5_Applications;

import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdOut;

import java.security.PublicKey;
import java.util.HashSet;


/*
*   UniQueue. Create a data type that is a queue, except that an element may only be inserted the queue once. Use an existence symbol table
*   to keep track of all elements that have ever been inserted and ignore requests to re-insert such items.
*
* */
public class Ex28_UniQueue<Item> {
    private HashSet<Item> st;
    private class Node{
        Item item;
        Node next;
    }
    private Node first;
    private Node last;
    private int n;
    public Ex28_UniQueue(){
        st = new HashSet<>();
    }
    public boolean isEmpty(){return st.isEmpty();}
    public boolean contains(Item item){
        if (item == null){throw new IllegalArgumentException("Argument cannot be null");}
        return st.contains(item);
    }

    public int size(){return n;}
    public void enqueue(Item item){
        if (item == null){throw new IllegalArgumentException("Argument cannot be null");}
        if (contains(item)){return;}
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
        st.add(item);
    }
    public Item dequeue(){
        if (isEmpty()){throw new UnsupportedOperationException("Queue underflow");}
        Item item = first.item;
        first = first.next;
        if (isEmpty()){
            last = null;
        }
        st.remove(first.item);
        n--;
        return item;
    }

    public static void main(String[] args) {
        Ex28_UniQueue<String> uniQueue = new Ex28_UniQueue<>();
        uniQueue.enqueue("A");
        uniQueue.enqueue("B");
        uniQueue.enqueue("B");
        uniQueue.enqueue("C");
        uniQueue.enqueue("C");
        StdOut.println("The size of UniQueue " + uniQueue.size() + " expected 3");
    }
}
