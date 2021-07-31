package Chapter3_5_Applications;

import edu.princeton.cs.algs4.*;

import java.io.File;

/*
*   Develop and test a static method invert() that takes as argument an ST<String, Bag<String>> and produces as return
*   value the inverse of the given symbol table(a symbol table of the same type).
*
* */
public class Ex14_FileIndexing {
    public static void main(String[] args) {
        ST<String, Bag<String>> st = new ST<>();
        Bag<String> bag = new Bag<>();
        bag.add("Apple");
        bag.add("Pineapple");
        bag.add("Berry");
        String fruit = "Fruit";
        st.put(fruit, bag);
        StdOut.println("Before inverse ----\n");
        for (String key : st.keys()){
            StdOut.println("Key:    " + key);
        }
        StdOut.print("Value: ");
        for (String val : st.get(fruit)){
            StdOut.print("  " + val);
        }
        ST<String, Bag<String>> inverseIndex = Ex14_FileIndexing.invert(st);
        //invert(st);
        StdOut.println("\nAfter inverse ----\n");
        StdOut.print("All keys:  ");
        for (String key : inverseIndex){
            StdOut.print(key + " ");
        }
        StdOut.print("\nValues: ");

        for (String key : inverseIndex){
            for (String val : inverseIndex.get(key)){
                StdOut.print(" " + val);
            }
        }

    }
    // retrieving the vals corresponding to the key, then put all the vals as key into ST
    public static ST<String, Bag<String>> invert(ST<String, Bag<String>> st){
        ST<String, Bag<String>> inverseST = new ST<>();
        for (String key : st.keys()){
            Bag<String> vals = st.get(key);
            for (String inverseKey : vals){
                if (!inverseST.contains(inverseKey)){
                    inverseST.put(inverseKey, new Bag<>());
                }
                inverseST.get(inverseKey).add(key);
            }
        }
        return inverseST;
    }
}
