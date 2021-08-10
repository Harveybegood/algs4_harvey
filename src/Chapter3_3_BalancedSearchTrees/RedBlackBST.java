package Chapter3_3_BalancedSearchTrees;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    public class Node{
        public Key key;
        public Value value;
        public Node left, right;
        int N;
        boolean color;
        public Node(Key key, Value value, int N, boolean color){
            this.key = key;
            this.value = value;
            this.N = N;
            this.color = color;
        }
    }
    public Node root;
    public boolean isEmpty(){return root == null;}
    // create a method to tell if it's node is RED
    public boolean isRed(Node node){
        if (node == null) return false;
        return node.color;
    }
    // calculate the size of node
    public int size(){
        return size(root);
    }
    private int size(Node node){
        if (node == null) return 0;
        return node.N;
    }
    // rotate left to it if any right-leaning 3-node
    private Node rotateLeft(Node node){
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        x.N = node.N;
        node.N = 1 + size(node.left) + size(node.right);
        return x;
    }
    // rotate right to it if two left-leaning red links in a row
    private Node rotateRight(Node node){
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        x.N = node.N;
        node.N = 1 + size(node.left) + size(node.right);
        return x;
    }
    // flip the color if a node with two red children
    private void flipColor(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }
    public void put(Key key, Value value){
        root = put(root, key, value);
        root.color = BLACK;
    }
    private Node put(Node node, Key key, Value value){
        if (node == null) return new Node(key, value, 1, RED);
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
        if (isRed(node.right) && !isRed(node.left)){
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)){
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)){
            flipColor(node);
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }
    public Value get(Key key){
        if (key == null){throw new IllegalArgumentException("Argument key cannot be null");}
        return get(root, key);
    }
    private Value get(Node node, Key key){
        if (node == null){return null;}
        if (node.key.compareTo(key) < 0){
            return get(node.right, key);
        }
        else if (node.key.compareTo(key) > 0){
            return get(node.left, key);
        }
        else {
            return node.value;
        }
    }
     private void flipColors(Node node){
        if (node == null){
            return;
        }
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }
    private Node moveRedLeft(Node node){
        flipColors(node);
        if (isRed(node.right.left)){
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
        }
        return node;
    }
    private Node moveRedRight(Node node){
        flipColors(node);
        if (!isRed(node.left.left)){
            node = rotateRight(node);
        }
        return node;
    }

    private Node balance(Node node){
        if (!isRed(node.left) && isRed(node.right)){
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)){
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)){
            flipColors(node);
        }
        return node;
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


    public Key min(){
        return min(root).key;
    }
    private Node min(Node node){
        if (node.left == null){
            return node;
        }
        return min(node.left);
    }

    public Key select(int i){
        if (i < 0 || i > size()){throw new IllegalArgumentException("Illegal argument");}
        return select(root, i).key;
    }
    private Node select(Node node, int i){
        if (node == null){return null;}
        if (i < node.left.N){
            return select(node.left, i);
        }
        else if (i > node.right.N){
            return select(node.right, i - node.left.N - 1);
        }
        else {
            return node;
        }
    }

    public int rank(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        return rank(root, key);
    }
    private int rank(Node node, Key key){
        if (node == null){return 0;}
        if (key.compareTo(node.key) < 0){
            return rank(node.left, key);
        }
        else if (key.compareTo(node.key) > 0){
            return node.left.N + 1 + rank(node.right, key);
        }
        else {
            return node.N;
        }
    }
    public void delMin(){
        if (!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }
        root = delMin(root);
        if (!isEmpty()){
            root.color = BLACK;
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
    public void delMax(){
        if (!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }
        root = delMax(root);
        if (!isEmpty()){
            root.color = BLACK;
        }
    }
    private Node delMax(Node node){
        if (node.right == null){
            return null;
        }
        if (isRed(node.left)){
            node = rotateRight(node);
        }
        if (!isRed(node.right) && !isRed(node.right.left)){
            node = moveRedRight(node);
        }
        node.right = delMin(node.right);
        return balance(node);
    }
    public void delete(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be omitted");}
        if (!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }
        root = delete(root, key);
        if (!isEmpty()){
            root.color = BLACK;
        }
    }

    private Node delete(Node node, Key key){
        int cmp = key.compareTo(node.key);
        if (cmp < 0){
            if (!isRed(node.left) && !isRed(node.left.left)){
                node = moveRedLeft(node);
            }
            node.left = delete(node.left, key);
        }
        else {
            if (isRed(node.left)){
                node = rotateRight(node);
            }
            if (key.compareTo(node.key) == 0 && (node.right == null)){
                return null;
            }
            if (!isRed(node.right) && !isRed(node.right.left)){
                node = moveRedRight(node);
            }
            if (key.compareTo(node.key) == 0) {
                node.key = min(node.right).key;
                node.value = get(node.right, min(node.right).key);
                node.right = delMin(node.right);
            }
            else {
                node.right = delete(node.right, key);
            }
        }
        return balance(node);
    }
    /*public Iterable<Key> keys(){
        Queue<Key> queue = new Queue<>();
        Stack<Node> stack = new Stack<>();
        Node node = root;
        while (node != null){
            stack.push(node);
            node = node.left;
            if (node.left == null && node.right != null){
                node = node.right;
            }
            if (node.left == null && node.right == null){
                Node temp = stack.pop();
                queue.enqueue(temp.key);
                node = stack.pop();
                queue.enqueue(node.key);
                if (node.right != null){
                    node = node.right;
                }
            }
        }
        return queue;
    }*/
    public Iterable<Key> keys(){
        return keys(min(), max());
    }
    public Iterable<Key> keys(Key min, Key max){
        Queue<Key> queue = new Queue<>();
        keys(root, queue, min, max);
        return queue;
    }
    private void keys(Node node, Queue<Key> queue, Key min, Key max){
        if (node == null){
            return;
        }
        int cmpLo = min.compareTo(node.key);
        int cmpHi = max.compareTo(node.key);
        if (cmpLo < 0){
            keys(node.left, queue, min, max);
        }
        if (cmpLo <=0 && cmpHi >= 0){
            queue.enqueue(node.key);
        }
        if (cmpHi > 0){
            keys(node.right, queue, min, max);
        }
    }

    public static void main(String[] args) {
        RedBlackBST<String, Integer> redBlackBST = new RedBlackBST<>();
        redBlackBST.put("R", 15);
        redBlackBST.put("E", 5);
        redBlackBST.put("S", 20);
        redBlackBST.put("A", 1);
        redBlackBST.put("H", 10);
        redBlackBST.put("G", 8);
        redBlackBST.put("M", 12);
        for (String s : redBlackBST.keys()){
            StdOut.println(s + " " + redBlackBST.get(s));
        }
    }
}
