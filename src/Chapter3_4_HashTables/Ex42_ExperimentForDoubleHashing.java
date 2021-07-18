package Chapter3_4_HashTables;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Double hashing. Run experimental studies to evaluate the effectiveness of double hashing.
*
* */
public class Ex42_ExperimentForDoubleHashing {

    public void runExperiments(int n){
        LinearProbingHashST<Integer, Integer> linearProbingHashST = new LinearProbingHashST<>();
        Ex28_DoubleHashing<Integer, Integer> doubleHashing = new Ex28_DoubleHashing<>();
        int[] randomKey = new int[n];
        for (int i = 0; i < n; i++){
            randomKey[i] = StdRandom.uniform(Integer.MAX_VALUE);
        }
        // put() test
        Stopwatch timerForPutLinearProbingHashST = new Stopwatch();
        for (int i = 0; i < n; i++){
            linearProbingHashST.put(randomKey[i], randomKey[i]);
        }
        double costPutLinearProbingHashST = timerForPutLinearProbingHashST.elapsedTime();
        Stopwatch timerForPutDoubleHashing = new Stopwatch();
        for (int i = 0; i < n; i++){
            doubleHashing.put(randomKey[i], randomKey[i]);
        }
        double costPutDoubleHashing = timerForPutDoubleHashing.elapsedTime();
        // get() test
        Stopwatch timerForGetLinearProbingHashST = new Stopwatch();
        for (int i = 0; i < n; i++){
            linearProbingHashST.get(randomKey[i]);
        }
        double costGetLinearProbingHashST = timerForGetLinearProbingHashST.elapsedTime();
        Stopwatch timerForGetDoubleHashing = new Stopwatch();
        for (int i = 0; i < n; i++){
            doubleHashing.get(randomKey[i]);
        }
        double costGetDoubleHashing = timerForGetDoubleHashing.elapsedTime();
        StdOut.printf("%12d %21.1f % 21.1f\n", n, costPutLinearProbingHashST, costPutDoubleHashing);
        StdOut.printf("%12d %21.1f % 21.1f\n", n, costGetLinearProbingHashST, costGetDoubleHashing);
    }

    public static void main(String[] args) {
        StdOut.printf("%12s %21s %21s\n", "NumberOfData", "ResultOfLinearProbing", "ResultOfDoubleHashing");
        Ex42_ExperimentForDoubleHashing experimentForDoubleHashing = new Ex42_ExperimentForDoubleHashing();
        experimentForDoubleHashing.runExperiments(2);
    }
}

/*
*       NumberOfData ResultOfLinearProbing ResultOfDoubleHashing
           2                  27.9                  43.6
           2                  16.8                   9.8

* */
