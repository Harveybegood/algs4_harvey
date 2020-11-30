package Chapter3_1_SymbolTables;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Interpolation search. Suppose that arithmetic operations are allowed on keys(for example, they may be Double or
*   Integer value). Write a version of binary search that mimics the process of looking near the beginning of a dictionary
*   when the word begins with a letter near the beginning of the alphabet. Specifically, if kx is the key value sought,
*   klo is the key value of the first key in the table, and khi is the key value of the last key in the table, look first
*   ⎣(kx - klo)/(khi - klo)⎦-way through the table, not half-way. Test your implementation against BinarySearchST for
*   FrequencyCounter using SearchCompare.
*
* */
@SuppressWarnings("unchecked")
public class Ex24_InterpolationSearch {
    public static class InterpolationSearch<Value>{
        private Integer[] keys;
        private Value[] values;
        private int n;
        public InterpolationSearch(int cap){
            keys = new Integer[cap];
            values = (Value[]) new Object[cap];
        }
        public boolean isEmpty(){return n == 0;}
        public int size(){return n;}
        public int rank(Integer key){
            int lo = 0;
            int hi = n - 1;
            while (lo <= hi){
                if (keys[lo].compareTo(key) > 0){
                    return lo;
                }
                if (keys[lo].compareTo(keys[hi]) ==0){
                    return lo;
                }
                int interpolation =(int) (lo + Math.floor(((key - keys[lo]) * (hi - lo)) / (keys[hi] - keys[lo])));
                int cmp = key.compareTo(keys[interpolation]);
                if (cmp < 0) hi = interpolation - 1;
                else if (cmp > 0) lo = interpolation + 1;
                else return interpolation;
            }
            return lo;
        }
        public void delete(Integer key){
            if (key == null) throw new IllegalArgumentException("Argument for delete() is null");
            if (isEmpty()) return;
            int i = rank(key);
            for (int j = i; j < n; j++){
                keys[j] = keys[j+1];
                values[j] = values[j+1];
            }
            keys[n-1] = null;
            values[n-1] = null;
            n--;
            if (n > 1 && n == keys.length / 4){
                resize(keys.length *2);
            }
        }
        public void resize(int newSize){
            Integer[] tempKey =  new Integer[newSize];
            Value[] tempValue = (Value[]) new Object[newSize];
            for (int i = 0; i < n; i++){
                tempKey[i] = keys[i];
                tempValue[i] = values[i];
            }
            keys = tempKey;
            values = tempValue;
        }
        public Value get(Integer key){
            if (key == null) throw new IllegalArgumentException("Argument for get() is null");
            if (isEmpty()) return null;
            int i = rank(key);
            if (key.compareTo(keys[i]) == 0){
                return values[i];
            }else {
                return null;
            }
        }
        public void put(Integer key, Value value){
            if (key == null) throw new IllegalArgumentException("Argument for put() is null");
            if (value == null){
                delete(key);
                return;
            }
            int i = rank(key);
            if (i < n && key.compareTo(keys[i]) == 0){
                values[i] = value;
            }
            if (n == keys.length / 2){
                resize(keys.length * 2);
            }
            for (int j = n; j > i; j--){
                keys[j] = keys[j-1];
                values[j] = values[j-1];
            }
            keys[i] = key;
            values[i] = value;
            n++;
        }
        public Integer min(){
            if (isEmpty()) return null;
            return keys[0];
        }
        public Integer max(){
            if (isEmpty()) return null;
            return keys[n-1];
        }
        public Iterable<Integer> keys(){
            return keys(min(), max());
        }
        public boolean contains(Integer key){
            if (key == null) throw new IllegalArgumentException("Argument for contains() is null");
            return get(key) != null;
        }
        public Iterable<Integer> keys(Integer lo, Integer hi){
            Queue<Integer> queue = new Queue<>();
            for (int i = rank(lo); i < rank(hi); i++){
                queue.enqueue(keys[i]);
            }
            if (contains(hi)){
                queue.enqueue(keys[rank(hi)]);
            }
            return queue;
        }
    }
    private String FrequencyCounter1(int[] key){
        binarySearchST<Integer, Integer> binarySearchST = new binarySearchST<>(2);
        for (int i : key){
            if (binarySearchST.contains(i)){
                binarySearchST.put(i, binarySearchST.get(i) + 1);
            }
            else {
                binarySearchST.put(i, 1);
            }
        }
        int max = 0;
        binarySearchST.put(max, 0);
        for (int i : key){
            if (binarySearchST.get(i) > binarySearchST.get(max)){
                max = i;
            }
        }
        return max + "  " + binarySearchST.get(max);
    }
    private String FrequencyCounter2(int[] key){
        InterpolationSearch<Integer> integerInterpolationSearch = new InterpolationSearch<>(2);
        for (int i : key){
            if (integerInterpolationSearch.contains(i)){
                integerInterpolationSearch.put(i, integerInterpolationSearch.get(i) + 1);
            }else {
                integerInterpolationSearch.put(i, 1);
            }
        }
        int max = 0;
        integerInterpolationSearch.put(max, 0);
        for (int i : key){
            if (integerInterpolationSearch.get(i) > integerInterpolationSearch.get(max)){
                max = i;
            }
        }
        return max + "   " + integerInterpolationSearch.get(max);
    }
    public int[] generateKey(int n){
        int[] temp = new int[n];
        for (int i = 0; i < n; i++){
            temp[i] = StdRandom.uniform(1, n/10);
        }
        return temp;
    }
    private double timeCost(int n, String s){
        int[] key = generateKey(n);
        Stopwatch timer = new Stopwatch();
        if (s.equals("f1")){
            FrequencyCounter1(key);
        }
        if (s.equals("f2")){
            FrequencyCounter2(key);
        }
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        Ex24_InterpolationSearch interpolationSearch = new Ex24_InterpolationSearch();
        for (int i = 10000; i < 100000; i *= 10){
            StdOut.print(i + "  " + interpolationSearch.timeCost(i, "f1"));
            StdOut.print("  ");
            StdOut.print(interpolationSearch.timeCost(i, "f2"));
        }
    }
}
