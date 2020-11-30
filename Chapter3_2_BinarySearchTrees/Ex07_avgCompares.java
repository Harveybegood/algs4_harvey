package Chapter3_2_BinarySearchTrees;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/*
*   Add to BST a recursive method avgCompares() that computes the average number of compares required by a random search
*   hit in a given BST(the internal path length of the tree divided by its size, plus one). Develop two implementations:
*   a recursive method(which takes linear time and space proportional to the height), and a method like size() that adds
*   a field to each node in the tree(and takes linear space and constant time per query).
*
* */
public class Ex07_avgCompares<Key extends Comparable<Key>, Value> {
    private class Node{
        private int depthSum;
        private Value value;
        private Key key;
        private int n;
        private Node left;
        private Node right;
        public Node(Key key, Value value, int n, int depthSum){
            this.key = key;
            this.value = value;
            this.n = n;
            this.depthSum = depthSum;
        }
    }
    private Node root;
    public int size(){
        return size(root);
    }
    private int size(Node node){
        if (node == null) return 0;
        return node.n;
    }
    public void put(Key key, Value value){
        root = put(root, key, value, 0);
    }
    private Node put(Node node, Key key, Value value, int depth){
        if (node == null)
            return new Node(key, value, 1, 0);
        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            node.left = put(node.left, key, value, depth + 1);
        else if (cmp > 0)
            node.right = put(node.right, key, value, depth + 1);
        else
            node.value = value;
        node.n = size(node.left) + size(node.right) + 1;
        node.depthSum += depth;
        if (node.left != null){
            node.depthSum += node.left.depthSum;
        }
        if (node.right != null){
            node.depthSum += node.right.depthSum;
        }
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
    public Iterable<Key> keys(){
        return keys(min(), max());
    }
    private Iterable<Key> keys(Key lo, Key hi){
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }
    private void keys(Node node, Queue<Key> queue, Key lo, Key hi){
        if (node == null) return;
        int cmplo = lo.compareTo(node.key);
        int cmphi = hi.compareTo(node.key);
        if (cmplo < 0) keys(node.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(node.key);
        if (cmphi > 0) keys(node.right, queue, lo, hi);
    }
    public int avgComparesByRecursive(){
        return avgComparesByRecursive(root) / size() + 1;
    }
    private int avgComparesByRecursive(Node node){
        if (node == null) return 0;
        return avgComparesByRecursive(node.left) + avgComparesByRecursive(node.right) + 1;
    }
    public static void main(String[] args) {
        Ex07_avgCompares<Integer, String> avgCompares = new Ex07_avgCompares<>();
        avgCompares.put(5, "Test" + 5);
        avgCompares.put(3, "Test" + 3);
        avgCompares.put(8, "Test" + 8);
        avgCompares.put(4, "Test" + 4);
        avgCompares.put(6, "Test" + 6);
        avgCompares.put(2, "Test" + 2);
       /* avgCompares.put(9, "Test" + 9);
        avgCompares.put(10, "Test" + 10);
        avgCompares.put(7, "Test" + 7);*/
        StdOut.println(avgCompares.avgComparesByRecursive() + " " + avgCompares.root.depthSum);
    }
}
