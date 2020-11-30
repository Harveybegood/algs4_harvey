package Chapter3_2_BinarySearchTrees;


import edu.princeton.cs.algs4.Queue;

public class Algorithm3_3_BST<Key extends Comparable<Key>, Value> {
    private class Node{
        private Key key;
        private Value val;
        private Node left, right;
        private int N;
        public Node(Key key, Value val, int N){
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }
    private Node root;
    public int size(){return size(root);}
    private int size(Node node){
        if (node == null) return 0;
        return node.N;
    }
    public Value get(Key key){
        return get(root, key);
    }
    private Value get(Node node, Key key){
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return get(node.left, key);
        else if (cmp > 0) return get(node.right, key);
        else return node.val;
    }
    public void put(Key key, Value value){
        root = put(root, key, value);
    }
    private Node put(Node node, Key key, Value value){
        if (node == null) return new Node(key, value, 1);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, value);
        else if (cmp > 0) node.right = put(node.right, key, value);
        else node.val = value;
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }
    public Key min(){
        return min(root).key;
    }
    private Node min(Node node){
        if (node.left == null) return node;
        return min(node.left);
    }
    public Key max(){
        return max(root).key;
    }
    private Node max(Node node){
        if (node.right == null) return node;
        return max(node.right);
    }
    public Key floor(Key key){
        Node node = floor(root, key);
        if (node == null) return null;
        return node.key;
    }
    private Node floor(Node node, Key key){
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node;
        if (cmp < 0) return floor(node.left, key);
        Node t = floor(node.right, key);
        if (t != null) return t;
        return node;
    }
    public Key ceiling(Key key){
        Node node = ceiling(root, key);
        if (node == null) return null;
        return node.key;
    }
    private Node ceiling(Node node, Key key){
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node;
        if (cmp < 0){
            Node t = ceiling(node.left, key);
            if (t != null) return t;
            return node;
        }
        return ceiling(node.right, key);
    }
    public Key select(int k){
        return select(root, k).key;
    }
    private Node select(Node node, int k){
        if (node == null) return null;
        int t = size(node.left);
        if (t > k) return select(node.left, k);
        else if (t < k) return select(node.right, k - t - 1);
        else return node;
    }
    public int rank(Key key){
        return rank(key, root);
    }
    private int rank(Key key, Node node){
        if (node == null) return 0;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return rank(key, node.left);
        else if (cmp > 0) return 1 + size(node.left) + rank(key, node.right);
        else return size(node.left);
    }
    public void deleteMin(){
        root = deleteMin(root);
    }
    private Node deleteMin(Node node){
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }
    public void deleteMax(){
        root = deleteMax(root);
    }
    private Node deleteMax(Node node){
        if (node.right == null) return node.left;
        node.right = deleteMax(node.right);
        node.N = size(node.left) + size(node.right) + 1;
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
            if (node.right == null) return node.left;
            if (node.left == null) return node.right;
            Node t = node;
            node = min(t.right);
            node.right = deleteMin(t.right);
            node.left = t.left;
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }
    public Iterable<Key> keys(){
        return keys(min(), max());
    }
    public Iterable<Key> keys(Key lo, Key hi){
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return  queue;
    }
    private void keys(Node node, Queue<Key> queue, Key lo, Key hi){
        if (node == null) return;
        int cmplo = lo.compareTo(node.key);
        int cmphi = hi.compareTo(node.key);
        if (cmplo < 0) keys(node.left, queue, lo, hi);
        if (cmplo <= 0 && cmplo >= 0) queue.enqueue(node.key);
        if (cmphi > 0) keys(node.right, queue, lo, hi);
    }
}
