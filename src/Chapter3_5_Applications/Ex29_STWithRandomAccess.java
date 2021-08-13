package Chapter3_5_Applications;

import Chapter1_3_BagsQueuesStacks.Ex35_RandomQueue;
import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Symbol table with random access. Create a data type that supports inserting a key-value pair, searching for a key and returning
*   the associated value, and deleting and returning a random key.
*
*       Hint: Combine a symbol table and a randomized queue.
* */
public class Ex29_STWithRandomAccess<Key, Value> {
    private LinearProbingHashST<Key, Value> st;
    private Ex35_RandomQueue<Key> randomQueue;
    public Ex29_STWithRandomAccess(){
        st = new LinearProbingHashST<>();
        randomQueue = new Ex35_RandomQueue<>();
    }
    public boolean isEmpty(){return st.isEmpty();}
    public boolean contains(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        return st.contains(key);
    }
    public int size(){return st.size();}
    public void add(Key key, Value value){
        st.put(key, value);
        randomQueue.enqueue(key);
    }
    public Value get(Key key){
        return st.get(key);
    }
    public Key deleteAndReturnRandomKey(){
        if (isEmpty()){throw new RuntimeException("Symbol table under flow");}
        Key randomKey = randomQueue.dequeue();
        st.delete(randomKey);
        return randomKey;
    }

    public static void main(String[] args) {
        Ex29_STWithRandomAccess<Integer, Integer> stWithRandomAccess = new Ex29_STWithRandomAccess<>();
        for (int i = 0; i < 10; i++){
            int randomValue = StdRandom.uniform(200);
            stWithRandomAccess.add(randomValue, randomValue);
        }
        for (Integer key : stWithRandomAccess.st.keys()){
            StdOut.println(key + "  " + stWithRandomAccess.get(key));
        }
        StdOut.println("deleting and returning a random key");
        StdOut.println("returning key: " + stWithRandomAccess.deleteAndReturnRandomKey());
        StdOut.println("Key-value pair after deleting a random key");
        for (Integer key : stWithRandomAccess.st.keys()){
            StdOut.println(key + "  " + stWithRandomAccess.get(key));
        }
    }
}

