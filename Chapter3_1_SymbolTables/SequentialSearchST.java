package Chapter3_1_SymbolTables;

import edu.princeton.cs.algs4.Queue;

public class SequentialSearchST<Key, Value> {
    private Node first;
    private int n;
    private class Node{
        Node next;
        Value value;
        Key key;
        public Node(Node next, Value val, Key key){
            this.next = next;
            this.value = val;
            this.key = key;
        }
    }
    public Value get(Key key){
        if (key == null) throw new IllegalArgumentException("Argument for get() is null");
        if (isEmpty()) return null;
        for (Node x = first; x != null; x = x.next){
            if (key.equals(x.key)){
                return x.value;
            }
        }
        return null;
    }
    public void put(Key key, Value val){
        if (key == null) throw new IllegalArgumentException("Argument for put() is null");
        if (val == null){
            delete(key);
        }
        for (Node x = first; x != null; x = x.next){
            if (key.equals(x.key)){
                x.value = val;
                n++;
                return;
            }
        }
        first = new Node(first, val, key);
    }
    public boolean isEmpty(){return n == 0;}
    public int size(){return n;}
    public boolean contains(Key key){
        if (key == null) throw new IllegalArgumentException("Argument for contains() is null");
        return get(key) != null;
    }
    public void delete(Key key){
        if (key == null) throw new IllegalArgumentException("Argument for delete() is null");
        if (isEmpty()){return;}
        if (first.key.equals(key)){
            first.key = null;
            first.value = null;
        }
        for (Node node = first.next; node != null; node = node.next){
            if (node.key.equals(key)){
                node.value = null;
                node.key = null;
                node = node.next;
            }
        }
        // what if the node is the last node?
    }
    public Iterable<Key> keys(){
        Queue<Key> queue = new Queue<>();
        for (Node node = first; node != null; node = node.next){
            queue.enqueue(node.key);
        }
        return queue;
    }
}
