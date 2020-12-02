package Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.StdOut;

/*
*   Add a method peek() to Stack that returns the most recently inserted item on the stack(without popping it).
*
* */
public class Ex07_Peek<Item> {
    public class Node{
        Item item;
        Node next;
    }
    private Node first;
    private int n;
    public boolean isEmpty(){return n == 0;}
    public int size(){return n;}
    public void push(Item item){
        if (item == null) return;
       /* if (isEmpty()){
            //first.next = null;
            first.item = item;
        }*/
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
    }
    public Item pop(){
        if (isEmpty()) return null;
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }
    public Item peek(){
        if (isEmpty()) return null;
        return first.item;
    }

    public static void main(String[] args) {
        Ex07_Peek peek = new Ex07_Peek();
        peek.push(3);
        peek.push(5);
        peek.push(2);
        peek.push(7);
        StdOut.println("Expected 7: " + peek.peek() + "  Size: 4  " + peek.size());
        StdOut.println("Expected 7: " + peek.pop() + "  Size: 3  " + peek.size());
    }
}
