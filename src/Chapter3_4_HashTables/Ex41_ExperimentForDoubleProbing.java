package Chapter3_4_HashTables;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Double probing. Run experimental studies to evaluate the effectiveness of double probing
*
* */
public class Ex41_ExperimentForDoubleProbing<Key, Value> {

    public void runExperiments(int n){
        SeparateChainingHashST<Integer, Integer> separateChainingHashST = new SeparateChainingHashST<>();
        Ex27_DoubleProbing<Integer, Integer> doubleProbing = new Ex27_DoubleProbing<>();
        int[] randomKeys = new int[n];
        for (int i = 0; i < n; i++){
            randomKeys[i] = StdRandom.uniform(Integer.MAX_VALUE);
        }
        // put() test
        Stopwatch timerPutInSeparateChaining = new Stopwatch();
        for (int i = 0; i < n; i++){
            separateChainingHashST.put(randomKeys[i], randomKeys[i]);
        }
        double costForPutInSeparateChaining = timerPutInSeparateChaining.elapsedTime();
        Stopwatch timerPutInDoubleProbing = new Stopwatch();
        for (int i = 0; i < n; i++){
            doubleProbing.put(randomKeys[i], randomKeys[i]);
        }
        double costForPutInDoubleProbing = timerPutInDoubleProbing.elapsedTime();
        // get() test
        Stopwatch timerGetInSeparateChaining = new Stopwatch();
        for (int i = 0; i < n; i++){
            separateChainingHashST.get(randomKeys[i]);
        }
        double costForGetInSeparateChaining = timerGetInSeparateChaining.elapsedTime();
        Stopwatch timerGetInDoubleProbing = new Stopwatch();
        for (int i = 0; i < n; i++){
            doubleProbing.get(randomKeys[i]);
        }
        double costForGetInDoubleProbing = timerGetInDoubleProbing.elapsedTime();
        // leave out the delete test since delete() was put in Ex29_Deletion
        StdOut.printf("%18d %20f %17f\n", n, costForPutInSeparateChaining, costForPutInDoubleProbing);
        StdOut.printf("%18d %20f %17f\n", n, costForGetInSeparateChaining, costForGetInDoubleProbing);
        StdOut.println();
    }

    public static void main(String[] args) {
        Ex41_ExperimentForDoubleProbing<Integer, Integer> experimentForDoubleProbing = new Ex41_ExperimentForDoubleProbing<>();
        StdOut.printf("%18s %20s %17s\n", "ExperimentQuantity", "SeparateChainingTime", "DoubleProbingTime");
        for (int n = 1000; n < 10001; n += n){
            experimentForDoubleProbing.runExperiments(n);
        }
    }
}
