package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/*
*   Write a method max() that takes a reference to the first node in a linked list as argument and returns the value of the
*   maximum key in the list. Assume that all keys are positive integer, and return 0 if the list is empty.
*
* */
@SuppressWarnings("unchecked")
public class Ex27_Max<Item extends Comparable> implements Iterable<Item> {
    private class Node{
        Node next;
        Item item;
    }
    public Node first;
    public int N;
    public boolean isEmpty(){return N == 0;}
    public int size(){return N;}
    public void push(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }
    public Item max(Ex27_Max<Item>.Node first){
        if (isEmpty()){
            return null;
        }
        Node temp;
        temp = first;
        Item max = temp.item;
        for (Node current = first; current != null; current = current.next){
            if (current.next != null && temp.item.compareTo(current.next.item) < 0){
                temp = current.next;
                max = temp.item;
            }
        }
        return max;
    }
    public Iterator<Item> iterator(){
        return new listIterator();
    }
    private class listIterator implements Iterator<Item>{
        Node current = first;
        Item item =  current.item;
        public boolean hasNext(){return current != null;}
        public Item next(){
            current = current.next;
            return item;
        }
    }
    public static void main(String[] args) {
        Ex27_Max<Integer> ex27_max = new Ex27_Max<>();
        ex27_max.push(6);
        ex27_max.push(9);
        ex27_max.push(7);
        ex27_max.push(2);
        ex27_max.push(5);
        ex27_max.push(8);
        StdOut.println(ex27_max.max(ex27_max.first));
    }
}
