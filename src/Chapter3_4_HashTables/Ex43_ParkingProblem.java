package Chapter3_4_HashTables;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Parking problem. (D.Knuth)Run experimental studies to validate the hypothesis that the number of compares needed to
*   insert M random keys into a linear-probing table of size M is ~cM 3/2, where c = /2.
*
* */
public class Ex43_ParkingProblem<Key, Value> extends LinearProbingHashST<Key, Value>{
    public Ex43_ParkingProblem(int M){
        super(M);
    }
    public void runExperimentForPut(int n){
        Ex43_ParkingProblem<Integer, Integer> parkingProblem = new Ex43_ParkingProblem<>(n);
        int compares = 0;
        for (int i = 0; i < n; i++){
            int randomKey = StdRandom.uniform(Integer.MAX_VALUE);
            compares += parkingProblem.putAndCountCompares(randomKey, randomKey, n);
        }
        double expectedValue = Math.sqrt(Math.PI/2) * Math.pow(n, 1.5);
        StdOut.printf("%9d %17d %13.1f %5.1f\n", n, compares, expectedValue, compares/expectedValue);
    }
    public int putAndCountCompares(Key key, Value value, int M){
        int compares = 0;
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M){
            compares++;
            if (keys[i].equals(key)){
                values[i] = value;
                return compares;
            }
        }
        if (!contains(key)){
            N++;
        }
        compares++;
        keys[i] = key;
        values[i] = value;
        return compares;
    }

    public static void main(String[] args) {
        StdOut.printf("%9s %17s %13s %5s\n" ,"RandomKey", "ExperimentalValue", "ExpectedValue", "Ratio");
        for (int n = 100; n < 1601; n += n) {
            new Ex43_ParkingProblem<>(n).runExperimentForPut(n);
        }
    }
}
 /*
 *                  RandomKey ExperimentalValue ExpectedValue Ratio
 *                        100               644        1253.3   0.5
 *                        200              1980        3544.9   0.6
 *                        400              6430       10026.5   0.6
 *                        800             17175       28359.3   0.6
 *                       1600             42699       80212.1   0.5
 */