package Chapter3_3_BalancedSearchTrees;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node{
        Key key;
        Value value;
        Node left, right;
        int N;
        boolean color;
        public Node(Key key, Value value, int N, boolean color){
            this.key = key;
            this.value = value;
            this.N = N;
            this.color = color;
        }
    }
    private Node root;
    // create a method to tell if it's node is RED
    private boolean isRed(Node node){
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
}
