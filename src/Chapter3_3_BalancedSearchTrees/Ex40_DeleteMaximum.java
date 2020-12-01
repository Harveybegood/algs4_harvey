package Chapter3_3_BalancedSearchTrees;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/*
    Delete the maximum. Implement the deleteMax() operation for red-black BSTs. Note that the transformations involved differ
    slightly from those in the previous exercise because red links are left-leaning.
*
* */
public class Ex40_DeleteMaximum<Key extends Comparable<Key>, Value> {
    public static final boolean Red = true;
    public static final boolean Black = false;
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
    public int size(){
        return size(root);
    }
    private int size(Node node){
        if (node == null){return 0;}
        return node.numberOfSubTree;
    }
    private Node rotateLeft(Node node){
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = Red;
        x.numberOfSubTree = node.numberOfSubTree;
        node.numberOfSubTree = size(node.left) + size(node.right) + 1;
        return x;
    }
    private Node rotateRight(Node node){
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = Red;
        x.numberOfSubTree = node.numberOfSubTree;
        node.numberOfSubTree = size(node.left) + size(node.right) + 1;
        return x;
    }
    private void flipColors(Node node){
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
    public void put(Key key, Value value){
        if (key == null){throw new IllegalArgumentException("Argument cannot be omitted.");}
        if (value == null){throw new IllegalArgumentException("Argument cannot be omitted.");}
        root = put(root, key, value);
        if (!isEmpty()){
            root.color = Black;
        }
    }
    private Node put(Node node, Key key, Value value){
        if (node == null){
            return new Node(key, value, 1, Red);
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
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)){
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)){
            flipColors(node);
        }
        node.numberOfSubTree = size(node.left) + size(node.right) + 1;
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
        if (cmpLo <= 0 && cmpHi >= 0){
            queue.enqueue(node.key);
        }
        if (cmpHi > 0){
            keys(node.right, queue, min, max);
        }
    }

    public static void main(String[] args) {
        Ex40_DeleteMaximum<String, Integer> deleteMaximum = new Ex40_DeleteMaximum<>();
        deleteMaximum.put("S", 11);
        deleteMaximum.put("E", 3);
        deleteMaximum.put("U", 16);
        deleteMaximum.put("A", 1);
        deleteMaximum.put("R", 8);
        deleteMaximum.put("T", 13);
        deleteMaximum.put("H", 4);
        deleteMaximum.put("K", 7);
        for (String s : deleteMaximum.keys()){
            StdOut.print(s + " ");
        }
        StdOut.println("\ndelete maximum key");
        deleteMaximum.delMax();
        for (String s : deleteMaximum.keys()){
            StdOut.print(s + " ");
        }
    }
}
