package Chapter3_4_HashTables;
import edu.princeton.cs.algs4.Queue;

/*
*   Lazy delete for linear probing. Add to LinearProbingHashST a delete() method that deletes a key-value pair by setting the
*   value to null(but not removing the key)and later removing the pair from the table in resize(). Your primary challenge is to
*   decide when to call resize(). Note: You should overwrite the null value if a subsequent put() operation associates a new
*   value with the key. Make sure that your program takes into account the number of such tombstone items, as well as the number
*   of empty positions, in making the decision whether to expand or contract the table.
*
* */
@SuppressWarnings("unchecked")
public class Ex26_LazyDeleteLinearProbing<Key, Value> {
    private int M;
    private int N;
    private Key[] keys;
    private Value[] values;
    private final static int INIT_CAP = 4;
    public Ex26_LazyDeleteLinearProbing(){
        this(INIT_CAP);
    }
    public Ex26_LazyDeleteLinearProbing(int M){
        this.M = M;
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }
    public boolean isEmpty(){return N == 0;}
    public int size(){return N;}
    public boolean isContains(Key key){
        if (key == null){
            throw new IllegalArgumentException("Argument key cannot be empty");
        }
        return get(key) != null;
    }
    public Value get(Key key){
        if (key == null){throw new IllegalArgumentException("Argument key cannot be empty");}
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M){
            if (keys[i].equals(key)){
                return values[i];
            }
        }
        return null;
    }
    public int hash(Key key){
        return (key.hashCode() & 0x7ffffff) % M;
    }
    public void put(Key key, Value value){
        if (key == null){throw new IllegalArgumentException("Argument key cannot be empty");}
        if (value == null){
            delete(key);
            return;
        }
        if (N <= M / 2){
            resize(2 * M);
        }
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M){
            if (keys[i].equals(key)){
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;
        N++;
    }
    public void resize(int newCap){
        Ex26_LazyDeleteLinearProbing<Key, Value> temp = new Ex26_LazyDeleteLinearProbing<>(newCap);
        for (int i = 0; i < M; i++){
            if (isContains(keys[i])){
                if (values[i] == null){
                    keys[i] = null;
                    continue;
                }
                temp.put(keys[i], values[i]);
            }
        }
        keys = temp.keys;
        values = temp.values;
        M = temp.M;
    }
    public void delete(Key key){
        if (!isContains(key)){return;}
        int i = hash(key);
        while (!keys[i].equals(key)){
            i = (i + 1) % M;
        }
        //keys[i] = null;
        values[i] = null;
        N--;
        if (N > 0 && N <= M / 4){
            resize(M / 2);
        }
    }
    public Iterable<Key> keys(){
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < M; i++){
            if (keys[i] != null){
                queue.enqueue(keys[i]);
            }
        }
        return queue;
    }

    public static void main(String[] args) {

    }
}
