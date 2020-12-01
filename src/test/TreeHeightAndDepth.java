package test;

import edu.princeton.cs.algs4.StdOut;

public class TreeHeightAndDepth<Key extends Comparable<Key>, Value> {
    private class Node{
        private Key key;
        private Value value;
        private Node left, right;
        private int height;
        private int numberOfTreeNodes;
        public Node(Key key, Value value, int height, int numberOfTreeNodes){
            this.key = key;
            this.value = value;
            this.height = height;
            this.numberOfTreeNodes = numberOfTreeNodes;
        }
    }
    private Node root;
    public int size(){
        return size(root);
    }
    private int size(Node node){
        if (node == null){
            return 0;
        }
        return node.numberOfTreeNodes;
    }
    public void put(Key key, Value value){
        root = put(root, key, value);
    }
    private Node put(Node node, Key key, Value value){
        if (node == null){
            return new Node(key, value, 0, 1);
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
        node.height = 1 + Math.max(heightTree(node.left), heightTree(node.right));
        node.numberOfTreeNodes = size(node.left) + 1 + size(node.right);
        return node;
    }
    public int heightTree(){
        return heightTree(root);
    }
    private int heightTree(Node node){
        if (node == null){
            return 0;
        }
        return node.height;
    }
    public Value get(Key key){
        if (key == null){throw new IllegalArgumentException("Argument is required");}
        return get(root, key).value;
    }
    private Node get(Node node, Key key){
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
    public static void main(String[] args) {
        TreeHeightAndDepth<String, Integer> treeHeightAndDepth = new TreeHeightAndDepth<>();
        treeHeightAndDepth.put("K", 10);
        treeHeightAndDepth.put("E", 5);
        treeHeightAndDepth.put("S", 15);
        treeHeightAndDepth.put("A", 1);
        treeHeightAndDepth.put("H", 8);
        treeHeightAndDepth.put("R", 13);
        treeHeightAndDepth.put("Y", 18);
        treeHeightAndDepth.put("M", 11);
        int height = treeHeightAndDepth.heightTree();
        StdOut.println("The height of Tree: " + height);
        int heightOfNode = treeHeightAndDepth.heightTree(treeHeightAndDepth.get(treeHeightAndDepth.root, "H"));
        StdOut.println("The height of Key H: " + heightOfNode);
    }
}
