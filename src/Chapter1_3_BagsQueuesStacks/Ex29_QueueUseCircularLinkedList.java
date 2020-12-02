package Chapter1_3_BagsQueuesStacks;


import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/*
*   Write a queue implementation that uses a circular linked list, which is the same as a linked list except that no links
*   are null and the value of last.next is first whenever the list is not empty. Keep only one Node instance variable(last).
*
* */
public class Ex29_QueueUseCircularLinkedList<Item> implements Iterable<Item> {
    private class Node{
        Item item;
        Node next;
        public Node(){}
    }
    private Node last;
    private int n;
    public boolean isEmpty(){return n == 0;}
    public int size(){return n;}
    public void enqueue(Item item){
        if (isEmpty()){
            last = new Node();
            last.item = item;
            last.next = last;
        }
        else {
            Node node = new Node();
            node.item = item;
            if (n == 1){
                last.next = node;
                node.next = last;
            }
            else {
                node.next = last.next;
                last.next = node;
            }
        }
        n++;
    }
    public Item dequeue(){
        Item item = last.next.item;
        last.next = last.next.next;
        n--;
        return item;
    }
    public Iterator<Item> iterator(){
        return new listIterator();
    }
    private class listIterator implements Iterator<Item>{
        Item item;
        Node current = last.next;
        public boolean hasNext(){return current != null;}
        public Item next(){
            item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Ex29_QueueUseCircularLinkedList<String> queueUseCircularLinkedList = new Ex29_QueueUseCircularLinkedList<>();
        queueUseCircularLinkedList.enqueue("to");
        queueUseCircularLinkedList.enqueue("be");
        queueUseCircularLinkedList.enqueue("or");
        queueUseCircularLinkedList.enqueue("not");
        queueUseCircularLinkedList.enqueue("to");
        StdOut.println("The size of queue: " + queueUseCircularLinkedList.size() + " expected 5");
        StdOut.println("Print out all items from queue twice in a circle way: " );
        int i = 0;
        for (String s : queueUseCircularLinkedList){
            while (i < 10){
                StdOut.print(s + " ");
                i++;
            }
        }

    }
}
