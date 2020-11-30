package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/*
*   Write a Queue implementation that uses a circular linked list, which is the same as a linked list except that no links
*   are null and the value of last.next is first whenever the list is not empty. Keep only one Node instance variable(last).
*
* */
public class Ex29_QueueUsingCircularLinkedList<Item> implements Iterable<Item>{
    private class Node{
        Node next;
        Item item;
        public Node(){}
    }
    private Node last;
    private int counterOfQueue;
    public boolean isEmpty(){return counterOfQueue == 0;}
    public int size(){return counterOfQueue;}
    public void enqueue(Item item){
        Node node = new Node();
        node.item = item;
        //node.next = last;
        if (isEmpty()){
            last = node;
            last.next = last;
        }
        else {
            if (counterOfQueue == 1){
                node.next = last;
                last.next = node;
            }
            else {
                node.next = last.next;
                last.next = node;
            }
        }
        counterOfQueue++;
    }
    public Iterator<Item> iterator(){
        return new listIterator();
    }
    private class listIterator implements Iterator<Item>{
        private Node current = last.next;
        Item item;
        public boolean hasNext(){return current != null;}
        public Item next(){
            item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Ex29_QueueUsingCircularLinkedList<String> queueUsingCircularLinkedList = new Ex29_QueueUsingCircularLinkedList<>();
        queueUsingCircularLinkedList.enqueue("a");
        queueUsingCircularLinkedList.enqueue("b");
        queueUsingCircularLinkedList.enqueue("c");
        queueUsingCircularLinkedList.enqueue("d");
        queueUsingCircularLinkedList.enqueue("e");
        int count = 1;
        for (String s : queueUsingCircularLinkedList){
            StdOut.print(s + " ");
            if (s.equals("a")){
                count++;
            }
            if (count > 2){
                break;
            }
        }
    }
}
