package Chapter3_4_HashTables;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
*   Modify your implementation of the previous exercise to include an integer field for each key-value pair that is set to
*   the number of entries in the table at the time that pair is inserted. Then implement a method that deletes all keys
*   (and associated values)for which the field is greater than a given integer k. Note: This extra functionality is useful
*   in implementing the symbol table for a compiler.
*
* */

/*Since we have to add one more property of integer field for each of Node Object which will acts as holding the number
* of entries*/
@SuppressWarnings("unchecked")
public class Ex03_DeleteSomeKeysWithIntegerField<Key, Value> {
    class SequentialSearchST<Key, Value>{
        private Node first;
        private int n;
        private class Node{
            private Node next;
            private Value value;
            private Key key;
            private int countOfEntries;
            public Node(Node next, Value val, Key key, int countOfEntries){
                this.next = next;
                this.value = val;
                this.key = key;
                this.countOfEntries = countOfEntries;
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
                    return;
                }
            }
            first = new Node(first, val, key, 0);
            first.countOfEntries = first.countOfEntries + 1;
            n++;
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
    private int n;
    private int m;
    private SequentialSearchST<Key, Value>[] st;
    public Ex03_DeleteSomeKeysWithIntegerField(){
        this(97);
    }
    public Ex03_DeleteSomeKeysWithIntegerField(int m){
        this.m = m;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
        for (int i = 0; i < m; i++){
            st[i] = new SequentialSearchST<>();
        }
    }
    public int size(){return n;}
    public boolean isEmpty(){return n == 0;}
    public boolean contains(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be empty");}
        return get(key) != null;
    }
    public void resize(int chains){
        Ex03_DeleteSomeKeysWithIntegerField<Key, Value> temp = new Ex03_DeleteSomeKeysWithIntegerField<>(chains);
        for (int i = 0; i < m; i++){
            for (Key key : st[i].keys()){
                temp.put(key, st[i].get(key));
            }
        }
        this.m = temp.m;
        this.n = temp.n;
        this.st = temp.st;
    }
    public void put(Key key, Value value){
        if (key == null){throw new IllegalArgumentException("Argument key cannot be empty");}
        if (value == null){
            delete(key);
            return;
        }
        if (n == m / 2){resize(m * 2);}
        int i = hash(key);
        if (st[i].contains(key)){
            st[i].put(key, value);
        }
        else {
            st[i].put(key, value);
            n++;
        }
    }
    public int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % m;
    }
    public Value get(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be empty");}
        int i = hash(key);
        return st[i].get(key);
    }
    public void delete(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be empty");}
        int i = hash(key);
        st[i].delete(key);
        n--;
        if (n <= 2 * m){resize(m / 2);}
    }
    public void deleteKeyGreaterThanK(int k){
        for (int i = 0; i < m; i++){
            if (!st[i].isEmpty()){
                SequentialSearchST.Node node = st[i].first;
                while (node != null){
                    if (node.countOfEntries > k){
                        node = node.next;
                    }
                }
            }
        }
    }
    public Iterable keys(){
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < m; i++){
            for (Key key : st[i].keys()){
                queue.enqueue(key);
            }
        }
        return queue;
    }

    public static void main(String[] args) {
        Ex03_DeleteSomeKeysWithIntegerField<String, Integer> deleteSomeKeys = new Ex03_DeleteSomeKeysWithIntegerField<>();
        for (int i = 0; !StdIn.isEmpty(); i++){
            String s = StdIn.readString();
            deleteSomeKeys.put(s, i);
        }
        for(int i = 0; i < deleteSomeKeys.m; i++){
            for (String s : deleteSomeKeys.st[i].keys()){
                StdOut.println(s + " " + deleteSomeKeys.hash(s) + " " + deleteSomeKeys.st[i].first.countOfEntries);
            }
        }
        deleteSomeKeys.deleteKeyGreaterThanK(1);
        for(int i = 0; i < deleteSomeKeys.m; i++){
            for (String s : deleteSomeKeys.st[i].keys()){
                StdOut.println(s + " " + deleteSomeKeys.hash(s));
            }
        }
    }
}
