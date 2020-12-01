package Chapter3_3_BalancedSearchTrees;
/*
*   2-3 trees without balance restriction. Develop an implementation of the basic symbol-table API that uses 2-3 trees
*   that are not-necessarily balanced as the underlying data structure. Allow 3-nodes to lean either way. Hook the new node
*   onto the bottom with a black link when inserting into a 3-node at the bottom. Run experiments to develop a hypothesis
*   estimating the average path length in a tree built from N random insertions.
*
* */
public class Ex23_TreesWithoutBalanceRestriction<Key extends Comparable<Key>, Value> {
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

    }

    // deleteMax

    // size(Key lo,  Key hi)

    // keys()

    // keys(Key lo,  Key hi)

    // delete
}
