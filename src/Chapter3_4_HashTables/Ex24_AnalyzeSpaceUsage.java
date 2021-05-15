package Chapter3_4_HashTables;
/*
*   Analyze the space usage of separate chaining, linear probing, and BSTs for double keys. Present your results in a table
*   like the one on page 476.
*
* */
@SuppressWarnings("unchecked")
public class Ex24_AnalyzeSpaceUsage {
    // space usage of separate chaining
    public class SeparateChaining<Key, Value>{
        // separate chaining extents DAT sequentialSearchST
        public class SequentialSearchST<Key, Value>{
            private class Node{
                Key key;
                Value value;
                Node next;
                public Node(Key key, Value value, Node next){
                    this.key = key;
                    this.value = value;
                    this.next = next;
                }
            }
            private Node first;
            //private int numberOfNodes;
        }
        private int M;
        private int n;
        private SequentialSearchST<Key, Value>[] st;
        public SeparateChaining(){
            this(97);
        }
        public SeparateChaining(int M){
            this.M = M;
            st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
            for (int i = 0; i < M; i++){
                st[i] = new SequentialSearchST<>();
            }
        }
    }
    /*
    *   SeparateChaining: object overhead(16)
    *                     + st array object overhead(16)
    *                     + st array length(4)
    *                     + padding(4)
    *                     + M * reference to st array(8)
    *
    *              + M objects to SequentialSearchST: M * (object overhead(16)
    *                                        + reference to Node first(8))
    *
    *                       + N * ((object Node overhead(16)
    *                                        + inner class(8)
    *                                        + reference to double(8)
    *                                        + reference to value(8)
    *                                        + reference to Node next(8))
    *   + int M(4)
    *   + int n(4)
    *
    *   =
    *
    * */
    // space usage of linear probing
    public class LinearProbing<Key, Value>{
        private int M;
        private int n;
        Key[] keys = (Key[]) new Object[M];
        Value[] values = (Value[]) new Object[M];
        public LinearProbing(int M){
            for (int i = 0; i < M; i++){

                keys[i] = null;
                values[i] = null;
            }
        }
    }
    /*
    *   LinearProbing objects: 16
    *       + N double keys[]: array object overhead(16)
    *                         + record array length(4)
    *                         + padding(4)
    *                         + n double keys(8 * n)
    *                         = 24 + 8*n
    *
    *       + N values[]: 24 + 8 * n
    *   + int M(4)
    *   + int n(4)
    *
    *   =
    *
    * */

    // space usage of BST
    public class BST<Key extends Comparable<Key>, Value>{
        private class Node{
            Node left;
            Node right;
            Key key;
            Value value;
            int numberOfSubTree;
            public Node(Key key, Value value, int numberOfSubTree){
                this.key = key;
                this.value = value;
                this.numberOfSubTree = numberOfSubTree;
            }
        }
        private Node root;
        //private int n;

    }
    /*
    *   BST objects: 16
    *   + Node object overhead(8)
    *       + reference to node left(8)
    *       + reference to node right(8)
    *       + reference to key(double)(8)
    *       + reference to value (8)
    *       + int numberOfSubTree(4)
    *       + padding (4)
    *
    *   + reference to Node root(8)
    *
    *   =
    *
    * */
}
