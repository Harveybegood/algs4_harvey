package Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/*
*   Move-To-Front. Read in a sequence of characters from standard input and maintain the characters in a linked list with
*   no duplicates. When you read in a previously unseen character, insert it at the front of the list. When you read in a
*   duplicate character, delete it from the list and reinsert it at the beginning. Name your program MoveToFront: it implements
*   the well-known move-to-front strategy, which is useful for caching, data compression, and many other applications where items
*   that have been recently accessed are more likely to be reaccessed.
*
* */
public class Ex40_MoveToFront<Item> implements Iterable<Item>{
    private class Node{
        Item item;
        Node next;
    }
    private Node first;
    private int n;
    public boolean isEmpty(){return n == 0;}
    public int size(){return n;}
    //String s = String.valueOf("s");
    // retrieve characters from standard input
    public void insertFront(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        n++;
        //Node current = first;
        Node pre = first;
        for(Node current = oldFirst; current != null; current = current.next){
            if (first.item.equals(current.item)){
                pre.next = current.next;
                //delDuplicate(current);
                //current = current.next;
                n--;
                break;
            }
            pre = current;
        }
        first.next = oldFirst;
    }
    public Iterator<Item> iterator(){
        return new StackIterator();
    }
    private class StackIterator implements Iterator<Item>{
        Item item;
        Node current = first;
        public boolean hasNext(){
            return current != null;
        }
        public Item next(){
            item = current.item;
            current = current.next;
            return item;
        }
    }
    public static void main(String[] args) {
        String s1 = StdIn.readString();
        //String[] s = s1.split("//s+");
        Ex40_MoveToFront<String> moveToFront = new Ex40_MoveToFront<>();
        //String s1 = "cbca";
        int N = s1.length();
        for (int i = 0; i < N; i++){
            moveToFront.insertFront(String.valueOf(s1.charAt(i)));
        }
        for (String c : moveToFront){
            StdOut.print(c + " ");
        }
        StdOut.println();
    }
}
