package Chapter3_5_Applications;
/*
*   Develop classes HashSETint and HashSETdouble for maintaining sets of keys of primitive int and double types, respectively.
*   (Eliminate code involving values in your solution to Ex3.5.4)
*
* */
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

@SuppressWarnings("unchecked")
public class Ex06_HashSETintdouble {
    private static class HashSETint{
        private static final int INIT_CAP = 5;
        private static final int EMPTY_KEY = Integer.MAX_VALUE;
        private int M;
        private int N;
        private int[] keys;
        //private Value[] values;
        public HashSETint(){
            this(INIT_CAP);
        }
        public HashSETint(int M){
            this.M = M;
            keys = new int[M];
            for (int i = 0; i < M; i++){
                keys[i] = EMPTY_KEY;
            }
        }
        public int hash(int key){
            return (Integer.valueOf(key).hashCode() & 0x7fffffff) % M;
        }
        public boolean isEmpty(){return N == 0;}
        public int size(){return N;}
        public boolean contains(int key){
            for (int i = hash(key); keys[i] != EMPTY_KEY; i = (i + 1) % M){
                if (key == keys[i]){
                    return true;
                }
            }
            return false;
        }
        public void put(int key){
            int i;
            for (i = hash(key); keys[i] != EMPTY_KEY; i = (i + 1) % M){
                if (key == keys[i]){
                    return;
                }
            }
            if (N == M / 2){
                resize(2 * M);
            }
            // if program comes to here, it means the key that is not in the table, then using the statement of
            // whether or not the new key contained is not necessary.
           /* if (!contains(key)){
                N++;
            }*/
            keys[i] = key;
            N++;
        }
        public void resize(int cap){
            HashSETint temp = new HashSETint(cap);
            for (int i = 0; i < M; i++){
                if (keys[i] != EMPTY_KEY){
                    temp.put(keys[i]);
                }
            }
            this.keys = temp.keys;
            this.M = temp.M;
        }
        public void delete(int key){
            if (!contains(key)){return;}
            int i = hash(key);
            while (keys[i] != key){
                i = (i + 1) % M;
            }
            keys[i] = EMPTY_KEY;
            i = (i + 1) % M;
            while (keys[i] != EMPTY_KEY){
                int keyToRedo = keys[i];
                keys[i] = EMPTY_KEY;
                N--;
                put(keyToRedo);
                i = (i + 1) % M;
            }
            N--;
            if (N > 0 && N <= M / 4){
                resize(M / 2);
            }
        }
        public Iterable<Integer> keys(){
            Queue<Integer> queue = new Queue<>();
            for (Integer i : keys){
                if (i != EMPTY_KEY){
                    queue.enqueue(i);
                }
            }
            return queue;
        }
    }

    public static void main(String[] args) {
        HashSETint hashSETint = new HashSETint();
        hashSETint.put(33);
        hashSETint.put(3);
        hashSETint.put(53);
        hashSETint.put(133);
        hashSETint.put(303);
        hashSETint.put(330);
        hashSETint.put(233);
        hashSETint.put(83);
        hashSETint.put(30);
        hashSETint.put(73);
        hashSETint.put(343);
        StdOut.println("All keys in the symbol table");
        for (Integer i : hashSETint.keys()){
            StdOut.println(i);
        }
        StdOut.println("The size of Symbol table: " + hashSETint.size() + " expected 11");
        StdOut.println("If containing 3: " + hashSETint.contains(3) + " expected true");
        hashSETint.delete(3);
        StdOut.println("If containing 3: " + hashSETint.contains(3) + " expected false");
        StdOut.println("All keys in the symbol table after deleting 3");
        for (Integer i : hashSETint.keys()){
            StdOut.println(i);
        }
        StdOut.println("The size of symbol table: 10" + hashSETint.size() + " expected 10");
    }


    // this class to write will be similar to the one above
    private class HashSETdouble{

    }
}
