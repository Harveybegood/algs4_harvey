package Chapter3_5_Applications;

import edu.princeton.cs.algs4.ST;

import java.util.LinkedList;

/*
*   LRU cache. Create a data structure that supports the following operations: access and remove. The access operation inserts the item onto
*   the data structure if it's not already present. The remove operation deletes and returns the item that was least recently accessed.
*
*   Hint: Maintain the items in order of access in a doubly linked list, along with pointers to the first and last nodes. Use a symbol table
*   with keys = items, values = location in linked list. When you access an element, delete it from the linked list and reinsert it at the
*   beginning. When you remove an element, delete it from the end and remove it from the symbol table.
*
* */
public class Ex26_LRUCache {
    private class DoublyLinkedList<Item>{
        private class Node{
            Item item;
            Node next, prev;
            public Node(Item item, Node next, Node prev){
                this.item = item;
                this.next = next;
                this.prev = prev;
            }
            private Node first;
            private Node last;
        }
        public DoublyLinkedList(){

        }
        private int n;
        public boolean isEmpty(){return n == 0;}
        public int size(){return n;}
        public void dLinkedListAdd(Item item){

        }
    }
    private ST<String, LinkedList<Integer>> st;
    public Ex26_LRUCache(){
        st = new ST<>();
    }
    public void add(){
        if (!st.contains("a")){st.put("a", new LinkedList<>());}

    }
    public void access(){}
    
}
