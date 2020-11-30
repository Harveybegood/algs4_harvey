package Chapter3_1_SymbolTables;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/*
*   Self-Organizing search. A self-organizing search algorithm is one that rearranges items to make those that are accessed
*   frequently likely to be found early in the search. Modify your search implementation for Ex3.1.2 to perform the
*   following action on every search hit: move the key-value pair found to the beginning of the list, moving all pairs
*   between the beginning of the list and the vacated position to the right one position. This procedure is called the
*   move-to-front heuristic.
*
* */
@SuppressWarnings("unchecked")
public class Ex22_SelfOrganizingSearch<Key, Value> {
    private Key[] keys;
    private Value[] values;
    private int n;
    public Ex22_SelfOrganizingSearch(int cap){
        keys = (Key[]) new Object[cap];
        values = (Value[]) new Object[cap];
    }
    public boolean isEmpty(){return n == 0;}
    public int size(){return n;}
    public boolean contains(Key key){
        if (key == null) throw new IllegalArgumentException("Argument for contains() is null");
        return get(key) != null;
    }
    public Value get(Key key){
        if (key == null) throw new IllegalArgumentException("Argument for get() is null.");
        if (isEmpty())return null;
        for (int i = 0; i < n; i++){
            if (key.equals(keys[i])){
                //StdOut.println(i + " " + keys[i]);
                moveToFront(i);
                return values[i];
            }
        }
        return null;
    }
    public void delete(Key key){
        if (key == null) throw new IllegalArgumentException("Argument for delete() is null.");
        if (isEmpty()) return;
        for (int i = 0; i < n; i++){
            if (key.equals(keys[i])){
                keys[i] = keys[i+1];
                values[i] = values[i+1];
            }
        }
        n--;
        if (n == keys.length/4){
            resize(n/2);
        }
    }
    public void put(Key key, Value value){
        if (key == null || value == null) throw new IllegalArgumentException("Argument for put() is null.");
        if (n == keys.length/2){
            resize(keys.length * 2);
        }
        for (int i = 0; i < n; i++){
            if (key.equals(keys[i]))
                values[i] = value;
        }
        keys[n] = key;
        values[n] = value;
        n++;
    }
    // the key idea of this self-organizing search is to move the frequently accessed item to front of the current list
    public void moveToFront(int v){
        //if (key == null) throw new IllegalArgumentException("Argument for moreToFront() is null.");
        //if (isEmpty()) return;
        Key tempKey = keys[v];
        Value tempValue = values[v];
        for (int i = v; i > 0; i--){
            keys[i] = keys[i-1];
            values[i] = values[i-1];
        }
        keys[0] = tempKey;
        values[0] = tempValue;
    }

    public void resize(int newSize){
        Key[] tempKey = (Key[]) new Object[newSize];
        Value[] tempValue = (Value[]) new Object[newSize];
        for (int i = 0; i < n; i++){
            tempKey[i] = keys[i];
            tempValue[i] = values[i];
        }
        keys = tempKey;
        values = tempValue;
    }
    public Iterable<Key> keys(){
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < n; i++){
            queue.enqueue(keys[i]);
        }
        return queue;
    }

    public static void main(String[] args) {
        Ex22_SelfOrganizingSearch<Integer, String> selfOrganizingSearch = new Ex22_SelfOrganizingSearch<>(2);
        selfOrganizingSearch.put(5, "Value" + 5);
        selfOrganizingSearch.put(8, "Value" + 8);
        selfOrganizingSearch.put(1, "Value" + 1);
        selfOrganizingSearch.put(3, "Value" + 3);
        selfOrganizingSearch.put(2, "Value" + 2);
        StdOut.println("The current symbol table");
        for (Integer i : selfOrganizingSearch.keys()){
            StdOut.println("Key: " + i + "   " + selfOrganizingSearch.get(i));
        }
        StdOut.println("Search operation to key 3");
        selfOrganizingSearch.get(3);
        /*for (int i = 0; i < selfOrganizingSearch.size(); i++){
            StdOut.println("key: " +  selfOrganizingSearch.keys[i] + "  " + selfOrganizingSearch.values[i]);
        }*/
        for (Integer i : selfOrganizingSearch.keys()){
            StdOut.println("Key: " + i + "   " + selfOrganizingSearch.get(i));
        }

    }
}
