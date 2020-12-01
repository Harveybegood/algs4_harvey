package Chapter3_2_BinarySearchTrees;

import Tool.VisualAccumulator;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

/*
*   Average search time. Run empirical studies to compute the average and standard deviation of the average length of a
*   path to a random node(internal path length divided by tree size, plus 1)in a BST built by insertion of N random keys
*   into an initially empty tree, for N from 100 to 10000 trials for each tree size. Plot the results in a Tufte plot, like
*   the one at the bottom of this page, fit with a curve plotting the function 1.39lgN-1.85(see Ex3.2.35 and Ex3.2.39).
*
* */
public class Ex47_AverageSearchTime<Key extends Comparable<Key>, Value> {
    public static void draw(){
        StdDraw.setXscale(0, 10000);
        StdDraw.setYscale(0, 20);
        StdDraw.setPenColor(Color.red);
        StdDraw.setPenRadius(0.005);
        for (int n = 100; n <= 10000; n += 100){
            StdDraw.point(n, 1.39*Math.log(n) - 1.85);
        }
    }

    public static void main(String[] args) {
        draw();
        doExperiment();
    }
    private class Node{
        Key key;
        Value value;
        Node left, right;
        int numberOfSubNodes;
        int depthOfNode;
        public Node(Key key, Value value, int numberOfSubNodes){
            this.key = key;
            this.value = value;
            this.numberOfSubNodes = numberOfSubNodes;
        }
    }
    public Node root;
    public int size(){
        return size(root);
    }
    private int size(Node node){
        if (node == null){
            return 0;
        }
        return node.numberOfSubNodes;
    }
    public void put(Key key, Value value){
        root = put(root, key, value);
    }
    private Node put(Node node, Key key, Value value){
        if (node == null){
            return new Node(key, value, 1);
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
        node.numberOfSubNodes = size(node.left) + size(node.right) + 1;
        return node;
    }
    // compute the internal path length
    public int internalPathLength(){
        if (root == null){
            return 0;
        }
        int internalDepth = 0;
        Queue<Node> queue = new Queue<>();
        queue.enqueue(root);
        root.depthOfNode = 0;
        while (!queue.isEmpty()){
            Node current = queue.dequeue();
            internalDepth += internalDepth;
            if (current.left != null){
                current.left.depthOfNode = current.depthOfNode + 1;
                queue.enqueue(current.left);
            }
            if (current.right != null){
                current.right.depthOfNode = current.depthOfNode + 1;
                queue.enqueue(current.right);
            }
        }
        return internalDepth;
    }
    // compute the average length
    public int averageLength(){
        if (size() == 0){
            return 0;
        }
        return internalPathLength() / size() + 1;
    }
    /*
    *   average deviation = sum[x-x'avg]/n
    * 
    *   x'avg = sum[x1+x2+...+xn]/n
    * 
    *   standard deviation = s^2 = sum[(x1-x'avg) + (x2-x'avg) + ... + (xn-x'avg)]/n
    * 
    * */
    public static void doExperiment(){
        Ex47_AverageSearchTime<String, Integer> averageSearchTime = new Ex47_AverageSearchTime<>();
        String title = "Average path length to a random node in a BST built from random keys";
        String xAxisLabel = "Number of keys N";
        String yAxisLabel = "Compares";
        double maxCost = 20;
        //int originalValue = 100;
        VisualAccumulator visualAccumulator = new VisualAccumulator(100, 10000, maxCost, title, xAxisLabel, yAxisLabel);
        double meanDeviation;
        double standardDeviation;
        double totalAverageLength = 0;
        double totalAfterEachItemMinusAvg = 0;
        double AVGValue;
        double[] valueOfEach = new double[1000];
        double x = 0;
        int a = 0;
        for (int i = 0; i < 10000; i++){
            for (int j = 0; j < 1000; j++){
                for (int k = 0; k < 100 + x && x < 9900; k++){
                    averageSearchTime.put("a" + k, k);
                    a = k;
                }
                valueOfEach[j] = averageSearchTime.averageLength();
                totalAverageLength += averageSearchTime.averageLength();
            }
            x++;
            AVGValue = totalAverageLength / 1000;
            for (int l = 0; l < 1000; l++){
                totalAfterEachItemMinusAvg += valueOfEach[l] - AVGValue;
            }
            meanDeviation = totalAfterEachItemMinusAvg / 1000;
            standardDeviation = Math.sqrt(totalAfterEachItemMinusAvg/1000);
            StdDraw.setXscale(100, 10000);
            StdDraw.setXscale(0, 20);
            StdDraw.setPenRadius(0.005);
            StdDraw.setPenColor(Color.black);
            StdDraw.point(a, meanDeviation);
            StdDraw.point(a, standardDeviation);
        }
    }
}
