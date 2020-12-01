package Chapter3_3_BalancedSearchTrees;

import Chapter3_2_BinarySearchTree.Ex38_TreeDrawing;
import edu.princeton.cs.algs4.StdDraw;

/*
*   Generate two random 16-node red-black BSTs. Draw them (either by hand or with a program). Compare them with (unbalanced)
*   BSTs built with the same keys.
*
* */
public class Ex17_CompareRedBlackBST_BST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node{
         Key key;
         Value value;
         Node left, right;
         int numberOfNodes;
         boolean color;
         double Xcoordinate, Ycoordinate;
         public Node(Key key, Value value, int numberOfNodes, boolean color){
             this.key = key;
             this.value = value;
             this.numberOfNodes = numberOfNodes;
             this.color = color;
         }
    }
    private boolean isRED(Node node){
         if (node == null){
             return false;
         }
         return node.color = RED;
    }
    private Node root;
    private int treeLevel;
    public int size(){
         return size(root);
    }
    private int size(Node node){
         if (node == null){
             return 0;
         }
         return node.numberOfNodes;
    }
    public Node rotateRight(Node node){
         Node x = node.left;
         node.left = x.right;
         x.right = node;
         x.color = node.color;
         node.color = RED;
         x.numberOfNodes = node.numberOfNodes;
         node.numberOfNodes = size(node.left) + size(node.right) + 1;
         return x;
    }
    public Node rotateLeft(Node node){
         Node x = node.right;
         node.right = x.left;
         x.left = node;
         x.color = node.color;
         node.color = RED;
         x.numberOfNodes = node.numberOfNodes;
         node.numberOfNodes = size(node.left) + size(node.right) + 1;
         return x;
    }
    public void flipColor(Node node){
         node.color = RED;
         node.left.color = BLACK;
         node.right.color = BLACK;
    }
    public void put(Key key, Value value){
         root = put(root, key, value);
         root.color = BLACK;
    }
    private Node put(Node node, Key key, Value value){
         if (node == null){
             return new Node(key, value, 1, RED);
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
         if (isRED(node.left) && isRED(node.left.left)){
             node = rotateRight(node);
         }
         if (!isRED(node.left) && isRED(node.right)){
             node = rotateLeft(node);
         }
         if (isRED(node.left) && isRED(node.right)){
             flipColor(node);
         }
         node.numberOfNodes = size(node.left) + size(node.right) + 1;
         return node;
    }

    public void draw(){
        treeLevel = 0;
        setCoordinates(root, .9);
        drawLines(root);
        drawNodes(root);
    }
    private void setCoordinates(Node node, double distance){
        if (node == null){
            return;
        }
        setCoordinates(node.left, distance - .05);
        node.Xcoordinate = (0.5 + treeLevel++) / size();
        node.Ycoordinate = distance - .05;
        setCoordinates(node.right, distance - .05);
    }
    private void drawLines(Node node){
        if (node == null){
            return;
        }
        drawLines(node.left);
        if (node.left != null){
            StdDraw.line(node.Xcoordinate, node.Ycoordinate, node.left.Xcoordinate, node.left.Ycoordinate);
        }
        if (node.right != null){
            StdDraw.line(node.Xcoordinate, node.Ycoordinate, node.right.Xcoordinate, node.right.Ycoordinate);
        }
        drawLines(node.right);
    }
    private void drawNodes(Node node){
        if (node == null){
            return;
        }
        double nodeRadius = .03;
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledCircle(node.Xcoordinate, node.Ycoordinate, nodeRadius);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.circle(node.Xcoordinate, node.Ycoordinate, nodeRadius);
        StdDraw.text(node.Xcoordinate, node.Ycoordinate, String.valueOf(node.key));
        drawNodes(node.right);
    }

    public static void main(String[] args) {
        Ex17_CompareRedBlackBST_BST<String, Integer> compareRedBlackBST_bst = new Ex17_CompareRedBlackBST_BST<>();
        Ex17_CompareRedBlackBST_BST<String, Integer> compareRedBlackBST_bst1 = new Ex17_CompareRedBlackBST_BST<>();
        compareRedBlackBST_bst.put("g", 4);
        compareRedBlackBST_bst.put("r", 4);
        compareRedBlackBST_bst.put("a", 4);
        compareRedBlackBST_bst.put("s", 4);
        compareRedBlackBST_bst.put("t", 4);
        compareRedBlackBST_bst.put("x", 4);
        compareRedBlackBST_bst.put("v", 4);
        compareRedBlackBST_bst.put("p", 4);
        compareRedBlackBST_bst.put("z", 4);
        compareRedBlackBST_bst.put("k", 4);
        compareRedBlackBST_bst.put("j", 4);
        compareRedBlackBST_bst.put("o", 4);
        compareRedBlackBST_bst.put("l", 4);
        compareRedBlackBST_bst.put("i", 4);
        compareRedBlackBST_bst.put("w", 4);
        compareRedBlackBST_bst.put("n", 4);
        //compareRedBlackBST_bst.draw();
        compareRedBlackBST_bst1. put("u", 4);
        compareRedBlackBST_bst1. put("r", 4);
        compareRedBlackBST_bst1. put("c", 4);
        compareRedBlackBST_bst1. put("s", 4);
        compareRedBlackBST_bst1. put("t", 4);
        compareRedBlackBST_bst1. put("y", 4);
        compareRedBlackBST_bst1. put("v", 4);
        compareRedBlackBST_bst1. put("q", 4);
        compareRedBlackBST_bst1. put("z", 4);
        compareRedBlackBST_bst1. put("k", 4);
        compareRedBlackBST_bst1. put("j", 4);
        compareRedBlackBST_bst1. put("o", 4);
        compareRedBlackBST_bst1. put("b", 4);
        compareRedBlackBST_bst1. put("i", 4);
        compareRedBlackBST_bst1. put("w", 4);
        compareRedBlackBST_bst1. put("m", 4);
        compareRedBlackBST_bst1.draw();
    }
}
