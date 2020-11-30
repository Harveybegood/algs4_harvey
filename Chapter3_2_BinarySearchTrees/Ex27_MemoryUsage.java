package Chapter3_2_BinarySearchTrees;
/*
*   Memory usage. Compare the memory usage of BST with the memory usage of BinarySearchST and SequentialSearchST for
*   N key-value pairs, under the assumptions described in Section1.4(see Ex3.1.21). Do not count the memory for the keys
*   and values themselves, but do count references to them. Then draw a diagram that depicts the precise memory usage of
*   a BST with String keys and Integer values(such as the ones built by FrequencyCounter), and then estimate the memory
*   usage(in bytes)for the BST built when FrequencyCounter uses BST for Tale of Two Cities.
*
* */
public class Ex27_MemoryUsage {
    /*
    *       BST,
    *               Inner Node as a reference to object: 16 bytes.
    *               Node left as a reference to object: 8 bytes.
    *               Node right as a reference to object: 8 bytes.
    *               Key as a reference to object: 8 bytes.
    *               Value as a reference to object: 8 bytes.
    *               int n as a counter to count the number of nodes: 4 bytes.
    *               Extra overhead for a reference to the enclosing Node: 8 bytes.
    *               Padding:
    *               Note: there will be N key-value pairs to be created.
    *
    *               BST object: 16 bytes.
    *               Root as a reference to object: 8 bytes.
    *
    *               Then, we come to the total of memory usage:
    *               = 16 + 8 + N * (16 + 8 + 8 + 8 + 8 + 8 + 4 + 4) = 24 + 64*N
    *
    *
    *
    *
    *       BinarySearchST,
    *               A reference to Key array: 8 bytes.
    *               A reference to Value array: 8 bytes.
    *               int n to express the length of array: 4 bytes.
    *               Padding:
    *               Object overhead: 16 bytes.
    *
    *               Key[]:
    *                   Object overhead: 16 bytes.
    *                   int n to express the length of array: 4 bytes.
    *                   Padding:
    *               Value[]:
    *                   Object overhead: 16 bytes.
    *                   int n to express the length of array; 4 bytes.
    *                   Padding:
    *
    *                Total = 8 + 8 + 4 + 4 + 16 + 16 + 4 + 4 + N * (8) * 2 + 16 + 4 + 4 = 88 + 16 * N
    *
    *
    *       SequentialSearchST,
    *               Object overhead: 16 bytes.
    *               Reference to the enclosing instance: 8 bytes.
    *               A reference to Key: 8 bytes.
    *               A reference to Value: 8 bytes.
    *               A reference to next: 8 bytes.
    *               Instance Variable first: 8 bytes.
    *               length of object: 4 bytes.
    *               Object overhead: 16 bytes.
    *               Padding:
    *               Total = 16 + 4 + 8 + 4 + N * (16 + 8 + 8 + 8 + 8) = 32 + 48 * N
    *
    *       Draw a diagram, BST(Key<String>, Value<Integer>)
    *               replace the Key and value with String and Integer respectively.
    *               = 16 + 8 + 100(...).
    *               = 16 + 8 + 1000(...).
    *               Memory usage .
    *                            .
    *                            .
    *                            .
    *                            .
    *                            .
    *                            .
    *                            .
    *                            - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    *                                  100           1000                  10000                 key/value
    *       BST built when FrequencyCounter uses BST for Tale of Two Cities,
    *               distinct words: 10,679(refer to text)
    *               (64 + 2 * N) * 10679 =
    *               N = 6,
    * */
    public class BST<Key extends Comparable<Key>, Value>{
        private class Node{
            private Node left;
            private Node right;
            private Key key;
            private Value value;
            private int n;
            public Node(Key key, Value value, int n){
                this.key = key;
                this.value = value;
                this.n = n;
            }
        }
        private Node root;
    }
    @SuppressWarnings("unchecked")
    public class BinarySearchST<Key extends Comparable<Key>, Value>{
        private Key[] key;
        private Value[] value;
        private int n;
        public BinarySearchST(int cap){
            key = (Key[]) new Comparable[cap];
            value = (Value[]) new Object[cap];
        }
    }
    public class SequentialSearchST<Key, Value>{
        private class Node{
            private Key key;
            private Value value;
            private Node next;
            public Node(Node next, Key key, Value  value){
                this.next = next;
                this.key = key;
                this.value = value;
            }
        }
        private Node first;
        private int n;
    }
}
