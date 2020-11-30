package Chapter3_1_SymbolTables;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Exercise driver. Write an exercise driver program that uses the methods in our ordered symbol-table API on difficult
*   or pathological cases that might turn up in practical applications. Simple example include key sequences that are
*   already in order, key sequences in reverse order, key sequences where all keys are the same, and keys consisting of
*   only two distinct values.
*
* */
public class Ex32_ExerciseDriver {
    // build symbol table for such key sequences
    // already in order
    public static void inOrderPut(int n){
        binarySearchST<Integer, String> binarySearchST = new binarySearchST<>(5);
        for (int i = 0; i < n; i++){
            binarySearchST.put(i, "Value" + i);
        }
        for (int i = 0; i < 500; i++){
            binarySearchST.get(i);
        }
        for (int i = 0; i < 100; i++){
            binarySearchST.delete(i);
        }
    }
    // reverse order
    public static void reverseOrderPut(int n){
        binarySearchST<Integer, String> binarySearchST = new binarySearchST<>(5);
        for (int i = n - 1; i >= 0; i--){
            binarySearchST.put(i, "Value" + i);
        }
        for (int i = 0; i < 500; i++){
            binarySearchST.get(i);
        }
        for (int i = 0; i < 100; i++){
            binarySearchST.delete(i);
        }
    }
    // all keys are the same
    public static void sameKeys(int n){
        binarySearchST<Integer, String> binarySearchST = new binarySearchST<>(5);
        for (int i = 0; i < n; i++){
            binarySearchST.put(10, "Value" + 10);
        }
        for (int i = 0; i < 500; i++){
            binarySearchST.get(i);
        }
        for (int i = 0; i < 100; i++){
            binarySearchST.delete(i);
        }
    }
    // only two distinct keys
    public static void twoDistinctKeyPut(int n){
        binarySearchST<Integer, String> binarySearchST = new binarySearchST<>(5);
        for (int i = 0; i < n; i++){
            if (i < n/2){
                binarySearchST.put(5, "Value" + 5);
            }else {
                binarySearchST.put(10, "Value" + 10);
            }
        }
        for (int i = 0; i < 500; i++){
            binarySearchST.get(i);
        }
        for (int i = 0; i < 100; i++){
            binarySearchST.delete(i);
        }
    }
    //static Ex32_ExerciseDriver exerciseDriver = new Ex32_ExerciseDriver();
    public static Double exerciseInOrderPut(String s){
        Stopwatch timer = new Stopwatch();
        if (s.equals("inOrderPut")){
            inOrderPut(100000);
        }
        if (s.equals("reverseOrderPut")){
            reverseOrderPut(100000);
        }
        if (s.equals("sameKeys")){
            sameKeys(100000);
        }
        if (s.equals("twoDistinctKeyPut")){
            twoDistinctKeyPut(100000);
        }
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        StdOut.printf("%10s %10s %10s %10s\n", "inOrder", "reverseOrder", "sameKeys", "twoDistinctKeys");
        double t1 = exerciseInOrderPut("inOrderPut");
        double t2 = exerciseInOrderPut("reverseOrderPut");
        double t3 = exerciseInOrderPut("sameKeys");
        double t4 = exerciseInOrderPut("twoDistinctKeyPut");
        StdOut.printf("%10.2f %10.2f %10.2f %10.2f", t1, t2, t3, t4);
    }
}
