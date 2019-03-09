package linkedlists;

import edu.princeton.cs.algs4.StdOut;

/*
*   singly linked list, such as that 3 -> 8 -> 5 -> 1 -> 9 - > 11 - > null.
*   then we do write a method to reverse it which will look like as below:
*                                    <- 3 <- 8 <- 5 <- 1 <- 9 <- 11
*
* */
public class singlyLinkedList {
    // first, we initial node class
    private static Node head;
    private static class Node{
        int data;
        Node next;
        public Node(int data){
            this.data = data;
        }
    }
    private static void reverseSinglyLinkedList(){
        if (head == null || head.next == null){
            return;
        }
        Node p1 = head;
        Node p2 = p1.next;
        Node p3 = null;
        while (p2 != null){
            p3 = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = p3;
        }
        head.next = null;
        head = p1;
    }

    public static void main(String[] args) {
        // generate a singly linked list
        head = new Node(3);
        head.next = new Node(8);
        Node temp = head.next;
        temp.next = new Node(5);
        temp = temp.next;
        temp.next = new Node(1);
        temp = temp.next;
        temp.next = new Node(9);
        temp = temp.next;
        temp.next = new Node(11);
        // print out the current linked list
        temp = head;
        while (temp != null){
            StdOut.print(temp.data + " ");
            temp = temp.next;
        }
        reverseSinglyLinkedList();
        StdOut.println();
        temp = head;
        while (temp != null){
            StdOut.print(temp.data + " ");
            temp = temp.next;
        }
    }
}
