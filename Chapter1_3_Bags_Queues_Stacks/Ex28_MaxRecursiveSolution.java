package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.StdOut;

/*
*   Develop a recursive solution to the previous question.
*
* */
public class Ex28_MaxRecursiveSolution<Item extends Comparable<Item>> {
    private class Node{
        Node next;
        Item item;
    }
    private Node first;
    private int N;
    private Item max;
    private Node temp;
    public boolean isEmpty(){return  N == 0;}
    public int size(){return N;}
    public void push(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }
    public Item maxRecursive(){
        max = first.item;
        temp = first;
       return maxRecursive(first);
    }
    private Item maxRecursive(Node node){
        if (node == null){
            return null;
        }
        if (node.next != null && temp.item.compareTo(node.next.item) < 0){
            temp = node.next;
            max = temp.item;
            return maxRecursive(temp);
        }
        if(node.next != null && temp.item.compareTo(node.next.item) >= 0){
            return maxRecursive(node.next);
        }
        return max;
    }

    public static void main(String[] args) {
        Ex28_MaxRecursiveSolution<Integer> maxRecursiveSolution = new Ex28_MaxRecursiveSolution<>();
        maxRecursiveSolution.push(5);
        maxRecursiveSolution.push(8);
        maxRecursiveSolution.push(6);
        maxRecursiveSolution.push(9);
        maxRecursiveSolution.push(3);
        StdOut.println(maxRecursiveSolution.maxRecursive());
    }
}
