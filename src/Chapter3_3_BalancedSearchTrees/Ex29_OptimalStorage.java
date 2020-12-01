package Chapter3_3_BalancedSearchTrees;
/*
*   Optimal storage. Modify RedBlackBST so that it does not use any extra storage for the color bit, based on the following
*   trick: To color a node red, swap its two links. Then, to test whether a node is red, test whether its left child is larger
*   than its right child. You have to modify the compares to accommodate the possible link swap, and this trick replaces bit
*   compares with key compares that are presumably more expensive, but it shows that the bit in the nodes can be eliminated,
*   if necessary.

    https://github.com/reneargento/algorithms-sedgewick-wayne/blob/master/src/chapter3/section3/Exercise29_OptimalStorage.java
*
* */
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;


public class Ex29_OptimalStorage<Key extends Comparable<Key>, Value> {
    private class Node{
        private Key key;
        private Value value;
        private Node left, right;
        private int numberOfSubtree;
        public Node(Key key, Value value, int numberOfSubtree){
            this.key = key;
            this.value = value;
            this.numberOfSubtree = numberOfSubtree;
        }
    }
    private Node root;

    public int size(){return size(root);}
    private int size(Node node){
        if (node == null){return 0;}
        return node.numberOfSubtree;
    }

    // swap a node's left and right links to color it red
    private void swapLinks(Node node){
        if (node == null){return;}
        Node temp = node.left;
        node.left = node.right;
        node.right = temp;
    }

    // To test whether a node is red,
    private boolean isRed(Node node){
        if (node == null){return false;}
        if (node == root){return false;}

        Node current = root;
        while (current != null){
            if (current.left == node && current.right == null){
                return true;
            }
            if (current.right == node && current.left == null){
                return true;
            }
            if (current.left == node || current.right == node){
                break;
            }
            int cmp = node.key.compareTo(current.key);
            if (current.left == null || current.right == null){
                if (current.left == null){
                    current = current.right;
                }
                else {
                    current = current.left;
                }
            }
            else {
                boolean isRed = current.left.key.compareTo(current.right.key) > 0;
                if (!isRed){
                    if (cmp < 0){
                        current = current.left;
                    }
                    else if (cmp > 0){
                        current = current.right;
                    }
                    else {
                        break;
                    }
                }
                else {
                    if (cmp < 0){
                        current = current.left;
                    } else if (cmp > 0) {
                        current = current.right;
                    }
                    else {
                        break;
                    }
                }
            }
        }
        if (node.left == null || node.right == null){
            return false;
        }
        return node.left.key.compareTo(node.right.key) > 0;
    }

    private Node rotateRight(Node node){
        if (!isRed(node)){
            if (node.left == null){
                return node;
            }
        }
        else {
            if (node.right == null){
                return node;
            }
        }
        if (isRed(node)){
            swapLinks(node);
        }
        boolean isNewRoot = isRed(node.left);
        if (isNewRoot){
            swapLinks(node.left);
        }
        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        if (!isRed(node)){
            swapLinks(node);
        }
        if (!isRed(newRoot) && newRoot.left != null && newRoot.right != null){
            swapLinks(newRoot);
            swapLinks(newRoot.left);
            swapLinks(newRoot.right);
        }
        newRoot.numberOfSubtree = node.numberOfSubtree;
        node.numberOfSubtree = size(node.left) + size(node.right) + 1;
        return newRoot;
    }

