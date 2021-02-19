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
    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % M;
    }
    private void resize(){
        
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
}
