package Chapter3_2_BinarySearchTrees;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/*
 *   Add to BST a method height() that computes the height of the tree. Develop two implementations: a recursive method
 *   (which takes linear time and space proportional to the height), and a method like size() that adds a field to each
 *   node in the tree(and takes linear space and constant time per query).
 *
 * */
public class Ex06_HeightOfTree<Key extends Comparable<Key>, Value> {
    // add a nest class named Node to help building and implementation of BST
    private class Node{
        private int heightOfTree;
        private Node left;
        private Node right;
        private Key key;
        private Value value;
        private int n;
        public Node(Key key, Value value, int n, int heightOfTree){
            this.key = key;
            this.value = value;
            this.n = n;
            this.heightOfTree = heightOfTree;
        }
    }
    private Node root;
    public int size(){
        return size(root);
    }
    private int size(Node node){
        if(node == null) return 0;
        return node.n;
    }
    public Value get(Key key){
        return get(root, key);
    }
    private Value get(Node node, Key key){
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            return get(node.left, key);
        else if (cmp > 0)
            return get(node.right, key);
        else
            return node.value;
    }
    public void put(Key key, Value value){
        root = put(root, key, value);
    }
    private Node put(Node node, Key key, Value value){
        if (node == null) {
            return new Node(key, value, 1, 0);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        }
        else if (cmp > 0) {
            node.right = put(node.right, key, value);
        }
        else {
            node.value = value;
        }
        node.n = size(node.left) + size(node.right) + 1;
        node.heightOfTree = 1 + Math.max(heightConstant(node.left), heightConstant(node.right));
        return node;
    }
    public int heightConstant(){
        return heightConstant(root);
    }
    private int heightConstant(Node node){
        if (node == null) return -1;
        return node.heightOfTree;
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
    public Iterable<Key> keys(Key lo, Key hi){
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }
    private void keys(Node node, Queue<Key> queue, Key lo, Key hi){
        if (node == null) return;
        int cmpLo = lo.compareTo(node.left.key);
        int cmpHi = hi.compareTo(node.right.key);
        if (cmpLo < 0)
            keys(node.left, queue, lo, hi);
        if (cmpLo <= 0 && cmpHi >= 0)
            queue.enqueue(node.key);
        if (cmpHi > 0)
            keys(node.right, queue, lo, hi);
    }
    // a recursive method, linear time and space proportional to the height

    public int heightByRecursive(){
        return heightByRecursive(root, -1);
    }
    private int heightByRecursive(Node node, int currentHeight){
        if (node == null)
            return currentHeight;
        int height = currentHeight;
        int leftHeight = heightByRecursive(node.left, currentHeight + 1);
        if (leftHeight > height){
            height = leftHeight;
        }
        int rightHeight = heightByRecursive(node.right, currentHeight + 1);
        if (rightHeight > height){
            height = rightHeight;
        }
        return height;
    }

    public static void main(String[] args) {
        Ex06_HeightOfTree<Integer, String> heightOfTree = new Ex06_HeightOfTree<>();
        /*for (int i = 0; i < 10; i++){
            height.put(i, i + " Test");
        }*/
        heightOfTree.put(5, " test" + 5);
        heightOfTree.put(7, " test" + 7);
        heightOfTree.put(3, " test" + 3);
        heightOfTree.put(2, " test" + 2);
        heightOfTree.put(6, " test" + 6);
        heightOfTree.put(9, " test" + 9);
      /*  for (int i = 1; i < 7; i++){
            StdOut.println(heightOfTree.get(i));
        }*/
        StdOut.println(heightOfTree.heightConstant());
        StdOut.println(heightOfTree.heightByRecursive());

    }
}