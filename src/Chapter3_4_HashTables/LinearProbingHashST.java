package Chapter3_4_HashTables;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

@SuppressWarnings("unchecked")
public class LinearProbingHashST<Key, Value> {
    private static final int INIT_CAP = 4;
    private int N;
    private int M;
    private Key[] keys;
    private Value[] values;
    public LinearProbingHashST(){
        this(INIT_CAP);
    }
    public LinearProbingHashST(int cap){
        this.M = cap;
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }
    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % M;
    }
    private void resize(int cap){
        LinearProbingHashST<Key, Value> t = new LinearProbingHashST<>(cap);
        for (int i = 0; i < M; i++){
            if (keys[i] != null){
                t.put(keys[i], values[i]);
            }
        }
        keys = t.keys;
        values = t.values;
        M = t.M;
    }
    public void put(Key key, Value value){
        if (N >= M/2){resize(2 * M);}
        int i;
        for (i = hash(key); keys[i] != null; i = (i+1) % M){
            if (keys[i].equals(key)){
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;
        N++;
    }
    public Value get(Key key){
        for (int i = hash(key); keys[i] != null; i = (i+1)%M){
            if (keys[i].equals(key)){
                return values[i];
            }
        }
        return null;
    }
    public boolean contains(Key key){
        return get(key) != null;
    }
    public void delete(Key key){
        if (!contains(key)){
            return;
        }
        int i = hash(key);
        while (!key.equals(keys[i])){
            i = (i + 1) % M;
        }
        keys[i] = null;
        values[i] = null;
        i = (i + 1) % M;
        while (keys[i] != null){
            Key keyToRedo = keys[i];
            Value valueToRedo = values[i];
            //N--;
            put(keyToRedo, valueToRedo);
            i = (i + 1) % M;
        }
        N--;
        if (N > 0 && N == M/8){
            resize(M/2);
        }
    }
    public Iterable<Key> keys(){
        Queue<Key> queue = new Queue<>();
        //LinearProbingHashST<Key, Value> linearProbingHashST = new LinearProbingHashST<>(M);
        for (int i = 0; i < M; i++){
            if (keys[i] != null){
                queue.enqueue(keys[i]);
            }
        }
        return queue;
    }

    // Computes the average cost of a search hit in a table. assuming that each key in the table is equally likely to be sought
    // We shall do the search hit for each key, the way of which simulates the action of get and then add the number of
    // search hit of each key, then divide the number of key-value pair N
    public double averageCostOfSearchHit(){
        double averageCost = 0;
        for (Key key : keys()){
            int countSearchHit = 0;
            for (int i = hash(key); keys[i] != null; i = (i + 1) % M){
                if (keys[i].equals(key)){
                    countSearchHit++;
                }
                else {
                    countSearchHit++;
                }
            }
            averageCost += (1.0) * countSearchHit / N;

        }
        return averageCost;
    }

    public double averageCostOfSearchMiss(){
        double averageCost = 0;
        for (Key key : keys()){
            int countSearchMiss = 0;
            for (int i = hash(key); keys[i] != null; i = (i + 1) % M){
                if (keys[i].equals(key)){
                    countSearchMiss++;
                }
                else {
                    countSearchMiss++;
                }
            }
            // add one more while probing process reaches to the null entry
            countSearchMiss++;
            averageCost += (1.0) * countSearchMiss / N;
        }
        return averageCost;
    }

    public static void main(String[] args) {
        LinearProbingHashST<String, Integer> linearProbingHashST = new LinearProbingHashST<>();
        linearProbingHashST.put("S", 0);
        linearProbingHashST.put("E", 1);
        linearProbingHashST.put("A", 2);
        linearProbingHashST.put("R", 3);
        linearProbingHashST.put("C", 4);
        linearProbingHashST.put("H", 5);
        linearProbingHashST.put("E", 6);
        linearProbingHashST.put("X", 7);
        linearProbingHashST.put("A", 8);
        linearProbingHashST.put("M", 9);
        linearProbingHashST.put("P", 10);
        linearProbingHashST.put("L", 11);
        linearProbingHashST.put("E", 12);
        for (String s : linearProbingHashST.keys()){
            StdOut.println(s + " - " + linearProbingHashST.get(s));
        }
    }
}
