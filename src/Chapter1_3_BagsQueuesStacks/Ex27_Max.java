package Chapter1_3_BagsQueuesStacks;
/*
*   Write a method max() that takes a reference to the first node in a linked list as argument and returns the value of
*   maximum key in the list. Assume that all keys are positive integer, and return 0 if the list is empty.
*
* */
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
@SuppressWarnings("unchecked")
public class Ex27_Max<Item extends Comparable> implements Iterable<Item> {
    private class Node{
        Node next;
        Item item;
        public Node(){}
    }
    private Node first;
    private int n;
    public boolean isEmpty(){return  first == null;}
    public int size(){return n;}
    public void push(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    public Item max(Node node){
        if (isEmpty()){
            return null;
        }
        Item item = node.item;
        Node current = node;
        Node temp = current;
        for (; current.next != null; current = current.next){
            if (temp.item.compareTo(current.next.item) < 0){
                item = current.next.item;
                temp = current.next;
            }
        }
        return item;
    }
    public Iterator<Item> iterator(){
        return new listIterator();
    }
    private class listIterator implements Iterator<Item>{
        Node current = first;
        Item item;
        public boolean hasNext(){return current != null;}
        public Item next(){
            item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Ex27_Max<String> maxMethod = new Ex27_Max<>();
        maxMethod.push("p");
        maxMethod.push("b");
        maxMethod.push("q");
        maxMethod.push("k");
        maxMethod.push("l");
        String string = maxMethod.max(maxMethod.first);
        StdOut.print(string + " expected q");
    }
}
