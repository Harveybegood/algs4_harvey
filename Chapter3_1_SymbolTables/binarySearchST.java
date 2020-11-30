package Chapter3_1_SymbolTables;


import edu.princeton.cs.algs4.Queue;

@SuppressWarnings("unchecked")
public class binarySearchST<Key extends Comparable, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;
    public binarySearchST(){}
    public binarySearchST(int cap){
        keys = (Key[]) new Comparable[cap];
        vals = (Value[]) new Object[cap];
    }
    public int size(){return N;}
    public boolean contains(Key key){return get(key) != null;}
    public boolean isEmpty(){return N == 0;}
    public Value get(Key key){
        if (key == null) throw new IllegalArgumentException("Argument for get() is null");
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0)
            return vals[i];
        else
            return null;
    }
    public int rank(Key key){
        if (key == null) throw new IllegalArgumentException("Argument for rank() is null");
        int lo = 0, hi = N - 1;
        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0)  hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }
    public void put(Key key, Value val){
        if (key == null) throw new IllegalArgumentException("Argument for put() is null");
        if (val == null){
            delete(key);
            return;
        }
        int i = rank(key);
        if (N == keys.length){
            resize(keys.length * 2);
        }
        if (i < N && keys[i].compareTo(key) == 0){
            vals[i] = val;
            return;
        }
        for (int j = N; j > i; j--){
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }
    public void resize(int newSize){
        Key[] tempKey = (Key[]) new Comparable[newSize];
        Value[] tempVal = (Value[]) new Object[newSize];
        for (int i = 0; i < N; i++){
            tempKey[i] = keys[i];
            tempVal[i] = vals[i];
        }
        keys = tempKey;
        vals = tempVal;
    }
    public void delete(Key key){
        if (key == null) throw new IllegalArgumentException("Argument for delete() is null");
        if (isEmpty()) return;
        int i = rank(key);
        for (int j = i; j < N; j++){
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }
        keys[N-1] = null;
        vals[N-1] = null;
        N--;
        if (N > 1 && N == keys.length / 4){
            resize(keys.length / 2);
        }
    }
    public Key min(){return keys[0];}
    public Key max(){return keys[N-1];}
    public Key select(int k){return keys[k];}
    public Key ceiling(Key key){
        int i = rank(key);
        return keys[i];
    }

    public Key floor(Key key){
        int i = rank(key);
        return keys[i-1];
    }

    public Iterable<Key> keys(Key lo, Key hi){
        Queue<Key> q = new Queue<>();
        for (int i = rank(lo); i < rank(hi); i++){
            q.enqueue(keys[i]);
        }
        if (contains(hi)){
            q.enqueue(keys[rank(hi)]);
        }
        return q;
    }

    public Iterable<Key> keys(){
       return keys(min(), max());
    }

    public static void main(String[] args) {
        /*binarySearchST<String, Integer> binarySearchST = new binarySearchST(100);*/
    }
}
