package Chapter3_4_HashTables;

import Chapter3_1_SymbolTables.SequentialSearchST;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
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
    /*public class SequentialSearchST<K, V>{
        public Node first;
        public class Node{
            public Node next;
            public Key key;
            public Value value;
            public Node(Node next, Key key, Value value){
                this.next = next;
                this.key = key;
                this.value = value;
            }
        }
    }*/
    private SequentialSearchST<Key, Value>[] st;
    public Ex02_AlternateSeparateChainingHashST(){
        this(5);
    }
    public Ex02_AlternateSeparateChainingHashST(int M){
        this.M = M;
        // I am confused with this question of "directly uses the linked-list code from SequentialSearchST"
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++){
            st[i] = new SequentialSearchST<>();
        }
    }
    public int size(){return N;}
    public boolean contains(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be empty");}
        return get(key) != null;
    }
    public boolean isEmpty(){return N == 0;}
    public int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % M;
    }
    public Value get(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be empty");}
        for (SequentialSearchST.Node x = st[hash(key)].first; x != null; x = x.next){
            if (key.equals(x.key)){
                return (Value) x.value;
            }
        }
        return null;
    }
    public void put(Key key, Value value){
        if (key == null){throw new IllegalArgumentException("Argument key cannot be empty");}
        if (value == null){
            delete(key);
            return;
        }
        if (N >= M * 10){resize(M * 2);}
        for (SequentialSearchST.Node x = st[hash(key)].first; x != null; x = x.next){
            if (key.equals(x.key)){
                x.value = value;
                N++;
                return;
            }
        }
        /*int i = hash(key);
        if (!st[i].contains(key)){
            N++;
            st[i].put(key, value);
        }*/
    }
    public void resize(int newSize){
        Ex02_AlternateSeparateChainingHashST<Key, Value> temp = new Ex02_AlternateSeparateChainingHashST<>(newSize);
        for (int i = 0; i < st.length; i++){
            for (Key key : st[i].keys()){
                temp.put(key, st[i].get(key));
            }
        }
        this.M = temp.M;
        this.N = temp.N;
        this.st = temp.st;
    }
    public void delete(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be empty");}
        int i = hash(key);
        if (st[i].contains(key)){
            N--;
            st[i].delete(key);
        }
        if (N < 2*M){
            resize(M/2);
        }
    }
    public Iterable<Key> keys(){
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < st.length; i++){
            for (Key key : st[i].keys()){
                queue.enqueue(key);
            }
        }
        return queue;
    }

    public static void main(String[] args) {
        Ex02_AlternateSeparateChainingHashST<String, Integer> alternateSeparateChainingHashST = new Ex02_AlternateSeparateChainingHashST<>();
       /* alternateSeparateChainingHashST.put("S",0);
        alternateSeparateChainingHashST.put("E",1);
        alternateSeparateChainingHashST.put("A",2);*/
        for (int i = 0; !StdIn.isEmpty(); i++){
            alternateSeparateChainingHashST.put(StdIn.readString(), i);
        }
        for (String s : alternateSeparateChainingHashST.keys()){
            StdOut.print(s + " ");
        }
    }
}
