package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/*
*   Write a function that takes the first Node in a linked list as argument and (destructively) reverses the list, returning
*   the first node in the result.
*
*   Iterative solution: To accomplish this task, we maintain references to three consecutive nodes in the linked list.
*   reverse, first, and second. At each iteration, we extract the node first from the original linked and insert it at the
*   beginning of the reversed list. We maintain the invariant that first is the first node of what's left of the original
*   list, second is the second node of what's left of the original list, and reverse is the first node of the resulting
*   reversed list.
*
*   When writing code involving linked lists, we must always be careful to properly handle the exceptional cases(when the
*   linked list is empty, when the list has only one or two nodes) and the boundary cases (dealing with the first or last
*   item). This is usually much trickier than handling the normal cases.
*
*   Recursive solution: Assuming the linked list has N nodes, we recursively reverses the last N-1 nodes, and then carefully
*   append the first node to the end.
*
* */
public class Ex30_ReverseList<Item> implements Iterable<Item>{
    private class Node{
        Node next;
        Item item;
    }
    private Node first;
    /*private Node second;
    private Node reverse;*/
    private int N;
    public void push(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }
    public Item pop(){
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }
    public boolean isEmpty(){return first == null;}
    public int size(){return N;}

    // iterative solution
    public void IterativeSolution(){
        first = IterativeSolution(first);
    }
    public Node IterativeSolution(Node x){
        Node first = x;
        Node reverse = null;
        while (first != null){
            Node second = first.next;
            first.next = reverse;
            reverse = first;
            first = second;
        }
        return reverse;
    }
    public Iterator<Item> iterator(){
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item>{
        Node current = first;
        public boolean hasNext(){return current != null;}
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    // recursive solution
    public void RecursiveSolution(){
        first = RecursiveSolution(first);
    }
    public Node RecursiveSolution(Node first){
        if (first == null) return null;
        if (first.next == null) return first;
        Node second = first.next;
        Node rest = RecursiveSolution(second);
        second.next = first;
        first.next = null;
        return rest;
    }

    public static void main(String[] args) {
        Ex30_ReverseList<Integer> reverseList = new Ex30_ReverseList<>();
        reverseList.push(5);
        reverseList.push(7);
        reverseList.push(2);
        reverseList.push(9);
        StdOut.println("The order before reverse");
        /*for (int i = 0; i < 4; i++){
            StdOut.print(reverseList.pop() + " ");
        }*/
        for (int i : reverseList){
            StdOut.print(i + " ");
        }
        StdOut.println("\nThe order after reverse");
        reverseList.IterativeSolution();
        for (int i : reverseList){
            StdOut.print(i + " ");
        }
        StdOut.println();
        reverseList.RecursiveSolution();
        for (int i : reverseList){
            StdOut.print(i + " ");
        }
    }
}
