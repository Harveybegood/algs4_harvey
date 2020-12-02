package Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Ex41_CopyQueueRecursiveSolution<Item> implements Iterable<Item> {
    private class Node{
        Item item;
        Node next;
        public Node(Node x){
            item = x.item;
            if (x.next != null){
                next = new Node(x.next);
            }
        }
        public Node(){}
    }
    private Node first;
    private Node last;
    private int n;
    public boolean isEmpty(){return n == 0;}
    public int size(){return n;}
    public void enqueue(Item item){
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()){
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
    public Iterator<Item> iterator(){
        return new queueIterator();
    }
    private class queueIterator implements Iterator<Item>{
        Node current;
        Item item;
        public queueIterator(){
            this.current = first;
        }
        public boolean hasNext(){return current != null;}
        public Item next(){
            item = current.item;
            current = current.next;
            return item;
        }
    }
    @SuppressWarnings("unchecked")
    public Ex41_CopyQueueRecursiveSolution(){}
    public Ex41_CopyQueueRecursiveSolution(Ex41_CopyQueueRecursiveSolution queue){
        first = new Node(queue.first);
    }

    public static void main(String[] args) {
        Ex41_CopyQueueRecursiveSolution<String> copyQueueRecursiveSolution = new Ex41_CopyQueueRecursiveSolution<>();
        copyQueueRecursiveSolution.enqueue("a");
        copyQueueRecursiveSolution.enqueue("b");
        copyQueueRecursiveSolution.enqueue("c");
        Ex41_CopyQueueRecursiveSolution<String> queue = new Ex41_CopyQueueRecursiveSolution<>(copyQueueRecursiveSolution);
        StdOut.print(queue.dequeue());
        StdOut.print(queue.dequeue());
        StdOut.print(queue.dequeue());
    }
}
