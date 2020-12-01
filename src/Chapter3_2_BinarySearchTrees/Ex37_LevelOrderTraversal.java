package Chapter3_2_BinarySearchTrees;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;


/*
*   Level-order-traversal. Write a method printLevel() that takes a Node as argument and prints the keys in the subtree
*   rooted at that node in level order(in order of their distance from the root, with nodes on each level in order from
*   left to right). Hint: Use a Queue.
*
* */
public class Ex37_LevelOrderTraversal<Key extends Comparable<Key>, Value> {
    private class Node{
        private Node left, right;
        private Key key;
        private Value value;
        private int countOfTree;
        private int heightOfNode;
        public Node(Key key, Value value, int countOfTree, int heightOfNode){
            this.key = key;
            this.value = value;
            this.countOfTree = countOfTree;
            this.heightOfNode = heightOfNode;
        }
    }

    private Node root;
    public int size(){
        return size(root);
    }
    private int size(Node node){
        if (node == null) return 0;
        return node.countOfTree;
    }
    public void put(Key key, Value value){
        root = put(root, key, value);

    }
    public int height(){
        return height(root);
    }
    private int height(Node node){
        if (node == null) return -1;
        return node.heightOfNode;
    }

    private Node put(Node node, Key key, Value value){
        if (node == null)
            return new Node(key, value, 1, 0);
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
        node.countOfTree = size(node.left) + size(node.right) + 1;
        //node.heightOfNode =  height(node.left) > height(node.right) ? height(node.left) : height(node.right) + 1;
        //node.heightOfNode = Math.max(height(node.left), height(node.right)) + 1;
        return node;
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
        int cmplo = lo.compareTo(node.key);
        int cmphi = hi.compareTo(node.key);
        if (cmplo < 0) keys(node.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(node.key);
        if (cmphi > 0) keys(node.right, queue, lo, hi);
    }
    // take root as argument, meanwhile we allow to take any node as argument
    public void printLevel(){
        Queue<Node> queue = new Queue<>();
        printLevel(root, queue);
        //return queue;
    }
    private void printLevel(Node node, Queue<Node> queue){
        queue.enqueue(node);
        int i = root.countOfTree;
        while (node != null && i > 0){
            if (!queue.isEmpty()){
                node = queue.dequeue();
                StdOut.print(node.key + " ");
                i--;
            }
            if (node.left != null){
                queue.enqueue(node.left);
            }
            if (node.right != null){
                queue.enqueue(node.right);
            }

        }
    }

    public static void main(String[] args) {
        Ex37_LevelOrderTraversal<String, Integer> levelOrderTraversal = new Ex37_LevelOrderTraversal<>();
        levelOrderTraversal.put("S", 7);
        levelOrderTraversal.put("E", 2);
        levelOrderTraversal.put("X", 9);
        levelOrderTraversal.put("A", 1);
        levelOrderTraversal.put("R", 6);
        levelOrderTraversal.put("T", 8);
        levelOrderTraversal.put("H", 4);
        /*levelOrderTraversal.put("G", 3);
        levelOrderTraversal.put("M", 5);
        levelOrderTraversal.put("K", 5);
        levelOrderTraversal.put("P", 5);*/
        /*for (String s : levelOrderTraversal.keys()){
            StdOut.println(s + " " + levelOrderTraversal.root.heightOfNode);
        }*/
        levelOrderTraversal.printLevel();
    }
}
