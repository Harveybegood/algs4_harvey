package Chapter3_3_BalancedSearchTrees;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

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

    public Key min(){
        return min(root).key;
    }
    private Node min(Node node){
        if (node.left == null){
            return node;
        }
        return min(node.left);
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
        else if (cmp > 0){
            if (isRed(node.left)){
                node = rotateRight(node);
            }
            if (!isRed(node.right) && !isRed(node.right.left)){
                node = rotateRight(node);
            }
            node.right = delete(node.right, key);
        }
        else {
            //Node temp = node;
            if (node.right == null){
                return null;
            }
            Node temp = min(node.right);
            node.key = temp.key;
            node.value = temp.value;
            node.right = delMin(node.right);
        }
        return balance(node);
    }
    public Iterable<Key> keys(){
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
    }
}
