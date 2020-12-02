package Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Queue;

/*
*   Write a method find() that takes a linked list and a string key as arguments and returns true if some node in the list
*   has key as its item field, false otherwise.
*
* */
public class Ex21_Find<Item> {
    private class Node{
        Node next;
        Item item;
    }
    private int n;
    private Node first;
    public boolean isEmpty(){
        return first == null;
    }
    public int size(){
        return n;
    }
    public void push(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
    }
    public Item pop(){
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }
    public boolean find(Node nodes, String keys){
        while (nodes != null){
            for (int i = 0; i < keys.length(); i++){
                if (nodes.item.equals(keys.charAt(i))){
                    return true;
                }
            }
            nodes = nodes.next;
        }
        return false;
    }

    public static void main(String[] args) {
        Ex21_Find<String> ex21_find = new Ex21_Find<>();
        ex21_find.push("s");
        ex21_find.push("u");
        ex21_find.push("p");
        ex21_find.push("e");
        ex21_find.push("r");
        String keys1 = "argument";
        String keys2 = "abcd";
        StdOut.println("Expected true: " + ex21_find.find(ex21_find.first, keys1));
        StdOut.println("Expected false: " + ex21_find.find(ex21_find.first, keys2));

    }
}
