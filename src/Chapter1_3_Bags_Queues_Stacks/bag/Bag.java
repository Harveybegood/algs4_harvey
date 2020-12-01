package Chapter1_3_Bags_Queues_Stacks.bag;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Bag<Item> implements Iterable<Item> {

    private Node first;
    private int N;
    private class Node{
        Item item;
        Node next;
    }

    public boolean isEmpty(){
        return first == null;
    }
    public int size(){
        return N;
    }

    public void add(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

  /*  public Item removing(){
        Item item =
    }*/

    @Override
    public Iterator<Item> iterator() {
        //return null;
        return new ListIterator();
    }

    public class ListIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext(){
            return current != null;
        }
        public void remove(){}
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Bag<String> b = new Bag<>();
        while (!StdIn.isEmpty()){
            String item = StdIn.readString();
            if (!item.equals("-")){
                b.add(item);
            }else if (!b.isEmpty()){
                StdOut.print(b.iterator().next() + " ");
            }
        }
        StdOut.printf(" (" + b.size() + " left on Chapter1_3_Bags_Queues_Stacks.bag)");
    }
}
