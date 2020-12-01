package Chapter1_3_Bags_Queues_Stacks;


import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.StringJoiner;

/*
*   Copy a queue. Create a new constructor so that
*       Queue<Item> r = new Queue<Item>(q);
*   makes r a reference to a new and independent copy of the queue q. You should be able to push and pop
*   either q or r without influencing the other. Hint: Delete all of the elements from q and add these
*   elements to both q and r.

* */
public class Ex41<Item> extends Queue<Item> {
 /*   private class Node{
        Item item;
        Node next;

        Node(Node x){
            item = x.item;
            if (x.next != null){next = new Node(x.next);}  // Recursive solution
        }
    }*/

  /*  private Node first;
    public Queue<Item> queueCopy;
    private int size = 0;
    public boolean isEmpty(){return size == 0;}
    public int size(){return size;}*/

    private Ex41(Queue<Item> q){
      for (Item item : q){
          enqueue(item);
      }
    }

   /* public void enqueue(Item item){}
    public Node dequeue(){}*/

    /*public Iterator<Item> iterator(){
        return new queueIterator();
    }

    public class queueIterator implements Iterator<Item>{

        public boolean hasNext(){}
        public Item next(){}
    }*/

    public static void main(String[] args) {
        Queue<String> cp = new Queue<>();
        cp.enqueue("a");
        cp.enqueue("b");
        cp.enqueue("c");

        Ex41<String> queueCp = new Ex41<>(cp);
        queueCp.enqueue("d");
        queueCp.enqueue("e");

        StringJoiner m = new StringJoiner(" ");
        for (String alph : cp){
            m.add(String.valueOf(alph));
        }
        StdOut.println(m);
        StringJoiner n = new StringJoiner(" ");
        for (String alpha : queueCp){
            n.add(String.valueOf(alpha));
        }
        StdOut.println(n);

    }
}
