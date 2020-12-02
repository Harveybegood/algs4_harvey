package Chapter1_3_BagsQueuesStacks;


import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/*
*   Copy a queue. Create a new constructor so that
*
*       Queue<Item> r = new Queue<Item>(q)
*
*   makes r a reference to a new and independent copy of the queue q. You should be able to push and pop from either q or r
*   without influencing the other. Hint: Delete all of the elements from q and add these elements to both q and r.
*
* */
@SuppressWarnings("unchecked")
public class Ex41_CopyaQueue<Item> implements Iterable<Item>{
    // this is inner class for help
    private class Node{
        Item item;
        Node next;
        /*public Node(Node x){
            item = x.item;
            if (x.next != null){
                next = new Node(x.next);
            }
        }*/
        public Node(Node x){
            this.item = x.item;
            this.next = x.next;
        }
        public Node(){}

    }
    private Node first;
    private Node last;
    private int n;
    public boolean isEmpty(){return  n == 0;}
    public int size(){return n;}
    public void enqueue(Item item){
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (first == null){
            first = last;
        }
        else {
            oldLast.next = last;
        }
        n++;
    }
    public Item dequeue(){
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    // This new constructor implements the copy function from a queue being as argument
    public Ex41_CopyaQueue(Ex41_CopyaQueue<Item> s){
        if (s.first != null){
            first = new Node(s.first);
            for (Node x = first; x.next != null; x = x.next){
                x.next = new Node(x.next);
            }
        }

    }
    public Ex41_CopyaQueue(){}
    public Iterator<Item> iterator(){
        return new queueIterator();
    }
    private class queueIterator implements Iterator<Item>{
        Node current = first;
        Item item;
        public boolean hasNext(){return current != null;}
        public Item next(){
            item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {

        Ex41_CopyaQueue<String> copyaQueue = new Ex41_CopyaQueue<>();
        copyaQueue.enqueue("a");
        copyaQueue.enqueue("b");
        copyaQueue.enqueue("c");
        Ex41_CopyaQueue<String> copyQueue = new Ex41_CopyaQueue<>(copyaQueue);
        for (String s : copyQueue){
            StdOut.print(s + " ");
        }
        StdOut.println();
        copyaQueue.enqueue("d");
        for (String s : copyaQueue){
            StdOut.print(s + " ");
        }
        StdOut.println();
        copyQueue.dequeue();
        copyQueue.dequeue();
        for (String s : copyQueue){
            StdOut.print(s + " ");
        }
    }
}
