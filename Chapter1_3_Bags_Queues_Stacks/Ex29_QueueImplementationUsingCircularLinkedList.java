package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/*
*   Write a Queue implementation that uses a circular linked list, which is the same as a linked list except that no links
*   are null and the value of last.next is first whenever the list is not empty. Keep only one Node instance variable(last).
*
* */
public class Ex29_QueueImplementationUsingCircularLinkedList<Item> implements Iterable<Item>{
    private class Node{
        Node next;
        Item item;
    }
    private Node last;
    private Node first;
    private int N;
    public boolean isEmpty(){return N == 0;}
    public int size(){return N;}
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
            last.next = first;
        }
        N++;
    }
    public Item dequeue(){
        Item item = first.item;
        first = first.next;
        last.next = first;
        N--;
        if (isEmpty()){
            last = null;
        }
        return item;
    }
    public Iterator<Item> iterator(){
        return new listIterator();
    }
    public class listIterator implements Iterator<Item>{
        Node current = first;
        public boolean hasNext(){return current != null;}
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    public static void main(String[] args) {
        Ex29_QueueImplementationUsingCircularLinkedList<Integer> queueImplementationUsingCircularLinkedList =
                new Ex29_QueueImplementationUsingCircularLinkedList<>();
        queueImplementationUsingCircularLinkedList.enqueue(7);
        queueImplementationUsingCircularLinkedList.enqueue(2);
        queueImplementationUsingCircularLinkedList.enqueue(9);
        queueImplementationUsingCircularLinkedList.enqueue(6);
        StdOut.println("The size of current linked list: 4 " + queueImplementationUsingCircularLinkedList.size());
        StdOut.print("The next value of last(6) is 7" + "  ");
        StdOut.println(queueImplementationUsingCircularLinkedList.last.next.item);
        StdOut.println(queueImplementationUsingCircularLinkedList.dequeue());
        StdOut.println("The size of current linked list: 3 " + queueImplementationUsingCircularLinkedList.size());
        StdOut.print("The next value of last(6) is 2" + "  ");
        StdOut.println(queueImplementationUsingCircularLinkedList.last.next.item);

    }
}
