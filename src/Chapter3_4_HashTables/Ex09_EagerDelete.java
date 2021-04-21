package Chapter3_4_HashTables;

import edu.princeton.cs.algs4.StdOut;

/*
*   Implement an eager delete() method for SeparateChainingHashST.
*
* */
public class Ex09_EagerDelete<Key, Value> extends SeparateChainingHashST<Key, Value> {
    public void eagerDelete(Key key){
        if (key == null){
            throw new IllegalArgumentException("Argument cannot be null");
        }
        if (!contains(key)){
            return;
        }
        int i = hash(key);
        st[i].delete(key);
        n--;
        if (n > 0 && n < m / 4){
            resize(m / 2);
        }
    }

    public static void main(String[] args) {
        Ex09_EagerDelete<String, Integer> eagerDeleteObject = new Ex09_EagerDelete<>();
        eagerDeleteObject.put("S", 0);
        eagerDeleteObject.put("E", 1);
        eagerDeleteObject.put("A", 2);
        eagerDeleteObject.put("R", 3);
        eagerDeleteObject.put("C", 4);
        eagerDeleteObject.put("H", 5);
        for (String s : eagerDeleteObject.keys()){
            StdOut.print(s + " ");
        }
        StdOut.println("After deletion");
        eagerDeleteObject.eagerDelete("A");
        eagerDeleteObject.eagerDelete("C");
        for (String s : eagerDeleteObject.keys()){
            StdOut.print(s + " ");
        }
    }
}
