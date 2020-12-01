package Chapter3_2_BinarySearchTrees;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/*
*   Give non recursive implementation of min(), max(), floor(), ceiling(), rank(), and select().
*
* */
public class Ex14_nonRecursiveImplementation<Key extends Comparable<Key>, Value> {
    private class Node{
        private Node left;
        private Node right;
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
    private int size;

    public int size(){
        return size(root);
    }
    private int size(Node node){
        if (node == null) return 0;
        return node.n;
    }
    public void put(Key key, Value value){
        // avoid the existed key to be inserted again
        boolean isExist = false;
        Node node = root;
        while (node != null){
            int cmp = key.compareTo(node.key);
            if (cmp < 0){
                node = node.left;
            }else if (cmp > 0){
                node = node.right;
            }else {
                node.value = value;
                isExist = true;
                break;
            }
        }
        if (isExist){
            return;
        }
        if (root == null){
            root = new Node(key, value, 1);
            size++;
            return;
        }
        node = root;
        while (node != null){
            int cmp = key.compareTo(node.key);
            if (cmp < 0){
                if (node.left != null){
                    node = node.left;
                }else {
                    node.left = new Node(key, value, 1);
                    size++;
                    break;
                }
            }else {
                if (node.right != null){
                    node = node.right;
                }else {
                    node.right = new Node(key, value, 1);
                    size++;
                    break;
                }
            }
        }
    }
    //  in reference to https://algs4.cs.princeton.edu/32bst/NonrecursiveBST.java.html
   /* public void put(Key key, Value value){
        Node node = new Node(key, value);
        if (root == null){
            root = node;
            return;
        }
        Node parent = null;
        Node temp = root;
        while (temp != null){
            parent = temp;
            int cmp = key.compareTo(temp.key);
            if (cmp < 0) {
                temp = temp.left;
            }else if (cmp > 0){
                temp = temp.right;
            }else {
                temp.value = value;
                return;
            }
        }
        int cmp = key.compareTo(parent.key);
        if (cmp < 0){
            parent.left = node;
        }
        else {
            parent.right = node;
        }
    }*/
    public Value get(Key key){
        Node node = root;
        while (node != null){
            int cmp = key.compareTo(node.key);
            if (cmp == 0)
                return node.value;
            else if (cmp < 0)
                node = node.left;
            else
                node = node.right;
        }
        return null;
    }
    // min()
    public Key min(){
        Node node = root;
        while (node != null){
            if (node.left == null){
                return node.key;
            }
            node = node.left;
        }
        return null;
    }
    // max()
    public Key max(){
        Node node = root;
        while (node != null){
            if (node.right == null){
                return node.key;
            }
            node = node.right;
        }
        return null;
    }
    // floor(). To search an element that is just smaller than or equal to the searched element
    public Key floor(Key key){
        Node node = root;
        Node temp = root;
        while (node != null){
            int cmp = key.compareTo(node.key);
            if (cmp == 0) return node.key;
            if (cmp < 0){
                if (node.left == null){
                    return temp.key;
                }
                node = node.left;
            }else {
                temp = node;
                node = node.right;
            }
        }
        return null;
    }
    // ceiling()
    public Key ceiling(Key key){
        Node node = root;
        Node temp = root;
        while (node != null){
            int cmp = key.compareTo(node.key);
            if (cmp == 0) return node.key;
            if (cmp < 0){
                temp = node;
                if (temp.left == null){
                    return temp.key;
                }
                node = node.left;
            }else {
                if (node.right == null){
                    return temp.key;
                }
                node = node.right;
            }
        }
        return null;
    }
    public int rank(Key key){
        Node node = root;
        int rank = 0;
        while (node != null){
            int cmp = key.compareTo(node.key);
            if (cmp < 0){
                if (node.left != null){
                    node = node.left;
                }
            }
            else if (cmp > 0){
                rank += size(node.left) + 1;
                node = node.right;
            }
            else  {
                rank += size(node.left);
                return rank;
            }
        }
        return rank;
    }
    /*public Key select(int rank){

    }*/
    public Iterable<Key> keys(){
        Stack<Node> stack = new Stack<>();
        Queue<Key> queue = new Queue<>();
        Node node = root;
        while (node != null || stack.isEmpty()){
            if (node != null){
                stack.push(node);
                node = node.left;
            }
            else {
                node = stack.pop();
                queue.enqueue(node.key);
                node = node.right;
            }
        }
        return queue;
    }
    public static void main(String[] args) {
        Ex14_nonRecursiveImplementation<String, Integer> nonRecursiveImplementation = new Ex14_nonRecursiveImplementation<>();
        nonRecursiveImplementation.put("S", 5);
        nonRecursiveImplementation.put("E", 3);
        nonRecursiveImplementation.put("R", 6);
        nonRecursiveImplementation.put("X", 7);
        nonRecursiveImplementation.put("H", 4);
        nonRecursiveImplementation.put("M", 4);
        nonRecursiveImplementation.put("A", 1);
        nonRecursiveImplementation.put("C", 1);
        nonRecursiveImplementation.put("T", 17);
        StdOut.println(nonRecursiveImplementation.min());
        StdOut.println(nonRecursiveImplementation.max());
        StdOut.println(nonRecursiveImplementation.floor("G"));
        StdOut.println(nonRecursiveImplementation.floor("B"));
        StdOut.println(nonRecursiveImplementation.ceiling("G"));
        StdOut.println(nonRecursiveImplementation.ceiling("B"));
        StdOut.println(nonRecursiveImplementation.size());
        StdOut.println(nonRecursiveImplementation.rank("R"));
    }
}
