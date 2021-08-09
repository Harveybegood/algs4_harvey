package Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;


/*
*   Implement a nested class DoubleNode for building doubly-linked lists, where each node contains a reference to the item
*   preceding it and the item following it in the list(null if there is no such item). Then implement static methods for
*   the following tasks: insert at the beginning, insert at the end, remove from the beginning, remove from the end,
*   insert before a given node, insert after a given node, and remove a given node.
*
* */
@SuppressWarnings("unchecked")
public class Ex31_DoublyLinkedList<Item>{
    public static class DoubleNode<Item> implements Iterable<Item>{
        Item item;
        DoubleNode next;
        DoubleNode prev;
        /*public DoubleNode(Item item, DoubleNode next, DoubleNode prev){
            this.item = item;
            this.next = next;
            this.prev = prev;
        }*/
        //private DoubleNode first;
        private DoubleNode first;
        private DoubleNode last;
        public void insertBeginning(Item item){
            if (isEmpty()){
                first = new DoubleNode<>();
                first.item = item;
                last = first;
                n++;
            }
            else {
                DoubleNode<Item> newFirst = first;
                first = new DoubleNode<>();
                first.item = item;
                first.next = newFirst;
                newFirst.prev = first;
                n++;
            }
        }
        public DoubleNode insertBeginningAndReturnNode(Item item){
            if (isEmpty()){
                first = new DoubleNode<>();
                first.item = item;
                last = first;
                n++;
            }
            else {
                DoubleNode<Item> newFirst = first;
                first = new DoubleNode<>();
                first.item = item;
                first.next = newFirst;
                newFirst.prev = first;
                n++;
            }
            return first;
        }
        public void insertEnd(Item item){
            if (isEmpty()){
                last = new DoubleNode<>();
                last.item = item;
                first = last;
                n++;
            }
            else {
                DoubleNode<Item> oldLast = last;
                last = new DoubleNode<>();
                last.item = item;
                oldLast.next = last;
                last.prev = oldLast;
                n++;
            }
        }
        public Item removeBeginning(){
            if (isEmpty()){throw new UnsupportedOperationException("Double linked list is under flow.");}
            Item item = (Item) first.item;
            first = first.next;
            first.next.prev = null;
            n--;
            return item;
        }
        public Item removeEnd(){
            if (isEmpty()) {
                throw new UnsupportedOperationException("Double linked list is under flow.");
            }
            Item item = (Item) last.item;
            last.prev.next = null;
            last = last.prev;
            n--;
            return item;
        }
        public DoubleNode removeEndAndReturnNode(){
            if (isEmpty()){
                throw new UnsupportedOperationException("Double linked list is under flow");
            }
            last.prev.next = null;
            last = last.prev;
            n--;
            return first;
        }
        private void insertBeforeNode(Item item1, Item item2){
            if (isEmpty()){
                first = new DoubleNode<>();
                first.item = item1;
                last = first;
                n++;
            }
            else {
                DoubleNode<Item> current;
                for (current = first; current != null; current = current.next){
                    if (current.item == item2){
                        DoubleNode<Item> insertedNode = new DoubleNode<>();
                        insertedNode.item = item1;
                        if (current.item == first.item){
                            insertBeginning(item1);
                            return;
                        }
                        /*if (current.item == last.item){
                            insertEnd(item1);
                            return;
                        }*/
                        insertedNode.next = current;
                        insertedNode.prev = current.prev;
                        current.prev.next = insertedNode;
                        current.prev = insertedNode;
                        n++;
                    }
                }
            }
        }
        private void insertAfterNode(Item item1, Item item2){
            if (isEmpty()){
                last = new DoubleNode<>();
                last.item = item1;
                first = last;
                n++;
            }
            else {
                DoubleNode<Item> current;
                for (current = first; current != null; current = current.next){
                    if (current.item == item2){
                        DoubleNode<Item> insertedNode = new DoubleNode<>();
                        insertedNode.item = item1;
                        /*if (current.item == first.item){
                            insertBeginning(item1);
                            return;
                        }*/
                        if (current.item == last.item){
                            insertEnd(item1);
                            return;
                        }
                        insertedNode.next = current.next;
                        current.next = insertedNode;
                        insertedNode.prev = current;
                        current.next.prev = insertedNode;
                        n++;
                    }
                }
            }
        }
        private Item removeNode(Item item){
            if (isEmpty()){throw new RuntimeException("Double linked list is under flow.");}
            Item item1 = (Item) new Object();
            DoubleNode<Item> current;
            for (current = first; current != null; current = current.next){
                if (current.item == item){
                    item1 = current.item;
                    if (current.item == first.item){
                        return removeBeginning();
                    }
                    if (current.item == last.item){
                        return removeEnd();
                    }
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    current.next = null;
                    current.prev = null;
                }
            }
            return item1;
        }
        public int size(){return n;}

