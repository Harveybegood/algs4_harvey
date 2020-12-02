package Chapter1_3_BagsQueuesStacks;


import edu.princeton.cs.algs4.StdOut;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/*
*   Fail-fast iterator. Modify the iterator code in Stack to immediately throw a java.util.ConcurrentModificationException
*   if the client modifies the collection(via push() or pop()) during iteration? b).
*
*   Solution: Maintain a counter that counts the number of push() and pop() operations. When creating an iterator, store
*   this value as an Iterator instance variable. Before each call to hasNext() and next(), check that this value has not
*   changed since construction of the iterator; if it has, throw the exception.
*
* */
public class Ex50_FailFastIterator<Item> implements Iterable<Item> {
    private class Node{
        Item item;
        Node next;
    }
    private Node first;
    private int counter;
    private int n;
    public boolean isEmpty(){return n == 0;}
    public int size(){return n;}
    public void push(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
        counter++;
    }
    public Item pop(){
        if (isEmpty()){throw new RuntimeException("Stack underflow");}
        Item item = first.item;
        first = first.next;
        n--;
        counter++;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new stackIterator();
    }
    private class stackIterator implements Iterator<Item>{
         Item item;
         Node current;
         int currentCounter;
         public stackIterator(){
             currentCounter = counter;
             current = first;
         }

         public boolean hasNext(){
             if (counter != currentCounter){
                 throw new ConcurrentModificationException();
             }
             return current != null;
         }
         public Item next(){
             if (counter != currentCounter){
                 throw new ConcurrentModificationException();
             }
             item = current.item;
             current = current.next;
             return item;
         }
    }

    public static void main(String[] args) {
        Ex50_FailFastIterator<String> failFastIterator = new Ex50_FailFastIterator<>();
        StdOut.println("A java concurrent modification exception not expected\n");
        failFastIterator.push("T");
        failFastIterator.push("A");
        failFastIterator.push("N");
        failFastIterator.pop();
        for (String s : failFastIterator){
            StdOut.println(s + " ");
        }
        StdOut.println("\nA java concurrent modification exception expected");
        for (String s : failFastIterator){
            StdOut.print(s + " ");
            failFastIterator.pop();
        }
    }
}
