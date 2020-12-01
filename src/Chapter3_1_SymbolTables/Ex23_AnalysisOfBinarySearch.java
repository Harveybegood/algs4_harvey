package Chapter3_1_SymbolTables;
/*
*   Analysis of binary search. Prove that the maximum number of compares used for a binary search in a table of size N
*   is precisely the number of bits in the binary representation of N, because the operation of shifting 1 bit to the
*   right converts the binary representation of N into the binary representation of [N/2].
*
* */
@SuppressWarnings("unchecked")
public class Ex23_AnalysisOfBinarySearch<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private int n;
    public Ex23_AnalysisOfBinarySearch(int cap){
        keys = (Key[]) new Comparable[cap];
        values = (Value[]) new Object[cap];
    }
    public int rank(Key key){
        int lo = 0;
        int hi = n - 1;
        while (lo <= hi){
            int mid = lo + (hi - lo)/2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }
    public boolean isEmpty(){return n == 0;}
    public int size(){return n;}
    public void delete(Key key){}
    public Value get(Key key){
        //...
        int i = rank(key);
        return values[i];
        //...
    }
    public void put(Key key, Value value){}

    /*
       the number of compares for rank() is lgN + 1.
    *  lgN + 1,
    *  say N = 10, lgN + 1 = 3 + 1 => 4
    *  10 => 0000 1010
    *
    *
    *  say N = 5, lgN + 1 = 2 + 1 => 3
    *  5 => 0000 0101
    *
    *  say N = 2, lgN + 1 = 1 + 1 => 2
    *  2 => 0000 0010
    *
    *  say N = 1, lgN + 1 = 0 + 1 => 1
    *  1 => 0000 0001
    *
    * */
}
