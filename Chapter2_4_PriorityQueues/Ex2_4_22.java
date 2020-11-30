package Chapter2_4_PriorityQueues;

import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

/*
*   Array resizing. Add array resizing to MaxPQ, prove that bounds like those proposition Q for array access,
*   in an amortized sense.
*   Proposition Q: In an N-key priority queue, the heap algorithms require no more than 1 + lgN compares for insert and
*   no more than 2lgN compares for remove the maximum.
*   Proof: By Proposition P, both operations involved moving along a path between the root and the bottom of the heap
*   whose number of linkedlists is on more than lgN. The remove the maximum operation requires two compares for each node on
*   the path (except at the bottom): one to find the child with the larger key, the other to decide whether that child 
*   needs to be promoted.
*   Proposition Q: In an index priority queue of size N, the number of compares required is proportional to at most
*   logN for insert, change priority, delete, and remove the minimum.
*   
*       insert() -> lgN, change() -> lgN, delete() -> lgN, removeMini() -> lgN
* */
@SuppressWarnings("unchecked")
public class Ex2_4_22 {
    public static class MaxPQWithArrayResizing<Key extends Comparable<Key>>{
        public enum Type{
            insert, delete, remove
        }
        private int arrayAccessInsert = 0;
        private int arrayAccessChange = 0;
        private int arrayAccessDelete = 0;
        private int arrayAccessRemoveMax = 0;
        private int totalArrayAccess = 0;
        private int N;
        private int[] pq; // binary heap using 1-based indexing
        private int[] qp; // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
        private Key[] keys;
        public MaxPQWithArrayResizing(){
            keys = (Key[]) new Comparable[2];
            N = 0;
            pq = new int[2];
            qp = new int[2];
            for(int i = 0; i < keys.length; i++){
                qp[i] = -1;
            }
        }
        public boolean isEmpty(){return N == 0;}
        public boolean contains(int i){return qp[i] != -1;}
        public int size(){return N;}
        public void resize(int newSize, Type type){
            Key[] array = (Key[]) new Object[newSize];
            for (int i = 0; i < N; i++){
                array[i] = keys[i];
            }

            if (type == Type.insert){
                arrayAccessInsert += 2 * keys.length;
                totalArrayAccess += 2 * keys.length;
            }
            else if (type == Type.delete){
                arrayAccessDelete += 2 * keys.length;
                totalArrayAccess += 2 * keys.length;
            }
            else if (type == Type.remove){
                arrayAccessRemoveMax += 2 * keys.length;
                totalArrayAccess += 2 * keys.length;
            }
            keys = array;
        }
        public void insert(Key key, int i){
            if (N == keys.length - 1){resize(2 * keys.length, Type.insert);}
            if (contains(i)) {
                throw new IllegalArgumentException("Index i is already in the priority queue. ");
            }
            //totalArrayAccess = 0;
            keys[++N] = key;
            totalArrayAccess++;
            arrayAccessInsert++;
            qp[i] = N;
            pq[N] = i;
            swim(N);
        }
        public Key keyOf(int i){
            if (!contains(i)){
                throw new NoSuchElementException("Index is not in the priority queue. ");
            }
            else {
                totalArrayAccess++;
                arrayAccessChange++;
                return keys[i];
            }
        }
        public void changeKey(int i, Key key){
            if (!contains(i)){
                throw new NoSuchElementException("Index is not in the priority queue. ");
            }
            arrayAccessChange++;
            totalArrayAccess++;
            keys[i] = key;
            swim(qp[i]);
            sink(qp[i]);
        }
        public void change(int i, Key key){
            totalArrayAccess++;
            arrayAccessChange++;
            changeKey(i, key);
        }
        public int delMax(){
            if (N > 0 && N == keys.length / 4){resize(keys.length / 2, Type.remove);}
            if (N == 0) throw new NoSuchElementException("Priority queue underflow. ");
            int max = pq[1];
            arrayAccessInsert++;
            exchange(1, N--);
            sink(1);
            qp[max] = -1;
            keys[max] = null;
            arrayAccessInsert++;
            pq[N + 1] = -1;
            arrayAccessRemoveMax += 3;
            totalArrayAccess += 4;
            return max;
        }
        public void delete(int i){
            if (N > 0 && N == keys.length / 4){resize(keys.length / 2, Type.delete);}
            if (!contains(i)){
                throw new IllegalArgumentException("Index i is not in the priority queue. ");
            }
            int index = qp[i];
            exchange(index, N--);
            swim(index);
            sink(index);
            keys[i] = null;
            qp[i] = -1;
            arrayAccessDelete += 2;
            totalArrayAccess += 3;
        }
        public void swim(int k){
            while (k > 1){
                if (less(k / 2, k)){
                    exchange(k / 2, k);
                }
                k = k / 2;
            }
        }
        public void sink(int k){
            while (2 * k <= N){
                int j = 2 * k;
                if (j < N && less(j, j + 1)){
                    j++;
                }
                if (!less(k, j)){
                    break;
                }
                exchange(k, j);
                k = j;
            }
        }
        public boolean less(int v, int w){
            totalArrayAccess += 2;
            return keys[pq[v]].compareTo(keys[pq[w]]) < 0;
        }
        public void exchange(int k, int l) {
            totalArrayAccess += 5;
            int temp = pq[k];
            pq[k] = pq[l];
            pq[l] = temp;
            qp[pq[k]] = k;
            qp[pq[l]] = l;
        }
        public static void main(String[] args) {
            MaxPQWithArrayResizing maxPQWithArrayResizing = new MaxPQWithArrayResizing();
            for (int i = 0; i < 10; i++){
                maxPQWithArrayResizing.insert(i, i);
                maxPQWithArrayResizing.change(i, i);
                maxPQWithArrayResizing.delMax();
                //maxPQWithArrayResizing.delete(i);
            }
            StdOut.println(maxPQWithArrayResizing.arrayAccessInsert);
            StdOut.println(maxPQWithArrayResizing.arrayAccessChange);
            StdOut.println(maxPQWithArrayResizing.arrayAccessRemoveMax);
            //StdOut.println(maxPQWithArrayResizing.arrayAccessDelete);
            StdOut.println(maxPQWithArrayResizing.totalArrayAccess);
        }
    }
}
