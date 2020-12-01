package Chapter3_2_BinarySearchTrees;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Put/get ratio. Determine empirically the ratio of the amount of time that BST spends on put() operations to the time
*   that it spends on get() operations when FrequencyCounter is used to find the frequency of occurrence of values in 1
*   million randomly-generated integers.
*
* */
public class Ex43_PutGetRatio<Key extends Comparable<Key>, Value> {
    private class Node{
        Key key;
        Value value;
        Node left, right;
        int numberOfNodes;
        public Node(Key key, Value value, int numberOfNodes){
            this.key = key;
            this.value = value;
            this.numberOfNodes = numberOfNodes;
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
            node.left = put(node.left, key, value);
        }
        else if (cmp > 0){
            node.right = put(node.right, key, value);
        }
        else {
            node.value = value;
        }
        node.numberOfNodes = size(node.left) + size(node.right) + 1;
        return node;
    }
    public Value get(Key key){
        return get(root, key);
    }
    private Value get(Node node, Key key){
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
            return node.value;
        }
    }
    public boolean contain(Key key){
        return contain(root, key);
    }
    private boolean contain(Node node, Key key){
        if (node == null){
            return false;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0){
            return contain(node.left, key);
        }
        else if (cmp > 0){
            return contain(node.right, key);
        }
        else {
            return true;
        }
    }
    public static int[] generateExperimentData(int n){
        int[] experimentData = new int[n];
        for (int i = 0; i < n; i++){
            experimentData[i] = StdRandom.uniform(n);
        }
        return experimentData;
    }
    public static void doExperiment(int n){
        int[] datas = generateExperimentData(n);
        double costOfTimeInPut = 0;
        double costOfTimeInGet = 0;
        Ex43_PutGetRatio<Integer, Integer> putGetRatio = new Ex43_PutGetRatio<>();
        for (int i = 0; i < n; i++){
            if (!putGetRatio.contain(datas[i])){
                Stopwatch timer1 = new Stopwatch();
                putGetRatio.put(datas[i], 1);
                costOfTimeInPut += timer1.elapsedTime();
            }
            else {
                Stopwatch timer2 = new Stopwatch();
                Stopwatch timer3 = new Stopwatch();
                putGetRatio.put(datas[i], putGetRatio.get(datas[i]) + 1);
                costOfTimeInPut += timer2.elapsedTime();
                costOfTimeInGet += timer3.elapsedTime();
            }
        }
        double ratio = costOfTimeInPut / costOfTimeInGet;
        StdOut.printf("%5s %12s %10s\n", "costOfTimeInPut", "costOfTimeInGet", "Ratio");
        StdOut.printf("%5.2f %18.2f %16.2f", costOfTimeInPut, costOfTimeInGet, ratio);
    }

    public static void main(String[] args) {
        doExperiment((int)Math.pow(10, 6));
    }
}
