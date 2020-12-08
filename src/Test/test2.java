package Test;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.StringJoiner;

public class test2<Item> implements Iterable<Item> {
    private class Node{
        Node previous;
        Node next;
        Item item;
    }
    private Node first;
    private Node last;
    private int size;
   /* public test2(){

    }*/
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void push(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        if (oldFirst != null) oldFirst.previous = first;
        else last = first;
        size++;
    }
    public void enqueue(Item item){
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.previous = oldLast;
        if (oldLast != null) oldLast.next = last;
        else first = last;
        size++;
    }
    public Item pop(){
        if (this.isEmpty()){
            throw new RuntimeException("Steque underflow");
        }
        Item item = first.item;
        first = first.next;
        if (first != null)first.previous = null;
        else last = null;
        size--;
        return item;
    }
    public Iterator<Item> iterator(){
        return new StequeIterator();
    }
    public class StequeIterator implements Iterator<Item>{
        Node current = first;
        int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }
        public Item next(){
            Item item = current.item;
            current = current.next;
            index++;
            return item;
        }
    }

    public static void main(String[] args) {
        test2<Integer> steque = new test2<>();
        steque.push(1);
        steque.push(2);
        steque.push(3);
        steque.pop();
        steque.enqueue(5);
        steque.enqueue(6);
        StringJoiner stequeItems = new StringJoiner(" ");
        for (int number : steque){
            stequeItems.add(String.valueOf(number));
        }
        StdOut.println("Steque Items: " + stequeItems.toString());
    }
}
