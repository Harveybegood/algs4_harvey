package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.StringJoiner;

/*
*       Write a method removeAfter() that takes a linked-list Node as argument and removes the node following
*       the given one (and does nothing if the argument or the next field in the argument node is null).
*
* */
public class Ex24<Item> implements Iterable<Item>{

    private class Node{
        Item item;
        Node next;
    }
    private Node first;
    private int N;

    public boolean isEmpty(){return N == 0;}
    public int size(){return N;}

    public Node createNode(Item item){
        Node node = new Node();
        node.item = item;
        return node;
    }

    public void add(Item item){
        if (isEmpty()){
            first = new Node();
            first.item = item;
        }else {
            Node current;
            for (current = first; current.next != null; current = current.next){
                Node newNode = new Node();
                newNode.item = item;
                current.next = newNode;
            }
        }
        N++;
    }

    public void removeAfter(Node node){
        if (node == null || node.next == null){return;}
        node.next = node.next.next;
    }

  /*  @Override
    public Iterator<Item> iterator() {
        return null;
    */

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;

            return item;
        }
    }

    public static void main(String[] args) {
        Ex24<Integer> linkedList = new Ex24<Integer>();
        linkedList.add(0);
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        StdOut.println("Before removing node after node 0.\n");
        StringJoiner listBeforeRemove = new StringJoiner(" ");
        for (int number : linkedList){
            listBeforeRemove.add(String.valueOf(number));
        }
        StdOut.println(listBeforeRemove.toString());

        Ex24<Integer>.Node nodeToBeDeleted = linkedList.createNode(0);
        linkedList.removeAfter(nodeToBeDeleted);
        StringJoiner listAfterRemove = new StringJoiner(" ");
        for (int number : linkedList) {
            listAfterRemove.add(String.valueOf(number));
        }

        StdOut.println(listAfterRemove.toString());
        StdOut.println("Expected: 0 2 3 4");
    }

}


