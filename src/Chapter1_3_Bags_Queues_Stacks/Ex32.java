package Chapter1_3_Bags_Queues_Stacks;



import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.StringJoiner;

/*
*   Steque. A Chapter1_3_Bags_Queues_Stacks.stack-ended queue or steque is a data type that supports push, pop and enqueue. Articulate
*   an API for this ADT. Develop a linked-list-based implementation
*
* */
public class Ex32<Item> implements Iterable<Item> {

    private class Node{
        //Node ;
        Node previous;
        Node next;
        Item item;
    }
    private Node first;
    private Node last;
    private int N;

    public boolean isEmpty(){return N == 0;}
    public int size(){return N;}

    public void push(Item item){
        Node oldNode = first;
        first = new Node();
        first.item = item;
        first.next = oldNode;
        if (oldNode != null){oldNode.previous = first;}
        else {last = first;}
        N++;
    }
    public Item pop(){
        if (isEmpty()){throw new RuntimeException("Steque underflow.");}
        Item item = first.item;
        first = first.next;
        if (first != null){first.previous = null;}
        else {last = null;}
        N--;
        return item;
    }
    public void enqueue(Item item){
        Node oldNode = last;
        last = new Node();
        last.item = item;
        last.previous = oldNode;
        if (oldNode != null){oldNode.next = last;}
        else {first = last;}
        N++;
    }

    @Override
    public Iterator<Item> iterator() {
        return new stequeIterator();
    }

    public class stequeIterator implements Iterator<Item>{
        Node current = first;
        int index = 0;
        public boolean hasNext(){return index < N;}

        @Override
        public Item next() {
            Item item = first.item;
            current = current.next;
            index++;
            return item;
        }
    }

    public static void main(String[] args) {
        Ex32<Integer> steque = new Ex32<>();
        steque.push(1);
        StdOut.println(steque.toString());
        steque.push(2);
        StdOut.println(steque.toString());
        steque.push(3);
        StdOut.println(steque.toString());
        steque.push(4);
        StdOut.println(steque.toString());
        steque.pop();
        steque.enqueue(5);
        StdOut.println(steque.toString());
        steque.enqueue(6);
        StdOut.println(steque.toString());

        StringJoiner stequeList = new StringJoiner(" - ");
        for (int number : steque){
            stequeList.add(String.valueOf(number));
        }
        StdOut.println("Steque Items: " + stequeList.toString());
    }
}
