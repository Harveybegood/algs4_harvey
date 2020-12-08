package Chapter3_2_BinarySearchTrees;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/*
*   Write a Test client TestBST.java for use in testing the implementations of min(), max(), floor(), ceiling(),
*   select(), rank(), delete(), deleteMin(), deleteMax(), and keys() that are given in the text. Start with the
*   standard indexing client given on page 370. Add code to take additional command-line arguments, as appropriate.
*
* */
@SuppressWarnings("unchecked")
public class Ex10_TestBST<Key extends Comparable<Key>, Value>{
    public class Node{
        public int n;
        public Node left;
        public Node right;
        public Key key;
        public Value value;
        public int heightOfNode;
        public Node(Key key, Value value, int n, int heightOfNode){
            this.key = key;
            this.value = value;
            this.n = n;
            this.heightOfNode = heightOfNode;
        }
        public Node(Key key, Value value, int n){
            this.key = key;
            this.value = value;
            this.n = n;
        }
        public Node(){}
    }
    public Node root;
    public int size(){
        return size(root);
    }
    private int size(Node node){
        if (node == null) return 0;
        return node.n;
    }
    public void put(Key key, Value value){
        root = put(root, key, value);
    }
    private Node put(Node node, Key key, Value value){
        if (node == null) return new Node(key, value, 1, 0);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, value);
        else if (cmp > 0) node.right = put(node.right, key, value);
        else node.value = value;
        node.n = size(node.left) + size(node.right) + 1;
        node.heightOfNode = 1 + Math.max(heightOfTree(node.left), heightOfTree(node.right));
        return node;
    }
    public Value get(Key key){
        return get(root, key);
    }
    private Value get(Node node, Key key){
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return get(node.left, key);
        else if (cmp > 0) return get(node.right, key);
        else return node.value;
    }
    public boolean contains(Key key){
        return get(key) != null;
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
    public int heightOfTree(){
        return heightOfTree(root);
    }
    private int heightOfTree(Node node){
        if (node == null) return -1;
        return node.heightOfNode;
    }
    // floor()
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
        else return node;
    }
    // ceiling()
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
            else return node;
        }
        return ceiling(node.right, key);
    }
    // select()
    public Key select(int rank){
        return select(root, rank).key;
    }
    private Node select(Node node, int rank){
        if (node == null) return null;
        int t = size(node.left);
        if (rank < t) return select(node.left, rank);
        else if (rank > t) return select(node.right, rank-t-1);
        else return node;
    }
    // rank()
    public int rank(Key key){
        return rank(root, key);
    }
    private int rank(Node node, Key key){
        if (node == null) return 0;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return rank(node.left, key);
        else if (cmp > 0) return 1 + size(node.left) + rank(node.right, key);
        else return size(node.left);
    }
    // deleteMin()
    public void deleteMin(){
        root = deleteMin(root);
    }
    private Node deleteMin(Node node){
        if (node.left == null)
            return node.right;
        node.left = deleteMin(node.left);
        node.n = size(node.left) + size(node.right) + 1;
        return node;
    }
    // deleteMax()
    public void deleteMax(){
        root = deleteMax(root);
    }
    private Node deleteMax(Node node){
        if (node.right == null) return node.left;
        node.right = deleteMax(node.right);
        node.n = size(node.left) + size(node.right) + 1;
        return node;
    }
    // delete()
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
        node.n = size(node.left) + size(node.right) + 1;
        return node;
    }
    // keys()
    public Iterable<Key> keys(){
        return keys(min(), max());
    }
    public Iterable<Key> keys(Key lo, Key hi){
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }
    private void keys(Node node, Queue queue, Key lo, Key hi){
        if (node == null) return;
        int cmplo = lo.compareTo(node.key);
        int cmphi = hi.compareTo(node.key);
        if (cmplo < 0) keys(node.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(node.key);
        if (cmphi > 0) keys(node.right, queue, lo, hi);
    }
    public int heightByRec(){
        return heightByRec(root, -1);
    }
    private int heightByRec(Node node, int currentHeight){
        if (node == null)
            return currentHeight;
        int height = currentHeight;
        int leftHeight = heightByRec(node.left, currentHeight + 1);
        if (leftHeight > height){
            height = leftHeight;
        }
        int rightHeight = heightByRec(node.right, currentHeight + 1);
        if (rightHeight > height){
            height = rightHeight;
        }
        return height;
    }
    public static void main(String[] args) {
        Ex10_TestBST<String, Integer> testBST = new Ex10_TestBST<>();
        testBST.put("S", 8);
        testBST.put("E", 3);
        testBST.put("A", 1);
        testBST.put("R", 7);
        testBST.put("C", 2);
        testBST.put("H", 5);
        testBST.put("M", 6);
        testBST.put("X", 9);
        // print the size of BST
        StdOut.println(testBST.size(testBST.root) + " Expected with 8");
        StdOut.println(testBST.size(testBST.root.left) + " Expected with 6");
        // Test method get() to get the value of key A
        StdOut.println("Key A expecting value 1 : " + testBST.get("A"));
        // Test method put(), one to change the value of key A to 10, add a new Key T with value 5
        testBST.put("A", 10);
        testBST.put("T", 5);
        StdOut.println("The value of Key A changed as 10 : " + testBST.get("A"));
        StdOut.println("The size of BST expecting 9 : " + testBST.size());
        // print out the current BST in order
        for (String s : testBST.keys()){
            StdOut.print(s + " ");
        }
        StdOut.println();
        // min() and max() methods Test
        StdOut.println("Expected minimum key A : " + testBST.min());
        StdOut.println("Expected Maximum key X : " + testBST.max());
        // ceiling() and floor() methods Test
        StdOut.println("The given Key I for ceiling expecting M : " + testBST.ceiling("I"));
        StdOut.println("The given Key B for floor expecting A : " + testBST.floor("B"));
        StdOut.println("The given Key R for ceiling expecting R : " + testBST.ceiling("R"));
        StdOut.println("The given Key R for floor expecting R : " + testBST.floor("R"));
        // select() method Test
        for (int i = 0; i < 9; i++){
            StdOut.print(testBST.select(i) + " ");
        }
        StdOut.println();
        // rank() method Test
        StdOut.println("The rank of Key H expecting 3 : " + testBST.rank("H"));
        // deleteMin() method Test
        testBST.deleteMin();
        StdOut.println("Expecting size 8 : " + testBST.size());
        StdOut.println("Expected min key C : " + testBST.min());
        // delete() method Test
        testBST.delete("X");
        StdOut.println("Current Value expecting T : " + testBST.max());
        // Test the high of the tree
        StdOut.println("Height of Tree Expecting 4 : " + testBST.heightOfTree());
        StdOut.println("Height of Tree Expecting 4 : " + testBST.heightByRec());
    }
}
