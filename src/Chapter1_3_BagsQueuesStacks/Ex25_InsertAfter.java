package Chapter1_3_BagsQueuesStacks;


import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/*
*   Write a method insertAfter() that takes two linked-list node arguments and inserts the second after the first on its
*   list(and does nothing if either argument is null).
*
* */
public class Ex25_InsertAfter<Item> implements Iterable<Item>{
    private  class Node{
        Item item;
        Node next;
        public Node(Item item){
            this.item = item;
        }
    }
    private Node first;
    private int N;

    public boolean isEmpty(){return N == 0;}
    public int size(){return N;}
    public void push(Item item){
        Node oldNode = first;
        first = new Node(item);
        first.item = item;
        first.next = oldNode;
        N++;
    }
    public Item pop(){
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }
   /* public Node generateNode(Item item){
        Node node = new Node();
        node.item = item;
        return node;
    }*/
    public void insertSecondAfterFirst(Node node1, Node node2){
        while (node1 != null){
            if (node1.next == null){
                node1.next = node2;
                break;
            }
            node1 = node1.next;
        }
    }
    public Iterator<Item> iterator(){
        return new linkedListIterator();
    }

    public class linkedListIterator implements Iterator<Item> {
        Node node = first;
        Item item;
        public boolean hasNext(){return node != null;}
        public Item next(){
            item = node.item;
            node = node.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Ex25_InsertAfter<String> insertAfter1 = new Ex25_InsertAfter<>();
        /*insertAfter1.push("C");
        insertAfter1.push("B");
        insertAfter1.push("A");*/
        Ex25_InsertAfter<String> insertAfter2 = new Ex25_InsertAfter<>();
        /*insertAfter2.push("F");
        insertAfter2.push("E");
        insertAfter2.push("D");*/

      /*  for (int i = 0; i < insertAfter1.size(); i++){
            StdOut.print(insertAfter1.pop() + " ");
        }
        for (int i = 0; i < insertAfter2.size(); i++){
            StdOut.print(insertAfter2.pop() + " ");
        }*/
       /* for (String s : insertAfter1){
            StdOut.print(s + " ");
        }
        StdOut.println();
        for (String s : insertAfter2){
            StdOut.print(s + " ");
        }*/
        Ex25_InsertAfter<String> ex25_insertAfter = new Ex25_InsertAfter<>();
        ex25_insertAfter.insertSecondAfterFirst(insertAfter1.new Node("C"), insertAfter2.new Node("F"));
        for (String s : ex25_insertAfter){
            StdOut.print(s + " ");
        }
    }
}