    private Node rotateLeft(Node node){
        if(!isRed(node)){
            if (node.right == null){
                return node;
            }
        }
        else {
            if (node.left == null){
                return node;
            }
        }
        if (isRed(node)){
            swapLinks(node);
        }
        boolean isNewRoot = isRed(node.right);
        if (isNewRoot){
            swapLinks(node.right);
        }
        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        if (isRed(node)){
            swapLinks(newRoot);
        }
        if (!isRed(node)){
            swapLinks(node);
        }
        newRoot.numberOfSubtree = node.numberOfSubtree;
        node.numberOfSubtree = size(node.left) + size(node.right) + 1;
        return newRoot;
    }
    private void flipColor(Node node){
        if (node == null || node.left == null || node.right == null){
            return;
        }
        if ((isRed(node) && !isRed(node.left) && !isRed(node.right)) || (!isRed(node) && isRed(node.left) && isRed(node.right))){
            swapLinks(node);
            swapLinks(node.left);
            swapLinks(node.right);
        }
    }
    public void put(Key key, Value value){
        root = put(root, key, value);
        if (isRed(root)){
            swapLinks(root);
        }
    }
    private Node put(Node node, Key key, Value value){
        if (node == null){
            return new Node(key, value, 1);

        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0){
            if (isRed(node)){
                node.right = put(node.right, key, value);
            }
            else {
                node.left = put(node.left, key, value);
            }
        }
        else if (cmp > 0){
            if (isRed(node)){
                node.left = put(node.left, key, value);
            }
            else {
                node.right = put(node.right, key, value);
            }
        }
        else {
            node.value = value;
        }
        if (!isRed(node)){
            if (isRed(node.right) && !isRed(node.left)){
                node = rotateLeft(node);
            }
        }
        else {
            if (isRed(node.left) && !isRed(node.right)){
                node = rotateLeft(node);
            }
        }

        if (!isRed(node)){
            if (!isRed(node.left)){
                if (isRed(node.left) && isRed(node.left.left)){
                    node = rotateRight(node);
                }
            }
            else {
                if (isRed(node.left) && isRed(node.left.right)){
                    node = rotateRight(node);
                }
            }
        }
        else {
            if (!isRed(node.right)) {
                if (isRed(node.right) && isRed(node.right.left)){
                    node = rotateRight(node);
                }
            }
            else {
                if (isRed(node.right) && isRed(node.right.right)){
                    node = rotateRight(node);
                }
            }
        }
        if (isRed(node.left) && isRed(node.right)){
            flipColor(node);
        }
        node.numberOfSubtree = size(node.left) + size(node.right) + 1;
        return node;
    }
    public Value get(Key key){
        if (key == null){return null;}
        return get(root, key);
    }
    private Value get(Node node, Key key){
        int cmp = key.compareTo(node.key);
        if (cmp < 0){
            if (isRed(node)){
                return get(node.right, key);
            }
            else {
                return get(node.left, key);
            }
        }
        else if (cmp > 0){
            if (isRed(node)){
                return get(node.left, key);
            }
            else {
                return get(node.right, key);
            }
        }
        else {
            return node.value;
        }
    }
    public Key min(){
        return min(root).key;
    }
    private Node min(Node node){
        if (node.left == null){
            return node;
        }
        if (isRed(node)){
            return min(node.right);
        }
        return min((node.left));
    }
    public Key max(){
        return max(root).key;
    }
    private Node max(Node node){
        if (node.right == null){
            return node;
        }
        if (isRed(node)){
            return max(node.left);
        }
        return max(node.right);
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
        if (node == null){return;}
        int cmpLow = low.compareTo(node.key);
        int cmpHigh = high.compareTo(node.key);
        if (cmpLow < 0){
            if (isRed(node)){
                keys(node.right, queue, low, high);
            }
            else {
                keys(node.left, queue, low, high);
            }
        }
        if (cmpLow <= 0 && cmpHigh >= 0){
            queue.enqueue(node.key);
        }
        if (cmpHigh > 0){
            if (isRed(node)){
                keys(node.left, queue, low, high);
            }
            else {
                keys(node.right, queue, low, high);
            }
        }
    }

    public static void main(String[] args) {
        Ex29_OptimalStorage<String, Integer> optimalStorage = new Ex29_OptimalStorage<>();
        optimalStorage.put("S", 10);
        optimalStorage.put("E", 3);
        optimalStorage.put("U", 15);
        optimalStorage.put("A", 1);
        optimalStorage.put("R", 7);
        optimalStorage.put("T", 12);
        optimalStorage.put("H", 4);
        optimalStorage.put("K", 6);
        for (String s : optimalStorage.keys()){
            StdOut.print(s + " ");
        }
    }
}
