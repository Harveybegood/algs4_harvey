package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.StringJoiner;

/*
*       Write a method delete() that takes an int argument k and deletes the kth element in a linked list, if it
*       exists.
* */
public class Ex20<Item> implements Iterable<Item> {

    private class Node{
        Item item;
        Node next;
    }
    private Node first;
    private int size;

    public boolean isEmpty(){return size == 0;}
    public int sizeOfList(){return size;}
    
    public void add(Item item){
        if (isEmpty()) {
            first = new Node();
            first.item = item;
        } else {
            Node current;
            for(current = first; current.next != null; current = current.next);

            Node newNode = new Node();
            newNode.item = item;
            current.next = newNode;
        }
        size++;
    }

    public void delete(int k){
        if (isEmpty()){
            throw new RuntimeException("Linked list is empty.");
        }else {
            Node current;
            for (current = first; current != null; current = current.next){
                current.next = current.next.next;
            }
        }
        --k;
    }

    private class ListIterator implements Iterator<Item>{
        Node current = first;
        public boolean hasNext(){return current != null;}
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    public static void main(String[] args) {
        Ex20<Integer> linkedList = new Ex20<>();
        linkedList.add(0);
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        StdOut.println("Before removing second node");

        StringJoiner listBeforeRemove = new StringJoiner(" ");
        for (int number : linkedList) {
            listBeforeRemove.add(String.valueOf(number));
        }

        StdOut.println(listBeforeRemove.toString());
        StdOut.println("Expected: 0 1 2 3");

        linkedList.delete(2);

        StdOut.println("\nAfter removing second node");
        StringJoiner listAfterRemove = new StringJoiner(" ");
        for (int number : linkedList) {
            listAfterRemove.add(String.valueOf(number));
        }

        StdOut.println(listAfterRemove.toString());
        StdOut.println("Expected: 0 2 3");
    }
}
