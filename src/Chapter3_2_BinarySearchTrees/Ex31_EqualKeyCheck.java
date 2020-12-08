package Chapter3_2_BinarySearchTrees;

import edu.princeton.cs.algs4.StdOut;

/*
*   Equal key check. Write a method hasNoDuplicate() that takes a Node as argument and returns true if there are no equal
*   keys in the binary tree rooted at the argument node, false otherwise. Assume that the Test of the previous exercise
*   has passed.
*
* */
public class Ex31_EqualKeyCheck<Key extends Comparable<Key>, Value> {
    public class Node{
        private Node left, right;
        private Key key;
        private Value value;
        private int numberOfTree;
        public Node(Key key, Value value, int numberOfTree){
            this.key = key;
            this.value = value;
            this.numberOfTree = numberOfTree;
        }
    }
    private Node root;
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
    public boolean hasNoDuplicate(){
        return hasNoDuplicate(root);
    }
    public boolean hasNoDuplicate(Node node){
        if (node == null) return true;
        /*if (node.left != null){
            int cmp = node.key.compareTo(node.left.key);
            if (cmp == 0){
                return false;
            }else {
                return hasNoDuplicate(node.left);
            }
        }
        if (node.right != null){
            int cmp = node.key.compareTo(node.right.key);
            if (cmp == 0){
                return false;
            }else {
               return hasNoDuplicate(node.right);
            }
        }*/
        if (node.left == null || node.right == null){
            return true;
        }
        if (node.key.compareTo(node.left.key) == 0 || node.key.compareTo(node.right.key) == 0){
            return false;
        }
        return hasNoDuplicate(node.left) && hasNoDuplicate(node.right);
    }

    public static void main(String[] args) {
        Ex31_EqualKeyCheck<String, Integer> equalKeyCheck = new Ex31_EqualKeyCheck<>();
        equalKeyCheck.put("S", 6);
        equalKeyCheck.put("E", 2);
        equalKeyCheck.put("X", 7);
        equalKeyCheck.put("A", 1);
        equalKeyCheck.put("R", 5);
        equalKeyCheck.put("H", 3);
        equalKeyCheck.put("M", 4);
        equalKeyCheck.put("M", 4);
        StdOut.println("Expecting true: " + equalKeyCheck.hasNoDuplicate());
    }
}
