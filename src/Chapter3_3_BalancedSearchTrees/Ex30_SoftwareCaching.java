package Chapter3_3_BalancedSearchTrees;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;


/*
*   Software caching. Modify RedBlackBST to keep the most recently accessed Node in an instance variable so that it can be
*   accessed in constant time if the next put() or get() uses the same key(see Ex3.1.25)
*
* */
public class Ex30_SoftwareCaching<Key extends Comparable<Key>, Value>{
    public static final boolean Red = true;
    public static final boolean Black = false;
    private Node recentAccessedNode;
    private class Node{
        private Key key;
        private Value value;
        private Node left, right;
        private int numberOfSubTree;
        private boolean color;
        public Node(Key key, Value value, int numberOfSubTree, boolean color){
            this.key = key;
            this.value = value;
            this.numberOfSubTree = numberOfSubTree;
            this.color = color;
        }
    }
    private Node root;
    public boolean isEmpty(){return root == null;}
    private Node rotateLeft(Node node){
        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;
        temp.color = node.color;
        node.color = Red;
        temp.numberOfSubTree = node.numberOfSubTree;
        node.numberOfSubTree = size(node.left) + size(node.right) + 1;
        return temp;
    }
    private Node rotateRight(Node node){
        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;
        temp.color = node.color;
        node.color = Red;
        temp.numberOfSubTree = node.numberOfSubTree;
        node.numberOfSubTree = size(node.left) + size(node.right) + 1;
        return temp;
    }
    private void flipColor(Node node){
        if (node == null){
            return;
        }
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }
    private boolean isRed(Node node){
        if (node == null){
            return false;
        }
        return node.color;
    }
    public int size(){
        return size(root);
    }
    private int size(Node node){
        if (node == null){
            return 0;
        }
        return node.numberOfSubTree;
    }
    public void put(Key key, Value value){
        if (key == null){throw new IllegalArgumentException("Key cannot be omitted");}
        if (value == null){throw new IllegalArgumentException("Value cannot be omitted");}
        //
        if (recentAccessedNode != null && key.compareTo(recentAccessedNode.key) == 0){
            StdOut.println("\ncache hit");
            return;
        }
        root = put(root, key, value);
        root.color = Black;
    }
    private Node put(Node node, Key key, Value value){
        if(node == null){
            Node newNode = new Node(key, value, 1, Red);
            recentAccessedNode = newNode;
            return newNode;
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
        if (!isRed(node.left) && isRed(node.right)){
            rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)){
            rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)){
            flipColor(node);
        }
        node.numberOfSubTree = size(node.left) + size(node.right) + 1;
        return node;
    }
    public Value get(Key key){
        if (key == null){throw new IllegalArgumentException("Key cannot be omitted");}
        if (key.compareTo(recentAccessedNode.key) == 0){
            StdOut.println("cache hit");
            return recentAccessedNode.value;
        }
        return get(root, key);
    }
    private Value get(Node node, Key key){
        if (node == null){
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0){
            return get(node.left, key);
        }
        else if (cmp > 0){
            return get(node.right, key);
        }
        else {
            recentAccessedNode = node;
            return node.value;
        }
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
    public void delMin(){
        if (!isRed(root.left) && !isRed(root.right)){
            root.color = Red;
        }
        root = delMin(root);
        if (!isEmpty()){
            root.color = Black;
        }
    }
    private Node delMin(Node node){
        if (node.left == null){
            return null;
        }
        if (!isRed(node.left) && !isRed(node.left.left)){
            node = moveRedLeft(node);
        }
        node.left = delMin(node.left);
        return balance(node);
    }
    private Node moveRedLeft(Node node){
        flipColor(node);
        if (isRed(node.right.left)){
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            //flipCOlor(node);
        }
        return node;
    }
    private Node balance(Node node){
        if (isRed(node.right) && !isRed(node.left)){
            rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)){
            rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)){
            flipColor(node);
        }
        return node;
    }
    public void delMax(){
        if (!isRed(root.left) && !isRed(root.right)){
            root.color = Red;
        }
        root = delMax(root);
        if (!isEmpty()){
            root.color = Black;
        }
    }
    private Node delMax(Node node){
        if (isRed(node.left)){
            node = rotateRight(node);
        }
        if (node.right == null){
            return null;
        }
        if (!isRed(node.right) && !isRed(node.right.left)){
            node = moveRedRight(node);
        }
        node.right = delMax(node.right);
        return balance(node);
    }
    private Node moveRedRight(Node node){
        flipColor(node);
        if (!isRed(node.left.left)){
            node = rotateRight(node);
            //flipColor(node);
        }
        return node;
    }
    public Iterable<Key> keys(){
        return keys(min(), max());
    }
    public Iterable<Key> keys(Key min, Key max){
        Queue<Key> queue = new Queue<>();
        keys(root, queue, min, max);
        return queue;
    }
    private void keys(Node node, Queue<Key> queue, Key min, Key max){
        if (node == null){return;}
        int cmpLo = min.compareTo(node.key);
        int cmpHi = max.compareTo(node.key);
        if (cmpLo < 0){
            keys(node.left, queue, min, max);
        }
        if (cmpLo <= 0 && cmpHi >= 0){
            queue.enqueue(node.key);
        }
        if (cmpHi > 0){
            keys(node.right, queue, min, max);
        }
    }

    public static void main(String[] args) {
        Ex30_SoftwareCaching<String, Integer> softwareCaching = new Ex30_SoftwareCaching<>();
        softwareCaching.put("S", 10);
        softwareCaching.put("E", 3);
        softwareCaching.put("U", 16);
        softwareCaching.put("A", 1);
        softwareCaching.put("R", 8);
        softwareCaching.put("T", 12);
        softwareCaching.put("H", 5);
        softwareCaching.put("K", 7);
        StdOut.println("The sequence of this RBTree");
        for (String s : softwareCaching.keys()){
            StdOut.print(s + " ");
        }
        softwareCaching.delMin();
        StdOut.println("\nAfter deleting the minimum key");
        for (String s : softwareCaching.keys()){
            StdOut.print(s + " ");
        }
        softwareCaching.delMax();
        StdOut.println("\nAfter deleting maximum key");
        for (String s : softwareCaching.keys()){
            StdOut.print(s + " ");
        }
    }
}
