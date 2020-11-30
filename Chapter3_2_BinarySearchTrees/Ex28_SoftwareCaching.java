package Chapter3_2_BinarySearchTrees;
/*
*   Software caching. Modify BST to keep the most recently accessed node in an instance variable so that it can be
*   accessed in constant time if the next put() or get() uses the same key (see Ex3.1.25)
*
* */
public class Ex28_SoftwareCaching<Key extends Comparable<Key>, Value> {
    private class Node{
        private Key key;
        private Value value;
        private Node left, right;
        private int numberOfNodes;
        public Node(Key key, Value value, int numberOfNodes){
            this.key = key;
            this.value = value;
            this.numberOfNodes = numberOfNodes;
        }
    }
    private Node root;
    private Node cache;
    public int size(){
        return size(root);
    }
    private int size(Node node){
        if (node == null) return 0;
        return node.numberOfNodes;
    }
    public void put(Key key, Value value){
        if (cache != null && cache.key.compareTo(key) == 0){
            return;
        }
        root = put(root, key, value, 1);
    }
    private Node put(Node node, Key key, Value value, int n){
        if (node == null) {
            Node newNode = new Node(key, value, 1);
            cache = newNode;
            return newNode;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, value, 1);
        else if (cmp > 0) node.right = put(node.right, key, value, 1);
        else node.value = value;
        node.numberOfNodes = size(node.left) + size(node.right) + 1;
        return node;
    }
    public Value get(Key key){
        if (cache != null && cache.key.compareTo(key) == 0){
            return cache.value;
        }
        return get(root, key).value;
    }
    private Node get(Node node, Key key){
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return get(node.left, key);
        else if (cmp > 0) return get(node.right, key);
        else {
            cache = node;
            return node;
        }
    }
}
