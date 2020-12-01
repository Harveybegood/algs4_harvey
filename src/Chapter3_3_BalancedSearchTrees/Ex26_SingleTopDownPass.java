package Chapter3_3_BalancedSearchTrees;
import Chapter1_3_Bags_Queues_Stacks.Ex29;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;


/*
*   Single top-down pass. Develop a modified version of your solution to Ex25 that does not use recursion. Complete all the work
*   splitting and balancing 4-nodes(and balancing 3-nodes) on the way down the tree, finishing with an insertion at the bottom.
*
* */
public class Ex26_SingleTopDownPass<Key extends Comparable<Key>, Value> {
    private final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node{
        private Key key;
        private Value value;
        private Node left, right;
        private int numbersOfSubTree;
        private boolean color;
        public Node(Key key, Value value, int numbersOfSubTree, boolean color){
            this.key = key;
            this.value = value;
            this.numbersOfSubTree = numbersOfSubTree;
            this.color = color;
        }
    }
    private Node root;
    public void put(Key key, Value value){
        root = put(root, key, value);
        root.color = BLACK;
    }
    private Node put(Node node, Key key, Value value){
        if (node == root && root == null){
            return new Node(key, value, 1, RED);
        }
        else {
            Node current = root;
            Node parent;
            //Node hNode;
            while (true){
                parent = current;
                //hNode = current;
                /*if (isRed(parent.left) && isRed(parent.right)){
                    flipColor(parent);
                }
                if (isRed(parent.left) && isRed(parent.left.left)){
                    parent = rotateRight(parent);
                }
                if (isRed(parent.right) && !isRed(parent.left)){
                    parent = rotateLeft(parent);
                }*/
                if (key.compareTo(current.key) < 0){
                    current = current.left;
                  /*  if (isRed(parent.left)) {
                        hNode = parent;
                    }*/
                    if (current == null){
                        parent.left = new Node(key, value, 1, RED);
                        //rotateRight(hNode);
                        //node.numbersOfSubTree = size(node.left) + size(node.right) + 1;
                        return node;
                    }
                }
                else if (key.compareTo(current.key) > 0){
                    current = current.right;
                    if (current == null){
                        parent.right = new Node(key, value, 1, RED);
                        /*if (isRed(parent.right) && !isRed(parent.left)){
                            parent = rotateLeft(parent);
                        }*/
                        //node.numbersOfSubTree = size(node.left) + size(node.right) + 1;
                        return node;
                    }
                }
                else {
                    current.value = value;
                    return node;
                }
            }
        }
    }
    public int size(){
        return size(root);
    }
    private int size(Node node){
        if (node == null){
            return 0;
        }
        return node.numbersOfSubTree;
    }
    // rotate to left
    private Node rotateLeft(Node node){
        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;
        temp.color = node.color;
        node.color = RED;
        temp.numbersOfSubTree = node.numbersOfSubTree;
        node.numbersOfSubTree = size(node.left) + size(node.right) + 1;
        return temp;
    }
    // rotate to right
    private Node rotateRight(Node node){
        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;
        temp.color = node.color;
        node.color = RED;
        temp.numbersOfSubTree = node.numbersOfSubTree;
        node.numbersOfSubTree = size(node.left) + size(node.right) + 1;
        return temp;
    }
    private void flipColor(Node node){
        if (node == null){return;}
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }
    private boolean isRed(Node node){
        if (node == null){
            return BLACK;
        }
        return node.color == RED;
    }
    public Key min(){
        return min(root);
    }
    private Key min(Node node){
        if (node == null){
            return null;
        }
        while (true){
            if (node.left == null){
                return node.key;
            }
            node = node.left;
        }
    }
    public Key max(){
        return max(root);
    }
    private Key max(Node node){
        if (node == null){
            return null;
        }
        while (true){
            if (node.right == null){
                return node.key;
            }
            node = node.right;
        }
    }
    public Iterable<Key> keys(){
        return keys(min(), max());
    }
    public Iterable<Key> keys(Key min, Key max){
        Queue<Key> queue = new Queue<>();
        keys(root, queue, min, max);
        return queue;
    }
    @SuppressWarnings("unchecked")
    private void keys(Node node, Queue<Key> queue, Key min, Key max) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack();
        Node current = node;
        //stack.push(current);
        int locmp = 0;
        int hicmp = 0;
        while (current != null || !stack.isEmpty()) {
            // traverse left child until null node
            if (current != null) {
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

    public static void main(String[] args) {
        Ex26_SingleTopDownPass<String, Integer> singleTopDownPass = new Ex26_SingleTopDownPass<>();
        singleTopDownPass.put("S", 6);
        singleTopDownPass.put("E", 2);
        singleTopDownPass.put("A", 1);
        singleTopDownPass.put("R", 5);
        singleTopDownPass.put("H", 3);
        singleTopDownPass.put("K", 4);
        singleTopDownPass.put("T", 7);
        for (String s : singleTopDownPass.keys("E", "S")){
            StdOut.print(s + " ");
        }
        StdOut.println();
        StdOut.println(singleTopDownPass.size());
    }
}
