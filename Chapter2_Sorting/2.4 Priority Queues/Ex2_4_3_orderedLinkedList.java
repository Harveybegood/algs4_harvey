package Chapter2_4_PriorityQueues;

import edu.princeton.cs.algs4.StdOut;

public class Ex2_4_3_orderedLinkedList<Item extends Comparable<Item>> {
    private class Node{
        Item item;
        Node next;
        Node prev;
    }
    private Node first;
    private Node last;
    private int N = 0;
    public boolean isEmpty(){return N == 0;}
    public int size(){return N;}
    public void pushPQ(Item item){
        if (N == 0 || item.compareTo(first.item) < 0){
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            if (oldFirst != null){
                oldFirst.prev = first;
            }
            if (first.prev == null){
                last = first;
            }
            N++;
        }
        else {
            Node sampleNode = first;
      
            // Compare the new inserted node with one of each existing node
            while (sampleNode.next != null && item.compareTo(sampleNode.next.item) > 0){
                sampleNode = sampleNode.next;
            }
            // if the new inserted node has the same value as the previous first node which is just the olfFirst node
            Node newNode = new Node();
            newNode.item = item;
            newNode.next = sampleNode.next;
            sampleNode.next = newNode;
            newNode.prev = sampleNode;
            if (newNode.next == null){
                last = newNode;
            }
            else {
                newNode.next.prev = newNode;
            }
            N++;
        }
    }
    public Item popPQ(){
        if (isEmpty()){
            throw new RuntimeException("It is empty.");
        }
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    public static void main(String[] args) {
        Ex2_4_3_orderedLinkedList<String> orderedLinkedList = new Ex2_4_3_orderedLinkedList<>();
        orderedLinkedList.pushPQ("b");
        orderedLinkedList.pushPQ("y");
        orderedLinkedList.pushPQ("e");
        orderedLinkedList.pushPQ("o");
        orderedLinkedList.pushPQ("a");
        StdOut.println("Expected y: " + orderedLinkedList.popPQ());
        StdOut.println("Expected o: " + orderedLinkedList.popPQ());
        StdOut.println("Expected e: " + orderedLinkedList.popPQ());
        StdOut.println("Expected b: " + orderedLinkedList.popPQ());
        StdOut.println("Expected a: " + orderedLinkedList.popPQ());
    }
}
