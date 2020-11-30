package Chapter3_2_BinarySearchTrees;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

import java.awt.*;

/*
*   Cost plots. Instrument BST so that you can produce plots like the ones in this section showing the cost of put()
*   operation during the computation (see Ex3.1.38)
*
* */
public class Ex44_CostPlots<Key extends Comparable<Key>, Value> {
    private class Node{
        Key key;
        Value value;
        private Node left, right;
        int numberOfNodes;
        public Node(Key key, Value value, int numberOfNodes){
            this.key = key;
            this.value = value;
            this.numberOfNodes = numberOfNodes;
        }
    }
    private Node root;
    public static int numberOfCompares = 0;
    public static int countOfWords = 0;
    public int size(){
        return size(root);
    }
    private int size(Node node){
        if (node == null){return 0;}
        return node.numberOfNodes;
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
            numberOfCompares++;
            node.left = put(node.left, key, value);
        }
        else if (cmp > 0){
            numberOfCompares++;
            node.right = put(node.right, key, value);
        }
        else {
            numberOfCompares++;
            node.value = value;
        }
        node.numberOfNodes = size(node.left) + size(node.right) + 1;
        return node;
    }
    public Value get(Key key){
        return get(root, key).value;
    }
    private Node get(Node node, Key key){
        if (node == null){
            return null;
        }
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
    public boolean contains(Key key){
        return contains(root, key);
    }
    private boolean contains(Node node, Key key){
        if (node == null){
            return false;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0){
            return contains(node.left, key);
        }
        else if (cmp > 0){
            return contains(node.right, key);
        }
        else {
            return true;
        }
    }
    public Key min(){
        return min(root).key;
    }
    private Node min(Node node){
        if (node == null){
            return null;
        }
        if (node.left == null){
            return node;
        }
        return min(node.left);
    }
    public Key max(){
        return max(root).key;
    }
    private Node max(Node node){
        if (node == null){
            return null;
        }
        if (node.right == null){
            return node;
        }
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
        if (node == null){
            return;
        }
        int cmplo = lo.compareTo(node.key);
        int cmphi = hi.compareTo(node.key);
        if (cmplo < 0){
            keys(node.left, queue, lo, hi);
        }
        if (cmplo >= 0 && cmphi <= 0){
            queue.enqueue(node.key);
        }
        if (cmphi > 0){
            keys(node.right, queue, lo, hi);
        }
    }
    public static void frequencyCounter(int n){
        Ex44_CostPlots<String, Integer> costPlots = new Ex44_CostPlots<>();
        while (!StdIn.isEmpty()){
            String word = StdIn.readString();
            if (word.length() < n) continue;
            if (!costPlots.contains(word)){
                countOfWords++;
                costPlots.put(word, 1);
            }
            else {
                countOfWords++;
                costPlots.put(word, costPlots.get(word) + 1);
            }
            StdDraw.setXscale(0, 15000);
            StdDraw.setYscale(0, 20);
            StdDraw.setPenRadius(.005);
            StdDraw.setPenColor(Color.black);
            StdDraw.point(countOfWords, numberOfCompares/countOfWords);
        }
        String max = "";
        costPlots.put(max, 0);
        for (String s : costPlots.keys()){
            if (costPlots.get(s) > costPlots.get(max)){
                max = s;
            }
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        frequencyCounter(n);
    }
}
