package Chapter3_5_Applications;


import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/*
*   Modify RedBlackBST to keep duplicate keys in the tree. Return any value associated with the given key for get(),
*   and remove all nodes in the tree that have keys equal to the given key for delete().
*
* */
@SuppressWarnings("unchecked")
public class Ex10_RedBlackBSTWithDuplicateKeys<Key extends Comparable<Key>, Value> {
    private final static boolean RED = true;
    private final static boolean BLACK = false;
    private class Node{
        Key key;
        Value value;
        Node left, right;
        boolean color;
        int numberOfNodes;
        public Node(Key key, Value value, int numberOfNodes, boolean color){
            this.key = key;
            this.value = value;
            this.numberOfNodes = numberOfNodes;
            this.color = color;
        }
    }
    private Node root;
    public boolean isEmpty(){return root == null;}
    public int size(){
        return size(root);
    }
    private int size(Node node){
        if (node == null){return 0;}
        return node.numberOfNodes;
    }
    public boolean contains(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        Node node = root;
        while (node != null){
            if (key.compareTo(node.key) < 0){
                node = node.left;
            }
            else if (key.compareTo(node.key) > 0){
                node = node.right;
            }
            else {
                return true;
            }
        }
        return false;
    }

    public Value get(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        StringBuilder stringBuilder = new StringBuilder();
        get(root, key, stringBuilder);
        return (Value) stringBuilder;
    }
    private void get(Node node, Key key, StringBuilder stringBuilder){
        if (node == null){return;}
        if (key.compareTo(node.key) < 0){
            get(node.left, key, stringBuilder);
        }
        else if (key.compareTo(node.key) > 0){
            get(node.right, key, stringBuilder);
        }
        else {
            stringBuilder.append(node.value).append(" ");
            if (node.left != null && key.compareTo(node.left.key) == 0){
                get(node.left, key, stringBuilder);
            }
            if (node.right != null && key.compareTo(node.right.key) == 0){
                get(node.right, key, stringBuilder);
            }
        }
        //return null;
    }
    public void put(Key key, Value value){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        if (value == null){throw new IllegalArgumentException("Argument cannot be null");}
        root = put(root, key, value);
        if (!isEmpty()){
            root.color = BLACK;
        }
    }
    private Node put(Node node, Key key, Value value){
        if (node == null){return new Node(key, value, 1, RED);}
        if (key.compareTo(node.key) < 0){
            node.left = put(node.left, key, value);
        }
        else {
            node.right = put(node.right, key, value);
        }
        if (isRed(node.right) && !isRed(node.left)){
            node = rotateToLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)){
            node = rotateToRight(node);
        }
        if (isRed(node.left) && isRed(node.right)){
            //flipColor(node);
            node.color = RED;
            node.left.color = BLACK;
            node.right.color = BLACK;
        }
        node.numberOfNodes = size(node.left) + size(node.right) + 1;
        return node;
    }
    public boolean isRed(Node node){
        if (node == null){return false;}
        return node.color == RED;
    }
    public Node rotateToLeft(Node node){
        Node newNode = node.right;
        node.right = newNode.left;
        newNode.left = node;
        newNode.color = node.color;
        node.color = RED;
        newNode.numberOfNodes = node.numberOfNodes;
        node.numberOfNodes = size(node.left) + size(node.right) + 1;
        return newNode;
    }
    public Node rotateToRight(Node node){
        Node newNode = node.left;
        node.left = newNode.right;
        newNode.right = node;
        newNode.color = node.color;
        node.color = RED;
        newNode.numberOfNodes = node.numberOfNodes;
        node.numberOfNodes = size(node.left) + size(node.right) + 1;
        return newNode;
    }
    public void flipColor(Node node){
        if (node == null || node.left == null || node.right == null){
            return;
        }
        if ((isRed(node) && !isRed(node.left) && !isRed(node.right)) ||
                (!isRed(node) && isRed(node.left) && isRed(node.right))){
            node.color = !node.color;
            node.left.color = !node.left.color;
            node.right.color = !node.right.color;
        }
    }
    public Key min(){
        if (isEmpty()){throw new RuntimeException("Red Black BST underflow");}
        return min(root).key;
    }
    private Node min(Node node){
        if (node.left == null){
            return node;
        }
        return min(node.left);
    }
    public Key max(){
        if (isEmpty()){throw new RuntimeException("Red Black BST underflow");}
        return max(root).key;
    }
    private Node max(Node node){
        if (node.right == null){return node;}
        return max(node.right);
    }
    public void deleteMin(){
        if (isEmpty()){throw new RuntimeException("Red Black BST underflow");}
        if (!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }
        root = deleteMin(root);
        if (!isEmpty()){
            root.color = BLACK;
        }
    }
    private Node deleteMin(Node node){
        if (node.left == null){
            return null;
        }
        if (!isRed(node.left) && !isRed(node.left.left)){
            node = moveRedLeft(node);
        }
        node.left = deleteMin(node.left);
        return balance(node);
    }
    public void deleteMax(){
        if (isEmpty()){throw new RuntimeException("Red Black BST underflow");}
        if (!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }
        root = deleteMax(root);
        if (!isEmpty()){
            root.color = BLACK;
        }
    }
    private Node deleteMax(Node node){
        if (isRed(node.left)){
            node = rotateToRight(node);
        }
        if (node.right == null){
            return null;
        }
        if (!isRed(node.right) && !isRed(node.left.right)){
            node = moveRedRight(node);
        }
        node.right = deleteMax(node.right);
        return balance(node);
    }
    public Node moveRedLeft(Node node){
        flipColor(node);
        if (isRed(node.right.left)){
            node.right = rotateToRight(node.right);
            node = rotateToLeft(node);
            flipColor(node);
        }
        return node;
    }
    public Node moveRedRight(Node node){
        flipColor(node);
        if (!isRed(node.left.left)){
            node = rotateToRight(node);
            flipColor(node);
        }
        return node;
    }
    public Node balance(Node node){
        if (isRed(node.right)){
            node = rotateToLeft(node);
        }

        if (!isRed(node.left) && isRed(node.right)){
            node = rotateToLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)){
            node = rotateToRight(node);
        }
        if (isRed(node.left) && isRed(node.right)){
            flipColor(node);
        }
        node.numberOfNodes = size(node.left) + size(node.right) + 1;
        return node;
    }
    public void delete(Key key){
        if (isEmpty()){throw new RuntimeException("Red Black BST underflow");}
        if (!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }
        root = delete(root, key);
        if (!isEmpty()){
            root.color = BLACK;
        }
    }
    private Node delete(Node node, Key key){
        //if (node == null){return null;}
        if (key.compareTo(node.key) < 0){
            if (!isRed(node.left) && !isRed(node.left.left)){
                node = moveRedLeft(node);
            }
            node.left = delete(node.left, key);
        }
        else {
            if (isRed(node.left)){
                node = rotateToRight(node);
            }
            if (key.compareTo(node.key) == 0 && node.right == null){
                return null;
            }
            if (!isRed(node.right) && !isRed(node.right.left)){
                node = moveRedRight(node);
            }
            if (key.compareTo(node.key) == 0){
                //StringBuilder s;
                Node temp = node;
                node = min(node.right);
                node.right = deleteMin(temp.right);
                node.left = temp.left;
                /*if (key.compareTo(node.key) == 0){
                    node = delete(node, key);
                }*/
                //Node reTravelTree = root;
                if (node.left != null && key.compareTo(node.left.key) ==0){
                    node.left = delete(node.left, key);
                }
                if (node.right != null && key.compareTo(node.right.key) == 0){
                    node.right = delete(node.right, key);
                }

            }
            else {
                node.right = delete(node.right, key);
            }

        }
        /*if (contains(key)){
            delete(root, key);
        }*/
        //node.numberOfNodes = size(node.left) + size(node.right) + 1;
        return balance(node);
    }
    public Iterable<Key> keys(){
        return keys(min(), max());
    }
    public Iterable<Key> keys(Key lo, Key hi){
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }
    private void keys(Node node, Queue<Key> queue, Key lo, Key hi){
        if (node == null){return;}
        if (lo.compareTo(node.key) < 0){
            keys(node.left, queue, lo, hi);
        }
        if (lo.compareTo(node.key) <= 0 && hi.compareTo(node.key) >= 0){
            queue.enqueue(node.key);
        }
        if (hi.compareTo(node.key) >0){
            keys(node.right, queue, lo, hi);
        }
    }

    public static void main(String[] args) {
        Ex10_RedBlackBSTWithDuplicateKeys<String, Integer> redBlackBSTWithDuplicateKeys = new Ex10_RedBlackBSTWithDuplicateKeys<>();
        redBlackBSTWithDuplicateKeys.put("R", 15);
        redBlackBSTWithDuplicateKeys.put("E", 5);
        redBlackBSTWithDuplicateKeys.put("E", 6);
        redBlackBSTWithDuplicateKeys.put("S", 25);
        redBlackBSTWithDuplicateKeys.put("A", 2);
        redBlackBSTWithDuplicateKeys.put("H", 9);
        redBlackBSTWithDuplicateKeys.put("G", 8);
        redBlackBSTWithDuplicateKeys.put("M", 12);
        StdOut.println("All keys in Red Black BST");
        StdOut.println("The size in symbol table " + redBlackBSTWithDuplicateKeys.size() + " expected 8");
        for (String s : redBlackBSTWithDuplicateKeys.keys()){
            StdOut.println(s + "  " + redBlackBSTWithDuplicateKeys.get(s));
        }
        StdOut.println("All keys in Red Black BST after deleting duplicate key E");
        redBlackBSTWithDuplicateKeys.delete("E");
        for (String s : redBlackBSTWithDuplicateKeys.keys()){
            StdOut.println(s + "  " + redBlackBSTWithDuplicateKeys.get(s));
        }
        StdOut.println("The size after deleting duplicate keys " + redBlackBSTWithDuplicateKeys.size() + " expected 6");
    }
}

/*
*       All keys in Red Black BST
        The size in symbol table 8 expected 8
        A  2
        E  6 5
        E  6 5 
        G  8
        H  9
        M  12
        R  15
        S  25
        All keys in Red Black BST after deleting duplicate key E
        A  2
        G  8
        H  9
        M  12
        R  15
        S  25
        The size after deleting duplicate keys 6 expected 6

*
* */