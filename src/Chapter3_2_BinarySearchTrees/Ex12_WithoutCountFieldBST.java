package Chapter3_2_BinarySearchTrees;

import edu.princeton.cs.algs4.StdOut;

/*
*   Develop a BST implementation that omits rank() and select() and does not use a count field in Node.
*
* */
public class Ex12_WithoutCountFieldBST<Key extends Comparable<Key>, Value> {
    private class Node{
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        public Node(Key key, Value value){
            this.key = key;
            this.value = value;
        }
    }
    private Node root;
    private int count;
    public void put(Key key, Value value){
        root = put(root, key, value);
    }
    private Node put(Node node, Key key, Value value){
        if (node == null){
            count++;
            return new Node(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, value);
        else if (cmp > 0) node.right = put(node.right, key, value);
        else node.value = value;
        return node;
    }
    public int size(){
        return count;
    }

    public static void main(String[] args) {
        Ex12_WithoutCountFieldBST<String, Integer> withoutCountFieldBST = new Ex12_WithoutCountFieldBST<>();
        withoutCountFieldBST.put("S", 10);
        withoutCountFieldBST.put("E", 12);
        withoutCountFieldBST.put("X", 1);
        withoutCountFieldBST.put("A", 9);
        withoutCountFieldBST.put("R", 19);
        withoutCountFieldBST.put("T", 16);
        withoutCountFieldBST.put("C", 7);
        withoutCountFieldBST.put("H", 4);
        withoutCountFieldBST.put("M", 2);
        StdOut.println(withoutCountFieldBST.count + " " + withoutCountFieldBST.size());
    }
}
