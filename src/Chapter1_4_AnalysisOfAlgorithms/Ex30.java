package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;

/*
*   Deque with a Chapter1_3_Bags_Queues_Stacks.stack and a steque. Implement a deque with a Chapter1_3_Bags_Queues_Stacks.stack and a steque so that each deque operation takes
*   a constant amortized number of Chapter1_3_Bags_Queues_Stacks.stack and steque operations.
*
* */
public class Ex30 {
    static class Node<Item>{
        Item item;
        Node<Item> previous;
        Node<Item> next;
        Node(){}
        Node(Item item, Node<Item> previous, Node<Item> next){
            this.item = item;
            this.previous = previous;
            this.next = next;
        }
        Node<Item> insertAfter(Item item){
            Node<Item> node = new Node<>(item, this,next);
            if (previous != null) previous.next = node;
            previous = node;
            return node;
        }
        Node<Item> insertBefore(Item item){
            Node<Item> node = new Node<>(item,this,next);
            if (next != null) next.previous = node;
            next = node;
            return node;
        }
        Item delete(){
            Item deletion = this.item;
            this.item = null;
            if (next != null) next.previous = previous;
            if (previous != null) previous.next = next;
            return deletion;
        }
    }
    static class Stack<Item>{
        private int size;
        private Node<Item> header = new Node<>();
        private Node<Item> tailer = new Node<>();
        {
            header.next = tailer;
            tailer.previous = header;
            header.previous = tailer.next = null;
        }
        boolean isEmpty(){return size == 0;}
        int size(){return size;}
        void push(Item item){
            size++;
            header.insertAfter(item);
        }
        Item pop(){
            if (isEmpty()) throw new RuntimeException("Steque underflow");
            size--;
            return header.next.delete();
        }
    }
    static class Steque<Item>{
        private int size;
        private Node<Item> header = new Node<>();
        private Node<Item> tailer = new Node<>();
        {
            header.next = tailer;
            tailer.previous = header;
            header.previous = tailer.next = null;
        }
        boolean isEmpty(){return size == 0;}
        int size(){return size;}
        void push(Item item){
            size++;
            header.insertAfter(item);
        }
        Item pop(){
            if (isEmpty()) throw new RuntimeException("Steque underflow");
            size--;
            return header.next.delete();
        }
        void enqueue(Item item){
            size++;
            tailer.insertBefore(item);
        }
    }
    static class Dequeue<Item>{
        private Steque<Item> steque = new Steque<>();
        private Stack<Item> stack = new Stack<>();
        void stequeAllToStack(){
            while (!steque.isEmpty())stack.push(steque.pop());
        }
        void stackAllToSteque(){
            while (!steque.isEmpty())steque.push(stack.pop());
        }
        void pushLeft(Item item){
            if (steque.isEmpty())stackAllToSteque();
            steque.push(item);
        }
        void pushRight(Item item){
            if (steque.isEmpty())stackAllToSteque();
            steque.enqueue(item);
        }
        Item popLeft(){
            if (steque.isEmpty())stackAllToSteque();
            return steque.pop();
        }
        Item popRight(){
            if (stack.isEmpty())stequeAllToStack();
            return stack.pop();
        }
    }

    public static void main(String[] args) {
        Dequeue<Integer> dequeue = new Dequeue<>();
        dequeue.pushLeft(1);
        dequeue.pushLeft(2);
        dequeue.pushLeft(3);
        dequeue.pushLeft(4);
        dequeue.pushLeft(5);
        dequeue.pushRight(6);
        dequeue.pushRight(7);
        StdOut.println(dequeue.popRight());
        StdOut.println(dequeue.popLeft());
        StdOut.println(dequeue.popRight());
        StdOut.println(dequeue.popLeft());
        StdOut.println(dequeue.popRight());
        StdOut.println(dequeue.popLeft());
        StdOut.println(dequeue.popRight());
    }
}
