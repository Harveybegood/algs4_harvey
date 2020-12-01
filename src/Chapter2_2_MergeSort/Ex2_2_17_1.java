package Chapter2_2_MergeSort;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.StringJoiner;

public class Ex2_2_17_1<Item> implements Iterable<Item> {
    private class Node{
        Node next;
        Item item;
        Node(){}
        Node(Item item){this.item = item;}
    }
    private int size;
    private Node first;
    public boolean isEmpty(){return size == 0;}
    public int size(){return size;}
    public void add(Item item){
        Node newNode = new Node(item);
        //first = new Node();
        newNode.next = first;
        first = newNode;
        size++;
    }
    public Iterator<Item> iterator(){
        return new ListIterator();
    }
    public class ListIterator implements Iterator<Item>{
        Node current = first;
        public boolean hasNext(){return current != null;}
        public Item next(){
            Item item = current.item;
            current =  current.next;
            return item;
        }
    }
    private static Ex2_2_17_1<Comparable> createList(){
        Ex2_2_17_1<Comparable> linkedList = new Ex2_2_17_1<>();
        for (int i = 0; i < 10; i++){
            linkedList.add(StdRandom.uniform(1, 20));
        }
        return linkedList;
    }

/*    public static void main(String[] args) {
        Ex2_2_17_1<Comparable> linkedList = createList();
        Ex2_2_17_1.Node newSourceNode = mergeSort(linkedList.first);
        linkedList.first = newSourceNode;
        StringJoiner sortedList = new StringJoiner(" ");
        while (newSourceNode != null){
            sortedList.add(String.valueOf(newSourceNode.item));
            newSourceNode = newSourceNode.next;
        }
        StdOut.println("Sorted list: " + sortedList.toString());
    }*/
   /* private static Ex2_2_17_1.Node mergeSort(Ex2_2_17_1<Comparable>.Node sourceNode){
        if (sourceNode == null || sourceNode.next == null) return sourceNode;
        int listSize = 1;
        int numberOfMerge;
        int leftSize;
        int rightSize;
        Ex2_2_17_1<Comparable>.Node tail;
        Ex2_2_17_1<Comparable>.Node left;
        Ex2_2_17_1<Comparable>.Node right;
        Ex2_2_17_1<Comparable>.Node next;
        do {
            numberOfMerge = 0;
            left = sourceNode;
            tail = null;
            sourceNode = null;
            while (left != null){
                numberOfMerge++;
                right = left;
                leftSize = 0;
                rightSize = leftSize;
                while (right != null && leftSize < listSize){
                    leftSize++;
                    right = right.next;
                }
                while (leftSize > 0 || (rightSize > 0 && right != null)){
                    if (leftSize == 0){
                        next = right;
                        right = right.next;
                        rightSize++;
                    }else if (rightSize == 0 || right == null){
                        next = left;
                        left = left.next;
                        leftSize--;
                    }else if (left.item.compareTo(right.item) < 0){
                        next = left;
                        left = left.next;
                        leftSize--;
                    }else {
                        next = right;
                        right = right.next;
                        rightSize--;
                    }
                    if (tail != null){
                        tail.next = next;
                    }else {
                        sourceNode = next;
                    }
                    tail = next;
                }
                left = right;
            }
            if (tail != null){
                tail.next = null;
            }
            listSize *= 2;
        }while (numberOfMerge > 1);
        return sourceNode;
    }*/
@SuppressWarnings("unchecked")
    private static Ex2_2_17_1.Node mergeSortRecursive(Ex2_2_17_1<Comparable>.Node sourceNode){
        if (sourceNode == null || sourceNode.next == null)return  sourceNode;
        Ex2_2_17_1.Node middle = getMiddle(sourceNode);
        Ex2_2_17_1.Node secondHalf = middle.next;
        middle.next = null;
        return merge(mergeSortRecursive(sourceNode), mergeSortRecursive(secondHalf));
    }
    private static Ex2_2_17_1.Node getMiddle(Ex2_2_17_1<Comparable>.Node sourceNode){
        if (sourceNode == null) return null;
        Ex2_2_17_1.Node slow = sourceNode;
        Ex2_2_17_1.Node fast = sourceNode;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    @SuppressWarnings("unchecked")
    private static Ex2_2_17_1.Node merge(Ex2_2_17_1<Comparable>.Node firstHalf, Ex2_2_17_1<Comparable>.Node secondHalf){
        Ex2_2_17_1<Comparable>.Node dummyHead = new Ex2_2_17_1<Comparable>().new Node();
        Ex2_2_17_1<Comparable>.Node current = dummyHead;
        while (firstHalf != null && secondHalf != null){
            if (firstHalf.item.compareTo(secondHalf.item) <= 0){
                current.next = firstHalf;
                firstHalf = firstHalf.next;
            }else {
                current.next = secondHalf;
                secondHalf = secondHalf.next;
            }
            current = current.next;
        }
        current.next = (firstHalf == null) ? secondHalf : firstHalf;
        return dummyHead;
    }

    public static void main(String[] args) {
        Ex2_2_17_1<Comparable> linkedList = createList();
        Ex2_2_17_1.Node sourceCode = mergeSortRecursive(linkedList.first);
        StringJoiner stringJoiner = new StringJoiner(" ");
        while (sourceCode != null){
            stringJoiner.add(String.valueOf(sourceCode.item));
            sourceCode = sourceCode.next;
        }
    }
}
