package Chapter3_3_BalancedSearchTrees;


import java.util.NoSuchElementException;

import Chapter3_2_BinarySearchTrees.Ex38_TreeDrawing;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/*
*   Tree drawing. Add a method draw() to RedBlackBST that draws red-black BST figure in the style of the text(see Ex3.2.28)
*
* */
public class Ex31_TreeDrawing<Key extends Comparable<Key>, Value> {
    public static final boolean Red= true;
    public static final boolean Black = false;
    private class Node{
        private Key key;
        private Value value;
        private Node left, right;
        private boolean color;
        private int numberOfSubTree;
        private double Xcoordinate, Ycoordinate;
        public Node(Key key, Value value, int numberOfSubTree, boolean color){
            this.key = key;
            this.value = value;
            this.numberOfSubTree = numberOfSubTree;
            this.color = color;
        }
    }
    private Node root;
    private int treeLevel;
    public boolean isEmpty(){return root == null;}
    public int size(){
        return size(root);
    }
    private int size(Node node){
        if (node == null){
            return 0;
        }
        return node.numberOfSubTree;
    }
    private Node rotateLeft(Node node){
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = Red;
        x.numberOfSubTree = node.numberOfSubTree;
        node.numberOfSubTree = size(node.left) + 1 + size(node.right);
        return x;
    }
    private Node rotateRight(Node node){
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = Red;
        x.numberOfSubTree = node.numberOfSubTree;
        node.numberOfSubTree = size(node.left) + 1 + size(node.right);
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
        if (key == null){throw new IllegalArgumentException("Argument cannot be omitted");}
        if (value == null){throw new IllegalArgumentException("Argument cannot be omitted");}
        root = put(root, key, value);
        root.color = Black;
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
        node.numberOfSubTree = size(node.left) + 1 + size(node.right);
        return node;
    }
    public Value get(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be omitted");}
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
    public Key min(){
        if (isEmpty()){
            throw new NoSuchElementException("RBTree underflow");
        }
        return min(root).key;
    }
    private Node min(Node node){
        if (node.left == null){
            return node;
        }
        return min(node.left);
    }
    public Key max(){
        if (isEmpty()){throw new NoSuchElementException("RBTree underflow");}
        return max(root).key;
    }
    private Node max(Node node){
        if (node.right == null){
            return node;
        }
        return max(node.right);
    }
    public void delMin(){
        if (isEmpty()){throw new NoSuchElementException("RBTree underflow");}
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
    public void delMax(){
        if (isEmpty()){throw new NoSuchElementException("RBTree underflow");}
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
    public void delete(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be omitted");}
        if (isEmpty()){throw new NoSuchElementException("RBTree underflow");}
        if (!isRed(root.left) && !isRed(root.right)){
            root.color = Red;
        }
        root = delete(root, key);
        if (!isEmpty()){
            root.color = Black;
        }
    }
    private Node delete(Node node, Key key){
        if (node == null){
            return null;
        }
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
                node = moveRedRight(node);
            }
            node.right = delete(node.right, key);
        }
        else {
            if (node.right == null){
                return null;
            }
            Node temp = min(node.right);
            node.key = temp.key;
            node.value = temp.value;
            node.right = delMin(node.right);
        }
        node.numberOfSubTree = size(node.left) + 1 + size(node.right);
        return balance(node);
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
    public int depthRBTree(){
        return depthRBTree(root, -1);
    }
    private int depthRBTree(Node node, int currentDepth){
        if (node == null){
            return currentDepth;
        }
        int depth = currentDepth;
        int leftHeight = depthRBTree(node.left, currentDepth + 1);
        if (leftHeight > depth){
            depth = leftHeight;
        }
        int rightHeight = depthRBTree(node.right, currentDepth + 1);
        if (rightHeight > depth){
            depth = rightHeight;
        }
        return depth;
    }
    // draw() for drawing RBTree

    private void draw(){
        treeLevel = 0;
        setCoordinates(root, 0.9);
        drawLines(root);
        drawNodes(root);
    }
    private void setCoordinates(Node node, double distance){
        if (node == null)
        {
            return;
        }
        setCoordinates(node.left, distance - .05);
        node.Xcoordinate = (.5 + treeLevel++) / size();
        node.Ycoordinate = distance - .05;
        setCoordinates(node.right, distance - .05);
    }
    private void drawLines(Node node){
        if (node == null){
            return;
        }
        drawLines(node.left);
        if (node.left != null){
            if (isRed(node.left)){
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.line(node.Xcoordinate, node.Ycoordinate, node.left.Xcoordinate, node.left.Ycoordinate);
            }
            else {
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.line(node.Xcoordinate, node.Ycoordinate, node.left.Xcoordinate, node.left.Ycoordinate);
            }
        }
        if (node.right != null){
            if (isRed(node.right)){
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.line(node.Xcoordinate, node.Ycoordinate, node.right.Xcoordinate, node.right.Ycoordinate);
            }
            else {
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.line(node.Xcoordinate, node.Ycoordinate, node.right.Xcoordinate, node.right.Ycoordinate);
            }
        }
        drawLines(node.right);
    }
    private void drawNodes(Node node){
        if (node == null){
            return;
        }
        double nodeRadius = .025;
        drawNodes(node.left);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledCircle(node.Xcoordinate, node.Ycoordinate, nodeRadius);
        if (isRed(node)){
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.circle(node.Xcoordinate, node.Ycoordinate, nodeRadius);
            StdDraw.text(node.Xcoordinate, node.Ycoordinate, String.valueOf(node.key));
        }
        else {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.circle(node.Xcoordinate, node.Ycoordinate, nodeRadius);
            StdDraw.text(node.Xcoordinate, node.Ycoordinate, String.valueOf(node.key));
        }

        drawNodes(node.right);
    }



    public static void main(String[] args) {
        Ex31_TreeDrawing<String, Integer> treeDrawing = new Ex31_TreeDrawing<>();
        treeDrawing.put("S", 13);
        treeDrawing.put("E", 4);
        treeDrawing.put("U", 18);
        treeDrawing.put("A", 1);
        treeDrawing.put("R", 9);
        treeDrawing.put("T", 11);
        treeDrawing.put("H", 5);
        treeDrawing.put("K", 8);
        StdOut.println("The initial sequence of RBTree");
        for (String s : treeDrawing.keys()){
            StdOut.print(s + " ");
        }
        //treeDrawing.depthRBTree();
        StdOut.println("\nDepth: " + treeDrawing.depthRBTree());
        // draw the original RBTree
        //treeDrawing.draw();
        // draw the RBTree deleted by one of nodes
        treeDrawing.delete("H");
        for (String s : treeDrawing.keys()){
            StdOut.print(s + " ");
        }
        treeDrawing.draw();
    }
}
