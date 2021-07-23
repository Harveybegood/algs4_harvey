package Chapter3_5_Applications;
/*
*   Develop classes SETint and SETdouble for maintaining ordered sets of keys of primitive int and double types, respectively.
*   (Eliminate code involving values in your solution to Ex3.5.5)
*
* */
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class Ex07_SETintAnddouble {
    private static class SETint{
        private static final boolean RED = true;
        private static final boolean BLACK = false;
        private static final int EMPTY_KEY = Integer.MIN_VALUE;
        private class Node{
            private Node left, right;
            private int key;
            private int numberOfNodes;
            private boolean color;
            public Node(int key, int numberOfNodes, boolean color){
                this.key = key;
                this.numberOfNodes = numberOfNodes;
                this.color = color;
            }
        }
        private Node root;
        public boolean isEmpty(){return root == null;}
        public int size(){return size(root);}
        private int size(Node node){
            if (node == null){
                return 0;
            }
            return node.numberOfNodes;
        }
        public boolean contains(int key){
            if (isEmpty()){return false;}
            return contains(root, key);
        }
        private boolean contains(Node node, int key){
            if (key < node.key){
                contains(node.left, key);
            }
            else if (key > node.key){
                contains(node.right, key);
            }
            else {
                return true;
            }
            return false;
        }
        public void put(int key){
            root = put(root, key);
            if (!isEmpty()){
                root.color = BLACK;
            }
        }
        private Node put(Node node, int key){
            if (node == null){return new Node(key, 1, RED);}
            if (key < node.key){
                node.left = put(node.left, key);
            }
            else if (key > node.key){
                node.right = put(node.right, key);
            }
            else {
                return node;
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
        public boolean isRed(Node node){
            if (node == null){return false;}
            return node.color == RED;
        }
        public void flipColor(Node node){
            node.color = !node.color;
            node.left.color = !node.left.color;
            node.right.color = !node.right.color;
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
        public int min(){
            if (isEmpty()){return EMPTY_KEY;}
            return min(root).key;
        }
        private Node min(Node node){
            if (node.left == null){
                return node;
            }
            return min(node.left);
        }
        public int max(){
            if (isEmpty()){return EMPTY_KEY;}
            return max(root).key;
        }
        private Node max(Node node){
            if (node.right == null){
                return node;
            }
            return max(node.right);
        }
        public int select(int rank){
            return select(root, rank).key;
        }
        private Node select(Node node, int rank){
            if (rank < node.numberOfNodes){
                return select(node.left, rank);
            }
            else if (rank > node.numberOfNodes){
                return select(node.right, rank);
            }
            else {
                return node;
            }
        }
        public int rank(int key){
            return rank(root, key);
        }
        private int rank(Node node, int key){
            if (node == null){return 0;}
            if (key < node.key){
                return rank(node.left, key);
            }
            else {
                return rank(node.right, key) + size(node.left) + 1;
            }
        }
        public int floor(int key){
            return floor(root, key).key;
        }
        private Node floor(Node node, int key){
            if (node == null){return null;}
            if (key < node.key){
                return floor(node.left, key);
            }
            Node temp = floor(node.right, key);
            if (temp != null){
                return temp;
            }
            else {
                return node;
            }
        }
        public int ceiling(int key){
            return ceiling(root, key).key;
        }
        private Node ceiling(Node node, int key){
            if (node == null){return null;}
            if (key < node.key){
                return ceiling(node.left, key);
            }
            Node temp = ceiling(node.right, key);
            if (temp != null){
                return temp;
            }
            else {
                return node;
            }
        }
        public Node moveRedLeft(Node node){
            flipColor(node);
            if (isRed(node.right.left)){
                node.right = rotateToRight(node.right);
                node = rotateToLeft(node);
            }
            return node;
        }
        public Node moveRedRight(Node node){
            flipColor(node);
            if (!isRed(node.left.left)){
                node = rotateToRight(node);
            }
            return node;
        }
        public Node balance(Node node){
            if (!isRed(node.left) && isRed(node.right)){
                node.right = rotateToLeft(node);
            }
            if (isRed(node.left) && isRed(node.left.left)){
                node.left = rotateToRight(node);
            }
            if (isRed(node.left) && isRed(node.right)){
                flipColor(node);
            }
            return node;
        }
        public void deleteMin(){
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
            if (!isRed(node.right) && !isRed(node.right.left)){
                node = moveRedRight(node);
            }
            node.right = deleteMax(node);
            return balance(node);
        }
        public void delete(int key){
            if (!isRed(root.left) && !isRed(root.right)){
                root.color = RED;
            }
            root = delete(root, key);
            if (!isEmpty()){
                root.color = BLACK;
            }
        }
        private Node delete(Node node, int key){
            if (key < node.key){
                if (!isRed(node.left) && !isRed(node.left.left)){
                    node = moveRedLeft(node);
                }
                node.left = delete(node.left, key);
            }
            else {
                if (isRed(node.left)){
                    node = rotateToRight(node);
                }
                if (key == node.key && node.right == null){
                    return null;
                }
                if (!isRed(node.right) && !isRed(node.right.left)){
                    node = moveRedRight(node);
                }
                if (key == node.key){
                    node.key = min(node.right).key;
                    node.right = deleteMin(node.right);
                }
                else {
                    node.right = delete(node.right, key);
                }
            }
            return balance(node);
        }
        private int count;
        public int size(int lo, int hi){
            //int count  = 0;
            size(root, lo, hi);
            return count;
        }
        private void size(Node node, int lo, int hi){
            if (node == null){return;}
            if (lo < node.key){
                size(node.left, lo, hi);
            }
            if (lo <= node.key && hi >= node.key){
                count++;
            }
            if (hi > node.key){
                size(node.right, lo, hi);
            }
        }
        public Iterable<Integer> keys(){
            return keys(min(), max());
        }
        public Iterable<Integer> keys(int lo, int hi){
            Queue<Integer> queue = new Queue<>();
            keys(root, queue, lo, hi);
            return queue;
        }
        private void keys(Node node, Queue<Integer> queue, int lo, int hi){
            if (node == null){return;}
            if (lo < node.key){
                keys(node.left, queue, lo, hi);
            }
            if (lo <= node.key && hi >= node.key){
                queue.enqueue(node.key);
            }
            if (hi > node.key){
                keys(node.right, queue, lo, hi);
            }
        }

        public static void main(String[] args) {
            SETint seTint = new SETint();
            seTint.put(10);
            seTint.put(3);
            seTint.put(15);
            seTint.put(1);
            seTint.put(6);
            seTint.put(5);
            seTint.put(8);
            StdOut.println("All keys in the symbol table");
            for (Integer i : seTint.keys()){
                StdOut.print(i + " ");
            }
            StdOut.println("\nBetween 3 and 10 " + seTint.size(3, 10) + " expected 5");
            seTint.deleteMin();
            StdOut.println("The size of symbol table " + seTint.size() + " expected 6");
            StdOut.println("All keys in the symbol table after deleting minimum");
            for (Integer i : seTint.keys()){
                StdOut.print(i + " ");
            }
        }
    }

    private class SETdouble{

    }
}
