package Chapter3_5_Applications;


import edu.princeton.cs.algs4.IndexFibonacciMinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SequentialSearchST;

/*
*   Multisets. After referring to Exercise 3.5.2 and 2.5.3 and the previous exercise, develop APIs MultiHashSET and MultiSET
*   for multisets(sets that can have equal keys)and implementations SeparateChainingMultiSET and BinarySearchMultiSET for multisets
*   and ordered multisets, respectively.
*
* */
@SuppressWarnings("unchecked")
public class Ex18_Multisets {
    // API for unordered MultiHashSET
    private interface MultiHashSET<Key>{
        boolean contains(Key key);
        boolean isEmpty();
        int size();
        void add(Key key);
        void delete(Key key);
        Iterable<Key> keys();
    }
    private class SeparateChainingMultiSET<Key> implements MultiHashSET<Key>{
        private class SequentialSearchST<Key>{
            private class Node{
                Key key;
                Node next;
                public Node(Key key, Node next){
                    this.key = key;
                    this.next = next;
                }
            }
            private Node first;
            public int n;
            public boolean isEmpty(){return first == null || n == 0;}
            public int size(){return n;}
            public boolean contains(Key key){
                if (key == null){throw new IllegalStateException("Argument cannot be null");}
                for (Node node = first; node != null; node = node.next){
                    if (node.key.equals(key)){
                        return true;
                    }
                }
                return false;
            }
            public void add(Key key){
                if (key == null){throw new IllegalStateException("Argument cannot be null");}
                first = new Node(key, first);
                n++;
            }
            public void delete(Key key){
                for (Node node = first; node != null; node = node.next){
                    if (node.key.equals(key)){
                        node = node.next;
                        n--;
                        return;
                    }
                }
            }
            public Iterable<Key> keys(){
                Queue<Key> queue = new Queue<>();
                for (Node node = first; node != null; node = node.next){
                    queue.enqueue(node.key);
                }
                return queue;
            }
        }
        private int M; // the size of symbol table
        private int N; // the size of items;
        private SequentialSearchST<Key>[] st;
        private int lgM;
        private final int[] PRIMES = {
                1, 1, 3, 7, 13, 31, 61, 127, 251, 509, 1021, 2039, 4093, 8191, 16381,
                32749, 65521, 131071, 262139, 524287, 1048573, 2097143, 4194301,
                8388593, 16777213, 33554393, 67108859, 134217689, 268435399,
                536870909, 1073741789, 2147483647
        };
        public SeparateChainingMultiSET(){
            this(10);
        }
        public SeparateChainingMultiSET(int M){
            this.M = M;
            st = (SequentialSearchST<Key>[]) new SequentialSearchST[M];
            for (int i = 0; i < M; i++){
                st[i] = (SequentialSearchST<Key>) new SequentialSearchST<>();
            }
            lgM = (int) (Math.log(M) / Math.log(2));
        }

        public boolean isEmpty(){return N == 0;}
        public int size(){return N;}
        public boolean contains(Key key){
            if (key == null){throw new IllegalStateException("Argument cannot be null");}
            return st[hash(key)].contains(key);
        }
        public int hash(Key key){
            int hash = key.hashCode() & 0x7fffffff;
            if (lgM < 26){
                hash = hash % PRIMES[lgM + 5];
            }
            return hash % M;
        }
        public void add(Key key){
            if (key == null){throw new IllegalStateException("Argument cannot be null");}
            if (N >= M / 2){
                resize(2 * M);
                lgM++;
            }
            st[hash(key)].add(key);
            N++;
        }
        public void delete(Key key){
            if (key == null){throw new IllegalStateException("Argument cannot be null");}
            if (isEmpty()){throw new RuntimeException("Symbol table underflow");}
            while (contains(key)){
                st[hash(key)].delete(key);
                N--;
            }
            if (N > 0 && N <= M / 4){
                resize(M / 2);
                lgM--;
            }
        }
        public void resize(int cap){
            SeparateChainingMultiSET<Key> temp = new SeparateChainingMultiSET<>(cap);
            for (Key key : keys()){
                temp.add(key);
            }
            this.M = temp.M;
            this.st = temp.st;
        }
        public Iterable<Key> keys(){
            Queue<Key> queue = new Queue<>();
            for (int i = 0; i < M; i++){
                for (Key key : st[i].keys()){
                    if (key != null){
                        queue.enqueue(key);
                    }
                }
            }
            return queue;
        }
    }

    // API for ordered MultiSET
    private interface MultiSET<Key>{
        boolean isEmpty();
        int size();
        boolean contains(Key key);
        void add();
        Key min();
        Key max();
        Key deleteMin();
        Key deleteMax();
        Key floor();
        Key ceiling();
        Key select(int i);
        int rank(Key key);
        int size(Key lo, Key hi);
        void delete(Key key);
        Iterable<Key> keys();
        Iterable<Key> keys(Key lo, Key hi);
        String toString();
    }
    private class BinarySearchMultiSET<Key> implements MultiSET<Key>{

    }
}
