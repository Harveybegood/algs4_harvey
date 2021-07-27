package Chapter3_5_Applications;


import com.sun.prism.impl.QueuedPixelSource;
import edu.princeton.cs.algs4.Queue;
/*
*   Develop classes STint and STdouble for maintaining ordered symbol tables where keys are primitive int and double types,
*   respectively. (Convert generics to primitive types in the code of RedBlackBST.)
*
* */
public class Ex05_RedBlackBSTWithPrimitiveTypes {
    private final static boolean RED = true;
    private final static boolean BLACK = false;
    private class STint{
        private class Node{
            Node left, right;
            int key;
            int value;
            boolean color;
            int numberOfNodes;
            public Node(int key, int value, boolean color, int numberOfNodes){
                this.key = key;
                this.value = value;
                this.color = color;
                this.numberOfNodes = numberOfNodes;
            }
        }
        private Node root;
        public boolean isEmpty(){return root.numberOfNodes == 0;}
        public int size(){
            return size(root);
        }
        private int size(Node node){
            if (node == null){return 0;}
            return node.numberOfNodes;
        }
        public boolean contains(int key){
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
        public int get(int key){
            return get(root, key).key;
        }
        private Node get(Node node, int key){
            if (node == null){return null;}
            if (key < node.key){return get(node.left, key);}
            else if (key > node.key){return get(node.right, key);}
            else {return node;}
        }
        public boolean isRed(Node node){
            if (node == null){return false;}
            return node.color == RED;
        }
        public Node rotateToLeft(Node node){
            Node temp = node.right;
            node.right = temp.left;
            temp.left = node;
            temp.numberOfNodes = node.numberOfNodes;
            node.numberOfNodes = size(node.left) + size(node.right) + 1;
            temp.color = node.color;
            node.color = RED;
            return temp;
        }
        public Node rotateToRight(Node node){
            Node temp = node.left;
            node.left = temp.right;
            temp.right = node;
            temp.numberOfNodes = node.numberOfNodes;
            node.numberOfNodes = size(node.left) + size(node.right) + 1;
            temp.color = node.color;
            node.color = RED;
            return temp;
        }
        public void flipColor(Node node){
            if (node == null){return;}
            node.color = RED;
            node.left.color = BLACK;
            node.right.color = BLACK;
        }
        public void put(int key, int value){
            root = put(root, key, value);
            root.color = BLACK;
        }
        private Node put(Node node, int key, int value){
            if (node == null){return new Node(key, value, RED, 1);}
            if (key < node.key){
                node.left = put(node.left, key, value);
            }
            else if (key > node.key){
                node.right = put(node.right, key, value);
            }
            else {
                node.value = value;
            }
            if (isRed(node.left) && isRed(node.left.left)){
                node.left = rotateToRight(node);
            }
            if (!isRed(node.left) && isRed(node.right)){
                node.right = rotateToRight(node);
            }
            if (isRed(node.left) && isRed(node.right)){
                flipColor(node);
            }
            node.numberOfNodes = size(node.left) + size(node.right) + 1;
            return node;
        }
        public int min(){
            return min(root).key;
        }
        private Node min(Node node){
            if (node.left == null){
                return node;
            }
            return min(node.left);
        }
        public int max(){
            return max(root).key;
        }
        private Node max(Node node){
            if (node.right == null){
                return node;
            }
            return max(node.right);
        }
        public void deleteMin(){
            if (isEmpty()){return;}
            root = deleteMin(root);
            if (!isEmpty()){
                root.color = BLACK;
            }
        }
        private Node deleteMin(Node node){
            if (node.left == null){return null;}
            if (!isRed(node.left) && !isRed(node.left.left)){
                node = moveRedLeft(node);
            }
            node.left = deleteMin(node.left);
            return balance(node);
        }
        public void deleteMax(){
            if (isEmpty()){return;}
            root = deleteMax(root);
            if (!isEmpty()){
                root.color = BLACK;
            }
        }
        private Node deleteMax(Node node){
            if (node.right == null){return null;}
            if (isRed(node.left)){
                node = rotateToRight(node);
            }
            if (!isRed(node.right) && !isRed(node.right.left)){
                node = moveRedRight(node);
            }
            node.right = deleteMax(node.right);
            return balance(node);
        }
        public Node balance(Node node){
            if (isRed(node.left) && isRed(node.left.left)){
                node.left = rotateToRight(node);
            }
            if (!isRed(node.left) && isRed(node.right)){
                node.right = rotateToLeft(node);
            }
            if (isRed(node.left) && isRed(node.right)){
                flipColor(node);
            }
            return node;
        }
        public Node moveRedLeft(Node node){
            flipColor(node);
            if (isRed(node.right.left)){
                node.right = rotateToRight(node.right);
                node.left = rotateToLeft(node);
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
        public void delete(int key){
            if (isEmpty() || !contains(key)){return;}
            if (!isRed(root.left) && !isRed(root.right)){
                root.color = RED;
            }
            root = delete(root, key);
            if (!isEmpty()){
                root.color = BLACK;
            }
        }
        private Node delete(Node node, int key){
            if (node == null){return null;}
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
            if (key < node.key && node.right == null){
                return null;
            }
            if (!isRed(node.right) && !isRed(node.right.left)){
                node = moveRedRight(node);
            }
            if (key == node.key) {
                node.key = min(node.right).key;
                node.value = get(node.right, min(node.right).key).value;
                node.right = deleteMin(node.right);
            }
            else {
                node.right = delete(node.right, key);
            }
            }
            return balance(node);
        }
        public Iterable<Integer> keys(){
            return keys(min(), max());
        }
        public Iterable<Integer> keys(int min, int max){
            Queue<Integer> queue = new Queue<>();
            keys(root, queue, min, max);
            return queue;
        }
        private void keys(Node node, Queue<Integer> queue, int min, int max){
            if (node == null){return;}
            if (min < node.key){
                keys(node.left, queue, min, max);
            }
            if (min <= node.key && max >= node.key){
                queue.enqueue(node.key);
            }
            if (max > node.key){
                keys(node.right, queue, min, max);
            }
        }
    }

    // code for this class would be similar with the one above, hence leave it
    private class STdouble{

    }
}
