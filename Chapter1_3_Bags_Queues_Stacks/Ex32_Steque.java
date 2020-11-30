package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/*
*   Steque. A stack-ended queue or steque is a data type that supports push, pop and enqueue. Articulate an API for this
*   ADT. Develop a linked-list-based implementation.
*
* */
@SuppressWarnings("unchecked")
public class Ex32_Steque<Item> implements Iterable<Item> {
    private static class Node<Item>{
        private Item item;
        private Node next;
    }
    private Node<Item> first;
    private Node<Item> last;
    private int N;
    // initialize an empty queue
    public Ex32_Steque(){
        first = null;
        last = null;
        N = 0;
    }
    public boolean isEmpty(){return first == null;}
    public int size(){return N;}
    public void push(Item item){
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        if (isEmpty()){
            last = first;
        }
        N++;
    }
    public Item pop(){
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }
    public void enqueue(Item item){
        Node<Item> oldLast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;
        Node temp = first;
        while (temp.next != null){
            temp = temp.next;
        }
        oldLast = temp;
        oldLast.next = last;
        N++;
    }
    public Iterator<Item> iterator(){
        return new listIterator();
    }
    private class listIterator implements Iterator<Item>{
        Node<Item> current = first;
        public boolean hasNext(){return current != null;}
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    public String toString(){
        StringBuilder s = new StringBuilder();
        for(Item item : this){
            s.append(item);
            s.append(" ");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        Ex32_Steque<Integer> steque = new Ex32_Steque<>();
        steque.push(5);
        steque.push(3);
        steque.enqueue(6);
        steque.enqueue(2);
        StdOut.println("3 5 6 2" + " expected");
        StdOut.print(steque.toString());
        StdOut.println();
        for (int i : steque){
            StdOut.print(i + " ");
        }
        StdOut.println();
        for (int i = 0; i < 4; i++){
            StdOut.print(steque.pop() + " ");
        }
    }
}
