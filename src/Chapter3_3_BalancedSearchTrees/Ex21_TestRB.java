package Chapter3_3_BalancedSearchTrees;


import org.omg.CORBA.NO_IMPLEMENT;

/*
*   Create a Test client TestRB.java, based on your solution to Ex3.2.10.
*
*   3.2.10 Write a Test client TestBST.java for use in testing the implementations of min(), max(), floor(), ceiling(),
*   select(), rank(), delete(), deleteMin(), deleteMax(), and keys() that are given in the text.
*   Start with the standard indexing client given on page 370. Add code to take additional command-line arguments, as appropriate.
*
*
* */
public class Ex21_TestRB<Key extends Comparable<Key>, Value> {
    private static final boolean red = true;
    private static final boolean black = false;
    private class Node{
        Key key;
        Value value;
        Node left, right;
        boolean color;
        int countOfNodes;
        public Node(Key key, Value value, boolean color, int countOfNodes){
            this.key = key;
            this.value = value;
            this.color = color;
            this.countOfNodes = countOfNodes;
        }
    }
    private Node root;
    public int size(){
        return size(root);
    }
    private int size(Node node){
        if (node == null) return 0;
        return node.countOfNodes;
    }
    public boolean isRed(Node node){
        if (node == null) {
            return false;
        }
        return node.color;
    }
    public void flipColor(Node node){
        if (node == null){
            return;
        }
        node.color = red;
        node.left.color = black;
        node.right.color = black;
    }
    public Node rotateLeft(Node node){
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = red;
        x.countOfNodes = node.countOfNodes;
        node.countOfNodes = 1 + size(node.left) + size(node.right);
        return x;
    }
    public Node rotateRight(Node node){
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = red;
        x.countOfNodes = node.countOfNodes;
        node.countOfNodes = 1 + size(node.left) + size(node.right);
        return x;
    }
    public void put(Key key, Value value){
        root = put(root, key, value);
        root.color = black;
    }
    private Node put(Node node, Key key, Value value){
        if (node == null){
            return new Node(key, value, red, 1);
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
        node.countOfNodes = 1 + size(node.left) + size(node.right);
        if (isRed(node.left) && isRed(node.right)){
            flipColor(node);
        }
        if (isRed(node.left) && isRed(node.left.left)){
            rotateRight(node);
        }
        if (isRed(node.right)){
            rotateLeft(node);
        }
        return node;
    }
    public Value get(Key key){
        return get(root, key).value;
    }
    private Node get(Node node, Key key){
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
            return node;
        }
    }
    // min()
    public Key min(){
        return min(root).key;
    }
    private Node min(Node node){
        if (node == null){
            return null;
        }
        if (node.left == null){
            return node;
        }
        return min(node.left);
    }

    // max()
    public Key max(){
        return max(root).key;
    }
    private Node max(Node node){
        if (node == null){
            return null;
        }
        if (node.right == null){
            return node;
        }
        return max(node.right);
    }

    // floor() to find a key which is just less than or equal to the key to be searched
    public Key floor(Key key){
        return floor(root, key).key;
    }
    private Node floor(Node node, Key key){
        if (node == null){
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node;
        if (cmp < 0){
            return floor(node.left, key);
        }
        Node temp = floor(node.right, key);
        if (temp != null){
            return temp;
        }
        else {
            return node;
        }
    }

    // ceiling() to find a key in the tree which is just greater than or equal the key to be searched
    public Key ceiling(Key key){
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
        if (cmp < 0){
            Node temp = ceiling(node.left, key);
            if (temp != null){
                return temp;
            }
            else {
                return node;
            }
        }
        return ceiling(node.right, key);
    }

    // select()
    public Key select(int rank){
        return select(root, rank).key;
    }
    private Node select(Node node, int rank){
        if (node == null){
            return null;
        }
        int t = size(node.left);
        if (rank < node.countOfNodes){
            return select(node.left, rank);
        }
        else if (rank > node.countOfNodes){
            return select(node.right, rank-t-1);
        }
        else {
            return node;
        }
    }

    // rank()
    public int rank(Key key){
        return rank(root, key);
    }
    private int rank(Node node, Key key){
        if (node == null){
            return 0;
        }
        int cmp = key.compareTo(node.key);

        if (cmp < 0){
            return rank(node.left, key);
        }
        else if (cmp > 0){
            return 1 + size(node.left) + rank(node.right, key);
        }
        else {
            return size(node.left);
        }
    }

    // keys()

}
