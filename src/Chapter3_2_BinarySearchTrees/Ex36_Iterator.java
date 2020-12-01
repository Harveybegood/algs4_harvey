package Chapter3_2_BinarySearchTrees;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/*
*   Iterator. Is it possible to write a nonrecursive version of keys() that uses space proportional to the tree height
*   (independent of the number of keys in the range)?
*
* */
public class Ex36_Iterator<Key extends Comparable<Key>, Value> {
    // firstly review the recursive method
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
    // write a method to help calculate size of subtree
    public int size(){
        return size(root);
    }
    private int size(Node node){
        if (node == null)
            return 0;
        return node.countOfTree;
    }
    // write a method to generate BST
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
        node.countOfTree = size(node.left) + size(node.right) + 1;
        return node;
    }
    // a recursive version of keys()
    public Iterable<Key> keys(Key lo, Key hi){
        return keys(root, lo, hi);
    }
    public Iterable<Key> keys(Node node, Key lo, Key hi){
        Queue<Key> queue = new Queue<>();
        keys(node, queue, lo, hi);
        return queue;
    }
    private void keys(Node node, Queue<Key> queue, Key lo, Key hi){
        if (node == null) return;
        int cmplo = lo.compareTo(node.key);
        int cmphi = hi.compareTo(node.key);
        if (cmplo < 0)
            keys(node.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0)
            queue.enqueue(node.key);
        if (cmphi > 0)
            keys(node.right, queue, lo, hi);
    }
    // a nonrecursive version of keys()
    public Iterable<Key> keysNonRecursive(Key lo, Key hi){
        return keysNonRecursive(root, lo, hi);
    }
    public Iterable<Key> keysNonRecursive(Node node, Key lo, Key hi){
        Queue<Key> queue = new Queue<>();
        keysNonRecursive(node, queue, lo, hi);
        return queue;
    }

    private void keysNonRecursive(Node node, Queue<Key> queue, Key lo, Key hi){
        Stack<Node> stack = new Stack<>();
        // move all left nodes starting from root to the most left node(still in scope) to stack
        while (node != null){
            int cmplo = lo.compareTo(node.key);
            int cmphi = hi.compareTo(node.key);
            if (cmplo <= 0 && cmphi >= 0){
                stack.push(node);
            }
            node = node.left;
        }
        while (!stack.isEmpty()){
            Node x = stack.pop();
            queue.enqueue(x.key);
            if (x.right != null) {
                Node temp = x.right;
                while (temp != null){
                    int cmplo = lo.compareTo(temp.key);
                    int cmphi = hi.compareTo(temp.key);
                    if (cmplo <= 0 && cmphi >= 0){
                        stack.push(temp);
                    }
                    temp = temp.left;
                }
            }
        }
    }
    public static void main(String[] args) {
        Ex36_Iterator<String, Integer> iterator = new Ex36_Iterator<>();
        iterator.put("S", 6);
        iterator.put("E", 2);
        iterator.put("X", 8);
        iterator.put("A", 1);
        iterator.put("R", 5);
        iterator.put("T", 7);
        iterator.put("H", 3);
        iterator.put("M", 4);
        StdOut.println("E H M R S T");
        for (String s : iterator.keys("E", "T")){
            StdOut.print(s + " ");
        }
        StdOut.println();
        StdOut.println("Size of Root 8 : " + iterator.size());
        // non-recursive version
        for (String s : iterator.keysNonRecursive("E", "T")){
            StdOut.print(s + " ");
        }
    }
}
