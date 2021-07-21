package Chapter3_5_Applications;


import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.RedBlackBST;

import java.security.PublicKey;

/*
*   Implement SET and HashSET as "wrapper class" clients of ST and HashST, respectively(provide dummy values and ignore them).
*
* */
public class Ex01_WrapperClass {
    private class SET<Key extends Comparable<Key>, Value>{
        private RedBlackBST<Key, Value> set;
        public SET(){
            set = new RedBlackBST<>();
        }
        public boolean isEmpty(){return set.isEmpty();}
        public int size(){return set.size();}
        public boolean contains(Key key){
            if (key == null){throw new IllegalStateException("Argument cannot be null");}
            return set.contains(key);
        }
        public void add(Key key, Value value){
            if (key == null){throw new IllegalStateException("Argument cannot be null");}
            set.put(key, value);
        }
        public Value get(Key key){
            return set.get(key);
        }
        public void delete(Key key){
            if (key == null){throw new IllegalStateException("Argument cannot be null");}
            if (isEmpty()){return;}
            set.delete(key);
        }
        public int size(Key low, Key high){
            if (low.compareTo(set.min()) < 0){
                throw new IllegalStateException("Argument low underflow");
            }
            if (high.compareTo(set.max()) > 0){
                throw new IllegalStateException("Argument high overflow");
            }
            return set.size(low, high);
        }
        public Key min(){
            return set.min();
        }
        public Key max(){
            return set.max();
        }
        public Key floor(Key key){
            if (key == null){throw new IllegalStateException("Argument cannot null");}
            return set.floor(key);
        }
        public Key ceiling(Key key){
            if (key == null){throw new IllegalStateException("Argument cannot be null");}
            return set.ceiling(key);
        }
        public void deleteMin(){
            set.deleteMin();
        }
        public void deleteMax(){
            set.deleteMax();
        }
        public int rank(Key key){
            if (key == null){throw new IllegalStateException("Argument cannot be null");}
            return set.rank(key);
        }
        public Key select(int index){
            if (index > size() || index < 0){throw new IllegalStateException("Argument index is out of bound");}
            return set.select(index);
        }
        public Iterable<Key> keys(){
            return set.keys(min(), max());
        }
        private Iterable<Key> keys(Key low, Key high){
            return set.keys(low, high);
        }
        //public String toString(){}
    }
    private class HashSET<Key, Value>{
        private LinearProbingHashST<Key, Value> hashST;
        public HashSET(){
            hashST = new LinearProbingHashST<>();
        }
        public boolean isEmpty(){return hashST.isEmpty();}
        public int size(){return hashST.size();}
        public boolean contains(Key key){
            if (key == null){throw new IllegalStateException("Argument cannot be null");}
            return hashST.contains(key);
        }

        public void put(Key key, Value value){
            if (key == null || value == null){throw new IllegalStateException("Argument cannot be null");}
            hashST.put(key, value);
        }
        public Value get(Key key){
            if (key == null){throw new IllegalStateException("Argument key cannot be null");}
            return hashST.get(key);
        }
        public void delete(Key key){
            if (key == null){throw new IllegalStateException("Argument cannot be null");}
            hashST.delete(key);
        }
        public Iterable<Key> keys(){
            return hashST.keys();
        }
        //public String toString(){}

    }
}
