package Chapter3_2_BinarySearchTrees;


import edu.princeton.cs.algs4.StdOut;

/*
*   Binary tree check. Write a recursive method isBinaryTree()that takes a Node as argument and returns true if the subtree
*   count field N is consistent in the data structure rooted at that node, false otherwise. Note: This check also ensures
*   the data structure has no cycles and is therefore a binary tree(!).
*
* */
public class Ex29_BinaryTreeCheck<Key extends Comparable<Key>, Value> {
    public class Node{
        public Node left, right;
        public Key key;
        public Value value;
        public int numberOfTree;
        public Node(Key key, Value value, int numberOfTree){
            this.key = key;
            this.value = value;
            this.numberOfTree = numberOfTree;
        }
    }
    public Node root;
    public int size(){
        return size(root);
    }
    private int size(Node node){
        if (node == null) return 0;
        return node.numberOfTree;
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
        node.numberOfTree = size(node.left) + size(node.right) + 1;
        return node;
    }
    public boolean isBinaryTree(){
        return isBinaryTree(root);
    }
    public boolean isBinaryTree(Node node){
        if (node == null) return true;
        if (node.left != null){
            if (node.numberOfTree == size(node.left) + size(node.right) + 1){
                isBinaryTree(node.left);
            }
            else {
                return false;
            }
        }
        if (node.right != null){
            if (node.numberOfTree == size(node.left) + size(node.right) + 1){
                isBinaryTree(node.right);
            }
            else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Ex29_BinaryTreeCheck<String, Integer> binaryTreeCheck = new Ex29_BinaryTreeCheck<>();
        binaryTreeCheck.put("S", 6);
        binaryTreeCheck.put("E", 2);
        binaryTreeCheck.put("X", 7);
        binaryTreeCheck.put("A", 1);
        binaryTreeCheck.put("R", 5);
        binaryTreeCheck.put("H", 3);
        binaryTreeCheck.put("M", 4);
        StdOut.println("Expecting true: " + binaryTreeCheck.isBinaryTree());
    }
}
