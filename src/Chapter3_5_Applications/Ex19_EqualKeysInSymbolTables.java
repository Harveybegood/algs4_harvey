package Chapter3_5_Applications;
import Chapter2_5_Applications.Ex19_KendallTauDistance;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/*
*   Equal keys in symbol tables. Consider the API MultiST(unordered and ordered)to be the same as our symbol-table APIs
*   defined on page 363 and page 366, but with equal keys allowed, so that the semantics of get() is to return any value
*   associated with the given key, and we add a new method
*           Iterable<Value> getAll(Key key)
*   that returns all values associated with the given key. Using our code for SeparateChainingST and BinarySearchST as
*   a starting point, develop implementations SeparateChainingMultiST and BinarySearchMultiST for these APIs.
*
* */
@SuppressWarnings("unchecked")
public class Ex19_EqualKeysInSymbolTables {
    // leave it, since a similar one already written in previous algorithm
    private class SeparateChainingMultiST<Key, Value>{}

    // comparing to Ex09_BSTWithDuplicateKeys in this section, add a new method that returns all values
    private class BinarySearchMultiST<Key extends Comparable<Key>, Value>{
        private class Node{
            Key key;
            Value value;
            Node left, right;
            int numberOfNodes;
            public Node(Key key, Value value, int numberOfNodes){
                this.key = key;
                this.value = value;
                this.numberOfNodes = numberOfNodes;
            }
        }
        private Node root;
        //private int n;
        public BinarySearchMultiST(){}
        public boolean isEmpty(){return root == null;}
        public boolean contains(Key key){
            if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
            return get(key) != null;
        }
        /*public int size(){return n;}*/
        // this method get() will only return a single value if it found, as getAll() implements to return all
        // associated values
        public Value get(Key key){
            if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
            StringBuilder string = new StringBuilder();
            get(root, key, string);
            return (Value) string.toString();
        }
        private void get(Node node, Key key, StringBuilder string){
            if (node == null){return;}
            if (key.compareTo(node.key) < 0){
                get(node.left, key, string);
            }
            else if (key.compareTo(node.key) > 0){
                get(node.right, key, string);
            }
            else {
                // bugs here, the duplicated keys which will be associated with all possible values
                string.append(node.value).append(" ");
                if (node.right != null && node.key.compareTo(node.right.key) == 0){
                    get(node.right, key, string);
                }
            }
        }
        public Iterable<Value> getAll(Key key){
            Queue<Value> queue = new Queue<>();
            Node node = root;
            while (node != null){
                if (key.compareTo(node.key) < 0){
                    node = node.left;
                }
                else if (key.compareTo(node.key) > 0){
                    node = node.right;
                }
                else {
                    queue.enqueue(node.value);
                    if (node.right != null && contains(node.right.key)){
                        node = node.right;
                    }
                }
            }
            return queue;
        }
        public void put(Key key, Value value){
            if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
            if (value == null){throw new IllegalArgumentException("Argument cannot be null");}
            root = put(root, key, value);
        }
        private Node put(Node node, Key key, Value value){
            if (node == null){return new Node(key, value, 1);}
            if (key.compareTo(node.key) < 0){
                node.left = put(node.left, key, value);
            }
            else {
                if (value.equals(node.value)){
                    return null;
                }
                node.right = put(node.right, key, value);
            }
            node.numberOfNodes = size(node.left) + size(node.right) + 1;
            return node;
        }
        public int size(){
            return size(root);
        }
        private int size(Node node){
            if (node == null){return 0;}
            return node.numberOfNodes;
        }
        public Key floor(Key key){
            if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
            Node node = floor(root, key);
            if (node == null){return null;}
            return node.key;
        }
        private Node floor(Node node, Key key){
            if (node == null){return null;}
            if (node.key == key){return node;}
            if (key.compareTo(node.key) < 0){
                return floor(node.left, key);
            }else {
                Node temp = floor(node.right, key);
                if (temp != null){
                    return temp;
                }
                else {
                    return node;
                }
            }
        }
        public Key ceiling(Key key){
            if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
            Node node = ceiling(root, key);
            if (node == null){return null;}
            return node.key;
        }
        private Node ceiling(Node node, Key key){
            if (node == null){return null;}
            if (node.key == key){return node;}
            if (key.compareTo(node.key) < 0){
                Node temp = floor(node.left, key);
                if (temp != null) {
                    return temp;
                }
                else {
                    return node;
                }
            }
            else {
                return ceiling(node.right, key);
            }
        }
        public Key select(int rank){
            if (rank < 0 || rank > root.numberOfNodes){
                throw new IllegalArgumentException("Argument cannot be out of scope");
            }
            return select(root, rank).key;
        }
        private Node select(Node node, int rank){
            if (node == null){return null;}
            int t = size(node.left);
            if (t > rank){
                return select(node.left, rank);
            }
            else if (t < rank){
                return select(node.right, rank - t - 1);
            }
            else {
                return node;
            }
        }
        public int rank(Key key){
            if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
            return rank(root, key);
        }
        private int rank(Node node, Key key){
            if (node == null){return 0;}
            if (key.compareTo(node.key) < 0){
                return rank(node.left, key);
            }
            else if (key.compareTo(node.key) > 0){
                return size(node.left) + 1 + rank(node.right, key);
            }
            else {
                return size(node.left);
            }
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
        // bugs while deleting duplicated keys
        public void deleteMin(){
            if (isEmpty()){throw new RuntimeException("Symbol table underflow");}
            root = deleteMin(root);
        }
        private Node deleteMin(Node node){
            if (node.left == null){
                return node.right;
            }
            node.left = deleteMin(node.left);
            node.numberOfNodes = size(node.left) + size(node.right) + 1;
            return node;
        }
        // bugs while deleting duplicated keys
        public void deleteMax(){
            if (isEmpty()){throw new RuntimeException("Symbol table underflow");}
            root = deleteMax(root);
        }
        private Node deleteMax(Node node){
            if (node.right == null){
                return node.left;
            }
            node.right = deleteMax(node.right);
            node.numberOfNodes = size(node.left) + size(node.right) + 1;
            return node;
        }
        // bugs while deleting duplicated keys
        public void delete(Key key){
            if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
            if (isEmpty()){throw new RuntimeException("Symbol table underflow");}
            root = delete(root, key);
        }
        private Node delete(Node node, Key key){
            if (node == null){return null;}
            if (key.compareTo(node.key) < 0){
                node.left = delete(node.left, key);
            }
            else if (key.compareTo(node.key) > 0){
                node.right = delete(node.right, key);
            }
            else {
                if (node.right == null){return node.left;}
                if (node.left == null){return node.right;}
                Node temp = node;
                node = min(node.right);
                node.right = deleteMin(node.right);
                node.left = temp.left;
            }
            node.numberOfNodes = size(node.left) + size(node.right) + 1;
            return node;
        }
        public int size(Key lo, Key hi){
            if (lo.compareTo(min()) < 0 || hi.compareTo(max()) > 0){throw new IllegalArgumentException("Argument out of scope");}
            if (hi.compareTo(lo) < 0){
                return 0;
            }
            else if (contains(hi)){
                return rank(hi) - rank(lo) + 1;
            }
            else {
                return rank(hi) - rank(lo);
            }
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
            if (hi.compareTo(node.key) > 0){
                keys(node.right, queue, lo, hi);
            }
        }

    }

    public static void main(String[] args) {
        Ex19_EqualKeysInSymbolTables equalKeysInSymbolTables = new Ex19_EqualKeysInSymbolTables();
        BinarySearchMultiST<String, Integer> binarySearchMultiST = equalKeysInSymbolTables.new BinarySearchMultiST<>();
        binarySearchMultiST.put("S", 20);
        binarySearchMultiST.put("E", 10);
        binarySearchMultiST.put("E", 50);
        binarySearchMultiST.put("E", 60);
        binarySearchMultiST.put("X", 30);
        binarySearchMultiST.put("A", 3);
        binarySearchMultiST.put("M", 16);
        binarySearchMultiST.put("H", 12);
        binarySearchMultiST.put("R", 18);
        for (String s : binarySearchMultiST.keys()){
            StdOut.println(s + " " + binarySearchMultiST.get(s));
        }
        StdOut.println(binarySearchMultiST.getAll("E"));
        binarySearchMultiST.delete("E");

    }

}
