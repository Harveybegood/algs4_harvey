package Chapter1_3_Bags_Queues_Stacks;

import java.util.Iterator;

/*
*       Give a code fragment that removes the last node in a linked list whose first node is first.
*
* */
public class Ex19<Item> implements Iterable<Item> {
    private Node first;
    private int N;
    private class Node{
        Item item;
        Node next;
    }
    public boolean isEmpty(){
        return N == 0;
    }
    public int size(){
        return N;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext(){return current != null;}
        public void remove(){}
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Ex19<String> strs = new Ex19<>();

    }
}
