package Chapter3_4_HashTables;


import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class DeleteKeysGreaterThanK<Key, Value> {
    private class Node{
        Key key;
        Value value;
        Node next;
        int numberOfNodes;
        public Node(Key key, Value value, Node next, int numberOfNodes){
            this.key = key;
            this.value = value;
            this.next = next;
            this.numberOfNodes = numberOfNodes;
        }
    }
    private List<Node> hashArray;
    //private static final int initSize = 5;
    private int m;
    private int n;
    public DeleteKeysGreaterThanK(){
        this(5);
    }
    public DeleteKeysGreaterThanK(int m){
        this.m = m;
        hashArray = new ArrayList<>(m);
        for (int i = 0; i < m; i++){
            hashArray.add(null);
        }
    }
    public boolean isEmpty(){return n == 0;}
    public int size(){return n;}
    public boolean contains(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be empty");}
        return get(key) != null;
    }
    public int hash(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be empty");}
        return (key.hashCode() & 0x7fffffff) % m;
    }
    public Value get(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be empty");}
        int i = hash(key);
        Node node = hashArray.get(i);
         while (node != null){
             if (node.key.equals(key)){
                 return node.value;
             }
             node = node.next;
         }
         return null;
    }
    public void put(Key key, Value value){
        if (key == null){throw new IllegalArgumentException("Argument for key cannot be empty");}
        if (value == null){throw new IllegalArgumentException("Argument for value cannot be empty");}
        int i = hash(key);
        Node node = hashArray.get(i);
        while (node != null){
            if (node.key.equals(key)){
                node.value = value;
            }
            node = node.next;
        }
        if (n >= hashArray.size() * 10){
            ResizeHashTable(2);
        }
        n++;
        Node newNode;
        node = hashArray.get(i);
        //node.numberOfNodes++;
        newNode = new Node(key, value, node, n);
        hashArray.set(i, newNode);
    }
    //List<Node> temp;
    public void ResizeHashTable(int size){
        List<Node> temp = hashArray;
        hashArray = new ArrayList<>(size);
        for (int i = 0; i < size; i++){
            hashArray.add(null);
        }
        for (Node node : temp){
            while (node != null){
                put(node.key, node.value);
                node = node.next;
            }
        }
    }
    public int getNumberOfEntriesEachPair(Key key){
        int i = hash(key);
        Node node = hashArray.get(i);
        while (node != null){
            if (node.key.equals(key)){
                return node.numberOfNodes;
            }
            node = node.next;
        }
        return -1;
    }
    public void delete(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be empty");}
        int i = hash(key);
        Node node = hashArray.get(i);
        if (node.key.equals(key)){
            hashArray.set(i, node.next);
        }
        else {
            while (node != null){
                if (node.next.key.equals(key)){
                    node.next = node.next.next;
                    break;
                }
                node = node.next;
            }
        }
        n--;
        if (n <= hashArray.size() / 4){
            ResizeHashTable(hashArray.size() / 2);
        }
    }
    public void deleteKeys(int k){
        for (int i = 0; i < hashArray.size(); i++){
            Node node = hashArray.get(i);
            boolean flag = true;
            while (node != null && node.numberOfNodes > k){
                if (flag){
                    hashArray.set(i, node.next);
                    node = node.next;
                    n--;
                    flag = false;
                }
                else {
                    node.next = node.next.next;
                    n--;
                }
            }
        }
        if (n <= hashArray.size() / 4){
            ResizeHashTable(hashArray.size() / 2);
        }
    }
    public Iterable<Key> keys(){
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < hashArray.size(); i++){
            Node node = hashArray.get(i);
            while (node != null){
                queue.enqueue(node.key);
                node = node.next;
            }
        }
        return queue;
    }
    public static void main(String[] args) {
        DeleteKeysGreaterThanK<String, Integer> deleteKeysGreaterThanK = new DeleteKeysGreaterThanK<>();
        deleteKeysGreaterThanK.put("E", 1);
        deleteKeysGreaterThanK.put("X", 2);
        deleteKeysGreaterThanK.put("A", 3);
        deleteKeysGreaterThanK.put("M", 4);
        deleteKeysGreaterThanK.put("P", 5);
        deleteKeysGreaterThanK.put("L", 6);
        StdOut.println("The size of hash table: " + deleteKeysGreaterThanK.size());
        StdOut.println("Expecting 3 - " + deleteKeysGreaterThanK.get("A"));
        StdOut.println("The number of entries of that pair: " + deleteKeysGreaterThanK.getNumberOfEntriesEachPair("P") + " 5 expected");
        StdOut.println("The number of entries of that pair: " + deleteKeysGreaterThanK.getNumberOfEntriesEachPair("X") + " 2 expected");
        deleteKeysGreaterThanK.deleteKeys(3);
        for (String s : deleteKeysGreaterThanK.keys()){
            StdOut.print(s + " ");
        }
        StdOut.println();
    }
}


