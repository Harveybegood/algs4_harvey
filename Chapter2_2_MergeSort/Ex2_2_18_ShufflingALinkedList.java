package Chapter2_2_MergeSort;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.StringJoiner;

/*
*   Shuffling a linked list. Develop and implement a divide-conquer algorithm that randomly shuffles a linked list in 
*   linearithmic time and logarithmic extra space.
* 
* */
public class Ex2_2_18_ShufflingALinkedList<Item> {
    private  class Node{
       Item item;
       Node next;
       public Node(){};
       public Node(Item item){
           this.item = item;
       }
    }
    private Node first;
    private int size;
    public boolean isEmpty(){return size == 0;}
    public int size(){return size;}
    public void add(Item item){
        Node newNode = new Node(item);
        newNode.next = first;
        first = newNode;
        size++;
    }
    public Iterator<Item> iterator(){
        return new listIterator();
    }
    public class listIterator implements Iterator<Item>{
        Node current = first;
        public boolean hasNext(){return current != null;}
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    public static Ex2_2_18_ShufflingALinkedList<Comparable> createLinkedList(){
        Ex2_2_18_ShufflingALinkedList<Comparable> linkedList = new Ex2_2_18_ShufflingALinkedList<>();
        for (int i = 1; i <= 10; i++){
            linkedList.add(StdRandom.uniform(i));
        }
        return linkedList;
    }
 /*   public static void sortForShuffling(Comparable[] a, int low, int high){
        if (high <= low) return;
        int mid = low + (high - low) / 2;
        sortForShuffling(a, low, mid);
        sortForShuffling(a, mid + 1, high);
        shufflingLinkedList(a, low, high);
    }
    public static void shufflingLinkedList(Comparable[] a, int low, int high){
       *//* int n = a.length;
        Comparable[] auxLinkedList =  new Comparable[n];
        for (int k = 0; k <= high; k++){
            auxLinkedList[k] = a[k];
        }*//*
        for (int i = low; i <= high; i++){
            a[i] = low + StdRandom.uniform(low, high - low);
        }
    }*/
    private static Ex2_2_18_ShufflingALinkedList.Node getMiddleNode(Ex2_2_18_ShufflingALinkedList<Comparable>.Node source){
        if (source == null) return null;
        Ex2_2_18_ShufflingALinkedList.Node slow = source;
        Ex2_2_18_ShufflingALinkedList.Node fast = source;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    @SuppressWarnings("unchecked")
    private static Ex2_2_18_ShufflingALinkedList.Node shuffle(Ex2_2_18_ShufflingALinkedList.Node firstHalf){
        if (firstHalf == null || firstHalf.next == null) return firstHalf;
        Ex2_2_18_ShufflingALinkedList.Node middle = getMiddleNode(firstHalf);
        Ex2_2_18_ShufflingALinkedList.Node secondHalf = middle.next;
        middle.next = null;
        return shuffleItems(shuffle(firstHalf), shuffle(secondHalf));
    }
    private static Ex2_2_18_ShufflingALinkedList.Node shuffleItems(Ex2_2_18_ShufflingALinkedList.Node firstHalf,
                                                                   Ex2_2_18_ShufflingALinkedList.Node secondHalf){
        Ex2_2_18_ShufflingALinkedList.Node dummyHead = new Ex2_2_18_ShufflingALinkedList<>().new Node();
        Ex2_2_18_ShufflingALinkedList.Node current = dummyHead;
        while (firstHalf != null && secondHalf != null){
            int random = StdRandom.uniform(2);
            if (random == 0){
                current.next = firstHalf;
                firstHalf = firstHalf.next;
            }else {
                current.next = secondHalf;
                secondHalf = secondHalf.next;
            }
            current = current.next;
        }
        current.next = firstHalf == null ? secondHalf : firstHalf;
        return dummyHead;
    }
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Ex2_2_18_ShufflingALinkedList<Comparable> linkedList = createLinkedList();
        StringJoiner initialList = new StringJoiner(" ");
        Ex2_2_18_ShufflingALinkedList<Comparable>.Node currentNode = linkedList.first;
        while (currentNode != null){
            initialList.add(String.valueOf(currentNode.item));
            currentNode = currentNode.next;
        }
        StdOut.print("Initial list: " + initialList.toString());
        StdOut.println();
        StdOut.println();
        Ex2_2_18_ShufflingALinkedList<Comparable>.Node newHead = shuffle(linkedList.first);
        linkedList.first = newHead;
        Ex2_2_18_ShufflingALinkedList.Node current = newHead;
        StringJoiner shuffledList = new StringJoiner(" ");
        while (current != null){
            shuffledList.add(String.valueOf(current.item));
            current = current.next;
        }
        StdOut.print("Shuffled list: " + shuffledList.toString());
    }
}