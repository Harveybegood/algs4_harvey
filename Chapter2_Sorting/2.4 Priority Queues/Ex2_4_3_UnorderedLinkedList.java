package Chapter2_4_PriorityQueues;

import edu.princeton.cs.algs4.StdOut;

@SuppressWarnings("unchecked")
public class Ex2_4_3_UnorderedLinkedList<Item extends Comparable<Item>> {
    private class Node{
        Item item;
        Node next;
    }
    private Node first;
    //private Node last;
    private int N;
    public boolean isEmpty(){return N == 0;}
    public int size(){return N;}
    public void push(Item item){
        Node oldNode = first;
        first = new Node();
        first.item = item;
        first.next = oldNode;
        N++;
    }
    public Item popMax(){
        if (isEmpty()){throw new RuntimeException("Node PQ is empty.");}
        Item item = first.item;
        Node maxNode = first;
        while (maxNode.next != null){
            if (item.compareTo(maxNode.next.item) < 0){
                item = maxNode.next.item;
                maxNode = maxNode.next;
            }else {
                maxNode = maxNode.next;
            }
        }
        // how to delete the node with the maximum value
        if (item == first.item){
            first = first.next;
        }else {
            maxNode = first;
            while (maxNode.next.item != item){
                maxNode = maxNode.next;
            }
            if (maxNode.next.next == null){
                maxNode.next = null;
            }else {
                maxNode.next = maxNode.next.next;
            }
        }
        N--;
        return item;
    }

    public static void main(String[] args) {
        Ex2_4_3_UnorderedLinkedList<Integer> unorderedLinkedList = new Ex2_4_3_UnorderedLinkedList<>();
        unorderedLinkedList.push(4);
        unorderedLinkedList.push(2);
        unorderedLinkedList.push(7);
        unorderedLinkedList.push(1);
        StdOut.println("Expected 7 - " + unorderedLinkedList.popMax());
        StdOut.println("Expected 4 - " + unorderedLinkedList.popMax());
        StdOut.println("Expected 2 - " + unorderedLinkedList.popMax());
        StdOut.println("Expected 1 - " + unorderedLinkedList.popMax());
    }
}
