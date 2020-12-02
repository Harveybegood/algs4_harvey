package Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/*
*   Write a method removeAfter() that takes a linked-list Node as argument and removes the node following the given one
*   (and does nothing if the argument or the next field in the argument node is null).
*
* */
public class Ex24_RemoveAfter<Item> {
    private class Node{
        Item item;
        Node next;
    }
    private Node first;
    private int N;
    public boolean isEmpty(){return N == 0;}
    public int size(){return N;}
    public void push(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }
    public void removeAfter(Node node){
        if (node.next.next != null){
            node.next = node.next.next;
            node.next.item = node.next.next.item;
            N--;
        }
    }
    public Item pop(){
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }
    public Iterator<Item> iterator(){
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item>{
        private Node current = first;
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
        Ex24_RemoveAfter<String> removeAfter = new Ex24_RemoveAfter<>();
        removeAfter.push("S");
        removeAfter.push("E");
        removeAfter.push("X");
        removeAfter.push("A");
        removeAfter.push("R");
        removeAfter.push("T");
        StdOut.println("The length of Nodes: " +  removeAfter.N + " Expected 6");
        removeAfter.removeAfter(removeAfter.first);
        StdOut.println("The length of Node: " + removeAfter.N + " Expected 5");
    }
}
