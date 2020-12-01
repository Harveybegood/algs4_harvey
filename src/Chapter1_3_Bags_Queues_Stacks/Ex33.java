package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.StringJoiner;

/*
* */
public class Ex33<Item> implements Iterable<Item> {
    private class Node{
        Item item;
        Node prev;
        Node next;
    }
    private Node first;
    private Node last;
    private int size;

    public Ex33(){
        first = null;
        last = null;
        size = 0;
    }
    public boolean isEmpty(){return size == 0;}
    public int size(){return size;}

    public void pushLeft(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        //oldFirst.prev = first;
        if (oldFirst != null){oldFirst.prev = first;}
        else {last = first;}
        size++;
    }
    public void pushRight(Item item){
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.prev = oldLast;
        if (oldLast != null){oldLast.next = last;}
        else {first = last;}
        size++;
    }
    public Item popLeft(){
        if (isEmpty()){throw new RuntimeException("deque underflow.");}
        Item item = first.item;
        first = first.next;
        if (first != null){first.prev = null;}
        else {last = null;}
        size--;
        return item;
    }
    public Item popRight(){
        if (isEmpty()){throw new RuntimeException("Deque underflow.");}
        Item item = last.item;
        last = last.prev;
        if (last != null){last.next = null;}
        else {first = null;}
        size--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new dequeIterator();
    }

    private class dequeIterator implements Iterator<Item>{
        int index = 0;
        Node current = first;
        public boolean hasNext(){return index < size();}
        public Item next(){
            index++;
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Ex33<String> Deque = new Ex33<>();
        Deque.pushLeft("3");
        Deque.pushLeft("2");
        Deque.pushLeft("1");
        Deque.pushRight("a");
        Deque.pushRight("b");
        Deque.pushRight("c");

        StringJoiner DequeList = new StringJoiner(" - ");
        for (String number : Deque){
            DequeList.add(String.valueOf(number));
        }
        StdOut.println("List of Deque after push: ");
        StdOut.print(DequeList);

        Deque.popLeft();
        Deque.popRight();
        StringJoiner newDequeList = new StringJoiner(" - ");
        for (String number : Deque){
            newDequeList.add(String.valueOf(number));
        }
        StdOut.println("\nList of Deque after pop: ");
        StdOut.println(newDequeList);
    }
}
