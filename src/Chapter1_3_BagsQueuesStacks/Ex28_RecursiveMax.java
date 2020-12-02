package Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.StdOut;


/*
*   Develop a recursive solution to the previous question.
*
* */
@SuppressWarnings("unchecked")
public class Ex28_RecursiveMax<Item extends Comparable> {
    private class Node{
        Item item;
        Node next;
        public Node(){}
    }
    private Node first;
    private int n;
    public boolean isEmpty(){return first == null;}
    public int size(){return n;}
    public void push(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    public Item max(Node node){
        if (isEmpty()){
            return null;
        }
        Item item = node.item;
        return max(node.next, item);
    }
    public Item max(Node node, Item item){
        if (node.next == null){
            return item;
        }
        if (item.compareTo(node.item) < 0){
            item = node.item;
        }
        return max(node.next, item);
    }

    public static void main(String[] args) {
        Ex28_RecursiveMax<String> recursiveMax = new Ex28_RecursiveMax<>();
        recursiveMax.push("p");
        recursiveMax.push("b");
        recursiveMax.push("q");
        recursiveMax.push("k");
        recursiveMax.push("l");
        StdOut.println(recursiveMax.max(recursiveMax.first) + " expected q");
    }
}
