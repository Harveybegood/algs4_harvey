package Chapter1_3_BagsQueuesStacks;


import edu.princeton.cs.algs4.StdOut;

/*
*   Write a method delete() that takes an int argument k and deletes the kth element in a linked list, if it exists.
*
* */
public class Ex20_deleteKthElement<Item> {
    private class Node{
        private Node next;
        private Item item;
    }
    private Node first;
    private int n;
    private int i = 1;
    public boolean isEmpty(){return first == null;}
    public int size(){return n;}
    public void push(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
    }
    public Item delete(int k){
        Item item;
        for (Node x = first; x != null; x = x.next){
            if (i == k){
                item = x.item;
                x = x.next;
                n--;
                return item;
            }
            i++;
        }
        return null;
    }

    public static void main(String[] args) {
        Ex20_deleteKthElement<String> deleteKthElement = new Ex20_deleteKthElement<>();
        deleteKthElement.push("T");
        deleteKthElement.push("J");
        deleteKthElement.push("B");
        deleteKthElement.push("O");
        deleteKthElement.push("X");
        deleteKthElement.push("Q");
        StdOut.println("Expected 6: " + deleteKthElement.size());
        StdOut.println("Expected with J: " + deleteKthElement.delete(5));
        StdOut.println("Expected 5: " + deleteKthElement.size());
    }
}
