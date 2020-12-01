package Chapter3_3_BalancedSearchTrees;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;
import java.util.NoSuchElementException;

/*
*   Bottom-up 2-3-4 trees. Develop an implementation of the basic symbol-table API that uses balanced 2-3-4 trees as the
*   underlying data structure. using the red-black representation and a bottom-up insertion method based on the same recursive
*   approach as algorithm 3.4. Your insertion method should split only the sequence of 4-nodes(if any)on the bottom of the
*   search path.
*
* */
public class Ex28_BottomUp234Trees<Key extends Comparable<Key>, Value> {
    // add new properties to newly created node
    private static final boolean Red = true;
    private static final boolean Black = false;
    // help function which will create the node elements
    private class Node{
        private Key key;
        private Value value;
        private Node left, right;
        private int numberOfSubNodes;
        private boolean color;
        public Node(Key key, Value value, int numberOfSubNodes, boolean color){
            this.key = key;
            this.value = value;
            this.numberOfSubNodes = numberOfSubNodes;
            this.color = color;
        }
    }
    private Node root;
    public void put(Key key, Value value){
        root = put(root, key, value);
        root.color = Black;
    }
    private Node put(Node node, Key key, Value value){
        if (node == null){
            return new Node(key, value, 1, Red);
        }

        // if and only if a 4-node is at its leaf node
        if (isRed(node.left) && isRed(node.right) && size(node) <= 3){
            flipColor(node);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0){node.left = put(node.left, key, value);}
        else if (cmp > 0){node.right = put(node.right, key, value);}
        else {node.value = value;}
        if (isRed(node.right) && !isRed(node.left)){
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)){
            node = rotateRight(node);
        }
        node.numberOfSubNodes = size(node.left) + size(node.right) + 1;
        return node;
    }
    private Node rotateLeft(Node node){
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = Red;
        x.numberOfSubNodes = node.numberOfSubNodes;
        node.numberOfSubNodes = size(node.left) + size(node.right) + 1;
        return x;
    }
    private Node rotateRight(Node node){
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = Red;
        x.numberOfSubNodes = node.numberOfSubNodes;
        node.numberOfSubNodes = size(node.left) + size(node.right) + 1;
        return x;
    }
    private void flipColor(Node node){
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }
    private boolean isRed(Node node){
        if (node == null){return Black;}
        return node.color;
    }
    public int size(){
        return size(root);
    }
    private int size(Node node){
        if (node == null){return 0;}
        return node.numberOfSubNodes;
    }
    // delete minimum value
    public void delMin(){
        if (isEmpty()){throw new NoSuchElementException("RBTree underflow");}
        if (isRed(root.left) && isRed(root.right)){
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
        if (isRed(node.right)){node = rotateLeft(node);}
        if (isRed(node.left) && isRed(node.left.left)){node = rotateRight(node);}
        if (isRed(node.left) && isRed(node.right)){flipColor(node);}
        return node;
    }
    public boolean isEmpty(){return root == null || root.numberOfSubNodes == 0;}

    public Iterable<Key> Key(){
        return Key(min(), max());
    }
    public Iterable<Key> Key(Key min, Key max){
        Queue<Key> queue = new Queue<>();
        Key(min, max, root, queue);
        return queue;
    }
    private void Key(Key min, Key max, Node node, Queue<Key> queue){
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
    public Key min(){
        return min(root).key;
    }
    private Node min(Node node){
        if (node.left == null) return node;
        return min(node.left);
    }
    public Key max(){
        return max(root).key;
    }
    private Node max(Node node){
        if (node.right == null){return node;}
        return max(node.right);
    }
    public static void main(String[] args) {
        Ex28_BottomUp234Trees<String, Integer> bottomUp234Trees = new Ex28_BottomUp234Trees<>();
        bottomUp234Trees.put("S", 8);
        bottomUp234Trees.put("E", 3);
        bottomUp234Trees.put("W", 15);
        bottomUp234Trees.put("A", 1);
        bottomUp234Trees.put("R", 7);
        bottomUp234Trees.put("T", 10);
        bottomUp234Trees.put("H", 5);
        bottomUp234Trees.put("G", 4);
        bottomUp234Trees.put("K", 6);
        StdOut.println("The size of RBTree: " + bottomUp234Trees.size());
        StdOut.println("Key sequence of RBTree ");
        for (String s : bottomUp234Trees.Key()){
            StdOut.print(s + " ");
        }
        StdOut.println("\nKey sequence of RBTree after deleting minimum key");
        bottomUp234Trees.delMin();
        for (String s : bottomUp234Trees.Key()){
            StdOut.print(s + " ");
        }
    }
}
