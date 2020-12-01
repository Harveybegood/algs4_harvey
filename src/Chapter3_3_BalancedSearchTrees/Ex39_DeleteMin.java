package Chapter3_3_BalancedSearchTrees;



import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

/*
*   Delete the minimum. Implement the deleteMin() operation for red-black BSTs by maintaining the correspondence with the
*   transformations given in the text for moving down the left spine of the tree while maintaining the invariant that the
*   current node is not a 2-node.
*
* */
public class Ex39_DeleteMin<Key extends Comparable<Key>, Value> {
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
        if (isEmpty()){throw new NoSuchElementException("BST underflow");}
        if (!isRed(root.left) && !isRed(root.right)){
            root.color = red;
        }
        root = deleteMin(root);
        if (!isEmpty()){
            root.color = black;
        }
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
    // restore red-black tree invariant
    private Node balance(Node node){
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
    //
    private Node moveRedLeft(Node node){
        //
        flipColor(node);
        if (isRed(node.right.left)){
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipColor(node);
        }
        return node;
    }
    private Node moveRedRight(Node node){
        //
        flipColor(node);
        if (isRed(node.left.left)){
            node = rotateRight(node);
            flipColor(node);
        }
        return node;
    }

    public static void main(String[] args) {
        Ex39_DeleteMin<String, Integer> deleteMin = new Ex39_DeleteMin<>();
        deleteMin.put("s", 7);
        deleteMin.put("e", 2);
        deleteMin.put("r", 5);
        deleteMin.put("a", 1);
        deleteMin.put("t", 8);
        deleteMin.put("k", 6);
        deleteMin.put("h", 3);
        deleteMin.put("i", 4);
        deleteMin.deleteMin();
        StdOut.println("Size: " + deleteMin.size());
    }
}
