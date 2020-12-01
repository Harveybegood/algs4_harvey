package Chapter3_3_BalancedSearchTrees;


import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;


/*
*   2-3 tree without balance restriction. Develop an implementation of the basic symbol-table API that uses 2-3 tree that are not
*   necessarily balanced as the underlying data structure. Allow 3-nodes to lean either way. Hook the new node onto the bottom with
*   a black link when inserting into a 3-node at the bottom. Run experiments to develop a hypothesis estimating the average path length
*   in a tree built from N random insertion.
*
* */
public class Ex23_23TreesWithoutBalance<Key extends Comparable<Key>, Value> {
    private static final boolean red = true;
    private static final boolean black = false;
    private class Node{
        private Key key;
        private Value value;
        private Node left, right;
        private int numberOfNodes;
        private boolean color;
        public Node(Key key, Value value, int numberOfNodes, boolean color){
            this.key = key;
            this.value = value;
            this.numberOfNodes = numberOfNodes;
            this.color = color;
        }
    }
    private Node root;
    public int size(){return size(root);}
    private int size(Node node){
        if (node == null){return 0;}
        return node.numberOfNodes;
    }
    public void put(Key key, Value value){
        if (key == null || value == null){throw new UnsupportedOperationException("Key cannot be null. ");}
        root = put(root, key, value);
        root.color = black;
    }
    private Node put(Node node, Key key, Value value){
        if (node == null){
            return new Node(key, value, 1, red);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0){
            node.left = put(node.left, key, value);
        }
        else if (cmp > 0){
            node.right = put(node.right, key, value);
        }
        else {
            node.value = value;
        }
        if (isRed(node.left) && isRed(node.right)){
            flipColor(node);
        }
        if (isRed(node.right) && !isRed(node.left)){
            rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)){
            rotateRight(node);
        }
        node.numberOfNodes = 1 + size(node.left) + size(node.right);
        return node;
    }
    public boolean isRed(Node node){
        if (node == null){return false;}
        return node.color;
    }
    public void flipColor(Node node){
        if (node == null){return;}
        node.color = red;
        node.left.color = black;
        node.right.color = black;
    }
    public Node rotateLeft(Node node){
        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;
        temp.color = node.color;
        node.color = red;
        temp.numberOfNodes = node.numberOfNodes;
        node.numberOfNodes = 1 + size(node.left) + size(node.right);
        return temp;
    }
    public Node rotateRight(Node node){
        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;
        temp.color = node.color;
        node.color = red;
        temp.numberOfNodes = node.numberOfNodes;
        node.numberOfNodes = 1 + size(node.left) + size(node.right);
        return temp;
    }
    public Value get(Key key){
        if (key == null){throw new UnsupportedOperationException("Key cannot be null. ");}
        return get(root, key).value;
    }
    private Node get(Node node, Key key){
        if (node == null){return null;}
        int cmp = key.compareTo(node.key);
        if (cmp < 0){
            return get(node.left, key);
        }
        else if (cmp > 0){
            return get(node.right, key);
        }
        else {
            return node;
        }
    }
    public boolean contains(Key key){
        return get(key) != null;
    }
    public boolean isEmpty(){return size() == 0 || root == null;}
    // min
    public Key min(){
        return min(root).key;
    }
    private Node min(Node node){
        if (node.left == null){
            return node;
        }
        return min(node.left);
    }
    // max
    public Key max(){
        return max(root).key;
    }
    private Node max(Node node){
        if (node.right == null){
            return node;
        }
        return max(node.right);
    }
    // floor
    public Key floor(Key key){
        if (key == null){throw new UnsupportedOperationException("Key cannot be null. ");}
        return floor(root, key).key;
    }
    private Node floor(Node node, Key key){
        if (node == null){
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0){
            return node;
        }
        else if (cmp < 0){
            return floor(node.left, key);
        }
        else {
            Node temp = floor(node.right, key);
            if (temp != null){
                return temp;
            }
            else {
                return node;
            }
        }
    }
    // ceiling
    public Key ceiling(Key key){
        if (key == null){throw new UnsupportedOperationException("Key cannot be null. ");}
        return ceiling(root, key).key;
    }
    private Node ceiling(Node node, Key key){
        if (node == null){
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0){
            return node;
        }
        else if (cmp < 0){
            Node temp = ceiling(node.left, key);
            if (temp != null){
                return temp;
            }
            else {
                return node;
            }
        }
        else {
            return ceiling(node.right, key);
        }
    }

    // select
    public Key select(int rank){
        if (rank < 0 || rank > root.numberOfNodes){throw new UnsupportedOperationException("the input rank not in scope. ");}
        return select(root, rank).key;
    }
    private Node select(Node node, int rank){
        if (node == null){
            return null;
        }
        int c = size(node.left);
        if (c > rank){
            return select(node.left, rank);
        }
        else if (c < rank){
            return select(node.right, rank - c - 1);
        }
        else {
            return node;
        }
    }
    // rank
    public int rank(Key key){
        if (key == null){throw new UnsupportedOperationException("Key cannot be null. ");}
        return rank(root, key);
    }
    private int rank(Node node, Key key){
        if (node == null){
            return 0;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0){
            return node.numberOfNodes;
        }
        else if (cmp < 0){
            return rank(node.left, key);
        }
        else {
            return size(node.left) + 1 + rank(node.right, key);
        }
    }
    // deleteMin
    public void deleteMin(){
        if (isEmpty()){throw new NoSuchElementException("BST underflow. ");}
        if (!isRed(root.left) && !isRed(root.right)){
            //flipColor(root);
            root.color = red;
        }
        root = deleteMin(root);
        if (!isEmpty()){root.color = black;}

    }
    private Node deleteMin(Node node){
        if (node.left == null){
            return null;
        }
        if (!isRed(node.left) && !isRed(node.left.left)){
            node = moveRedLeft(node);
        }
        node.left = deleteMin(node.left);
        return balance(node);
    }
    // deleteMax
    public void deleteMax(){
        if (isEmpty()){throw new NoSuchElementException("BST underflow. ");}
        if (!isRed(root.left) && !isRed(root.right)){
            //flipColor(root);
            root.color = red;
        }
        root = deleteMax(root);
        if (!isEmpty()){root.color = black;}
    }
    private Node deleteMax(Node node){
        if (isRed(node.left)){
            node = rotateRight(node);
        }
        if (node.right == null){
            return null;
        }
        if (!isRed(node.right) && !isRed(node.right.left)){
            node = moveRedRight(node);
        }
        node.right = deleteMax(node.right);
        return balance(node);
    }
    // Assuming that node is red and both node.left and node.left.left are black, make node.left or one of its children red.
    private Node moveRedLeft(Node node){
        if (isEmpty()){throw new NoSuchElementException("BST underflow. ");}
        flipColor(node);
        if (isRed(node.right.left)){
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipColor(node);
        }
        return node;
    }
    // Assuming that node is red and both node.right and node.right.left are black, make node.right or one of its children red.
    private Node moveRedRight(Node node){
        if (isEmpty()){throw new NoSuchElementException("BST underflow. ");}
        flipColor(node);
        if (isRed(node.left.left)){
            node = rotateRight(node);
            flipColor(node);
        }
        return node;
    }
    // restore red-black tree invariant
    private Node balance(Node node){
        if (isEmpty()){throw new NoSuchElementException("BST underflow. ");}
        if (isRed(node.right)){
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)){
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)){
            flipColor(node);
        }
        node.numberOfNodes = size(node.left) + size(node.right) + 1;
        return node;
    }

    // size(Key lo,  Key hi)
    public int size(Key lo, Key hi){
        int n = 0;
        return size(root, lo, hi, n);
    }
    public int size(Node node, Key lo, Key hi, int n){
        int locmp = lo.compareTo(node.key);
        int hicmp = hi.compareTo(node.key);
        if (locmp <= 0){
            n++;
            size(node.left, lo, hi, n);
        }
        if (hicmp >= 0){
            n++;
            size(node.right, lo, hi, n);
        }
        return n;
    }
    // keys()
    public Iterable<Key> keys(){
        return keys(min(), max());
    }
    public Iterable<Key> keys(Key lo, Key hi){
        if (lo == null) throw new IllegalArgumentException("First argument to keys() is null. ");
        if (hi == null) throw new IllegalArgumentException("Second argument to keys() is null. ");
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }
    private void keys(Node node, Queue<Key> queue, Key lo, Key hi){
        if (node == null)return;
        int locmp = lo.compareTo(node.key);
        int hicmp = hi.compareTo(node.key);
        if (locmp < 0){
            keys(node.left, queue, lo, hi);
        }
        if (hicmp > 0){
            keys(node.right, queue, lo, hi);
        }
        if (locmp <= 0 && hicmp >= 0){
            queue.enqueue(node.key);
        }
    }

    // delete
    public void delete(Key key){
        if (key == null){throw new IllegalArgumentException("argument to delete() is null. ");}
        if (!contains(key)){return;}
        // if both children of root are black, set root to red.
        if (!isRed(root.left) && !isRed(root.right)){
            root.color = red;
        }
        root = delete(root, key);
        if (!isEmpty()){
            root.color = black;
        }
    }
    private Node delete(Node node, Key key){
        if (key.compareTo(node.key) < 0){
            if (!isRed(node.left) && !isRed(node.left.left)){
                node = moveRedLeft(node);
            }
            node.left = delete(node.left, key);
        }
        else {
            if (isRed(node.left)){
                node = rotateRight(node);
            }
            if (key.compareTo(node.key) == 0 && node.right == null){
                return null;
            }
            if (!isRed(node.right) && !isRed(node.right.left)){
                node = moveRedRight(node);
            }
            if (key.compareTo(node.key) == 0){
                Node x = min(node.right);
                node.key = x.key;
                node.value = x.value;
                node.right = deleteMax(node.right);
            }
            else{
                node.right = delete(node.right, key);
            }
        }
        return balance(node);
    }
    private boolean isBST(){
        return isBST(root, null, null);
    }
    private boolean isBST(Node node, Key min, Key max){
        if (node == null) return true;
        if (min != null && node.key.compareTo(min) < 0){
            return false;
        }
        if (max != null && node.key.compareTo(max) > 0){
            return false;
        }
        return isBST(node.left, min, max) && isBST(node.right, min, max);
    }
    private boolean isSizeConsistent(){
        return isSizeConsistent(root);
    }
    private boolean isSizeConsistent(Node node){
        if (node == null) return true;
        if (node.numberOfNodes != size(node.left) + size(node.right) + 1){
            return false;
        }
        return isSizeConsistent(node.left) && isSizeConsistent(node.right);
    }
    private boolean isRankConsistent(){
        for (int i = 0; i < size(); i++){
            if (i != rank(select(i))){
                return false;
            }
        }
        for (Key key : keys()){
            if (key.compareTo(select(rank(key))) != 0){
                return false;
            }
        }
        return true;
    }
    private boolean is23(){
        return is23(root);
    }
    private boolean is23(Node node) {
        if (node == null) return true;
        if (node != root && isRed(node) && isRed(node.left)) {
            return false;
        }
        if (isRed(node.right)) {
            return false;
        }
        return is23(node.left) && is23(node.right);
    }
    // do all paths from root to leaf have the same number of black edges
    private boolean isBalanced(){
        int black = 0;
        Node x = root;
        while (x != null){
            if (!isRed(x)){
                black++;
            }
            x = x.left;
        }
        return isBalanced(root, black);
    }
    // does every path from the root to a leaf have the given number of black links?
    private boolean isBalanced(Node node, int black){
        if (node == null){
            return black == 0;
        }
        if (!isRed(node)){
            black--;
        }
        return isBalanced(node.left, black) && isBalanced(node.right, black);
    }
    private boolean check(){
        if (!isBST()){
            StdOut.println("Not in a symmetric order");
        }
        if (!isSizeConsistent()){
            StdOut.println("Subtree counts not consistent");
        }
        if (!isRankConsistent()){
            StdOut.println("Ranks not consistent");
        }
        if (!is23()){
            StdOut.println("Not a 2-3 tree");
        }
        if (!isBalanced()){
            StdOut.println("Not balanced");
        }
        return isBST() && isSizeConsistent() && isRankConsistent() && is23() && isBalanced();
    }
    public static void main(String[] args) {
        Ex23_23TreesWithoutBalance<String, Integer> treesWithoutBalance = new Ex23_23TreesWithoutBalance<>();
        treesWithoutBalance.put("S", 10);
        treesWithoutBalance.put("E", 3);
        treesWithoutBalance.put("X", 12);
        treesWithoutBalance.put("A", 1);
        treesWithoutBalance.put("R", 7);
        treesWithoutBalance.put("H", 4);
        treesWithoutBalance.put("K", 6);
        StdOut.println(treesWithoutBalance.check());
    }
}
