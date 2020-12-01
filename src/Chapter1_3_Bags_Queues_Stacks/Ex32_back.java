package Chapter1_3_Bags_Queues_Stacks;

//import static edu.princeton.cs.algs4.StdIn.isEmpty;

import edu.princeton.cs.algs4.StdOut;

public class Ex32_back<Item> {
   private class Node{
       Item item;
       Node next;
       Node previous;
       Node(){};
       Node(Item item, Node previous, Node next){
           this.item = item;
           this.previous = previous;
           this.next = next;
       }
       private Node first;
       private Node last;
       private int N;
       //public boolean isEmpty(){return  N == 0;}
       //public int size(){return N;}
       Node insertAfter(Item item){
           Node newNode = new Node(item, this,this);
           if (next != null){next.previous = newNode;}
           next = newNode;
           return newNode;
       }
       Node insertBefore(Item item){
           Node newNode =  new Node(item, this, this);
           if (previous != null){previous.next = newNode;}
           previous = newNode;
           return newNode;
       }

       Item delete(){
           //Node newNode = new Node(item, this, this);
          Item del = item;
          item = null;
          if (next != null){next.previous = previous;}
          if (previous != null){previous.next = next;}
          return del;
       }
   }

   private Node header = new Node();
   private Node tailer = new Node();
   private int size;
   {
       header.next = tailer;
       tailer.previous = header;
       header.previous = null;
       tailer.next = null;
   }

   void push(Item item){
       size++;
       header.insertAfter(item);
   }
   boolean isEmpty(){return size == 0;}
   Item pop(){
       if (isEmpty()){
           throw new RuntimeException("underflow.");
       }
       size--;
       return header.next.delete();
   }

   void enqueue(Item item){
       size++;
       tailer.insertBefore(item);
   }

   //Ex32_back<Integer> steque = new Ex32_back<>();
   public static void main(String[] args) {
       Ex32_back<Integer> steque = new Ex32_back<>();
       for (int i = 0; i < 10; i++){
           steque.push(i);
       }
       StdOut.println(steque);
       steque.pop();
       steque.pop();
       steque.pop();
       StdOut.println(steque);
       for (int i = 95; i < 100; i++){
           steque.enqueue(i);
       }
       StdOut.println(steque);
   }
}
