package Chapter3_2_BinarySearchTrees;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/*
*   Select/rank check. Write a method that checks, for all i from 0 to size() - 1, whether i is equal to rank(select(i))
*   and, for all keys in the BST, whether key is equal to select(rank(key)).
*
* */
public class Ex33_SelectRankCheck<Key extends Comparable<Key>, Value> {
    private class Node{
        private Node left, right;
        private Key key;
        private Value value;
        private int countOfTree;
        public Node(Key key, Value value, int countOfTree){
            this.key = key;
            this.value = value;
            this.countOfTree = countOfTree;
        }
    }
    private Node root;
    public int size(){
        return size(root);
    }
    private int size(Node node){
        if (node == null) return 0;
        return node.countOfTree;
    }
    // write a method to build a BST
    public void put(Key key, Value value){
        root = put(root, key, value);
    }
    private Node put(Node node, Key key, Value value){
        if (node == null) return new Node(key, value, 1);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, value);
        else if (cmp > 0) node.right = put(node.right, key, value);
        else node.value = value;
        node.countOfTree = size(node.left) + size(node.right) + 1;
        return node;
    }
    // select()
    public Key select(int rank){
        return select(root, rank);
    }
    private Key select(Node node, int rank){
        if (node == null) return null;

        if (rank == rank(node.key)){
            return node.key;
        }
        else if (rank < rank(node.key)){
            return select(node.left, rank);
        }
        else {
            return select(node.right, rank);
        }
    }
    public int rank(Key key){
        return rank(root, key);
    }
    private int rank(Node node, Key key){
        if (node == null) return 0;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return rank(node.left, key);
        }
        else if (cmp > 0){
            return size(node.left) + 1 + rank(node.right, key);
        }
        else {
            return size(node.left);
        }
    }
    public boolean rankCheck(){
        for (int i = 0; i < size() - 1; i++){
            if (rank(select(i)) != i){
                return false;
            }
        }
        return false;
    }
    public boolean selectCheck(){
        for (Key key : keys()){
            if (key.compareTo(select(rank(key))) != 0){
                return false;
            }
        }
        return true;
    }
    public Key min(){
        return min(root).key;
    }
    private Node min(Node node){
        if (node == null) return null;
        if (node.left != null){
            return min(node.left);
        }
        return node;
    }
    public Key max(){
        return max(root).key;
    }
    private Node max(Node node){
        if (node == null) return null;
        if (node.right != null){
            return max(node.right);
        }
        return node;
    }
    public Iterable<Key> keys(){
        return keys(min(), max());
    }
    public Iterable<Key> keys(Key low, Key high){
        Queue<Key> queue = new Queue<>();
        keys(root, queue, low, high);
        return queue;
    }
    private void keys(Node node, Queue<Key> queue, Key low, Key high){
        if (node == null) return;
        int cmplo = low.compareTo(node.key);
        int cmphi = high.compareTo(node.key);
        if (cmplo < 0){
            keys(node.left, queue, low, high);
        }
        if (cmplo <= 0 && cmphi >= 0){
            queue.enqueue(node.key);
        }
        if (cmphi >0){
            keys(node.right, queue, low, high);
        }
    }
    public static void main(String[] args) {
        Ex33_SelectRankCheck<String, Integer> selectRankCheck = new Ex33_SelectRankCheck<>();
        selectRankCheck.put("S", 6);
        selectRankCheck.put("E", 2);
        selectRankCheck.put("X", 8);
        selectRankCheck.put("A", 1);
        selectRankCheck.put("R", 5);
        selectRankCheck.put("H", 4);
        selectRankCheck.put("M", 3);
        selectRankCheck.put("T", 7);
        StdOut.println("Expecting true: " + selectRankCheck.rankCheck());
        StdOut.println("Expecting true: " + selectRankCheck.selectCheck());
    }
}
