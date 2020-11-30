package Chapter3_2_BinarySearchTrees;

import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

/*
*   Threading. Your goal is to support an extended API ThreadedST that supports the following additional operations in
*   constant time:
*       Key next(Key key)  key that follows key(null if key is the maximum)
*       Key prev(Key key)  key that precedes key(null if key is the minimum)
*   To do so, add fields pred and succ to Node that contain links to the predecessor and successor nodes, and modify put(),
*   deleteMin(), deleteMax()m and delete() to maintain these fields.
*
*
* */
public class Ex34_Threading<Key extends Comparable<Key>, Value> {
    private class Node{
        private Node left, right;
        private Node pred, succ;
        private Key key;
        private Value value;
        private int countOfTree;
        public Node(Key key, Value value, int countOfTree){
            this.key = key;
            this.value = value;
            this.countOfTree = countOfTree;
        }
    }
    private Node root;
    public int size(){
        return size(root);
    }
    private int size(Node node){
        if (node == null) return 0;
        return node.countOfTree;
    }
    public void put(Key key, Value value){
        root = put(root, key, value);
    }
    private Node put(Node node, Key key, Value value){
        if (node == null){
            node = new Node(key, value, 1);
            node.left = node.pred;
            node.succ = node.right;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
            //node.left = node.pred;
         }
        else if (cmp > 0) {
            node.right = put(node.right, key, value);
            //node.succ = node.right;
        }
        else node.value = value;
        node.countOfTree = size(node.left) + size(node.right) + 1;
        return node;
    }
    public Key min(){
        return min(root).key;
    }
    private Node min(Node node){
        if (node.left == null) return node;
        return min(node.left);
    }
    public void deleteMin(){
        root = deleteMin(root);
    }
    private Node deleteMin(Node node){
        if (node.left == null) {
            if (node.right != null){
                node.succ = node.right;
                node = node.right.pred;
            }
            return node.right;
        }
        node.left = deleteMin(node.left);
        node.countOfTree = size(node.left) + size(node.right) + 1;
        return node;
    }
    public void deleteMax(){
        root = deleteMax(root);
    }
    private Node deleteMax(Node node){
        if (node.right == null){
            if (node.left != null){
                node.succ = node.left;
                node = node.left.pred;
            }
            return node.left;
        }
        node.right = deleteMax(node.right);
        node.countOfTree = size(node.left) + size(node.right) + 1;
        return node;
    }
    public void delete(Key key){
        root = delete(root, key);
    }
    private Node delete(Node node, Key key){
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = delete(node.left, key);
        else if (cmp > 0) node.right = delete(node.right, key);
        else {
            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }
            Node x = node;
            node = min(x.right);
            node.left = x.left;
            node.right = deleteMin(x.right);
            node.left = node.succ;
            node.pred = node.right;
        }
        node.countOfTree = size(node.left) + size(node.right) + 1;
        return node;
    }
    public Key next(Key key){
        if (key == null) throw new NoSuchElementException("No argument key.");
        return next(root, key);
    }
    private Key next(Node node, Key key){
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0){
            return next(node.left, key);
        }
        else if (cmp > 0){
            return next(node.right, key);
        }
        else {
            // use node.right to retrieve its succ key
            return node.right.key;
        }
    }
    public Key prev(Key key){
        if (key == null) throw new NoSuchElementException("No argument key");
        return prev(root, key);
    }
    private Key prev(Node node, Key key){
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0){
            return prev(node.left, key);
        }
        else if (cmp > 0) {
            return prev(node.right, key);
        }
        else {
            // It seems node not link with its predecessor
            return node.pred.key;
        }
    }

    public static void main(String[] args) {
        Ex34_Threading<String, Integer> threading = new Ex34_Threading<>();
        threading.put("S", 6);
        threading.put("E", 2);
        threading.put("X", 7);
        threading.put("A", 1);
        threading.put("R", 5);
        threading.put("H", 4);
        threading.put("M", 3);
        threading.put("T", 8);
        StdOut.println("E's successor R; " + threading.next("E"));
        StdOut.println("E's predecessor A; " + threading.prev("E"));
    }
}
