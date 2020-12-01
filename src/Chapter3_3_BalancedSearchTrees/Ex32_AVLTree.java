package Chapter3_3_BalancedSearchTrees;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;
/*
*   AVL Trees. An AVL tree is a BST where the height of every node and that of its sibling differ by at most 1.
*   (The oldest balanced tree algorithms are based on using rotations to maintain height balance in AVL trees.)
*   Show that coloring red links that go from nodes of even height to nodes of odd height in an AVL gives a
*   (perfectly balanced)2-3-4 tree, where red links are not necessarily left-leaning.
*   Extra credit: Develop an implementation of the symbol-table API that uses this as the underlying data structure.
*   One approach is to keep a height as necessary;
*   another is to use the red-black representation and use methods like moveRedLeft() and moveRedRight() in Ex3.3.39 and Ex3.3.40.
*
*   This happens because every two levels of the tree will merge its nodes into 4-nodes
*   (or 3-nodes if the node has only one child or 2-nodes if the node has no children). This generates a perfectly balanced 2-3-4 tree.
*
* */
public class Ex32_AVLTree {
    public static class AVLUsingHeight<Key extends Comparable<Key>, Value>{
        /*public static final boolean RED = true;
        public static final boolean BLACK = false;*/
        private class Node{
            private Key key;
            private Value value;
            private Node left, right;
            private int numberOfNode;
            private int height;
            private double Xcoordinate, Ycoordinate;
            //private boolean color;
            public Node(Key key, Value value, int numberOfNode, int height){
                this.key = key;
                this.value = value;
                this.numberOfNode = numberOfNode;
                this.height = height;
                //this.color = color;
            }
        }
        private Node root;
        private int treeLevel;
        public int size(){
            return size(root);
        }
        private int size(Node node){
            if (node == null){
                return 0;
            }
            return node.numberOfNode;
        }
        public boolean isEmpty(){return root == null || size() == 0;}
        public boolean contains(Key key){return get(key) != null;}
        public Key get(Key key){
            if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
            return get(root, key).key;
        }
        private Node get(Node node, Key key){
            if (node == null){
                return null;
            }
            int cmp = key.compareTo(node.key);
            if (cmp < 0){
                return get(node.left, key);
            }
            else if (cmp > 0){
                return get(node.right, key);
            }
            else {
                return node;
            }
        }
        public void put(Key key, Value value){
            if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
            if (value == null){throw new IllegalArgumentException("Argument cannot be null");}
            root = put(root, key, value);
            //root.color = BLACK;
        }
        private Node put(Node node, Key key, Value value){
            if (node == null){
                return new Node(key, value, 1, 0);
                //newNode.height = 1 + Math.max(heightOfAVL(node.left), heightOfAVL(node.right));
                //return newNode;
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
            if (Math.abs(heightOfAVL(node) - heightOfAVL(node.right)) > 1){
                if (heightOfAVL(node.left) > heightOfAVL(node.left.right)){
                    node = rotateLL(node);
                }
                else {
                    node = rotateLR(node);
                }
            }
            else if (Math.abs(heightOfAVL(node) - heightOfAVL(node.left)) > 1){
                if (heightOfAVL(node.right) > heightOfAVL(node.right.left)){
                    node = rotateRR(node);
                }
                else {
                    node = rotateRL(node);
                }
            }
            node.height = 1 + Math.max(heightOfAVL(node.left), heightOfAVL(node.right));
            node.numberOfNode = size(node.left) + 1 + size(node.right);
            return node;
        }

        public int heightOfAVL(){
            return heightOfAVL(root);
        }
        private int heightOfAVL(Node node){
            if (node == null){
                return 0;
            }
            return node.height;
        }
        // move
        private Node rotateLL(Node node){
            if (node == null){
                return null;
            }
            Node x = node.left;
            node.left = x.right;
            x.right = node;
            x.numberOfNode = node.numberOfNode;
            node.numberOfNode = size(node.left) + 1 + size(node.right);
            x.height = 1 + Math.max(heightOfAVL(x.left), heightOfAVL(x.right));
            node.height = 1 + Math.max(heightOfAVL(node.left), heightOfAVL(node.right));
            return x;
        }
        private Node rotateLR(Node node){
            if (node == null){
                return null;
            }
            Node x = node.left.right;
            Node temp = node.left;
            temp.right = x.left;
            node.left = node.left.right.right;
            x.left = temp;
            x.right = node;
            x.numberOfNode = node.numberOfNode;
            node.numberOfNode = size(node.left) + 1 + size(node.right);
            x.height = 1 + Math.max(heightOfAVL(x.left), heightOfAVL(x.right));
            node.height = 1 + Math.max(heightOfAVL(node.left), heightOfAVL(node.right));
            return x;
        }
        private Node rotateRR(Node node){
            if (node == null){
                return null;
            }
            Node x = node.right;
            node.right = x.left;
            x.left = node;
            x.numberOfNode = node.numberOfNode;
            node.numberOfNode = size(node.left) + 1 + size(node.right);
            x.height = 1 + Math.max(heightOfAVL(x.left), heightOfAVL(x.right));
            node.height = 1 + Math.max(heightOfAVL(node.left), heightOfAVL(node.right));
            return x;
        }
        private Node rotateRL(Node node){
            if (node == null){
                return null;
            }
            Node x = node.right.left;
            Node temp = node.right;
            temp.left = x.right;
            node.right = x.left;
            x.left = node;
            x.right = temp;
            x.numberOfNode = node.numberOfNode;
            node.numberOfNode = size(node.left) + 1 + size(node.right);
            x.height = 1 + Math.max(heightOfAVL(x.left), heightOfAVL(x.right));
            node.height = 1 + Math.max(heightOfAVL(node.left), heightOfAVL(node.right));
            return x;
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
            int loCmp = min.compareTo(node.key);
            int hiCmp = max.compareTo(node.key);
            if (loCmp < 0){
                keys(node.left, queue, min, max);
            }
            if (loCmp <= 0 && hiCmp >= 0){
                queue.enqueue(node.key);
            }
            if (hiCmp > 0){
                keys(node.right, queue, min, max);
            }
        }
        // draw the avl tree
        private void draw(){
            treeLevel = 0;
            setCoordinates(root, 0.9);
            drawLines(root);
            drawNodes(root);
        }
        private void setCoordinates(Node node, double distance){
            if (node == null){
                return;
            }
            setCoordinates(node.left, distance - 0.05);
            node.Xcoordinate = (.5 + treeLevel++) / size();
            node.Ycoordinate = distance - 0.05;
            setCoordinates(node.right, distance - 0.05);
        }
        private void drawNodes(Node node){
            if (node == null){
                return;
            }
            double nodeRadius = 0.025;
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.filledCircle(node.Xcoordinate, node.Ycoordinate, nodeRadius);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.pause(1000);
            StdDraw.circle(node.Xcoordinate, node.Ycoordinate, nodeRadius);
            StdDraw.text(node.Xcoordinate, node.Ycoordinate, String.valueOf(node.key));
            drawNodes(node.left);
            drawNodes(node.right);
        }
        private void drawLines(Node node){
            if (node == null){
                return;
            }
            if (node.left != null){
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.pause(1000);
                StdDraw.line(node.Xcoordinate, node.Ycoordinate, node.left.Xcoordinate, node.left.Ycoordinate);
            }
            if (node.right != null){
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.pause(1000);
                StdDraw.line(node.Xcoordinate, node.Ycoordinate, node.right.Xcoordinate, node.right.Ycoordinate);
            }
            drawLines(node.left);
            drawLines(node.right);
        }
    }

    public static void main(String[] args) {
        AVLUsingHeight<String, Integer> avl = new AVLUsingHeight<>();
        avl.put("S", 10);
        avl.put("E", 3);
        avl.put("B", 2);
        avl.put("R", 6);
        avl.put("H", 5);
        avl.put("U", 16);
        avl.put("T", 12);
        avl.put("A", 1);
        StdOut.println("Height of AVLTree: " + avl.heightOfAVL());
        for (String s : avl.keys()){
            StdOut.print(s + " ");
        }
        avl.draw();
    }
}
