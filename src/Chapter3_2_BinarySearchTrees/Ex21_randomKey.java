package Chapter3_2_BinarySearchTrees;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Add a BST method randomKey() that returns a random key from the symbol table in time proportional to the tree
*   height, in the worst case.
*
* */
public class Ex21_randomKey<Key extends Comparable<Key>, Value> {
    private class Node{
        private Node left, right;
        private Key key;
        private Value value;
        private int n;
        public Node(Key key, Value value, int n){
            this.key = key;
            this.value = value;
            this.n = n;
        }
    }
    private Node root;
    public int size(){
        return size(root);
    }
    private int size(Node node){
        if (node == null) return 0;
        return node.n;
    }
    public Key randomKey(){
        int randomNumber = StdRandom.uniform(1, size() + 1);
        return select(randomNumber);
    }
    public void put(Key key, Value value){
        root = put(root, key, value);
    }
    private Node put(Node node, Key key, Value value){
        if (node == null) return new Node(key, value, 1);
        int cmp = key.compareTo(node.key);
        if (cmp < 0){
            node.left = put(node.left, key, value);
        }else if (cmp > 0){
            node.right = put(node.right, key, value);
        }else {
            node.value = value;
        }
        node.n = size(node.left) + size(node.right) + 1;
        return node;
    }
    public Key select(int rank){
        return select(root, rank).key;
    }
    private Node select(Node node, int rank){
        if (node == null) return null;
        int t = size(node);
        if (rank < t) return select(node.left, rank);
        else if (rank > t) return select(node.right, rank - t - 1);
        return node;
    }

    public static void main(String[] args) {
        Ex21_randomKey<Integer, Integer> randomKey = new Ex21_randomKey<>();
        randomKey.put(3, 3);
        randomKey.put(4, 4);
        randomKey.put(6, 6);
        randomKey.put(2, 2);
        randomKey.put(1, 1);
        randomKey.put(5, 5);
        StdOut.println(randomKey.randomKey());
        StdOut.println(randomKey.randomKey());
        StdOut.println(randomKey.randomKey());
        StdOut.println(randomKey.randomKey());
        StdOut.println(randomKey.randomKey());
        StdOut.println(randomKey.randomKey());
    }
}
