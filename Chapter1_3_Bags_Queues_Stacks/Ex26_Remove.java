package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/*
*   Write a method remove() that takes a linked list and a string key as arguments and removes all of the nodes in the
*   list that have key as its item field.
*
* */
public class Ex26_Remove<Item> implements Iterable<Item>{
    public class Node{
        Node next;
        Item item;
        public Node(){}
    }
    public void push(Item item){
        Node oldNode = first;
        first = new Node();
        first.item = item;
        first.next = oldNode;
        N++;

    }
    public Item get(int k){
        int i = 0; 
        Node current;
        for (current = first; current != null; current = current.next){
            if (i == k){
                return current.item;
            }
            i++;
        }
        return null;
    }
    public boolean isEmpty(){return N == 0;}
    public int size(){return N;}
    public void remove(Ex26_Remove<String> linkedList, String key){
        for (int i = 0; i < linkedList.size(); i++){
            if (linkedList.get(i).equals(key)){
                delete(i);
            }
        }
    }
    public void delete(int k){
        int i = 0;
        Node current;
        for (current = first; current != null; current = current.next){
            if (i == k){
                current = current.next;
                N--;
            }
            i++;
        }
    }
    public Node first;
    public int N;
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
    public static void main(String[] args) {
        Ex26_Remove<String> ex26_remove = new Ex26_Remove<>();
        ex26_remove.push("s");
        ex26_remove.push("e");
        ex26_remove.push("x");
        ex26_remove.push("a");
        ex26_remove.push("r");
        ex26_remove.push("t");
        StdOut.println("Before removing");
        for (String s : ex26_remove){
            StdOut.print(s + " ");
        }
        StdOut.println("\nAfter removing");
        ex26_remove.remove(ex26_remove, "e");
        for (String s : ex26_remove){
            StdOut.print(s + " ");
        }
    }
}
