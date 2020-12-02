package Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/*
*   Write a function that takes the first Node in a linked list as argument and (destructively) reverses the list, returning
*   the first Node in the result.
*
*
*
* */
public class Ex30_ReverseLinkedList<Item> implements Iterable<Item>{
    private class Node{
        Node next;
        Item item;
    }
    private Node first;
    private int N;
    public boolean isEmpty(){return N == 0;}
    public int size(){return N;}
    public void put(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }
    public Item get(){
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }
    public Node iterativeReverse(Node x){
        Node first = x;
        Node reverse = null;
        while (first != null){
            Node second = first.next;
            first.next = reverse;
            reverse = first;
            first = second;
        }
        return reverse;
    }
    public Node recursiveReverse(Node x){
        if (x == null)
            return null;
        if (x.next == null)
            return x;
        Node second = x.next;
        Node rest = recursiveReverse(second);
        second.next = x;
        x.next = null;
        return rest;
    }
    public Iterator<Item> iterator(){
        return new LinkedIterator();
    }
    private class LinkedIterator implements Iterator<Item>{
        Item item;
        Node current = first;
        public boolean hasNext(){return current != null;}
        public Item next(){
            item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Ex30_ReverseLinkedList<String> reverseLinkedList = new Ex30_ReverseLinkedList<>();
        reverseLinkedList.put("J");
        reverseLinkedList.put("C");
        reverseLinkedList.put("G");
        reverseLinkedList.put("B");
        StdOut.println("The order of linked list before manipulation: ");
        for (String s : reverseLinkedList){
            StdOut.print(s + " ");
        }
        StdOut.println();
        StdOut.println("The order of linked list after iterative reverse: ");
        reverseLinkedList.iterativeReverse(reverseLinkedList.first);
        for (String s : reverseLinkedList){
            StdOut.print(s + " ");
        }
        StdOut.println();
        StdOut.println("The first node is B: " + reverseLinkedList.first);


        Ex30_ReverseLinkedList<String> reverseLinkedList1 = new Ex30_ReverseLinkedList<>();
        reverseLinkedList1.put("J");
        reverseLinkedList1.put("C");
        reverseLinkedList1.put("G");
        reverseLinkedList1.put("B");
        StdOut.println("The order of linked list before manipulation: ");
        for (String s : reverseLinkedList1){
            StdOut.print(s + " ");
        }
        StdOut.println();
        StdOut.println("The order of linked list after iterative reverse: ");
        reverseLinkedList.recursiveReverse(reverseLinkedList1.first);
        for (String s : reverseLinkedList1){
            StdOut.print(s + " ");
        }
        StdOut.println();
        StdOut.println("The first node is B: " + reverseLinkedList1.first);
    }
}
