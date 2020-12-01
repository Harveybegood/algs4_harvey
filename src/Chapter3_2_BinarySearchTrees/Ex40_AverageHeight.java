package Chapter3_2_BinarySearchTrees;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Height. Run empirical studies to estimate average BST height by running 100 trials of the experiment of inserting
*   N random keys into an initially empty tree, for N = 10^4, 10^5, and 10^6. Compare your results against the 2.99lgN
*   estimate that is described in the text.
*
* */
public class Ex40_AverageHeight<Key extends Comparable<Key>, Value> {
    private class Node{
        private Node left, right;
        private Key key;
        private Value value;
        private int countOfNodes;
        private int heightOfTree;
        public Node(Key key, Value value, int countOfNodes, int heightOfTree){
            this.key = key;
            this.value = value;
            this.countOfNodes = countOfNodes;
            this.heightOfTree = heightOfTree;
        }
    }
    private Node root;
    public int size(){
        return size(root);
    }
    private int size(Node node){
        if (node == null)
            return 0;
        return node.countOfNodes;
    }
    public Value get(Key key){
        return get(root, key);
    }
    private Value get(Node node, Key key){
        if (node == null)
            return null;
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
        if (node == null){
            return new Node(key, value, 1, 0);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            node.left = put(node.left, key, value);
        else if (cmp > 0)
            node.right = put(node.right, key, value);
        else
            node.value = value;
        node.countOfNodes = size(node.left) + size(node.right) + 1;
        node.heightOfTree = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }
    public int height(){
        return height(root);
    }
    private int height(Node node){
        if (node == null)
            return -1;
        return node.heightOfTree;
    }
    public static int heightOfBST(int n){
        Ex40_AverageHeight<String, Integer> averageHeight = new Ex40_AverageHeight<>();
        int heightOfEachTree;
        for (int i = 0; i < n; i++){
            averageHeight.put("Average" + StdRandom.uniform(1, n), StdRandom.uniform(1, n));
        }
        heightOfEachTree = averageHeight.height();
        return heightOfEachTree;
    }
    public static void averageHeightOfN(){
        int sumOfHeight = 0;
        for (int i = 4; i <= 6; i++){
            for (int j = 0; j < 100; j++){
                sumOfHeight += heightOfBST((int)Math.pow(10, i));
            }
            StdOut.println("The average height of " + (int)Math.pow(10, i) + " " + (double)sumOfHeight/100);
            sumOfHeight = 0;
        }
    }

    public static void main(String[] args) {
        averageHeightOfN();
    }
}
