package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.StringJoiner;


@SuppressWarnings("unchecked")
public class Ex38_1<Item> implements Iterable<Item> {
    public class Node{
        Node next;
        Item item;
        Node prev;
    }
    private Node first;
    //private Node last;
    private int N;
    private Ex38_1(){
        this.first = null;
        //this.last = null;
        this.N = 0;
    }
    public boolean isEmpty(){return N == 0;}
    public int size(){return N;}

    public void insert(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }
    public Item delete(int k){
        //last = new Node();
        if (isEmpty()){throw new RuntimeException("Node is null");}
        /*if (first.next == null){
            first = null;

        }*/
        if (k < 0 && k > N){throw new RuntimeException("index overflow");}
        int count = 0;
        Node current;
        for (current = first; current.next != null; current = current.next){
            current.item = first.item;
            count++;
            if (count < k){}
            if (count == k){
                current.next = current.prev;
                N--;
                //current = null;
            }
        }
        return current.item;
    }

    public Iterator<Item> iterator(){
        return new linkIterator();
    }
    public class linkIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext(){return current != null;}
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Ex38_1<Integer> ex38_1 = new Ex38_1<>();
        for (int i = 0; i < 3; i++){
            ex38_1.insert(i);
        }


        StringJoiner linkedList = new StringJoiner(" ");
        for (int number : ex38_1){
            linkedList.add(String.valueOf(number));
        }
        StdOut.println("Numbers inside linkedList before deletion: ");
        StdOut.print(linkedList);

        //ex38_1.delete(3);

        StdOut.println("\nNumbers inside linkedList after deletion: ");
        ex38_1.delete(2);
        StringJoiner aLinkedList = new StringJoiner("");
        for (int number : ex38_1){
            aLinkedList.add(String.valueOf(number));
        }
        StdOut.print(linkedList);
    }
}
