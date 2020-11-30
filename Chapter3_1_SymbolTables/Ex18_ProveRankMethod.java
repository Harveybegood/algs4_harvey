package Chapter3_1_SymbolTables;
/*
*   Prove that the rank() method in BinarySearchST is correct.
*
* */
@SuppressWarnings("unchecked")
public class Ex18_ProveRankMethod<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private int n;
    public Ex18_ProveRankMethod(int cap){
        keys = (Key[]) new Comparable[cap];
        values = (Value[]) new Object[cap];
    }
    public boolean isEmpty(){return n == 0;}
    public int size(){return n;}
    public int rank(Key key){
        int low = 0;
        int high = n - 1;
        while (low <= high){
            int mid = low + (high - low)/2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) high = mid - 1;
            else if (cmp > 0) low = mid + 1;
            else return mid;
        }
        return low;
    }
    /*
    *   equivalent to the recursive version.
    *   both method will return low while the condition of low <= high not satisfied.
    *
    *   the loop always terminates with the value of lo
    *   the method rank() do a thing that identifies at which the input key is in the symbol table.
    *   there are two situations, one is the input key found at the symbol table. then obviously, index hi = lo = mid
    *   other is the input key not found at the symbol table. then we can say the index position of the input key where
    *   it is just greater than the lo.
    *
    *
    * */
}
