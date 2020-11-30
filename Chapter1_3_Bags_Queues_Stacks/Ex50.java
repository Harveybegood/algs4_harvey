package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/*
*   Fail-fast iterator. Modify the iterator code in Stack to immediately throw a
*   java.util.ConcurrentModificationException if the client modifies the collection(via push() or pop() during
*   iteration).
*   Solution: Maintain a counter that counts the number of push() and pop() operations. When creating an iterator,
*   store this value as an Iterator instance variable. Before each call to hasNext() and next(), check that this
*   value has not changed since construction of iterator; if it has, throw the exception.
*
* */
public class Ex50{
    static class Stack<Item> implements Iterable<Item>{
        private class Node{
            Item item;
            Node prev, next;
            Node(){};
            Node(Item item, Node prev, Node next){
                this.item = item;
                this.prev = prev;
                this.next = next;
            }
            Node insertAfter(Item item){
                Node node = new Node(item, this, next);
                if (next != null){
                    next.prev = node;
                }
                next = node;
                return node;
            }
            Item delete(){
                Item deletion = item;
                item = null;
                if (prev != null){
                    prev.next = next;
                }
                if (next != null){
                    next.prev = prev;
                }
                return deletion;
            }
        }
        private Node header = new Node();
        private Node tailer = new Node();
        {
            header.next = tailer;
            tailer.prev = header;
            header.prev = tailer.next = null;
        }
        private int popCount = 0;
        private int pushCount = 0;
        public boolean isEmpty(){return header.next == tailer;}
        public void push(Item item){
            pushCount++;
            header.insertAfter(item);
        }
        public Item pop(){
            if (isEmpty()){throw new RuntimeException("Chapter1_3_Bags_Queues_Stacks.stack underflow");}
            popCount++;
            return header.next.delete();
        }
        public String toString(){
            if (isEmpty()) return "[empty]";
            StringBuilder stringBuilder = new StringBuilder();
            Node current = header.next;
            while (current.next != tailer){
                stringBuilder.append(current.item + " -> ");
                current = current.next;
            }
            stringBuilder.append(current.item);
            return stringBuilder.toString();
        }
        public Iterator<Item> iterator(){
            return new Iterator<Item>() {
                private int popC = Stack.this.popCount;
                private int pushC = Stack.this.pushCount;
                private Node current = header.next;
                @Override
                public boolean hasNext() {
                    if (popC != Stack.this.popCount || pushC != Stack.this.pushCount){
                        throw new ConcurrentModificationException();
                    }
                    return current != tailer;
                }

                @Override
                public Item next() {
                    if (popC != Stack.this.popCount || pushC != Stack.this.pushCount){
                        throw new ConcurrentModificationException();
                    }
                    Item item = current.item;
                    current = current.next;
                    return item;
                }
            };
        }
        public static void ConcurrentModificationException(){
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < 5; i++){
                stack.push(i);
            }
            StdOut.println(stack);
            for (Integer i : stack){
                if (i == 3){
                    stack.pop();
                }else {
                    StdOut.println(i);
                }
            }
        }
    }

    public static void main(String[] args) {
        Stack.ConcurrentModificationException();
    }
}
