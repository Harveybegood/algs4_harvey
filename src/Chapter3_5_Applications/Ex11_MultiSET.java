package Chapter3_5_Applications;
/*
*   Develop a MultiSET class that is like SET, but allows equal keys and thus implements a mathematical multiset.
*
* */
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

@SuppressWarnings("ALL")
public class Ex11_MultiSET <Key>{
    private Key[] keys;
    private int M;
    private int N;
    private final static int INIT_CAP = 4;
    public Ex11_MultiSET(){
        this(INIT_CAP);
    }
    public Ex11_MultiSET(int cap){
        this.M = cap;
        keys = (Key[]) new Object[M];
    }
    public boolean isEmpty(){return N == 0;}
    public int size(){return N;}
    public int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % M;
    }
    public boolean contains(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M){
            if (key.equals(keys[i])){
                return true;
            }
        }
        return false;
    }
    public void add(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        int i = hash(key);
        while (keys[i] != null){
            i = (i + 1) % M;
        }
        if (N >= M / 2){resize(2 * M);}
        keys[i] = key;
        N++;
    }
    public void delete(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        int i = hash(key);
        while (key != keys[i]){
            i = (i + 1) % M;
        }
        while (key.equals(keys[i])){
            keys[i] = null;
            N--;
            i = (i + 1) % M;
        }
        //i = (i + 1) % M;
        while (keys[i] != null){
            Key keyToRedo = keys[i];
            keys[i] = null;
            N--;
            add(keyToRedo);
            i = (i + 1) % M;
        }
        if (N > 0 && N <= M / 4){resize(M / 2);}
    }
    public void resize(int cap){
        Ex11_MultiSET<Key> temp = new Ex11_MultiSET<>(cap);
        for (int i = 0; i < M; i++){
            if (keys[i] != null){
                temp.add(keys[i]);
            }
        }
        this.M = temp.M;
        this.keys = temp.keys;
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
        Ex11_MultiSET<String> multiSET = new Ex11_MultiSET<>();
        multiSET.add("a");
        multiSET.add("b");
        multiSET.add("b");
        multiSET.add("b");
        multiSET.add("c");
        multiSET.add("d");
        multiSET.add("e");
        multiSET.add("f");
        multiSET.add("g");
        StdOut.println("All keys in the set");
        for (String s : multiSET.keys()){
            StdOut.print(s + " ");
        }
        StdOut.println("\nAll keys in the set");
        multiSET.delete("b");
        for (String s : multiSET.keys()){
            StdOut.print(s + " ");
        }
        StdOut.println("\nThe size of set: " + multiSET.size() + " expected 6");
    }
}
