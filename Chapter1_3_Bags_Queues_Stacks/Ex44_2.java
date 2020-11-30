package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Ex44_2 {
    static class Stack<Item>{
        private class Node{
            Item item;
            Node previous, next;
            Node(Item item, Node previous, Node next){
                this.item = item;
                this.previous = previous;
                this.next = next;
            }
            Node(){this(null, null, null);}

            Node insertAfter(Item item){
                Node node = new Node(item, this, next);
                if (next != null){
                    node.item = item;
                    next.previous = node;
                }
                next = node;
                return node;
            }
            Item delete(){
                Item deletion = item;
                item = null;
                if (next != null){next.previous = previous;}
                if (previous != null){previous.next = next;}
                return deletion;
            }
        }

        private int size;
        private Node header = new Node();
        private Node tailer = new Node();
        {
            header.next = tailer;
            tailer.previous = header;
            header.previous = null;
            tailer.next = null;
        }
        public int size(){return size;}
        public boolean isEmpty(){return size == 0;}
        public void push(Item item){
            header.insertAfter(item);
            size++;
        }
        public Item pop(){
            if (isEmpty()){throw new RuntimeException("Chapter1_3_Bags_Queues_Stacks.stack underflow");}
            size--;
            return header.next.delete();
        }
        public String toString(){
            if (isEmpty()) return " ";
            StringBuilder stringBuilder = new StringBuilder();
            Node node = header.next;
            while (node.next != tailer){
                stringBuilder.append(node.item + " -> ");
                node = node.next;
            }
            stringBuilder.append(node.item);
            return stringBuilder.toString();
        }
        public Iterable<Item> order(){
            return new Iterable<Item>(){
                public Iterator<Item> iterator(){
                    return new Iterator<Item>() {
                        private Node current = header.next;
                        @Override
                        public boolean hasNext() {
                            return current != tailer;
                        }

                        @Override
                        public Item next() {
                            Item next = current.item;
                            current = current.next;
                            return next;
                        }
                    };
                }
            };
        }
        public Iterable<Item> reverseOrder(){
            return new Iterable<Item>() {
                @Override
                public Iterator<Item> iterator() {
                    return new Iterator<Item>() {
                        private Node current = tailer.previous;
                        @Override
                        public boolean hasNext() {
                            return current != header;
                        }

                        @Override
                        public Item next() {
                            Item previous = current.item;
                            current = current.previous;
                            return previous;
                        }
                    };
                }
            };
        }
    }
    static class Buffer{
        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();
        Buffer(){}
        int size(){return left.size() + right.size();}
        void insert(char c){
            left.push(c);
            StdOut.println(this);
        }
        char delete(){
            char deletion = left.pop();
            StdOut.println(this);
            return deletion;
        }
        void left(int k){
            while (k-- > 0){
                right.push(left.pop());
            }
            StdOut.println(this);
        }
        void right(int k){
            while (k-- > 0){
                left.push(right.pop());
            }
            StdOut.println(this);
        }
        public String toString(){
            if (left.size() == 0 && right.size() == 0)return "|";
            StringBuilder stringBuilder = new StringBuilder();
            if (left.size() == 0){
                stringBuilder.append(" | ");
                for (Character c : right.order()){
                    stringBuilder.append(c + " ");
                }
            }else if (right.size() == 0){
                for (Character c : right.reverseOrder()){
                    stringBuilder.append(c + " ");
                }
                stringBuilder.append("|");
            }else {
                for (Character c : left.reverseOrder()){
                    stringBuilder.append(c + " ");
                }
                stringBuilder.append(" | ");
                for (Character c : right.order()){
                    stringBuilder.append(c + " ");
                }
            }
            return stringBuilder.toString();
        }
    }

    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        StdOut.println("Insert character at cursor and move cursor point to next position");
        for (char c = 'a'; c <= 'h'; c++){
            buffer.insert(c);
        }
        StdOut.println("\ncursor move left");
        buffer.left(5);
        StdOut.println("\ncursor move right");
        buffer.right(3);
        StdOut.println("\ndelete character at cursor");
        buffer.delete();
        buffer.delete();
        buffer.delete();
        StdOut.println("\ncursor move right");
        buffer.right(2);
        StdOut.println("\ndelete character at cursor");
        buffer.delete();
        buffer.delete();
        buffer.delete();
        StdOut.println("\nprint the present size");
        StdOut.println(buffer.size());
    }
}
