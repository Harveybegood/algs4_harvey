package Chapter3_5_Applications;


import edu.princeton.cs.algs4.Queue;

/*
*   Develop a SET implementation BinarySearchSET by starting with the code for BinarySearchST and eliminating all of the
*   code involving values.
*
* */
public class Ex03_BinarySearchSET {
    private class BinarySearchSET<Key extends Comparable<Key>>{
        private class Node{
            Node left;
            Node right;
            Key key;
            int numberOfNode;
            public Node(Key key, int numberOfNode){
                this.key = key;
                this.numberOfNode = numberOfNode;
            }
        }
        private Node root;
        public BinarySearchSET(){}
        public boolean isEmpty(){return root == null;}
        public int size(){return size(root);}
        public int size(Node node){
            if (node == null){
                return 0;
            }
            return node.numberOfNode;
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
        public void put(Key key){
            if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
            root = put(root, key);
        }
        private Node put(Node node, Key key){
            if (node == null){return new Node(key, 1);}
            if (key.compareTo(node.key) < 0){
                node.left = put(node.left, key);
            }
            else if (key.compareTo(node.key) > 0){
                node.right = put(node.right, key);
            }
            else {
                // We just return while the key to be inserted is already there.
                return node;
            }
            node.numberOfNode = size(node.left) + size(node.right) + 1;
            return node;
        }
        public Key min(){
            if (isEmpty()){return null;}
            return min(root).key;
        }
        private Node min(Node node){
            while (node.left != null){
                node = node.left;
            }
            return node;
        }
        public Key max(){
            if (isEmpty()){return null;}
            return max(root).key;
        }
        private Node max(Node node){
            while (node.right != null){
                node = node.right;
            }
            return node;
        }
        public Key floor(Key key){
            if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
            Node node = floor(root, key);
            return node.key;
        }
        private Node floor(Node node, Key key){
            if (node == null){return null;}
            if (key.compareTo(node.key) == 0){return node;}
            if (key.compareTo(node.key) < 0){
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

        public Key ceiling(Key key){
            if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
            Node node = ceiling(root, key);
            return node.key;
        }
        private Node ceiling(Node node, Key key){
            if (node == null){return null;}
            if (key.compareTo(node.key) == 0){return node;}
            if (key.compareTo(node.key) < 0){
                Node temp = floor(node.left, key);
                if (temp != null){
                    return temp;
                }
                else {
                    return node;
                }
            }
            return floor(node.right, key);
        }
        public Key select(int index){
            if (index < 0 || index > size()){throw new RuntimeException("Argument index is out of band");}
            return select(root, index).key;
        }
        private Node select(Node node, int index){
            if (node == null){return null;}
            int t = size(node.left);
            if (t > index){
                return select(node.left, index);
            }
            else if (t < index){
                return select(node.right, index);
            }
            else {
                return node;
            }
        }
        public int rank(Key key){
            return rank(root, key);
        }
        private int rank(Node node, Key key){
            if (node == null){return 0;}
            if (key.compareTo(node.key) < 0){
                return rank(node.left, key);
            }
            else if (key.compareTo(node.key) > 0){
                return 1 + size(node.left) + rank(node.right, key);
            }
            else {
                return size(node.left);
            }
        }
        public void deleteMin(){
            if (isEmpty()){throw new RuntimeException("Binary search tree is underflow");}
            root = deleteMin(root);
        }
        private Node deleteMin(Node node){
            if (node.left == null){
                return node.right;
            }
            node.left = deleteMin(node.left);
            node.numberOfNode = size(node.left) + size(node.right) + 1;
            return node;
        }
        public void deleteMax(){
            if (isEmpty()){throw new RuntimeException("Binary search tree is underflow");}
            root = deleteMax(root);
        }
        private Node deleteMax(Node node){
            if (node.right == null){
                return node.left;
            }
            node.right = deleteMax(node.right);
            node.numberOfNode = size(node.left) + size(node.left) + 1;
            return node;
        }
        public void delete(Key key){
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
                node = min(temp.right);
                node.right = deleteMin(temp.right);
                node.left = temp.left;
            }
            node.numberOfNode = size(node.left) + size(node.right) + 1;
            return node;
        }
        public Iterable<Key> keys(){
            return keys(min(), max());
        }
        private Iterable<Key> keys(Key lo, Key hi){
            Queue<Key> queue = new Queue<Key>();
            keys(root, queue, lo, hi);
            return queue;
        }
        private void keys(Node node, Queue<Key> queue, Key lo, Key hi){
            if (node == null){return;}
            int cmplo = lo.compareTo(node.key);
            int cmphi = hi.compareTo(node.key);
            if (cmplo < 0){keys(node.left, queue, lo, hi);}
            if (cmplo <= 0 && cmphi >= 0){
                queue.enqueue(node.key);
            }
            if (cmphi >0){keys(node.right, queue, lo, hi);}
        }
    }
}
