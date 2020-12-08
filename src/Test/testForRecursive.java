package Test;

import edu.princeton.cs.algs4.StdOut;

public class testForRecursive<Key extends Comparable<Key>, Value> {
    public static final boolean Red = true;
    public static final boolean Black = false;
    private static int numberOfRedNodes;
    private class Node{
        private Key key;
        private Value value;
        private Node left, right;
        private int numberOfSubTree;
        private boolean color;
        private int height;
        public Node(Key key, Value value, int numberOfSubTree, boolean color, int height){
            this.key = key;
            this.value = value;
            this.numberOfSubTree = numberOfSubTree;
            this.color = color;
            this.height = height;
        }
    }
    private static int depth;
    private Node root;
    public boolean isEmpty(){return root == null;}
    public int size(){return size(root);}
    private int size(Node node){
        if (node == null){
            return 0;
        }
        return node.numberOfSubTree;
    }
    public void put(Key key, Value value){
        if (key == null){throw new IllegalArgumentException("Argument cannot be omitted");}
        if (value == null){throw new IllegalArgumentException("Argument cannot be omitted");}
        root = put(root, key, value);
        root.color = Black;
    }
    private Node put(Node node, Key key, Value value){
        if (node == null){
            return new Node(key, value, 1, Red, 0);
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
        node.height = 1 + Math.max(heightRBTree(node.left), heightRBTree(node.right));
        return node;
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

    // print all keys in an in-order sequence
    public void inOrderTraverse(){
        inOrderTraverse(root);
    }
    private void inOrderTraverse(Node node){
        if (node == null){
            return;
        }
        inOrderTraverse(node.left);
        StdOut.print(node.key + " " + node.value + "\n");
        if (isRed(node)){
            StdOut.print("Red node: " + node.key + "\n");
            numberOfRedNodes++;
        }
        //depth++;
        inOrderTraverse(node.right);
    }
    public int depthRBTree(){
        return depthRBTree(root, -1);
    }
    private int depthRBTree(Node node, int currentHeight){
        if (node == null){
            return currentHeight;
        }
        int height = currentHeight;
        int leftHeight = depthRBTree(node.left, currentHeight + 1);
        if (leftHeight > height){
            height = leftHeight;
        }
        int rightHeight = depthRBTree(node.right, currentHeight + 1);
        if (rightHeight > height){
            height = rightHeight;
        }
        return height;
    }
    private boolean isRed(Node node){
        if (node == null){
            return false;
        }
        return node.color;
    }
    public int heightRBTree(){
        return heightRBTree(root);
    }
    private int heightRBTree(Node node){
        if (node == null){
            return -1;
        }
        return node.height;
    }
    public static void main(String[] args) {
        testForRecursive<String, Integer> testTraverse = new testForRecursive<>();
        testTraverse.put("S", 12);
        testTraverse.put("E", 3);
        testTraverse.put("Y", 16);
        testTraverse.put("A", 1);
        testTraverse.put("R", 10);
        testTraverse.put("U", 15);
        testTraverse.put("H", 5);
        testTraverse.put("K", 8);
        testTraverse.put("M", 9);
        testTraverse.inOrderTraverse();
        StdOut.println("The number of Red nodes: " + numberOfRedNodes);
        //StdOut.println("Depth: " + testTraverse.depthRBTree());
        depth = testTraverse.depthRBTree();
        StdOut.println("Depth:" + depth);
        int height = testTraverse.heightRBTree();
        StdOut.println("Height: " + height);
    }
}
