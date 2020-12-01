package Chapter1_3_Bags_Queues_Stacks;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringJoiner;

/*
*   Move-to-front. Read in a sequence of characters from standard input and maintain the characters
*   in a linked list with no duplicates. When you read in a previously unseen character, insert it at
*   the front of the list. When you read in a duplicate character, delete it from the list and reinsert
*   it at the beginning. Name your program MoveToFirst: it implements the well-known move-to-front
*   strategy, which is useful for caching, data compression, and many other applications where items
*   that have been recently accessed are more likely to be reaccessed.
*
* */
public class Ex40<Item> implements Iterable<Item> {
    private class Node{
        Node next;
        Item item;
    }
    private Node first;
    private int size;

    private Set<Item> existingCharactersHashSet;
    private Ex40(){
        existingCharactersHashSet = new HashSet<>();
    }

    public boolean isEmpty(){return size == 0;}
    public int size(){return size;}

    @Override
    public Iterator<Item> iterator() {
        return new characterIterator();
    }

    public class characterIterator implements Iterator<Item>{
        Node current = first;
        public boolean hasNext(){return current != null;}
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public void insert(Item item){
        if (existingCharactersHashSet.contains(item)){delete(item);}
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        existingCharactersHashSet.add(item);
        size++;
    }
    public void delete(Item item){
        if (isEmpty()){throw new RuntimeException("list underflow");}
        Node current = first;
        if (current.item.equals(item)){
            first = current.next;
            size--;
        }
        else {
            for(current = first; current.next != null; current = current.next){
                if (current.next.item.equals(item)){
                    break;
                }
            }
            if (current.next != null){
                current.next = current.next.next;
                size--;
            }
        }
    }



    public static void main(String[] args) {
        //readSting, read a sequence of characters from standard input
        Ex40<Character> moveToFront = new Ex40<>();
        while (StdIn.hasNextChar()){
            moveToFront.insert(StdIn.readChar());
        }
        StringJoiner list = new StringJoiner(" ");
        for (char character : moveToFront){
            list.add(String.valueOf(character));
        }
        StdOut.println("Characters: " + list.toString());
    }
}
