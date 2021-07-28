package Chapter3_5_Applications;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/*
*   Modify BST to keep duplicate keys in the tree. Return any value associated with the given key for get(), and remove
*   all nodes in the tree that have keys equal to the given key for delete().
*
* */
@SuppressWarnings("unchecked")
public class Ex09_BSTWithDuplicateKeys<Key extends Comparable<Key>, Value> {
    private class Node{
        Node left, right;
        Key key;
        Value value;
        int numberOfNodes;
        public Node(Key key, Value value, int numberOfNodes){
            this.key = key;
            this.value = value;
            this.numberOfNodes = numberOfNodes;
        }
    }
    private Node root;
    public boolean isEmpty(){return root == null;}
    public boolean contains(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        return get(key) != null;
    }
    public int size(){
        return size(root);
    }
    private int size(Node node){
        if (node == null){return 0;}
        return node.numberOfNodes;
    }
    public Value get(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        StringBuilder stringBuilder = new StringBuilder();
        get(root, key, stringBuilder);
        return (Value) stringBuilder.toString();
    }
    private void get(Node node, Key key, StringBuilder stringBuilder){
        if (node == null){return;}
        if (key.compareTo(node.key) < 0){
            get(node.left, key, stringBuilder);
        }
        else if (key.compareTo(node.key) > 0){
            get(node.right, key, stringBuilder);
        }
        else {
            stringBuilder.append(node.value).append(" ");
            if (node.left != null && key.equals(node.left.key)){
                get(node.left, key, stringBuilder);
                //stringBuilder.append(node.left.value).append(" ");
            }
            if (node.right != null && key.equals(node.right.key)){
                get(node.right, key, stringBuilder);
                //stringBuilder.append(node.right.value).append(" ");
            }
        }
    }
    public void put(Key key, Value value){
        if (key == null || value == null){throw new IllegalArgumentException("Argument cannot be null");}
        root = put(root, key, value);
    }
    private Node put(Node node, Key key, Value value){
        if (node == null){return new Node(key, value, 1);}
        if (key.compareTo(node.key) < 0){
            node.left = put(node.left, key, value);
        }
        else {
            node.right = put(node.right, key, value);
        }
        node.numberOfNodes = size(node.left) + size(node.right) + 1;
        return node;
    }
    public void delete(Key key){
        if (isEmpty()){throw new RuntimeException("BST underflow");}
        root = delete(root, key);
    }
    private Node delete(Node node, Key key){
        if (node == null){return null;}
        if (key.compareTo(node.key) < 0){
            node.left = delete(node.left, key);
        }
        else if (key.compareTo(node.key) > 0){
            node.right = delete(node.right, key);
        }
        else {
            Node temp = node;
            node = min(node.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
            if (key.equals(node.key)){
                //node.left.key = null;
                //node.left.value = null;
                node = delete(node, key);
            }
        }
        node.numberOfNodes = size(node.left) + size(node.right) + 1;
        return node;
    }
    public Key min(){
        return min(root).key;
    }
    private Node min(Node node){
        if (node.left == null){
            return node;
        }
        return min(node.left);
    }
    public Key max(){
        return max(root).key;
    }
    private Node max(Node node){
        if (node.right == null){
            return node;
        }
        return max(node.right);
    }
    public void deteleMin(){
        if (isEmpty()){throw new RuntimeException("BST underflow");}
        root = deleteMin(root);
    }
    private Node deleteMin(Node node){
        if (node.left == null){
            return node.right;
        }
        node.left =  deleteMin(node.left);
        node.numberOfNodes = size(node.left) + size(node.right) + 1;
        return node;
    }

    public Iterable<Key> keys(){
        return keys(min(), max());
    }
    public Iterable<Key> keys(Key lo, Key hi){
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }
    private void keys(Node node, Queue<Key> queue, Key lo, Key hi){
        if (node == null){return;}
        if (lo.compareTo(node.key) < 0){
            keys(node.left, queue, lo, hi);
        }
        if (lo.compareTo(node.key) <= 0 && hi.compareTo(node.key) >= 0){
            queue.enqueue(node.key);
        }
        if (hi.compareTo(node.key) > 0){
            keys(node.right, queue, lo, hi);
        }
    }

    public static void main(String[] args) {
        Ex09_BSTWithDuplicateKeys<String, Integer> bstWithDuplicateKeys = new Ex09_BSTWithDuplicateKeys<>();
        bstWithDuplicateKeys.put("R", 15);
        bstWithDuplicateKeys.put("E", 5);
        bstWithDuplicateKeys.put("E", 6);
        bstWithDuplicateKeys.put("S", 20);
        bstWithDuplicateKeys.put("A", 1);
        bstWithDuplicateKeys.put("H", 10);
        bstWithDuplicateKeys.put("G", 9);
        bstWithDuplicateKeys.put("M", 12);
        StdOut.println("All keys and values inserted in BST");
        for (String s : bstWithDuplicateKeys.keys()){
            StdOut.println(s + "  " + bstWithDuplicateKeys.get(s));
        }
        StdOut.println("Return both values for Key E " + bstWithDuplicateKeys.get("E") + " expected 5 6");
        StdOut.println("All keys and values in BST after deleting duplicate keys");
        bstWithDuplicateKeys.delete("E");
        for (String s : bstWithDuplicateKeys.keys()){
            StdOut.println(s + "  " + bstWithDuplicateKeys.get(s));
        }
    }
}
/*
*           All keys and values inserted in BST
            A  1
            E  5 6
            E  5 6
            G  9
            H  10
            M  12
            R  15
            S  20
            Return both values for Key E 5 6  expected 5 6
            All keys and values in BST after deleting duplicate keys
            A  1
            G  9
            H  10
            M  12
            R  15
            S  20
*
*
* */