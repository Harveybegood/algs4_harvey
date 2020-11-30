package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.StringJoiner;

/*
*   Implement a nested class DoubleNode for building doubly-linked lists, where each node contains a reference to the
*   item preceding it and the item following it in the list(null if there is no such item). Then implement static
*   methods for the following tasks: insert at the beginning, insert at the end, remove from the beginning, remove
*   from the end, insert before a given node, insert after a given node, and remove a given node.
*
* */
public class Ex31<Item> implements Iterable<Item>{
    public class DoubleNode{
        Item item;
        DoubleNode previous;
        DoubleNode next;
    }
    private DoubleNode first;
    private DoubleNode last;
    private int N;

    public boolean isEmpty(){return N == 0;}
    public int size(){return N;}

    public void insertAtBeginning(Item item){
            DoubleNode oldNode = first;
            first = new DoubleNode();
            first.item = item;
            first.next = oldNode;

            if (oldNode != null){oldNode.previous = first;}
            if (last == null){last = first;}
            N++;
    }
    public void insertAtEnd(Item item){
        if (isEmpty()){
            DoubleNode first = new DoubleNode();
            first.item = item;
        }else {
            DoubleNode oldNode = last;
            last = new DoubleNode();
            last.item = item;
            last.previous = oldNode;
        }
        N++;
    }
    public void removeFromBeginning(Item item){
        if (isEmpty()){throw new RuntimeException("No node in linked list!");}
        if (first.next == null && last.previous == null){first.item = null;--N;}
        first.next = first;
        first.next.item = item;
        --N;
    }
    public void removeFromEnd(Item item){
        if (isEmpty()){throw new RuntimeException("No node in linked list!");}
        if (first.next == null && last.previous == null){last.item = null; --N;}
        last.previous = last;
        last.previous.item = item;
        --N;
    }
    public void insertBeforeNode(Item beforeItem, Item item){
        if (isEmpty()){
            DoubleNode first = new DoubleNode();
            first.item = item;
        }
        DoubleNode currentNode;
        for(currentNode = first; currentNode.next != null; currentNode = currentNode.next){
            if (currentNode.item.equals(beforeItem)){
                break;
            }
        }
        if (currentNode.next != null){
            DoubleNode newNode = new DoubleNode();
            newNode.item = item;
            DoubleNode previousNode = currentNode.previous;
            currentNode.previous = newNode;
            newNode.next = currentNode;
            newNode.previous = previousNode;
            if (newNode.previous == null){
                first = newNode;
            }else {
                newNode.previous.next = newNode;
            }
            N++;
        }

    }
    public void insertAfterNode(Item afterNode, Item item){
        if (isEmpty()){}
        DoubleNode currentNode;
        for (currentNode = first; currentNode.next != null; currentNode = currentNode.next){
            if (currentNode.next.equals(afterNode)){break;}
        }
        if (currentNode.next != null){
            DoubleNode newNode = new DoubleNode();
            newNode.item = item;
            DoubleNode nextNode = currentNode.next;
            currentNode.next = newNode;
            newNode.previous = currentNode;
            newNode.next = nextNode;
            if (newNode.next == null){
                last = newNode;
            }else {
                newNode.next.previous = newNode;
            }
        }
        N++;
    }
    public Item removeTheNode(int nodeIndex){
        if (isEmpty() || nodeIndex <= 0 || nodeIndex > N){return null;}
        boolean startFromTheBeginning = nodeIndex <= size()/2;
        int index = startFromTheBeginning ? 1 : size();
        DoubleNode currentNode = startFromTheBeginning ? first : last;
        while (currentNode != null){
            if (nodeIndex == index){break;}
            if (startFromTheBeginning){index++;}
            else {index--;}
            currentNode = startFromTheBeginning ? currentNode.next : currentNode.previous;
        }
        @SuppressWarnings("ConstantConditions")
        Item item = currentNode.item;
        DoubleNode previousNode = currentNode.previous;
        DoubleNode nextNode = currentNode.next;
        if (previousNode != null){
            previousNode.next = nextNode;
        }
        if (nextNode != null){nextNode.previous = previousNode;}
        if (currentNode == first){first = nextNode;}
        if (currentNode == last){last = previousNode;}
        N--;
        return item;
    }

    private class DoublyLinkedListIterator implements Iterator<Item>{
        int index = 0;
        DoubleNode currentNode = first;
        public boolean hasNext(){return index < size();}
        public Item next(){
            Item item;
            item = currentNode.item;
            currentNode = currentNode.next;
            index++;
            return item;
        }
    }

    public Iterator<Item> iterator(){
        return new DoublyLinkedListIterator();
    }

    public static void main(String[] args) {
        Ex31<Integer> doublyLinkedlist = new Ex31<>();
        doublyLinkedlist.insertAtBeginning(10);
        doublyLinkedlist.insertAtBeginning(30);
        doublyLinkedlist.insertAtEnd(99);

        StringJoiner doublyLinedListItems = new StringJoiner(" - ");
        for (int item : doublyLinkedlist){
            doublyLinedListItems.add(String.valueOf(item));
        }

        StdOut.println("Doubly Linked list items after initial insert: " + doublyLinedListItems.toString());
    }

}
