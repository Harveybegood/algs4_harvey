package Chapter3_2_BinarySearchTrees;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Hibbard deletion degradation. Write a program that takes an integer N from the command line, builds a random BST of
*   size N, then enters into a loop where it deletes a random key(using the code delete(select(StdRandom.uniform(N))))
*   and then inserts a random key, iterating the loop N^2 times. After the loop, measure and print the average length of
*   a path in the tree(the internal path length divided by N, plus 1). Run your program for N = 10^2, 10^3, and 10^4 to
*   Test the somewhat counterintuitive hypothesis that this process increase the average path length of the tree to be
*   proportional to the square root of N. Run the same experiments for a delete() implementation that makes a random
*   choice whether to use the predecessor or successor node.
*
* */
public class Ex42_HibbardDeletionDegradation<Key extends Comparable<Key>, Value> {
    private class Node{
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private int numberOfNodes;
        private int depthOfNode;
        public Node(Key key, Value value, int numberOfNodes){
            this.key = key;
            this.value = value;
            this.numberOfNodes = numberOfNodes;
        }
    }
    private Node root;
    //private int depthOfNode = -1;
    public int size(){
        return size(root);
    }
    private int size(Node node){
        if (node == null)
            return 0;
        return node.numberOfNodes;
    }
  /*  public int depth(){
        return depth(root);
    }
    private int depth(Node node){
        if (node == null){
            return 0;
        }
        return node.depthOfNode;
    }*/
    public void put(Key key, Value value){
        root = put(root, key, value);
    }
    private Node put(Node node, Key key, Value value){
        if (node == null){
            node = new Node(key, value, 1);
            //node.depthOfNode += 1;
            return node;
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
    public Key select(int rank){
        return select(root, rank).key;
    }
    private Node select(Node node, int rank){
        if (node == null)
            return null;
        int t = size(node.left);
        if (rank < t){
            return select(node.left, rank);
        }
        else if (rank > t){
            return select(node.right, rank - t - 1);
        }
        else {
            return node;
        }
    }
    public void deleteMin(){
        root = deleteMin(root);
    }
    private Node deleteMin(Node node){
        if (node.left == null){
            return node.right;
        }
        node.left = deleteMin(node.left);
        node.numberOfNodes = size(node.left) + size(node.right) + 1;
        return node;
    }
    public Key min(){
        return min(root).key;
    }
    private Node min(Node node){
        if (node.left == null)
            return node;
        return min(node.left);
    }
    public Key max(){
        return max(root).key;
    }
    private Node max(Node node){
        if (node.right == null){
            return node;
        }
        return max(node.right);
    }
    public void deleteMax(){
        root = deleteMax(root);
    }
    private Node deleteMax(Node node){
        if (node.right == null){
            return node;
        }
        return deleteMax(node.right);
    }
    public void delete(Key key){
        root = delete(root, key);
    }
    private Node delete(Node node, Key key){
        if (node == null)
            return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0){
            node.left = delete(node.left, key);
        }
        else if (cmp > 0){
            node.right = delete(node.right, key);
        }
        else {
            if (node.left == null){
                return node.right;
            }
            if (node.right == null){
                return node.left;
            }
            Node temp = node;
            node = min(temp.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }
        node.numberOfNodes = size(node.left) + size(node.right) + 1;
        return node;
    }
    // makes a random choice whether to use the predecessor or successor node
    public void deleteWithRandomChoice(Key key){
        root = deleteWithRandomChoice(root, key);
    }
    private Node deleteWithRandomChoice(Node node, Key key){
        if (node == null){
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0){
            node.left = deleteWithRandomChoice(node.left, key);
        }
        else if (cmp > 0){
            node.right = deleteWithRandomChoice(node.right, key);
        }
        else {
            if (node.left == null){
                return node.right;
            }
            if (node.right == null){
                return node.left;
            }
            int randomPredecessor = StdRandom.uniform(2);
            Node temp = node;
            if (randomPredecessor == 0){
                node = max(temp.left);
                node.right = temp.right;
                node.left = deleteMax(node.left);
            }
            else {
                node = min(temp.right);
                node.right = deleteMin(node.right);
                node.left = temp.left;
            }
        }
        node.numberOfNodes = size(node.left) + size(node.right) + 1;
        return node;
    }
    public int internalPathDepth(){
        if (root == null){
            return 0;
        }
        int internalDepth = 0;
        Queue<Node> queue = new Queue<>();
        root.depthOfNode = 0;
        queue.enqueue(root);
        while (!queue.isEmpty()){
            Node current = queue.dequeue();
            internalDepth += current.depthOfNode;
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
    public int avgPathDepth(){
        if (size() == 0){
            return 0;
        }
        return internalPathDepth() / size() + 1;
    }
    public static void measureAvgLengthWithNTimesN(int N){
        Ex42_HibbardDeletionDegradation<Integer, Integer> hibbardDeletionDegradation1 = new Ex42_HibbardDeletionDegradation<>();
        Ex42_HibbardDeletionDegradation<Integer, Integer> hibbardDeletionDegradation2 = new Ex42_HibbardDeletionDegradation<>();
        for (int i = 0; i < N; i++){
            hibbardDeletionDegradation1.put(StdRandom.uniform(N), StdRandom.uniform(N));
            hibbardDeletionDegradation2.put(StdRandom.uniform(N), StdRandom.uniform(N));
        }
        for (int i = 0; i < Math.pow(N, 2); i++){
            hibbardDeletionDegradation1.delete(hibbardDeletionDegradation1.select(StdRandom.uniform(N)));
            hibbardDeletionDegradation1.put(StdRandom.uniform(N), StdRandom.uniform(N));
            hibbardDeletionDegradation2.deleteWithRandomChoice(hibbardDeletionDegradation2.select(StdRandom.uniform(N)));
            hibbardDeletionDegradation2.put(StdRandom.uniform(N), StdRandom.uniform(N));
        }
        StdOut.println(hibbardDeletionDegradation1.avgPathDepth());
        StdOut.println(hibbardDeletionDegradation2.avgPathDepth());
    }
    public static void testWithDifferentN(int N){
        Ex42_HibbardDeletionDegradation<Integer, Integer> hibbardDeletionDegradation1 = new Ex42_HibbardDeletionDegradation<>();
        Ex42_HibbardDeletionDegradation<Integer, Integer> hibbardDeletionDegradation2 = new Ex42_HibbardDeletionDegradation<>();
        for (int i = 0; i < Math.pow(10, N); i++){
            hibbardDeletionDegradation1.put(StdRandom.uniform(N), StdRandom.uniform(N));
            hibbardDeletionDegradation2.put(StdRandom.uniform(N), StdRandom.uniform(N));
        }
        for (int i = 0; i < Math.pow(10, N); i++){
            hibbardDeletionDegradation1.delete(hibbardDeletionDegradation1.select(StdRandom.uniform(N)));
            hibbardDeletionDegradation1.put(StdRandom.uniform(N), StdRandom.uniform(N));
            hibbardDeletionDegradation2.deleteWithRandomChoice(hibbardDeletionDegradation2.select(StdRandom.uniform(N)));
            hibbardDeletionDegradation2.put(StdRandom.uniform(N), StdRandom.uniform(N));
        }
        StdOut.println(hibbardDeletionDegradation1.avgPathDepth());
        StdOut.println(hibbardDeletionDegradation2.avgPathDepth());
    }
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        measureAvgLengthWithNTimesN(N);
        for (int i = 2; i <= 4; i++){
            testWithDifferentN((int)Math.pow(10, i));
        }
    }
}
