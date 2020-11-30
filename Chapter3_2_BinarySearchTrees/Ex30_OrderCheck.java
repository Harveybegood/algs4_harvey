package Chapter3_2_BinarySearchTrees;

import edu.princeton.cs.algs4.StdOut;

/*
*   Order check. Write a recursive method isOrdered() that takes a Node and two keys min and max as arguments and returns
*   true if all the keys in the tree are between min and max; min and max are indeed the smallest and largest keys in the
*   tree, respectively; and the BST ordering property holds for all keys in the tree; false otherwise.
*
* */
public class Ex30_OrderCheck<Key extends Comparable<Key>, Value> {
    public class Node{
        private Node left, right;
        private Key key;
        private Value value;
        private int numberOfNode;
        public Node(Key key, Value value, int numberOfNode){
            this.key = key;
            this.value = value;
            this.numberOfNode = numberOfNode;
        }
    }
    private Node root;
    public int size(){
        return size(root);
    }
    private int size(Node node){
        if (node == null) return 0;
        return node.numberOfNode;
    }
    public void deleteMin(){
        root = deleteMin(root);
    }
    private Node deleteMin(Node node){
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.numberOfNode = size(node.left) + size(node.right) + 1;
        return node;
    }
    public void deleteMax(){
        root = deleteMax(root);
    }
    private Node deleteMax(Node node){
        if (node.right == null) return node.left;
        node.right = deleteMax(node.right);
        node.numberOfNode = size(node.left) + size(node.right) + 1;
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
    public void put(Key key, Value value){
        root = put(root, key, value);
    }
    private Node put(Node node, Key key, Value value){
        if (node == null) return new Node(key, value, 1);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, value);
        else if (cmp > 0) node.right = put(node.right, key, value);
        else node.value = value;
        node.numberOfNode = size(node.left) + size(node.right) + 1;
        return node;
    }
    public boolean isOrdered(Key min, Key max){
        return isOrdered(root, min, max);
    }
    private boolean isOrdered(Node node, Key min, Key max){
        if (node == null) return true;
        if (node.left != null){
            if (node.key.compareTo(min) > 0 && node.key.compareTo(max) < 0 && min.compareTo(min()) == 0 &&
                    node.left.key.compareTo(node.key) < 0 /*&& node.right.key.compareTo(node.key) > 0*/){
                isOrdered(node.left, min, max);
            }else {
                return false;
            }
        }
        if (node.right != null){
            if (node.key.compareTo(min) > 0 && node.key.compareTo(max) < 0 && max.compareTo(max()) == 0 &&
                    /*node.left.key.compareTo(node.key) < 0 &&*/ node.right.key.compareTo(node.key) > 0){
                isOrdered(node.right, min, max);
            }else {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Ex30_OrderCheck<String, Integer> orderCheck = new Ex30_OrderCheck<>();
        orderCheck.put("S", 6);
        orderCheck.put("E", 2);
        orderCheck.put("X", 7);
        orderCheck.put("A", 1);
        orderCheck.put("R", 5);
        orderCheck.put("H", 3);
        orderCheck.put("M", 4);
        StdOut.println("Expecting true: " + orderCheck.isOrdered("A", "X"));
    }
}
