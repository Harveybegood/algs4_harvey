package Chapter3_2_BinarySearchTrees;

import edu.princeton.cs.algs4.StdOut;

/*
*   Give nonRecursive implementations of get() and put() for BST.
*   partial solution: Here is an implementation of get():
*   The implementation of put() is more complicated because of the need to save a pointer to the parent node to link in
*   the new node at the bottom. Also, you need a separate pass to check whether the key is already in the table because
*   of the need to update the counts. Since there are many more searches than inserts in performance-critical implementations,
*   using this code for get() is justified; the corresponding changes for put() might not be noticed.
*
* */
public class Ex13_nonRecursiveBST<Key extends Comparable<Key>, Value> {
    private class Node{
        private Node left;
        private Node right;
        private Key key;
        private int n;
        private Value value;
        public Node(Key key, Value value, int n){
            this.key = key;
            this.value = value;
            this.n = n;
        }
    }
    private Node root;
    private int size;
    public Value get(Key key){
        Node node = root;
        while (node != null){
            int cmp = key.compareTo(node.key);
            if (cmp == 0) return node.value;
            else if (cmp < 0) node = node.left;
            else node = node.right;
        }
        return null;
    }
   
    private void put(Key key, Value value){
        boolean keyExist = false;
        Node currentNode = root;
        while (currentNode != null){
            int cmp = key.compareTo(currentNode.key);
            if (cmp < 0)
                currentNode = currentNode.left;
            else if (cmp > 0)
                currentNode = currentNode.right;
            else {
                currentNode.value = value;
                keyExist = true;
                break;
            }
        }
        if (keyExist) {
            return;
        }
        if (root == null){
            root = new Node(key, value, 1);
            size++;
            return;
        }
        currentNode = root;
        while (currentNode != null){
            int cmp = key.compareTo(currentNode.key);
            if (cmp < 0){
                if (currentNode.left != null){
                    currentNode = currentNode.left;
                }else {
                    currentNode.left = new Node(key, value, 1);
                    size++;
                    break;
                }
            }else {
                if (currentNode.right != null){
                    currentNode = currentNode.right;
                }else {
                    currentNode.right = new Node(key, value, 1);
                    size++;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Ex13_nonRecursiveBST<String, Integer> nonRecursiveBST = new Ex13_nonRecursiveBST<>();
        nonRecursiveBST.put("S", 5);
        nonRecursiveBST.put("E", 3);
        nonRecursiveBST.put("X", 7);
        nonRecursiveBST.put("A", 1);
        nonRecursiveBST.put("R", 4);
        nonRecursiveBST.put("C", 2);
        StdOut.println(nonRecursiveBST.get("S") + "  " + ":  Expecting 5");
        StdOut.println(nonRecursiveBST.get("R") + "  " + ":  Expecting 4");
        StdOut.println(nonRecursiveBST.get("X") + "  " + ":  Expecting 7");
        StdOut.println(nonRecursiveBST.size + "  " + ":  Expecting 6");
    }
}