        @Override
        public Iterator<Item> iterator() {
            return new doubleLinkedList();
        }
        private class doubleLinkedList implements Iterator<Item>{
            Item item;
            DoubleNode<Item> current = first;
            public boolean hasNext(){
                return current != null;
            }
            public Item next(){
                item = current.item;
                current = current.next;
                return item;
            }
        }
    }
    public static int n;
    public static boolean isEmpty(){return n == 0;}
    // insert at the beginning
    public static <Item> void insertAtBeginning(Item item, DoubleNode doubleNode){
        doubleNode.insertBeginning(item);
    }
    public <Item> void insertAtTheBeginning(Item item, DoubleNode doubleNode){doubleNode.insertBeginning(item);}
    public DoubleNode insertAtTheBeginningAndReturnNode(Item item, DoubleNode doubleNode){return doubleNode.insertBeginningAndReturnNode(item);}
    // insert at the end
    public static <Item> void insertAtEnd(Item item, DoubleNode doubleNode){
        doubleNode.insertEnd(item);
    }
    // remove from the beginning
    public static void removeFromBeginning(DoubleNode doubleNode){
        StdOut.println(doubleNode.removeBeginning());
    }
    // remove from the end
    public static void removeFromEnd(DoubleNode doubleNode){
        StdOut.println(doubleNode.removeEnd());
    }
    public Item removeFromEndAndReturnItem(){return (Item) new DoubleNode<>().removeEndAndReturnNode().item.toString();}
    // insert before a given node
    public static <Item> void insertBeforeGivenNode(Item item1, Item item2, DoubleNode doubleNode){
        doubleNode.insertBeforeNode(item1, item2);
    }
    // insert after a given node
    public static <Item> void insertAfterGivenNode(Item item1, Item item2, DoubleNode doubleNode){
        doubleNode.insertAfterNode(item1, item2);
    }
    // remove a given node
    public static <Item> Item removeGivenNode(Item item, DoubleNode<Item> doubleNode){
        return doubleNode.removeNode(item);
    }

    public static void main(String[] args) {
        Ex31_DoublyLinkedList doublyLinkedList = new Ex31_DoublyLinkedList();

        DoubleNode<String> doubleNode = new DoubleNode<>();
        insertAtBeginning("R", doubleNode);
        insertAtBeginning("H", doubleNode);
        insertAtEnd("T", doubleNode);
        insertAtEnd("X", doubleNode);
        StdOut.println("Insert from beginning and from end");
        for (String s : doubleNode){
            StdOut.print(s + " ");
        }
        StdOut.println("\n Remove from beginning and from end");
        removeFromBeginning(doubleNode);
        removeFromEnd(doubleNode);
        for (String s : doubleNode){
            StdOut.print(s + " ");
        }
        StdOut.println("\n Insert before a given node and after a given node");
        insertAtBeginning("H", doubleNode);
        insertAtEnd("X", doubleNode);
        insertBeforeGivenNode("K", "R", doubleNode);
        insertAfterGivenNode("W", "X", doubleNode);
        for (String s : doubleNode){
            StdOut.print(s + " ");
        }
        StdOut.println("\n Remove a given node");
        removeGivenNode("W", doubleNode);
        for (String s : doubleNode){
            StdOut.print(s + " ");
        }
    }
}
