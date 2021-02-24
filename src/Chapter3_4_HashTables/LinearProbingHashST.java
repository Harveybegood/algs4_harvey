package Chapter3_4_HashTables;

@SuppressWarnings("unchecked")
public class LinearProbingHashST<Key, Value> {
    private int N;
    private int M = 16;
    private Key[] keys;
    private Value[] values;
    public LinearProbingHashST(){
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }
    public LinearProbingHashST(int cap){
        LinearProbingHashST<Key, Value> linearProbingHashST;
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
        if (N >= M/2){resize();}
        int i;
        for (i = hash(key); keys[i] != null; i = (i+1)%M){
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
            i = (i + 1)%M;
        }
        keys[i] = null;
        values[i] = null;
        i = (i + 1)%M;
        while (keys[i] != null){
            Key keyToRedo = keys[i];
            Value valueToRedo = values[i];
            N--;
            put(keyToRedo, valueToRedo);
            i = (i + 1)%M;
        }
        N--;
        if (N > 0 && N == M/8){
            resize();
        }
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

    }
}
