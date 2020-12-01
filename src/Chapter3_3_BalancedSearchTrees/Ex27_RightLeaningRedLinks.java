package Chapter3_3_BalancedSearchTrees;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

/*
*   Allow right-leaning red links. Develop a modified version of your solution to
    Exercise 3.3.25 that allows right-leaning red links in the tree.
*
* */
public class Ex27_RightLeaningRedLinks<Key extends Comparable<Key>, Value> {
    private static final boolean Red = true;
    private static final boolean Black = false;
    private class Node{
        Key key;
        Value value;
        Node left, right;
        int numberOfSubTree;
        boolean color;
        public Node(Key key, Value value, int numberOfSubTree, boolean color){
            this.key = key;
            this.value = value;
            this.numberOfSubTree = numberOfSubTree;
            this.color = color;
        }
    }
    private Node root;
    public boolean isEmpty(){return root == null;}
    // To add each node to a tree
    public void put(Key key, Value value){
        root = put(root, key, value);
        root.color = Black;
    }
    private Node put(Node node, Key key, Value value){
        if (node == null){
            return new Node(key, value, 1, Red);
        }
        if (isRed(node.left) && isRed(node.right)){
            flipColor(node);
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
        if (isRed(node.left) && isRed(node.left.left)){
            node = rotateRight(node);
        }
        if (isRed(node.right) && isRed(node.right.right)){
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.right)){
            node = rotateLeftRight(node);
        }
        if (isRed(node.right) && isRed(node.right.left)){
            node = rotateRightLeft(node);
        }
        if (isRed(node.left) && isRed(node.right)){
            flipColor(node);
        }
        node.numberOfSubTree = size(node.left) + size(node.right) + 1;
        return node;
    }
    // A node that there are two nodes with Red color in a row
    private Node rotateLeft(Node node){
        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;
        temp.color = node.color;
        node.color = Red;
        temp.numberOfSubTree = node.numberOfSubTree;
        node.numberOfSubTree = size(node.left) + size(node.right) + 1;
        return temp;
    }
    private Node rotateRight(Node node){
        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;
        temp.color = node.color;
        node.color = Red;
        temp.numberOfSubTree = node.numberOfSubTree;
        node.numberOfSubTree = size(node.left) + size(node.right) + 1;
        return temp;
    }
    private Node rotateLeftRight(Node node){
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }
    private Node rotateRightLeft(Node node){
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }
    private void flipColor(Node node){
        if (node == null){
            return;
        }
        node.color = Red;
        node.left.color = Black;
        node.right.color = Black;
    }
    private boolean isRed(Node node){
        if (node == null){
            return Black;
        }
        return node.color == Red;
    }
    public int size(){
        return size(root);
    }
    private int size(Node node){
        if (node == null){
            return 0;
        }
        return node.numberOfSubTree;
    }
    // delete minimum key in the tree
    public void delMin(){
        if (isEmpty()){throw new NoSuchElementException("BST underflow");}
        if (!isRed(root.left) && !isRed(root.right)){
            root.color = Red;
        }
        root = delMin(root);
        if (!isEmpty()){root.color = Black;}
    }
    private Node delMin(Node node){
        if (node.left == null){
            return null;
        }
        if (!isRed(node.left) && !isRed(node.left.left)){
            node = moveRedLeft(node);
        }
        node.left = delMin(node.left);
        return balance(node);
    }
    private Node moveRedLeft(Node node){
        flipColor(node);
        if (isRed(node.right.left)){
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipColor(node);
        }
        return node;
    }
    private Node balance(Node node){
        if (isRed(node.left) && isRed(node.left.left)){
            node = rotateRight(node);
        }
        if (isRed(node.right) && isRed(node.right.right)){
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.right)){
            node = rotateLeftRight(node);
        }
        if (isRed(node.right) && isRed(node.right.left)){
            node = rotateRightLeft(node);
        }
        if (isRed(node.left) && isRed(node.right)){
            flipColor(node);
        }
        node.numberOfSubTree = size(node.left) + size(node.right) + 1;
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
        Stack<Node> stack = new Stack<>();
        Node current = node;
        int locmp;
        int hicmp;
        while (current != null || !stack.isEmpty()){
            if (current != null){
                locmp = min.compareTo(current.key);
                if (locmp <= 0) {
                    stack.push(current);
                    current = current.left;
                }
                // if there is no right child of the current sub-tree

            }
            else {
                current = stack.pop();
                hicmp = max.compareTo(current.key);
                if (hicmp >= 0) {
                    queue.enqueue(current.key);
                    current = current.right;
                }
            }
        }
    }
    public void delMax(){
        if (isEmpty()){
            throw new RuntimeException("Red Black underflow");
        }
        if (!isRed(root.left) && !isRed(root.right)){
            root.color = Red;
        }
        root = delMax(root);
        if (!isEmpty()){
            root.color = Black;
        }
    }
    private Node delMax(Node node){
        if (isRed(node.left)){
            node = rotateRight(node);
        }
        if (node.right == null){
            return null;
        }
        if (!isRed(node.right) && !isRed(node.right.left)){
            node = moveRedRight(node);
        }
        node.right = delMax(node.right);
        return balance(node);
    }

    private Node moveRedRight(Node node){
        flipColor(node);
        if (isRed(node.left.left)){
            node = rotateRight(node);
            flipColor(node);
        }
        return node;
    }
    public void delete(Key key){
        if (isEmpty()){throw new NoSuchElementException("RBTree underflow");}
        if (!isRed(root.left) && !isRed(root.right)){root.color = Red;}
        root = delete(root, key);
        if (!isEmpty()){root.color = Black;}
    }
    private Node delete(Node node, Key key){
        int cmp = key.compareTo(node.key);
        if (cmp < 0){
            if (!isRed(node.left) && !isRed(node.left)){
                node = moveRedLeft(node);
            }
            node.left = delete(node.left, key);
        }
        else if (cmp > 0){
            if (isRed(node.left)){
                node = rotateRight(node);
            }
            if (!isRed(node.right) && !isRed(node.right.left)){
                node = moveRedRight(node);
            }
            node.right = delete(node.right, key);
        }
        else {
            if (node.right == null){
                return null;
            }
            Node x = min(node.right);
            node.key = x.key;
            node.value = x.value;
            node.right = delMin(node.right);
        }
        return balance(node);
    }
    public static void main(String[] args) {
        Ex27_RightLeaningRedLinks<String, Integer> rightLeaningRedLinks = new Ex27_RightLeaningRedLinks<>();
        rightLeaningRedLinks.put("S", 6);
        rightLeaningRedLinks.put("E", 2);
        rightLeaningRedLinks.put("T", 7);
        rightLeaningRedLinks.put("A", 1);
        rightLeaningRedLinks.put("R", 5);
        rightLeaningRedLinks.put("H", 4);
        rightLeaningRedLinks.put("K", 3);
        StdOut.println("Size of this Red-Black Tree: " + rightLeaningRedLinks.size());
        for (String s : rightLeaningRedLinks.keys()){
            StdOut.print(s + " ");
        }
        StdOut.println("The size before deleting minimum: " + rightLeaningRedLinks.size());
        rightLeaningRedLinks.delMin();
        StdOut.println("The size after deleting minimum: " + rightLeaningRedLinks.size());
        for (String s : rightLeaningRedLinks.keys()){
            StdOut.print(s + " ");
        }
        rightLeaningRedLinks.delMax();
        StdOut.println("\nThe size of BST: " + rightLeaningRedLinks.size());
        for (String s : rightLeaningRedLinks.keys()){
            StdOut.print(s + " ");
        }
        rightLeaningRedLinks.delete("E");
        StdOut.println("\nThe size of RBTress: " + rightLeaningRedLinks.size());
        for (String s : rightLeaningRedLinks.keys()){
            StdOut.print(s + " ");
        }
    }
}
