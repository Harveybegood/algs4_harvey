package Chapter3_3_BalancedSearchTrees;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;
import org.omg.CORBA.NO_IMPLEMENT;

import java.util.NoSuchElementException;

/*
*   Top-down 2-3-4 trees. Develop an implementation of the basic symbol-table API that uses balanced 2-3-4 trees as the underlying data
*   structure, using the red-black representation and the insertion method described in the text, where 4-nodes are split by flipping
*   colors on the way down the search path and balancing on the way up.
*
* */
public class Ex25_TopDown234Trees<Key extends Comparable<Key>, Value> {
    private static final boolean Red = true;
    private static final boolean black = false;
    private class Node{
        Key key;
        Value value;
        Node left, right;
        int numberOfNode;
        boolean color;
        public Node(Key key, Value value, int numberOfNode, boolean color){
            this.key = key;
            this.value = value;
            this.numberOfNode = numberOfNode;
            this.color = color;
        }
    }
    private Node root;

    // put
    public void put(Key key, Value value){
        root = put(root,key, value);
        root.color = black;
    }
    private Node put(Node node, Key key, Value value){
        if (node == null){
            return new Node(key, value,1, Red);
        }
        if (isRed(node.left) && isRed(node.right)){
            flipColor(node);
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
        node.numberOfNode = size(node.left) + size(node.right) + 1;
        if (isRed(node.left) && isRed(node.left.left)){
            node = rotateRight(node);
        }
        if (isRed(node.right) && !isRed(node.left)){
            node = rotateLeft(node);
        }
        return node;
    }
    private void flipColor(Node node){
        // h must have opposite color of its two children
        // assert (h != null) && (h.left != null) && (h.right != null);
        // assert (!isRed(h) &&  isRed(h.left) &&  isRed(h.right))
        //    || (isRed(h)  && !isRed(h.left) && !isRed(h.right));
        if (node == null){
            return;
        }
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }
    private Node rotateLeft(Node node){
        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;
        temp.color = node.color;
        node.color = Red;
        temp.numberOfNode = node.numberOfNode;
        node.numberOfNode = size(node.left) + size(node.right) + 1;
        return temp;
    }
    private Node rotateRight(Node node){
        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;
        temp.color = node.color;
        node.color = Red;
        temp.numberOfNode = node.numberOfNode;
        node.numberOfNode = size(node.left) + size(node.right) + 1;
        return temp;
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
        return node.numberOfNode;
    }
    public boolean isEmpty(){return root == null;}
    public boolean contains(Key key){
        if (key == null) throw new IllegalArgumentException("Argument cannot be omitted. ");
        return get(key) != null;
    }
    public Value get(Key key){
        if (key == null) throw new IllegalArgumentException("Argument cannot be omitted.");
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
    // select(), find the key on condition of that a rank being given
    public Key select(int rank){
        if (rank < 0 || rank > size()){
            throw new IllegalArgumentException("Argument input is out of the scope of Object");
        }
        return select(root, rank);
    }
    private Key select(Node node, int rank){
        if (node == null){
            return null;
        }
        if (rank == node.numberOfNode){
            return node.key;
        }
        else if (rank < node.numberOfNode){
            return select(node.left, rank);
        }
        else {
            return select(node.right, rank);
        }
    }
    // rank(), tell what is the rank of a key
    public int rank(Key key){
        if (key == null){
            throw new IllegalArgumentException("Argument key cannot be null. ");
        }
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
            return node.numberOfNode + 1 + rank(node.right, key);
        }
        else {
            return node.numberOfNode;
        }
    }
    // ceiling(), smallest value not smaller than x
    public Key ceiling(Key key){
        if (key == null){
            throw new IllegalArgumentException("Argument cannot be null.");
        }
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
            return node;
        }
        else {
            return ceiling(node.right, key);
        }
    }
    // floor(), largest value not greater than x
    public Key floor(Key key){
        if (key == null){
            throw new IllegalArgumentException("Argument cannot be null");
        }
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
            return node;
        }
    }
    // min()
    public Key min(){
        return min(root).key;
    }
    private Node min(Node node){
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
        if (node.right == null){
            return node;
        }
        return max(node.right);
    }
    // key(). traversal the keys in the tree
    public Iterable<Key> Keys(){
        return Keys(min(), max());
    }
    public Iterable<Key> Keys(Key low, Key high){
        Queue<Key> queue = new Queue<>();
        Keys(root, queue, low, high);
        return queue;
    }
    private void Keys(Node node, Queue<Key> queue, Key low, Key high){
        if (node == null){
            return;
        }
        int locmp = low.compareTo(node.key);
        int hicmp = high.compareTo(node.key);
        if (locmp < 0){
            Keys(node.left, queue, low, high);
        }
        if (locmp <= 0 && hicmp >= 0){
            queue.enqueue(node.key);
        }
        if (hicmp > 0){
            Keys(node.right, queue, low, high);
        }
    }

    // delMin()
