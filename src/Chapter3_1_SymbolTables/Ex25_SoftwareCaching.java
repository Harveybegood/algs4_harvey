package Chapter3_1_SymbolTables;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
*   Software caching. Since the default implementation of contains() calls get(), the inner loop of FrequencyCounter
*       if(!st.contains(word)) st.put(word, 1);
*       else                   st.put(word, st.get(word)+1);
*   leads to two or three searches for the same key. To enable clear client code like this without sacrificing efficiency,
*   we can use a technique known as software caching, where we save the location of the most recently accessed key in an
*   instance variable. Modify SequentialSearchST and BinarySearchST to take advantage of this idea.
*
* */
@SuppressWarnings("unchecked")
public class Ex25_SoftwareCaching {
    public static class BinarySearchST<Key extends Comparable<Key>, Value>{
        private Key[] keys;
        private Value[] values;
        private int n;
        private int cache;
        public BinarySearchST(int cap){
            keys = (Key[]) new Comparable[cap];
            values = (Value[]) new Object[cap];
        }
        public boolean isEmpty(){return n == 0;}
        public int size(){return n;}
        public boolean contains(Key key){
            if (key == null) throw new IllegalArgumentException("Argument for contains() is null");
            return get(key) != null;
        }
        public int rank(Key key){
            if (key == null) throw new IllegalArgumentException("Argument for rank() is null");
            int lo = 0;
            int hi = n - 1;
            while (lo <= hi){
                int mid = lo + (hi - lo)/2;
                int cmp = key.compareTo(keys[mid]);
                if (cmp < 0) hi = mid - 1;
                else if (cmp > 0) lo = mid + 1;
                else return mid;
            }
            return lo;
        }
        public Value get(Key key){
            if (key == null) throw new IllegalArgumentException("Argument for get() is null");
            if (isEmpty()) return null;
            if (this.cache < n && keys[cache].compareTo(key) == 0){
                return values[cache];
            }
            int i = rank(key);
            if (i < n && key.compareTo(keys[i]) == 0){
                cache = i;
                return values[i];
            }
            return null;
        }
        public void delete(Key key){
            if (key == null) throw new IllegalArgumentException("Argument for delete() is null");
            if (isEmpty()) return;
            int i =  rank(key);
            for (int j = i; j < n; j++){
                keys[j] = keys[j+1];
                values[j] = values[j+1];
            }
            keys[n-1] = null;
            values[n-1] = null;
            n--;
            if (n > 1 && n == keys.length / 4){
                resize(keys.length / 2);
            }
        }
        public void resize(int newSize){
            Key[] tempKey = (Key[]) new Comparable[newSize];
            Value[] tempValue = (Value[]) new Object[newSize];
            for (int i = 0; i < n; i++){
                tempKey[i] = keys[i];
                tempValue[i] = values[i];
            }
            keys = tempKey;
            values = tempValue;
        }
        public void put(Key key, Value value){
            if (key == null) throw new IllegalArgumentException("Argument for put() is null");
            if (value == null){
                delete(key);
                return;
            }
            if (isEmpty())
                return;
            int i = rank(key);
            if (n == keys.length / 2){
                resize(keys.length * 2);
            }
            if (key.compareTo(keys[i]) == 0){
                values[i] = value;
                return;
            }
            for (int j = n; j > i; j--){
                keys[j] = keys[j-1];
                values[j] = values[j-1];
            }
            keys[i] = key;
            values[i] = value;
            n++;
        }
        public Key min(){
            if (isEmpty()) return null;
            return keys[0];
        }
        public Key max(){
            if (isEmpty()) return null;
            return keys[n-1];
        }
        public Iterable<Key> keys(){
            return keys(min(), max());
        }
        public Iterable<Key> keys(Key lo, Key hi){
            Queue<Key> queue = new Queue<>();
            for (int i = rank(lo); i < rank(hi); i++){
                queue.enqueue(keys[i]);
            }
            if (contains(hi)){
                queue.enqueue(keys[rank(hi)]);
            }
            return queue;
        }
    }
    public static class SequentialSearchST<Key, Value>{
        private Node first;
        private int n;
        private Node cache;
        public class Node{
            Node next;
            Key key;
            Value value;
            public Node(){}
            public Node(Node next, Key key, Value value){
                this.next = next;
                this.key = key;
                this.value = value;
            }
        }
        public boolean isEmpty(){return n == 0;}
        public int size(){return n;}
        public boolean contains(Key key){
            if (key == null) throw new IllegalArgumentException("Argument for contains() is null");
            return get(key) != null;
        }
        public Value get(Key key){
            if (key == null) throw new IllegalArgumentException("Argument for get() is null");
            if (isEmpty()) return null;
            if (cache.key.equals(key)){
                return cache.value;
            }
           /* if (first.key.equals(key)){
                return first.value;
            }*/
            for (Node node = first; node != null; node = node.next){
                if (node.key.equals(key)){
                    cache = node;
                    return node.value;
                }
            }
            return null;
        }
        public void put(Key key, Value value){
            if (key == null || value == null) throw new IllegalArgumentException("Argument for put() is null");
            if (isEmpty()){
                first = new Node(first, key, value);
                first.next = null;
                return;
            }
          /*  if (first.key.equals(key)){
                first.key = key;
                first.value = value;
                first.next = null;
            }*/
            for (Node node = first; node != null; node = node.next){
                if (node.key.equals(key)){
                    node.key = key;
                    node.value = value;
                }
            }
            first = new Node(first, key, value);
            first = first.next;
            n++;
        }
        public Iterable<Key> keys(){
            Queue<Key> queue = new Queue<>();
            for (Node node = first; node != null; node = node.next){
                queue.enqueue(node.key);
            }
            return queue;
        }
    }

    public static void main(String[] args) {
        int minLen = Integer.parseInt(args[0]);
        BinarySearchST<String, Integer> binarySearchST = new BinarySearchST<>(2);
        SequentialSearchST<String, Integer> sequentialSearchST = new SequentialSearchST<>();
        //SequentialSearchST.Node node = sequentialSearchST.new Node();
        while (!StdIn.isEmpty()){
            String word = StdIn.readString();
            if (word.length() < minLen) continue;
            if (!binarySearchST.contains(word)){
                binarySearchST.put(word, 1);
            }else {
                binarySearchST.put(word, binarySearchST.get(word) + 1);
            }
        }
        String max = "";
        binarySearchST.put(max, 0);
        for (String word : binarySearchST.keys()){
            if (binarySearchST.get(word) > binarySearchST.get(max)){
                max = word;
            }
        }
        StdOut.println(max + "  " + binarySearchST.get(max));
        while (!StdIn.isEmpty()){
            String word = StdIn.readString();
            if (word.length() < minLen) continue;
            if (!sequentialSearchST.contains(word)){
                sequentialSearchST.put(word, 1);
            }else {
                sequentialSearchST.put(word, sequentialSearchST.get(word) + 1);
            }
        }
        String maxNode = "";
        sequentialSearchST.put(maxNode, 0);
        for (String word : sequentialSearchST.keys()){
            if (sequentialSearchST.get(word) > sequentialSearchST.get(maxNode)){
                maxNode = word;
            }
        }
        StdOut.println(maxNode + "   " + sequentialSearchST.get(maxNode));
    }
}
