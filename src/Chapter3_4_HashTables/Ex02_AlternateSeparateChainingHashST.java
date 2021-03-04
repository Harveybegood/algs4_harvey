package Chapter3_4_HashTables;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/*
*   Develop an alternate implementation of SeparateChainingHashST that directly uses the linked-list code from
*   SequentialSearchST
*
* */
@SuppressWarnings("unchecked")
public class Ex02_AlternateSeparateChainingHashST<Key, Value> {
    private int N;
    private int M;
    private class Node{
        Node next;
        Key key;
        Value value;
        public Node(Node next, Key key, Value value){
            this.next = next;
            this.key = key;
            this.value = value;
        }
    }
    private Node first;
   /* SequentialSearchST<Key, Value>[] st;
    public Ex02_AlternateSeparateChainingHashST(){
        this.M = 997;
    }
    public Ex02_AlternateSeparateChainingHashST(int M){
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++){
            st[i] = new SequentialSearchST<>();
        }
    }*/
    private Ex02_AlternateSeparateChainingHashST<Key, Value>[] st;
    public Ex02_AlternateSeparateChainingHashST(){
        this(5);
    }
    public Ex02_AlternateSeparateChainingHashST(int M){
        this.M = M;
        // I am confused with this question of "directly uses the linked-list code from SequentialSearchST"
        st = (Ex02_AlternateSeparateChainingHashST<Key, Value>[]) new Ex02_AlternateSeparateChainingHashST[M];
        for (int i = 0; i < M; i++){
            st[i] = new Ex02_AlternateSeparateChainingHashST<>(M);
        }
    }
    public int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % M;
    }
    public Value get(Key key){
        for (Node x = st[hash(key)].first; x != null; x = x.next){
            if (key.equals(x.key)){
                return x.value;
            }
        }
        return null;
    }
    public void put(Key key, Value value){
        for (Node x = st[hash(key)].first ; x != null; x = x.next){
            if (key.equals(x.key)){
                x.value = value;
                return;
            }
        }
        first = new Node(first, key, value);
        N++;
    }
    public Iterable<Key> keys(){
        Queue<Key> queue = new Queue<>();
        for (Node x = first; x != null; x = x.next){
            queue.enqueue(x.key);
        }
        return queue;
    }

    public static void main(String[] args) {
        Ex02_AlternateSeparateChainingHashST<String, Integer> alternateSeparateChainingHashST = new Ex02_AlternateSeparateChainingHashST<>();
        alternateSeparateChainingHashST.put("S", 10);
        alternateSeparateChainingHashST.put("O", 6);
        alternateSeparateChainingHashST.put("R", 9);
        alternateSeparateChainingHashST.put("T", 12);
        for (String s : alternateSeparateChainingHashST.keys()){
            StdOut.print(s + " " + alternateSeparateChainingHashST.get(s) + "\n");
        }
    }
}