/*    public void delMin(){
        if (isEmpty()){throw new NoSuchElementException("BST underflow.");}
        root = delMin(root);
    }
    private Node delMin(Node node){
        if (node == null){
            return null;
        }
        if (node.left == null){
            // the current node is not a 2-node
            if (isRed(node)){
                node = null;
            }
            // the current node is a 2-node and its immediate sibling is not a 2-node
            if (!isRed(node) && isRed(node.left)*//*immediate sibling is not a 2-node*//*){
                node = moveToLeft(node);
            }
            // the current node is a 2-node and its immediate sibling is also a 2-node
            if (!isRed(node) && !isRed(node.left)*//*immediate sibling is a 2-node*//*){
                flipColor(node);
            }
        }
        node.left = delMin(node.left);
        return balance(node);
    }*/
    // copy from algs4...
    public void delMin(){
        if (isEmpty()){throw new NoSuchElementException("BST underflow. ");}
        if (!isRed(root.left) && !isRed(root.right)){
            root.color = Red;
        }
        root = delMin(root);
        if (!isEmpty()){root.color = black;}
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
            flipColor(node);
        }
        return node;
    }
    // delMax()
  /*  public void delMax(){
        if (isEmpty()){throw new NoSuchElementException("BST underflow.");}
        root = delMax(root);
    }
    private Node delMax(Node node){
        if (node == null){
            return null;
        }
        if (node.right == null){
            if (isRed(node)){
                node = null;
            }
            if (!isRed(node) && isRed(node.left)){
                node = moveRedRight(node);
            }
            if (!isRed(node) && !isRed(node.left)){
                flipColor(node);
            }
        }
        node.right = delMax(node.right);
        return balance(node);
    }*/
    public void delMax() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = Red;

        root = delMax(root);
        if (!isEmpty()) root.color = black;
        // assert check();
    }

    // delete the key-value pair with the maximum key rooted at h
    private Node delMax(Node h) {
        if (isRed(h.left))
            h = rotateRight(h);

        if (h.right == null)
            return null;

        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);

        h.right = delMax(h.right);

        return balance(h);
    }
    // Assuming that h is red and both h.right and h.right.left
    // are black, make h.right or one of its children red.
    private Node moveRedRight(Node node){
        // assert (h != null);
        // assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
        flipColor(node);
        if (isRed(node.left.left)){
            node = rotateRight(node);
            flipColor(node);
        }
        return node;
    }
    // delete()

    public void delete(Key key)
    {
        if (!isRed(root.left) && !isRed(root.right))
            root.color = Red;
        root = delete(root, key);
        if (!isEmpty()) root.color = black;
    }
    private Node delete(Node h, Key key)
    {
        if (key.compareTo(h.key) < 0)
        {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }
        else
        {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0)
            {
                Node x = min(h.right);
                h.key = x.key;
                h.value = x.value;
                // h.val = get(h.right, min(h.right).key);
                // h.key = min(h.right).key;
                h.right = delMin(h.right);
            }
            else h.right = delete(h.right, key);
        }
        return balance(h);
    }
    // restore red-black tree invariant
    private Node balance(Node node){
        if (isRed(node.right)){node = rotateLeft(node);}
        if (isRed(node.left) && isRed(node.left.left)){node = rotateRight(node);}
        if (isRed(node.left) && isRed(node.right)){flipColor(node);}
        node.numberOfNode = size(node.left) + size(node.right) + 1;
        return node;
    }

    public static void main(String[] args) {
        Ex25_TopDown234Trees<String, Integer> topDown234Trees = new Ex25_TopDown234Trees<>();
        topDown234Trees.put("S", 6);
        topDown234Trees.put("E", 2);
        topDown234Trees.put("T", 7);
        topDown234Trees.put("A", 1);
        topDown234Trees.put("R", 5);
        topDown234Trees.put("H", 3);
        topDown234Trees.put("K", 4);
        StdOut.println("Value of floor: " + topDown234Trees.floor("J") + " expecting H");
        StdOut.println("Value of ceiling : " + topDown234Trees.ceiling("J") + " expecting K");
        StdOut.println("Value of floor: " + topDown234Trees.floor("M") + " expecting K");
        StdOut.println("Value of ceiling : " + topDown234Trees.ceiling("M") + " expecting R");
        StdOut.println("Max value: " + topDown234Trees.max());
        StdOut.println("Min value: " + topDown234Trees.min());
        for (String s : topDown234Trees.Keys("A", "T")){
            StdOut.print(s + " ");
        }

        StdOut.println("\nSize of BST: " + topDown234Trees.size());
        topDown234Trees.delMin();
        StdOut.println("Size of BST after deleting minimum: " + topDown234Trees.size());
        for (String s : topDown234Trees.Keys()){
            StdOut.print(s + " ");
        }
        topDown234Trees.delMax();
        StdOut.println("\nSize of BST after deleting maximum: " + topDown234Trees.size());
        for (String s : topDown234Trees.Keys()){
            StdOut.print(s + " ");
        }
        topDown234Trees.delete("S");
        StdOut.println("\nSize of BST after deleting Key S: " + topDown234Trees.size());
        for (String s : topDown234Trees.Keys()){
            StdOut.print(s + " ");
        }
    }
}




