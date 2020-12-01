package Chapter3_3_BalancedSearchTrees;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Count red nodes. Write a program that computes the percentage of red nodes in a given red-black BST. Test your program by running
*   at least 100 trials of the experiment of inserting N random keys into an initially empty tree, for N = 10^4, 10^5, and 10^6, and
*   formulate an hypothesis.
*
* */
public class Ex42_CountRedNodes<Key extends Comparable<Key>, Value> {
    public static final boolean Red = true;
    public static final boolean Black = false;
    public static int numberOfRedNodes;
    public static int totalOfRedNodes;
    private class Node{
        private Key key;
        private Value value;
        private Node left, right;
        private int numberOfSubTree;
        private boolean color;
        public Node(Key key, Value value, int numberOfSubTree, boolean color){
            this.key = key;
            this.value = value;
            this.numberOfSubTree = numberOfSubTree;
            this.color = color;
        }
    }
    private Node root;
    public boolean isEmpty(){return root == null;}
    public int size(){
        return size(root);
    }
    private int size(Node node){
        if (node == null){
            return 0;
        }
        return node.numberOfSubTree;
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
    private Node rotateRight(Node node) {
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
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }
    private boolean isRed(Node node){
        if (node == null){
            return false;
        }
        return node.color;
    }
    public Value get(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be omitted");}
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
    public void put(Key key, Value value){
        if (key == null){throw new IllegalArgumentException("Argument cannot be omitted");}
        if (value == null){throw new IllegalArgumentException("Argument cannot be omitted");}
        root = put(root, key, value);
        if (!isEmpty()){
            root.color = Black;
        }
    }
    private Node put(Node node, Key key, Value value){
        if (node == null){
            return new Node(key, value, 1, Red);
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
        return node;
    }
    public Key min(){
        return min(root).key;
    }
    private Node min(Node node){
        if (node.left == null){
            return node;
        }
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
    public Iterable<Key> keys(){
        return keys(min(), max());
    }
    public Iterable<Key> keys(Key min, Key max){
        Queue<Key> queue = new Queue<>();
        keys(root, queue, min, max);
        return queue;
    }
    private void keys(Node node, Queue<Key> queue, Key min, Key max){
        if (node == null){
            return;
        }
        int cmpLo = min.compareTo(node.key);
        int cmpHi = max.compareTo(node.key);
        if (cmpLo < 0){
            keys(node.left, queue, min, max);
        }
        if (cmpLo <= 0 && cmpHi >= 0){
            queue.enqueue(node.key);
            //numberOfRedNodes++;
        }
        if (cmpHi > 0){
            keys(node.right, queue, min, max);
        }
    }
    // count the number of Red nodes
    /*public void countOfRedNodes(){
        countOfRedNodes(min(), max());
    }
    public void countOfRedNodes(Key min, Key max){
        //Integer numberOfRedNodes = 0;
        countOfRedNodes(root, min, max);
        //return numberOfRedNodes;
    }
    private void countOfRedNodes(Node node, Key min, Key max){
        if (node == null){
            return;
        }
        int cmpLo = min.compareTo(node.key);
        int cmpHi = max.compareTo(node.key);
        if (cmpLo < 0){
            countOfRedNodes(node.left, min, max);
        }
        if(cmpLo <= 0 && cmpHi >= 0){
            if (isRed(node)){
                numberOfRedNodes = numberOfRedNodes + 1;
                //StdOut.println(numberOfRedNodes + " ");
            }
        }
        if (cmpHi > 0){
            countOfRedNodes(node.right, min, max);
        }
    }*/
    public void redNodesCount(){
        redNodesCount(root);
    }
    private void redNodesCount(Node node){
        if (node == null){
            return;
        }
        redNodesCount(node.left);
        if (isRed(node)){
            numberOfRedNodes++;
        }
        redNodesCount(node.right);
    }
    private static void percentageOfRedNodes(int n){
        Ex42_CountRedNodes<Integer, Integer> countRedNodes = new Ex42_CountRedNodes<>();
        totalOfRedNodes = 0;
        for (int i = 0; i < 100; i++){
            numberOfRedNodes = 0;
            for (int j = 0; j < Math.pow(10, n); j++){
                countRedNodes.put(StdRandom.uniform(1, (int)Math.pow(10, n)), 1);
            }
            countRedNodes.redNodesCount();
            totalOfRedNodes += numberOfRedNodes;
            //StdOut.println(i + "  trials --- " + numberOfRedNodes + " NumberOfRedNodes --- " + totalOfRedNodes + " totalNumber");
        }
        StdOut.println("N random keys | Percentage");
        StdOut.printf("%8s %12.1f\n", n, (totalOfRedNodes * 1.0)/(Math.pow(10, n) * 100));
    }
    public static void main(String[] args) {
        for (int n = 4; n <= 6; n++){
            percentageOfRedNodes(n);
        }
    }
}

/*
*
*           N random keys | Percentage
                 4          0.2
            N random keys | Percentage
                 5          0.3
            N random keys | Percentage
                 6          0.3
*
*
* */