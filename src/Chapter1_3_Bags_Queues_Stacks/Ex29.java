package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.StdOut;

/*
*   Write a Queue implementation that uses a circular linked list, which is the same as a linked list
*   except that no linkedlists are null and the value of last.next is first whenever the list is not empty.
*   Keep only one Node instance variable(last).
*
* */
public class Ex29 {
    public static class Node<Item>{
        Item item;
        Node<Item> next;
        Node(Item item, Node<Item> next){
            this.item = item;
            this.next = next;
        }
        Node(Item item) {this(item, null);}
    }
    public static class CircularList<Item>{
        private Node<Item> tail = null, head = null;
        private int size;
        public boolean isEmpty(){return size == 0;}

        public void insertHead(Item item){
            if (isEmpty()){
                Node<Item> node = new Node<>(item);
                node.item = item;
                node.next = node;
                head = tail = node;
            }else {
                Node<Item> node = new Node<>(item);
                node.item = item;
                tail.next = node;
                node.next = head;
                tail = node;
            }
            size++;
        }

        public Item removeTail(){
            if (isEmpty()){throw new RuntimeException("Empty circular list.");}
            Item del = tail.next.item;
            tail.next.item = null;


            if (size == 1){head = tail = null;}
            else {
                head = head.next;
                tail.next = tail.next.next;
            }

            size--;
            return del;
        }
        public String toString(){
            if (isEmpty()){return "empty list";}
            Node<Item> cur = head;
            StringBuilder strs = new StringBuilder();
            while (cur != tail){
                strs.append(cur.item + " " + " ->  ");
                cur = cur.next;
            }
            strs.append(cur.item);
            return strs.toString();
        }
    }
    public static class Queue<Item> extends CircularList<Item>{
        public void enqueue(Item item){insertHead(item);}
        public Item dequeue(){return removeTail();}
        public String toString(){return super.toString();}
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < 10; i++){
            queue.enqueue(i);
        }
        StdOut.println(queue);
        for (int i = 0; i < 4; i++){
            queue.dequeue();
        }
        StdOut.println(queue);
    }

}
